package kr.pjs.booksearch.view.ui.searchInput

import kr.pjs.booksearch.data.remote.repository.SearchRepository
import kr.pjs.booksearch.view.base.DisposableViewModel

class SearchInputViewModel(private val searchRepository: SearchRepository): DisposableViewModel() {
    init{
        observer()
    }

    private fun observer(){

    }
}