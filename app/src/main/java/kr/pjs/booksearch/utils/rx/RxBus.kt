package kr.pjs.booksearch.utils.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import kr.pjs.booksearch.data.view.model.DocumentModel

/**
 * RxBus 클래스
 * Subject를 이용해서 데이터를 전달할 때 사용
 */
object RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}

class RxBusEvent {
    data class BookInfoItemClick(val item: DocumentModel)
}