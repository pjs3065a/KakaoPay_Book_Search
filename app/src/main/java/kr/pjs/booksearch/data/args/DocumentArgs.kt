package kr.pjs.booksearch.data.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kr.pjs.booksearch.data.view.model.DocumentModel

@Parcelize
data class DocumentArgs(
    val document: DocumentModel
) : Parcelable