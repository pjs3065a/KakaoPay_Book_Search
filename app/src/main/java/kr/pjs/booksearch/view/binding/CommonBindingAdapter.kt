package kr.pjs.booksearch.view.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kr.pjs.booksearch.R

/**
 * 이미지 로딩 처리
 */
@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view).load(url).error(R.drawable.no_image).fitCenter().into(view)
}

/**
 * Selector 전환 처리
 */
@BindingAdapter("isSelected")
fun setSelected(view: View, isSelected: Boolean) {
    view.isSelected = isSelected
}