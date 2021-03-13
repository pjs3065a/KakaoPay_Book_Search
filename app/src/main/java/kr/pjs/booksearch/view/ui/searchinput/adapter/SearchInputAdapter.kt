package kr.pjs.booksearch.view.ui.searchinput.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kr.pjs.booksearch.data.view.model.DocumentModel
import kr.pjs.booksearch.view.ui.searchinput.adapter.holder.BookInfoHolder

/**
 * 검색 입력 결과에 대한 어댑터 클래스
 */
class SearchInputAdapter :
    ListAdapter<DocumentModel, BookInfoHolder>(object : DiffUtil.ItemCallback<DocumentModel>() {
        override fun areItemsTheSame(oldItem: DocumentModel, newItem: DocumentModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: DocumentModel, newItem: DocumentModel) =
            areItemsTheSame(oldItem, newItem)
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookInfoHolder.create(parent)

    override fun onBindViewHolder(holder: BookInfoHolder, position: Int) {
        holder.onBindViewHolder(getItem(position))
    }
}

