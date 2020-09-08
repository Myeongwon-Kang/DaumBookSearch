package com.kang6264.daumbooksearch.data.response

data class BookResponse(
    val meta: BookMeta,
    val documents: List<BookDocument>
)
