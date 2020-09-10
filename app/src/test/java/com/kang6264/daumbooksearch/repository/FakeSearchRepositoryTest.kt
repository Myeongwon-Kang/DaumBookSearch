package com.kang6264.daumbooksearch.repository

import com.kang6264.daumbooksearch.presentation.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FakeSearchRepositoryTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Test
    fun bookApiTest() = coroutinesTestRule.testDispatcher.runBlockingTest{

    }
}