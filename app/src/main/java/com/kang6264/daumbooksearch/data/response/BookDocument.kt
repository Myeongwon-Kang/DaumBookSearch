package com.kang6264.daumbooksearch.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookDocument(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: Int,
    val publisher: String,
    val sale_price: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String
) : Parcelable