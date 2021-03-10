package kr.pjs.booksearch.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * 뷰모델이 없는 데이터 바인딩 액티비티 클래스
 * 뷰모델 없이 뷰바인딩만 사용하는 액티비티에 이 클래스를 상속하는 것을 지향
 */
abstract class DataBindingActivityWithoutVM<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    protected val mBinding: T by lazy {
        DataBindingUtil.setContentView(this, layoutResId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding(){
        with(mBinding){
            lifecycleOwner = this@DataBindingActivityWithoutVM
            executePendingBindings()
        }
    }
}