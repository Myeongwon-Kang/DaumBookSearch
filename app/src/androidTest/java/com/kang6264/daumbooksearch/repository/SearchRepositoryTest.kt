package com.kang6264.daumbooksearch.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kang6264.daumbooksearch.data.api.DaumApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class SearchRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var daumApi: DaumApi

    @Before
    fun init() {
        hiltRule.inject()

    }

    @After
    fun cleanUp(){

    }

    @Test
    fun bookApiTest() = runBlocking {
        val result = daumApi.searchBookList("korea", 1, 50, "", "")
        Assert.assertEquals(9106, result.meta.total_count)
    }
}