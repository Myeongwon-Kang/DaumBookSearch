package com.kang6264.daumbooksearch.presentation.ui.search.home

import android.view.View
import com.kang6264.daumbooksearch.data.response.BookDocument

interface ActionHandler {
    fun openDetail(document: BookDocument)
}