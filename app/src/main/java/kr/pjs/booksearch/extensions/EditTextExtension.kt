package kr.pjs.booksearch.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * 소프트 키보드 보이기
 */
fun EditText.showKeyboard() {
    requestFocus()
    val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

/**
 * 소프트 키보드 숨기기
 */
fun EditText.hideKeyboard() {
    clearFocus()
    val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}
