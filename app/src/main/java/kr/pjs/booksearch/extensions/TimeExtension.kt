package kr.pjs.booksearch.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDateFormat() = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(this)