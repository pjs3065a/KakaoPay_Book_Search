package kr.pjs.booksearch.view.ui.searchinput.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.pjs.booksearch.data.NONE
import kr.pjs.booksearch.data.view.model.DocumentModel
import kr.pjs.booksearch.databinding.HolderBookItemBinding
import kr.pjs.booksearch.extensions.toDateFormat
import kr.pjs.booksearch.extensions.toPriceFormat
import kr.pjs.booksearch.utils.rx.RxBus
import kr.pjs.booksearch.utils.rx.RxBusEvent
import kr.pjs.booksearch.view.binding.setImageUrl
import kr.pjs.booksearch.view.binding.setSelected

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
        mBinding.tvBookDatetime.text = item.datetime?.toDateFormat()
        mBinding.tvBookDescription.text = convertEmptyString(item.contents)
        mBinding.tvBookPrice.text = item.price.toString().toPriceFormat()
        setSelected(mBinding.ibFavorite, item.isFavorite)
        setImageUrl(mBinding.ivBook, item.thumbnail)

        mBinding.root.setOnClickListener {
            RxBus.publish(RxBusEvent.BookInfoItemClick(item))
        }
    }

    private fun convertEmptyString(data: String): String = if (data.isEmpty()) {
        NONE
    } else {
        data
    }
}