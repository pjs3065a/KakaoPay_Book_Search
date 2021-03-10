package kr.pjs.booksearch.utils

inline fun <reified T> resolveClass(): Class<T> {
    return T::class.java
}