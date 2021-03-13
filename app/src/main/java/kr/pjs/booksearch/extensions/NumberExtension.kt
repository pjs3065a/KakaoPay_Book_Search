package kr.pjs.booksearch.extensions

import java.text.DecimalFormat

/**
 * 가격 포맷으로 전환 처리
 */
fun String.toPriceFormat(): String =
    try {
        val price = this.replace(",", "").toInt()
        val myFormatter = DecimalFormat("###,###원")
        myFormatter.format(price)
    } catch (e: Exception) {
        ""
    }