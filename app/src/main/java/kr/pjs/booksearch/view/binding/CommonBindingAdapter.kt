package kr.pjs.booksearch.view.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}

@BindingAdapter("isSelected")
fun setSelected(view: View, isSelected: Boolean) {
    view.isSelected = isSelected
}