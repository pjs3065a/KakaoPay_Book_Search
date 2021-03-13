package kr.pjs.booksearch.utils.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * 스케줄러 프로바이더 클래스
 */
object SchedulerProvider {
    fun io(): Scheduler = Schedulers.newThread()
    fun ui(): Scheduler = AndroidSchedulers.mainThread()
}