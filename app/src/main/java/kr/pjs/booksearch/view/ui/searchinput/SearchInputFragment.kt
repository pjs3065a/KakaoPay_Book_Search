package kr.pjs.booksearch.view.ui.searchinput

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.pjs.booksearch.R
import kr.pjs.booksearch.databinding.FragmentSearchInputBinding
import kr.pjs.booksearch.extensions.getViewModelFactory
import kr.pjs.booksearch.extensions.hideKeyboard
import kr.pjs.booksearch.view.base.DataBindingFragment
import kr.pjs.booksearch.view.ui.searchinput.adapter.SearchInputAdapter

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

        viewModel.navigateSearchDetail = { data ->
            findNavController().navigate(R.id.searchDetail, data)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupEvent() {
        mBinding.rvSearchInput.apply {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItem =
                        (mBinding.rvSearchInput.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    mBinding.fbUp.isVisible = lastVisibleItem >= 7
                    viewModel.reqMoreSearchResult(lastVisibleItem)
                }
            })

            setOnTouchListener { _, _ ->
                mBinding.cvSearch.hideKeyboard()
                false
            }
        }

        mBinding.fbUp.setOnClickListener {
            mBinding.rvSearchInput.smoothScrollToPosition(0)
        }
    }

    private fun setupRecyclerView() {
        mBinding.rvSearchInput.adapter = mAdapter
    }
}