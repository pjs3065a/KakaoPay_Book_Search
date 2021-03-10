package kr.pjs.booksearch.view.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * 뷰모델 클래스
 * 뷰모델은 이 클래스를 상속하는 것을 지향
 */
abstract class DisposableViewModel: ViewModel() {
    val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}