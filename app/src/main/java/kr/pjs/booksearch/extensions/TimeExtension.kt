package kr.pjs.booksearch.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * 날짜 포맷으로 전환 처리
 */
fun Date.toDateFormat(): String = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(this)