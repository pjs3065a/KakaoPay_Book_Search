package kr.pjs.booksearch.view.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import kr.pjs.booksearch.R
import kr.pjs.booksearch.extensions.dpToPx
import kr.pjs.booksearch.extensions.hideKeyboard
import kr.pjs.booksearch.extensions.showKeyboard

/**
 * 검색 뷰 클래스
 */
class CustomSearchView : AppCompatEditText, TextWatcher, View.OnTouchListener,
    View.OnFocusChangeListener, View.OnKeyListener {

    private lateinit var clearDrawable: Drawable
    private lateinit var searchDrawable: Drawable
    var onTextChangeListener: ((String) -> Unit)? = null

    constructor(context: Context) : super(context) {
        setupView()
        setupEvent()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setupView()
        setupEvent()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setupView()
        setupEvent()
    }


    /**
     * 뷰 초기화하기
     */
    private fun setupView() {
        clearDrawable =
            ContextCompat.getDrawable(context, R.drawable.ic_clear)!!.apply {
                bounds = Rect(0, 0, 16.dpToPx(), 16.dpToPx())
                setTint(ContextCompat.getColor(context, R.color.black))
            }

        searchDrawable = ContextCompat.getDrawable(context, R.drawable.ic_search)!!.apply {
            bounds = Rect(0, 0, 16.dpToPx(), 16.dpToPx())
            setTint(ContextCompat.getColor(context, R.color.black))
        }

        compoundDrawablePadding = 8.dpToPx()
        setPadding(8.dpToPx(), 0, 8.dpToPx(), 0)
        setClearIconVisible(false)
    }


    /**
     * 이벤트 초기화하기
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun setupEvent() {
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        super.setOnKeyListener(this)
    }


    /**
     * 터치 이벤트 처리하기
     */
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            return onTouchClearDrawable(v, event)
        }
        return false
    }


    /**
     * 삭제 버튼 클릭시 이벤트 처리하기
     */
    private fun onTouchClearDrawable(v: View?, event: MotionEvent): Boolean {
        if (isTouchClearDrawableRange(event.x)) {
            text = null
            requestFocus()
            showKeyboard()
            return true
        }
        return false
    }


    /**
     * 삭제 버튼 범위인지 가져오기
     */
    private fun isTouchClearDrawableRange(xPoint: Float) =
        clearDrawable.isVisible && xPoint > width - paddingRight - clearDrawable.intrinsicWidth


    /**
     * 삭제 버튼 보이기 유무 처리하기
     */
    private fun setClearIconVisible(visible: Boolean) {
        if (::clearDrawable.isInitialized) {
            setCompoundDrawables(searchDrawable, null, if (visible) clearDrawable else null, null)
        }
    }


    /**
     * 포커스 변경 이벤트 처리하기
     */
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            hideKeyboard()
        }
    }


    /**
     * 텍스트 변경 이벤트 처리하기
     */
    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        onTextChangeListener?.invoke(text.toString())
        setClearIconVisible(text.isNullOrEmpty().not())
    }

    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}


    /**
     * 키 입력 이벤트 처리하기
     */
    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {

                return true
            }
        }
        return false
    }
}