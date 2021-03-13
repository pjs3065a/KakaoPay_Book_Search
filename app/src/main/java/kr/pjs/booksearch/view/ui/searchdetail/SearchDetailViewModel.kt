package kr.pjs.booksearch.view.ui.searchdetail

import androidx.databinding.ObservableField
import kr.pjs.booksearch.data.NONE
import kr.pjs.booksearch.data.args.DocumentArgs
import kr.pjs.booksearch.data.view.model.DocumentModel
import kr.pjs.booksearch.extensions.toDateFormat
import kr.pjs.booksearch.extensions.toPriceFormat
import kr.pjs.booksearch.view.base.DisposableViewModel

/**
 * 검색 상세 뷰모델 클래스
 */
class SearchDetailViewModel(args: DocumentArgs?) : DisposableViewModel() {

    private var itemModel: DocumentModel? = null

    val bindName by lazy {
        ObservableField<String>()
    }

    val bindThumbnail by lazy {
        ObservableField<String>()
    }

    val bindDateTime by lazy {
        ObservableField<String>()
    }

    val bindPrice by lazy {
        ObservableField<String>()
    }

    val bindNumber by lazy {
        ObservableField<String>()
    }

    val bindPublisher by lazy {
        ObservableField<String>()
    }

    val bindAuthor by lazy {
        ObservableField<String>()
    }

    val bindStatus by lazy {
        ObservableField<String>()
    }

    val bindTranslator by lazy {
        ObservableField<String>()
    }

    val bindContent by lazy {
        ObservableField<String>()
    }

    val bindIsFavorite by lazy {
        ObservableField<Boolean>()
    }

    init {
        itemModel = args?.document
        onBind()
    }

    private fun onBind() {
        itemModel?.let { data ->
            bindName.set(data.title)
            bindThumbnail.set(data.thumbnail)
            bindDateTime.set(data.datetime?.toDateFormat())
            bindPrice.set(
                "${data.price.toString().toPriceFormat()} (${
                    data.salePrice.toString().toPriceFormat()
                })"
            )
            bindNumber.set(data.isbn)
            bindAuthor.set(convertListString(data.authors))
            bindPublisher.set(data.publisher)
            bindTranslator.set(convertListString(data.translators))
            bindStatus.set(convertEmptyString(data.status))
            bindContent.set(convertEmptyString(data.contents))
            bindIsFavorite.set(data.isFavorite)
        }
    }

    /**
     * 문자열 타입의 리스트를 나열식 문자열 처리
     */
    private fun convertListString(list: MutableList<String>?): String {
        val replacedString = list.toString().replace("]", "").replace("[", "")
        return convertEmptyString(replacedString)
    }

    /**
     * 데이터가 없을 경우 해당 문자열 처리
     */
    private fun convertEmptyString(str: String): String = if (str.isEmpty()) NONE else str

    /**
     * 좋아요 버튼 클릭 이벤트 처리
     */
    fun onClickFavorite() {
        itemModel?.let { data ->
            data.isFavorite = data.isFavorite.not()
            bindIsFavorite.set(data.isFavorite)
        }
    }
}