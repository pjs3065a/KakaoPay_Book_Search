package kr.pjs.booksearch.data.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kr.pjs.booksearch.view.ui.searchinput.adapter.SearchItemType

/**
 * 검색 관련 모델 클래스
 */

interface SearchItem

data class SearchModel(
    val meta: MetaModel,
    val documents: MutableList<DocumentModel>
)

data class MetaModel(
    val isEnd: Boolean = true,
    val pageableCount: Int = 0,
    val totalCount: Int = 0,
    var query: String = "",
    var currentPage: Int = 1
)

@Parcelize
data class DocumentModel(
    val title: String = "",
    val contents: String = "",
    val url: String = "",
    val isbn: String = "",
    val datetime: String = "",
    val authors: MutableList<String>? = null,
    val publisher: String = "",
    val translators: MutableList<String>? = null,
    val price: Int = 0,
    val salePrice: Int = 0,
    val thumbnail: String = "",
    val status: String = "",
    var viewType: SearchItemType = SearchItemType.BOOK,
    var isFavorite: Boolean = false
) : Parcelable, SearchItem