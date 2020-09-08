package com.kang6264.daumbooksearch.presentation.ui.search

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.kang6264.daumbooksearch.BR

import com.kang6264.daumbooksearch.R
import com.kang6264.daumbooksearch.base.BaseFragment
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.databinding.FragmentBookListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListFragment : BaseFragment<FragmentBookListBinding, BookListViewModel>(), ActionHandler {

    override val layoutId: Int = R.layout.fragment_book_list
    override val viewModel: BookListViewModel by viewModels()
    override val bindingVariable: Int = BR.viewModel

    lateinit var binding: FragmentBookListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()

        val adapter = BookListAdapter(this)
        binding.bookList.adapter = adapter
        subscribe(adapter)

        initUI()
    }

    private fun subscribe(adapter: BookListAdapter) {
        viewModel.booksLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun initUI() {
        binding.editSearch.doAfterTextChanged { text ->
            viewModel.searchBook(text.toString())
        }
    }

    companion object {
        fun newInstance() = BookListFragment()
    }

    override fun openDetail(document: BookDocument) {

    }
}