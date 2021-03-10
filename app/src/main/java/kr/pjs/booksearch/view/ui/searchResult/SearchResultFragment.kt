package kr.pjs.booksearch.view.ui.searchResult

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import kr.pjs.booksearch.R
import kr.pjs.booksearch.databinding.FragmentSearchResultBinding
import kr.pjs.booksearch.view.base.DataBindingFragment

/**
 * 검색 결과 프래그먼트 클래스
 */
class SearchResultFragment :
    DataBindingFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {
    override val viewModel: ViewModel
        get() = SearchResultViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}