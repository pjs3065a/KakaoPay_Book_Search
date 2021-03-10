package kr.pjs.booksearch.data.remote.repository

import io.reactivex.rxjava3.core.Single
import kr.pjs.booksearch.data.remote.api.SearchDataSource
import kr.pjs.booksearch.data.remote.model.SearchResponse

class SearchRepositoryImpl(private val searchDataSource: SearchDataSource) : SearchRepository {
    override fun getSearchResult(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?,
        target: String?
    ): Single<SearchResponse> = searchDataSource.getSearchResult(query, sort, page, size, target)
}