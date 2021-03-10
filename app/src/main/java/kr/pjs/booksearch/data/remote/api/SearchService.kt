package kr.pjs.booksearch.data.remote.api

import io.reactivex.rxjava3.core.Single
import kr.pjs.booksearch.data.remote.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 검색 관련 Api 인터페이스
 */
interface SearchService {

    /**
     * 책 검색 API
     */
    @GET("/v3/search/book")
    fun getSearchResult(
        @Query("query") query: String,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("target") target: String? = null,
    ): Single<SearchResponse>
}