package com.carterchen247.currencyfinder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carterchen247.currencyfinder.data.Repository
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.ui.model.FilterType
import com.carterchen247.currencyfinder.ui.model.toCurrencyInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CurrencyListUiState())
    val uiState = _uiState.asStateFlow()

    private val _userInput = MutableStateFlow("")
    val userInput = _userInput.asStateFlow()

    private var searchJob: Job? = null

    fun onUserInputChange(input: String) {
        _userInput.value = input
        updateCurrencyListState()
    }

    fun onSearchCancel() {
        _userInput.value = ""
        updateCurrencyListState()
    }

    fun onUserInsertData() {
        viewModelScope.launch {
            repository.loadData()
            updateCurrencyListState()
        }
    }

    fun onUserClearData() {
        searchJob?.cancel()
        viewModelScope.launch {
            repository.clearData()
        }
        // reset currency info list
        _uiState.update {
            it.copy(
                currencyInfoList = emptyList(),
                isLoading = false,
            )
        }
    }

    fun onFilterTypeChange(filterType: FilterType) {
        _uiState.update { it.copy(selectedFilterType = filterType) }
        updateCurrencyListState()
    }

    private fun updateCurrencyListState() {
        val searchStartTimestamp = System.currentTimeMillis()
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val currencyType = convertCurrencyType(_uiState.value.selectedFilterType)
            val resultList = repository.searchCurrency(_userInput.value, currencyType)
                .map { currencyData -> currencyData.toCurrencyInfo() }

            Timber.d("search start timestamp: $searchStartTimestamp")
            _uiState.update {
                it.copy(
                    currencyInfoList = resultList,
                    isLoading = false,
                )
            }
        }
    }

    private fun convertCurrencyType(filterType: FilterType) = when (filterType) {
        FilterType.CRYPTO -> CurrencyType.CRYPTO
        FilterType.FIAT -> CurrencyType.FIAT
        FilterType.ALL -> null
    }
}
