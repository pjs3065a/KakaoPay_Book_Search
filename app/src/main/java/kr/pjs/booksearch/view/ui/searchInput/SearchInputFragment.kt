package kr.pjs.booksearch.view.ui.searchInput

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import kr.pjs.booksearch.R
import kr.pjs.booksearch.databinding.FragmentSearchInputBinding
import kr.pjs.booksearch.view.base.DataBindingFragment

/**
 * 검색 입력 프래그먼트 클래스
 */
class SearchInputFragment :
    DataBindingFragment<FragmentSearchInputBinding>(R.layout.fragment_search_input) {
    override val viewModel: ViewModel
        get() = SearchInputViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}