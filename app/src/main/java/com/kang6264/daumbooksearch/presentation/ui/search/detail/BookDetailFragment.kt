package com.kang6264.daumbooksearch.presentation.ui.search.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.kang6264.daumbooksearch.BR
import com.kang6264.daumbooksearch.R
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.databinding.FragmentBookDetailBinding
import com.kang6264.daumbooksearch.presentation.base.BaseFragment
import com.kang6264.daumbooksearch.presentation.ui.search.info.EmptyFragment
import com.kang6264.daumbooksearch.presentation.ui.search.info.InfoFragment

class BookDetailFragment : BaseFragment<FragmentBookDetailBinding, BookDetailViewModel>() {

    override val layoutId = R.layout.fragment_book_detail
    override val viewModel: BookDetailViewModel by activityViewModels()
    override val bindingVariable: Int = BR.viewModel

    private lateinit var binding: FragmentBookDetailBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()

        var bookDocument: BookDocument? = arguments?.getParcelable("document")
        bookDocument?.let {
            viewModel.data = it
            binding.document = it
        }

        binding.run {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            viewpager.offscreenPageLimit = INFO_PAGES.size
            viewpager.adapter = InfoAdapter(this@BookDetailFragment)

            TabLayoutMediator(tabs, viewpager) { tab, position ->
                tab.text = resources.getString(INFO_TITLES[position])
            }.attach()
        }
    }

    inner class InfoAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int) = INFO_PAGES[position].invoke()

        override fun getItemCount() = INFO_PAGES.size
    }

    companion object {

        private val INFO_TITLES = arrayOf(
            R.string.book_introduce_title,
            R.string.book_author_title,
            R.string.book_contents_title,
            R.string.book_review_title,
        )

        private val INFO_PAGES = arrayOf(
            { InfoFragment() },
            { EmptyFragment() },
            { EmptyFragment() },
            { EmptyFragment() }
        )
    }
}