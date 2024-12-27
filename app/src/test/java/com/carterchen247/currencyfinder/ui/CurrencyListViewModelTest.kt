package com.carterchen247.currencyfinder.ui

import com.carterchen247.currencyfinder.MainDispatcherRule
import com.carterchen247.currencyfinder.TestData
import com.carterchen247.currencyfinder.data.Repository
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.ui.model.CurrencyInfo
import com.carterchen247.currencyfinder.ui.model.FilterType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrencyListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: Repository
    private lateinit var viewModel: CurrencyListViewModel

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        viewModel = CurrencyListViewModel(repository)
    }

    @Test
    fun `when loading data, should call repository and refresh list`() = runTest {
        // Given
        coEvery { repository.loadData() } just runs
        coEvery { repository.searchCurrency(any(), any()) } returns listOf(
            TestData.currencyData
        )

        // When
        viewModel.onUserInsertData()

        // Then
        coVerify { repository.loadData() }
        coVerify { repository.searchCurrency(any(), any()) }
        val uiState = viewModel.uiState.value
        assertEquals(1, uiState.currencyInfoList.size)
        assertEquals(false, uiState.isLoading)
    }


    @Test
    fun `when clearing data, should call repository and clear list`() = runTest {
        // Given
        coEvery { repository.loadData() } just runs
        coEvery { repository.searchCurrency("", null) } returns listOf()

        // When
        viewModel.onUserClearData()

        // Then
        coVerify { repository.clearData() }
        coVerify(exactly = 0) { repository.loadData() }
        coVerify(exactly = 0) { repository.searchCurrency(any(), any()) }
        val uiState = viewModel.uiState.value
        assertEquals(emptyList<CurrencyInfo>(), uiState.currencyInfoList)
        assertEquals(false, uiState.isLoading)
    }


    @Test
    fun `when user search, should update userInput state and trigger search`() = runTest {
        // Given
        val testInput = "CRO"
        coEvery { repository.searchCurrency(any(), any()) } returns listOf(
            TestData.currencyData
        )

        // When
        viewModel.onUserInputChange(testInput)

        // Then
        assertEquals("CRO", viewModel.userInput.value)
        coVerify { repository.searchCurrency(any(), any()) }
        val uiState = viewModel.uiState.value
        assertEquals(false, uiState.isLoading)
        assertEquals(1, uiState.currencyInfoList.size)
    }

    @Test
    fun `when search is cancelled, should clear input and refresh list`() = runTest {
        // Given
        val testInput = "CRO"
        coEvery { repository.searchCurrency(any(), any()) } returns listOf(
            TestData.currencyData
        )

        // When
        viewModel.onUserInputChange(testInput)

        coEvery { repository.searchCurrency(any(), any()) } returns listOf(
            TestData.currencyData,
            TestData.currencyData,
        )

        viewModel.onSearchCancel()

        // Then
        coVerify(exactly = 2) { repository.searchCurrency(any(), any()) }
        val uiState = viewModel.uiState.value
        assertEquals("", viewModel.userInput.value)
        assertEquals(false, uiState.isLoading)
        assertEquals(2, uiState.currencyInfoList.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when user search, should update loading state and update data correctly`() = runTest {
        coEvery { repository.searchCurrency(any(), any()) } coAnswers {
            delay(5000)
            listOf(
                TestData.currencyData,
            )
        }

        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(0, viewModel.uiState.value.currencyInfoList.size)

        viewModel.requestCurrencyList()
        advanceTimeBy(1000)

        assertEquals(true, viewModel.uiState.value.isLoading)
        assertEquals(0, viewModel.uiState.value.currencyInfoList.size)

        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(1, viewModel.uiState.value.currencyInfoList.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when search fails, should capture error and update loading state`() = runTest {
        // Given
        coEvery { repository.searchCurrency(any(), any()) } throws RuntimeException("Search failed")

        val errorCollector = mutableListOf<Throwable>()
        val job = launch {
            viewModel.errorEvent.collect {
                errorCollector.add(it)
            }
        }
        advanceUntilIdle() // make sure the collector is ready

        // When
        viewModel.requestCurrencyList()
        advanceUntilIdle()

        // Then
        val uiState = viewModel.uiState.value
        assertEquals(false, uiState.isLoading)
        assertEquals(emptyList<CurrencyInfo>(), uiState.currencyInfoList)
        assertEquals("Search failed", errorCollector.last().message)
        job.cancel()
    }

    @Test
    fun `when filter type changes, should update state and refresh search`() = runTest {
        // Given
        coEvery { repository.searchCurrency("", CurrencyType.CRYPTO) } returns listOf(
            TestData.currencyData
        )

        // When
        viewModel.onFilterTypeChange(FilterType.CRYPTO)

        // Then
        coVerify { repository.searchCurrency("", CurrencyType.CRYPTO) }
        assertEquals(FilterType.CRYPTO, viewModel.uiState.value.selectedFilterType)
        assertEquals(1, viewModel.uiState.value.currencyInfoList.size)
    }
}