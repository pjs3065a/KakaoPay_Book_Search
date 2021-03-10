package kr.pjs.booksearch.data.remote.api

import io.reactivex.rxjava3.core.Single
import kr.pjs.booksearch.data.remote.model.SearchResponse

/**
 * 검색 관련 DataSource 인터페이스
 * 서버에서 데이터를 가져오는 쿼리를 담고 있고, 실제 데이터를 획득하는 역할을 한다.
 */
interface SearchDataSource {
    fun getSearchResult(
        query: String,
        sort: String? = null,
        page: Int? = null,
        size: Int? = null,
        target: String? = null
    ): Single<SearchResponse>
}