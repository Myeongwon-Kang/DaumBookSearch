package com.kang6264.daumbooksearch.presentation.ui.search.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kang6264.daumbooksearch.BR

import com.kang6264.daumbooksearch.R
import com.kang6264.daumbooksearch.presentation.base.BaseFragment
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.databinding.FragmentBookListBinding
import com.kang6264.daumbooksearch.domain.data.RequestParams
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_book_list.*
import java.lang.RuntimeException

@AndroidEntryPoint
class BookListFragment : BaseFragment<FragmentBookListBinding, BookListViewModel>(), ActionHandler {

    interface OnClickedListener {
        fun onClick(document: BookDocument)
    }

    override val layoutId: Int = R.layout.fragment_book_list
    override val viewModel: BookListViewModel by viewModels()
    override val bindingVariable: Int = BR.viewModel

    lateinit var binding: FragmentBookListBinding
    private lateinit var listener: OnClickedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickedListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()

        val adapter = BookListAdapter(this)
        binding.bookList.adapter = adapter
        subscribe(adapter)

        initUI()
        initSpinner()
        initSearchBtn()
    }

    private fun subscribe(adapter: BookListAdapter) {
        viewModel.booksLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun initUI() {
        binding.editSearch.doAfterTextChanged { text ->
            searchBook(text.toString())
        }
    }

    private fun initSearchBtn() {
        binding.btnSearch.setOnClickListener {
            searchFromSpinner(
                RequestParams(
                    binding.editSearch.text.toString(),
                    1,
                    50,
                    selectSearchSort(binding.spinnerSort.selectedItemPosition),
                    selectSearchTarget(binding.spinnerTarget.selectedItemPosition),
                )
            )
        }
    }

    private fun searchBook(query: String) {
        viewModel.searchBook(RequestParams(query))
    }

    private fun searchFromSpinner(requestParams: RequestParams) {
        viewModel.searchBook(requestParams)
    }

    override fun openDetail(document: BookDocument) {
        listener.onClick(document)
    }

    private fun selectSearchSort(pos: Int): String {
        return when (pos) {
            0 -> "accuracy"
            1 -> "recency"
            else -> ""
        }
    }

    private fun selectSearchTarget(pos: Int): String {
        return when (pos) {
            0 -> "title"
            1 -> "isbn"
            2 -> "publisher"
            3 -> "person"
            else -> ""
        }
    }

    private fun initSpinner() {
        spinner_sort.apply {
            adapter = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.search_sort)
            )
        }

        spinner_sort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                searchFromSpinner(
                    RequestParams(
                        binding.editSearch.text.toString(),
                        1,
                        50,
                        selectSearchSort(p2),
                        selectSearchTarget(binding.spinnerTarget.selectedItemPosition)
                    )
                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        spinner_target.apply {
            adapter = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.search_targer)
            )
        }

        spinner_target.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                searchFromSpinner(
                    RequestParams(
                        binding.editSearch.text.toString(),
                        1,
                        50,
                        selectSearchSort(binding.spinnerSort.selectedItemPosition),
                        selectSearchTarget(p2)
                    )
                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}