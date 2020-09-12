package com.kang6264.daumbooksearch.domain.data

data class RequestParams(
    var query: String = "",
    var page: Int = 1,
    var size: Int = 50,
    var sort: String = "accuracy",
    var target: String = "title"
)