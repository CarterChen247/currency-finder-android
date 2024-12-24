package com.carterchen247.currencyfinder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carterchen247.currencyfinder.data.Repository
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.ui.model.FilterType
import com.carterchen247.currencyfinder.ui.model.toCurrencyInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CurrencyListUiState())
    val uiState = _uiState.asStateFlow()

    private val _userInput = MutableStateFlow("")
    val userInput = _userInput.asStateFlow()

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
        viewModelScope.launch {
            repository.clearData()
        }
        _uiState.update { it.copy(currencyInfoList = emptyList()) }
    }

    fun onFilterTypeChange(filterType: FilterType) {
        _uiState.update { it.copy(selectedFilterType = filterType) }
        updateCurrencyListState()
    }

    private fun updateCurrencyListState() {
        viewModelScope.launch {
            val currencyType = convertCurrencyType(_uiState.value.selectedFilterType)
            val resultList = repository.searchCurrency(_userInput.value, currencyType)
                .map { currencyData -> currencyData.toCurrencyInfo() }
            _uiState.update { it.copy(currencyInfoList = resultList) }
        }
    }

    private fun convertCurrencyType(filterType: FilterType) = when (filterType) {
        FilterType.CRYPTO -> CurrencyType.CRYPTO
        FilterType.FIAT -> CurrencyType.FIAT
        FilterType.ALL -> null
    }
}
