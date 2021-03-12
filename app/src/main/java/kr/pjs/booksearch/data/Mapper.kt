package kr.pjs.booksearch.data

import androidx.annotation.WorkerThread
import io.reactivex.rxjava3.kotlin.toObservable
import kr.pjs.booksearch.data.remote.model.DocumentResponse
import kr.pjs.booksearch.data.remote.model.MetaResponse
import kr.pjs.booksearch.data.remote.model.SearchResponse
import kr.pjs.booksearch.data.view.model.DocumentModel
import kr.pjs.booksearch.data.view.model.MetaModel
import kr.pjs.booksearch.data.view.model.SearchModel

@WorkerThread
fun SearchResponse.toSearchModel(): SearchModel {
    return SearchModel(
        meta = this.meta.toMetaModel(),
        documents = this.documents.toDocumentModels()
    )
}

@WorkerThread
fun MetaResponse.toMetaModel(): MetaModel {
    return MetaModel(
        isEnd = this.isEnd,
        pageableCount = this.pageableCount,
        totalCount = this.totalCount
    )
}

@WorkerThread
fun MutableList<DocumentResponse>.toDocumentModels(): MutableList<DocumentModel> {
    return toObservable().map {
        DocumentModel(
            title = it.title,
            contents = it.contents,
            url = it.url,
            isbn = it.isbn,
            datetime = it.datetime,
            authors = it.authors,
            publisher = it.publisher,
            translators = it.translators,
            price = it.price,
            salePrice = it.salePrice,
            thumbnail = it.thumbnail,
            status = it.status
        )
    }.toList().blockingGet()
}
