package com.kang6264.daumbooksearch.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.kang6264.daumbooksearch.R
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.presentation.ui.search.BookDetailFragment
import com.kang6264.daumbooksearch.presentation.ui.search.BookListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BookListFragment.OnClickedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BookListFragment())
                .commit()
        }
    }

    override fun onClick(document: BookDocument) {
        hideCurrentFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, BookDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("document", document)
                }
            })
            .addToBackStack("")
            .commit()

        hideKeyboard()
    }

    private fun hideKeyboard() {
        val view = this.getCurrentFocus()
        if (view != null) {
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun hideCurrentFragment() {
        var currentFragment = supportFragmentManager.fragments.last()
        supportFragmentManager.beginTransaction()
            .hide(currentFragment)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}