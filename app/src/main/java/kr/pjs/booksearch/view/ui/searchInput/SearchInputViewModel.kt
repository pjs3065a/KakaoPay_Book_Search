package kr.pjs.booksearch.view.ui.searchInput

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.pjs.booksearch.data.remote.repository.SearchRepository
import kr.pjs.booksearch.data.toSearchModel
import kr.pjs.booksearch.data.view.model.DocumentModel
import kr.pjs.booksearch.data.view.model.SearchModel
import kr.pjs.booksearch.utils.rx.SchedulerProvider
import kr.pjs.booksearch.view.base.DisposableViewModel

class SearchInputViewModel(private val searchRepository: SearchRepository) : DisposableViewModel() {

    private var isEnd = false

    private var _bookInfoData = MutableLiveData<MutableList<DocumentModel>>()
    val bookInfoData: LiveData<MutableList<DocumentModel>>
        get() = _bookInfoData

    init {
        observer()
    }

    private fun observer() {

    }

    fun reqSearchResult(query: String) {
        fun success(data: SearchModel) {
            _bookInfoData.value = data.documents
        }

        fun error(t: Throwable) {
            t.printStackTrace()
        }

        if (!isEnd) {
            searchRepository.getSearchResult(query)
                .map { data -> data.toSearchModel() }
                .subscribeOn(SchedulerProvider.io())
                .observeOn(SchedulerProvider.ui())
                .subscribe(::success, ::error)
        }
    }
}