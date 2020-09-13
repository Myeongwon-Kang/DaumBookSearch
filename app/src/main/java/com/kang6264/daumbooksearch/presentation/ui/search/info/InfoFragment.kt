package com.kang6264.daumbooksearch.presentation.ui.search.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.kang6264.daumbooksearch.BR
import com.kang6264.daumbooksearch.R
import com.kang6264.daumbooksearch.databinding.FragmentInfoBinding
import com.kang6264.daumbooksearch.presentation.base.BaseFragment
import com.kang6264.daumbooksearch.presentation.ui.search.detail.BookDetailViewModel

class InfoFragment : BaseFragment<FragmentInfoBinding, BookDetailViewModel>() {

    override val layoutId = R.layout.fragment_info
    override val viewModel: BookDetailViewModel by activityViewModels()
    override val bindingVariable = BR.viewModel

    private lateinit var binding: FragmentInfoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()

        binding.document = viewModel.data

    }
}