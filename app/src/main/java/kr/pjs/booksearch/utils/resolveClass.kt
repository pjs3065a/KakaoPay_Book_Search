package kr.pjs.booksearch.utils

/**
 * 클래스 타입을 넘기면 Class 클래스 리턴 처리
 */
inline fun <reified T> resolveClass(): Class<T> {
    return T::class.java
}