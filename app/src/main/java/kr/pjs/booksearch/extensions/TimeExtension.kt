package kr.pjs.booksearch.extensions

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * 날짜 포맷으로 전환 처리
 */
fun String.toDateFormat(): String{
    return try {
        SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(this)
    }catch (e:Exception){
        ""
    }
}