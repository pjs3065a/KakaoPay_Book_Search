package kr.pjs.booksearch.data.view.model

import java.util.*

/**
 * 검색 관련 모델 클래스
 */
data class SearchModel(
    val meta: MetaModel,
    val documents: MutableList<DocumentModel>
)

data class MetaModel(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int
)

data class DocumentModel(
    val title: String,
    val contents: String,
    val url: String,
    val isbn: String,
    val datetime: Date,
    val authors: MutableList<String>,
    val publisher: String,
    val translators: MutableList<String>,
    val price: Int,
    val salePrice: Int,
    val thumbnail: String,
    val status: String,
)