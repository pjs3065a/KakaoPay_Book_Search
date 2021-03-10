package kr.pjs.booksearch.data.remote.api

import io.reactivex.rxjava3.core.Single
import kr.pjs.booksearch.data.remote.RetrofitManager
import kr.pjs.booksearch.data.remote.model.SearchResponse

class SearchDataSourceImpl : SearchDataSource {
    private val searchService by lazy {
        RetrofitManager.create().retrofit().create(SearchService::class.java)
    }

    override fun getSearchResult(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?,
        target: String?
    ): Single<SearchResponse> = searchService.getSearchResult(query, sort, page, size, target)
}