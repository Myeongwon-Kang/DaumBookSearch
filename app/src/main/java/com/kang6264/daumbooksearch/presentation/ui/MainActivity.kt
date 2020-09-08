package com.kang6264.daumbooksearch.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kang6264.daumbooksearch.R
import com.kang6264.daumbooksearch.presentation.ui.search.BookListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(null == savedInstanceState){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BookListFragment.newInstance())
                .commit()
        }
    }
}