package kr.pjs.booksearch.view.ui.searchinput.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.pjs.booksearch.data.view.model.DocumentModel
import kr.pjs.booksearch.databinding.HolderBookItemBinding
import kr.pjs.booksearch.extensions.toDateFormat
import kr.pjs.booksearch.utils.rx.RxBus
import kr.pjs.booksearch.utils.rx.RxBusEvent

class BookInfoHolder private constructor(private val mBinding: HolderBookItemBinding) :
    RecyclerView.ViewHolder(mBinding.root) {
    companion object {
        fun create(parent: ViewGroup): BookInfoHolder {
            return BookInfoHolder(
                HolderBookItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun onBindViewHolder(item: DocumentModel) {
        mBinding.tvBookName.text = item.title
        mBinding.tvBookDatetime.text = item.datetime.toDateFormat()
        mBinding.tvBookDescription.text = item.contents
        mBinding.tvBookPrice.text = item.price.toString()
        mBinding.ibFavorite.isSelected = item.isFavorite
        Glide.with(mBinding.root).load(item.thumbnail).into(mBinding.ivBook)

        mBinding.root.setOnClickListener {
            RxBus.publish(RxBusEvent.BookInfoItemClick(item))
        }
    }
}