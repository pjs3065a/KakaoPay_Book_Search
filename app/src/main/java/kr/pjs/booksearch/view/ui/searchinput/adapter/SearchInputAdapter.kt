package kr.pjs.booksearch.view.ui.searchinput.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.pjs.booksearch.data.view.model.DocumentModel
import kr.pjs.booksearch.view.ui.searchinput.adapter.holder.BookInfoHolder
import kr.pjs.booksearch.view.ui.searchinput.adapter.holder.NoneResultHolder

enum class SearchItemType {
    BOOK,
    NONE,
}

/**
 * 검색 입력 결과에 대한 어댑터 클래스
 */
class SearchInputAdapter :
    ListAdapter<DocumentModel, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<DocumentModel>() {
        override fun areItemsTheSame(oldItem: DocumentModel, newItem: DocumentModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: DocumentModel, newItem: DocumentModel) =
            areItemsTheSame(oldItem, newItem)
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            SearchItemType.BOOK.ordinal -> BookInfoHolder.create(parent)
            SearchItemType.NONE.ordinal -> NoneResultHolder.create(parent)
            else -> throw IllegalStateException("Not defined type")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookInfoHolder) {
            holder.onBindViewHolder(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }
}

