package com.kang6264.daumbooksearch.presentation.util

import java.text.SimpleDateFormat
import java.util.*

enum class DateFormat(val format: String) {
    NORMAL("yyyy-MM-dd."),
    ISO8601("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
}

object DateUtil {
    fun convertDate(
        dateString: String,
        inDateFormat: DateFormat,
        outDateFormat: DateFormat
    ): String {
        val outDate = SimpleDateFormat(outDateFormat.format, Locale.getDefault())

        return SimpleDateFormat(inDateFormat.format, Locale.getDefault())
            .parse(dateString)
            .let {
                outDate.format(it)
            }
    }
}