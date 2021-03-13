package kr.pjs.booksearch.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * 검색 관련 DTO 클래스
 */
data class SearchResponse(
    @SerializedName("meta") val meta: MetaResponse,
    @SerializedName("documents") val documents: MutableList<DocumentResponse>
)

data class MetaResponse(
    @SerializedName("is_end") val isEnd: Boolean,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("total_count") val totalCount: Int
)

data class DocumentResponse(
    @SerializedName("title") val title: String,
    @SerializedName("contents") val contents: String,
    @SerializedName("url") val url: String,
    @SerializedName("isbn") val isbn: String,
    @SerializedName("datetime") val datetime: String,
    @SerializedName("authors") val authors: MutableList<String>,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("translators") val translators: MutableList<String>,
    @SerializedName("price") val price: Int,
    @SerializedName("sale_price") val salePrice: Int,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("status") val status: String,
)