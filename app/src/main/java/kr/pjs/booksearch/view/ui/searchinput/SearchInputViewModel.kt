package kr.pjs.booksearch.view.ui.searchinput

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.kotlin.addTo
import kr.pjs.booksearch.data.ARGS
import kr.pjs.booksearch.data.args.DocumentArgs
import kr.pjs.booksearch.data.remote.repository.SearchRepository
import kr.pjs.booksearch.data.toSearchModel
import kr.pjs.booksearch.data.view.model.DocumentModel
import kr.pjs.booksearch.data.view.model.MetaModel
import kr.pjs.booksearch.data.view.model.SearchModel
import kr.pjs.booksearch.utils.rx.RxBus
import kr.pjs.booksearch.utils.rx.RxBusEvent
import kr.pjs.booksearch.utils.rx.SchedulerProvider
import kr.pjs.booksearch.view.base.DisposableViewModel

class SearchInputViewModel(private val searchRepository: SearchRepository) : DisposableViewModel() {

    private lateinit var metaData: MetaModel

    private var isLoading: Boolean = false
    private val documentSize = 50

    private var _bookInfoData = MutableLiveData<MutableList<DocumentModel>>()
    val bookInfoData: LiveData<MutableList<DocumentModel>>
        get() = _bookInfoData

    var navigateSearchDetail: ((Bundle) -> Unit)? = null

    init {
        observer()
    }

    private fun observer() {
        RxBus.listen(RxBusEvent.BookInfoItemClick::class.java).subscribe { data ->
            navigateSearchDetail?.invoke(bundleOf(ARGS to DocumentArgs(data.item)))
        }.addTo(compositeDisposable)
    }

    /**
     * 검색 데이터 요청하기
     */
    fun reqSearchResult(query: String) {
        fun success(item: SearchModel) {
            metaData = item.meta.apply {
                this.query = query
            }

            _bookInfoData.value = item.documents
        }

        fun error(t: Throwable) {
            t.printStackTrace()
        }

        if (!isSameQuery(query))
            searchRepository.getSearchResult(query = query, size = documentSize)
                .map { data -> data.toSearchModel() }
                .subscribeOn(SchedulerProvider.io())
                .observeOn(SchedulerProvider.ui())
                .subscribe(::success, ::error)
    }

    /**
     * 검색 데이터 더 가져오기 요청하기
     */
    fun reqMoreSearchResult(position: Int) {
        if (!isMorePosition(position)) {
            return
        }

        isLoading = true

        fun success(item: SearchModel) {

            metaData = item.meta.apply {
                query = metaData.query
                currentPage = metaData.currentPage.plus(1)
            }


            val initialData = _bookInfoData.value?.map { it.copy() }?.toMutableList()
            initialData?.addAll(item.documents)
            _bookInfoData.value = initialData
            isLoading = false
        }

        fun error(t: Throwable) {
            t.printStackTrace()

            isLoading = false
        }

        if (metaData.isEnd.not()) {
            searchRepository.getSearchResult(
                query = metaData.query,
                page = metaData.currentPage.plus(1),
                size = documentSize
            )
                .map { data -> data.toSearchModel() }
                .subscribeOn(SchedulerProvider.io())
                .observeOn(SchedulerProvider.ui())
                .subscribe(::success, ::error)
        }
    }

    /**
     * 스크롤 포지션이 절반 사이즈가 넘었을 경우 체크하기
     */
    private fun isMorePosition(position: Int) =
        metaData.isEnd.not() && position.plus(1) >= (metaData.currentPage * documentSize) - (documentSize / 2) && isLoading.not()

    /**
     * 같은 쿼리 요청인지 체크하기
     */
    private fun isSameQuery(query: String) = ::metaData.isInitialized && query == metaData.query

}