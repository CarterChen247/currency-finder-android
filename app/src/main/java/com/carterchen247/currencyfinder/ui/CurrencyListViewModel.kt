package com.carterchen247.currencyfinder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carterchen247.currencyfinder.data.Repository
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.ui.model.CurrencyInfo
import com.carterchen247.currencyfinder.ui.model.FilterType
import com.carterchen247.currencyfinder.ui.model.toCurrencyInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _userInput = MutableStateFlow("")
    val userInput = _userInput.asStateFlow()

    private val _currencyInfoList = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    val currencyInfoList = _currencyInfoList.asStateFlow()

    private val _filterType = MutableStateFlow(FilterType.ALL)

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
        _currencyInfoList.value = emptyList()
    }

    fun onFilterTypeChange(filterType: FilterType) {
        _filterType.value = filterType
        updateCurrencyListState()
    }

    private fun updateCurrencyListState() {
        viewModelScope.launch {
            val currencyType = when (_filterType.value) {
                FilterType.CRYPTO -> CurrencyType.CRYPTO
                FilterType.FIAT -> CurrencyType.FIAT
                FilterType.ALL -> null
            }
            _currencyInfoList.value =
                repository.searchCurrency(_userInput.value, currencyType)
                    .map { currencyData ->
                        currencyData.toCurrencyInfo()
                    }
        }
    }
}
