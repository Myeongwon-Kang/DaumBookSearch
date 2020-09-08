package com.kang6264.daumbooksearch.data.response

import com.google.gson.annotations.SerializedName

data class BookMeta(
    val is_end: Boolean,
    val pageable_count: Int,
    val total_count: Int
)