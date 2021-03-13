package kr.pjs.booksearch.view.ui.searchdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.pjs.booksearch.R
import kr.pjs.booksearch.databinding.FragmentSearchDetailBinding
import kr.pjs.booksearch.extensions.getViewModelFactory
import kr.pjs.booksearch.view.base.DataBindingFragment

/**
 * 검색 결과 프래그먼트 클래스
 */
class SearchDetailFragment :
    DataBindingFragment<FragmentSearchDetailBinding>(R.layout.fragment_search_detail) {
    override val viewModel by viewModels<SearchDetailViewModel> { getViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvent()
    }

    private fun setupEvent() {
        mBinding.ibBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}