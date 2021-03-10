package kr.pjs.booksearch.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kr.pjs.booksearch.BR

/**
 * 뷰모델이 있는 데이터 바인딩 프래그먼트 클래스
 * 뷰모델과 뷰바인딩을 사용하는 프래그먼트에 이 클래스를 상속하는 것을 지향
 */
abstract class DataBindingFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    protected lateinit var mBinding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        mBinding.setVariable(BR.viewModel, viewModel)
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    abstract val viewModel: ViewModel
}