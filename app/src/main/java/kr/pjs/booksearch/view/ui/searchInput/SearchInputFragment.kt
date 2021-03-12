package kr.pjs.booksearch.view.ui.searchInput

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.pjs.booksearch.R
import kr.pjs.booksearch.databinding.FragmentSearchInputBinding
import kr.pjs.booksearch.extensions.getViewModelFactory
import kr.pjs.booksearch.view.base.DataBindingFragment
import kr.pjs.booksearch.view.ui.searchInput.adapter.SearchInputAdapter

/**
 * 검색 입력 프래그먼트 클래스
 */
class SearchInputFragment :
    DataBindingFragment<FragmentSearchInputBinding>(R.layout.fragment_search_input) {
    override val viewModel by viewModels<SearchInputViewModel> { getViewModelFactory() }

    private val mAdapter by lazy {
        SearchInputAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupEvent()
        setupRecyclerView()
    }

    private fun setupObserver() {
        mBinding.cvSearch.onTextChangeListener = { query ->
            viewModel.reqSearchResult(query)
        }

        viewModel.bookInfoData.observe(viewLifecycleOwner) { data ->
            mAdapter.submitList(data)
        }
    }

    private fun setupEvent() {
        mBinding.rvSearchInput.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItem =
                    (mBinding.rvSearchInput.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                viewModel.reqMoreSearchResult(lastVisibleItem)
            }
        })
    }

    private fun setupRecyclerView() {
        mBinding.rvSearchInput.adapter = mAdapter
    }
}