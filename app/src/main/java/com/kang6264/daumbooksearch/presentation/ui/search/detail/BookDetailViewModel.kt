package com.kang6264.daumbooksearch.presentation.ui.search.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.presentation.base.BaseViewModel

class BookDetailViewModel : BaseViewModel() {

    lateinit var data : BookDocument
}