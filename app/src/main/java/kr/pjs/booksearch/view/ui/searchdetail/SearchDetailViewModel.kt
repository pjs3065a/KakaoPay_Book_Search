package kr.pjs.booksearch.view.ui.searchdetail

import androidx.databinding.ObservableField
import kr.pjs.booksearch.data.args.DocumentArgs
import kr.pjs.booksearch.data.view.model.DocumentModel
import kr.pjs.booksearch.extensions.toDateFormat
import kr.pjs.booksearch.view.base.DisposableViewModel

class SearchDetailViewModel(private var args: DocumentArgs?) : DisposableViewModel() {

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
            bindPrice.set(data.price.toString())
            bindNumber.set(data.isbn)
            bindAuthor.set(data.authors.toString())
            bindPublisher.set(data.publisher)
            bindTranslator.set(data.translators.toString())
            bindStatus.set(data.status)
            bindContent.set(data.contents)
            bindIsFavorite.set(data.isFavorite)
        } ?: run {
            //TODO 넘어온 값이 없을 경우 처리
        }
    }

    fun onClickFavorite() {
        itemModel?.let { data ->
            data.isFavorite = data.isFavorite.not()
            bindIsFavorite.set(data.isFavorite)
        }
    }
}