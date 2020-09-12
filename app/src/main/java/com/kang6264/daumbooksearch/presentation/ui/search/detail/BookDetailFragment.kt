package com.kang6264.daumbooksearch.presentation.ui.search.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kang6264.daumbooksearch.BR
import com.kang6264.daumbooksearch.R
import com.kang6264.daumbooksearch.presentation.base.BaseFragment
import com.kang6264.daumbooksearch.databinding.FragmentBookDetailBinding

class BookDetailFragment : BaseFragment<FragmentBookDetailBinding, BookDetailViewModel>(),
    CallActivityNavigator{

    override val layoutId = R.layout.fragment_book_detail
    override val viewModel: BookDetailViewModel by viewModels()
    override val bindingVariable: Int = BR.viewModel

    private lateinit var binding: FragmentBookDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()

        activity?.title = "책 검색"

        binding.document = arguments?.getParcelable("document")
    }

    override fun callActivity() {

    }
}