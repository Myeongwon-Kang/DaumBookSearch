package com.kang6264.daumbooksearch.presentation.ui.search.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kang6264.daumbooksearch.presentation.base.BaseViewModel

class BookDetailViewModel : BaseViewModel() {

    private val _onBackAction = MutableLiveData<Unit>()
    val onBackAction : LiveData<Unit> = _onBackAction

    fun onBackClicked() {
        _onBackAction.value = Unit
    }
}