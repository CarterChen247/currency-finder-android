package com.carterchen247.currencyfinder.ui

import androidx.lifecycle.ViewModel
import com.carterchen247.currencyfinder.ui.model.CurrencyInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor() : ViewModel() {
    private val _userInput = MutableStateFlow("")
    val userInput = _userInput.asStateFlow()

    private val _currencyInfoList = MutableStateFlow(
        // TODO: Replace with real data
        buildList {
            repeat(100) {
                add(CurrencyInfo("C", "Crypto.com Chain", "CRO"))
            }
        }
    )

    val currencyInfoList = _currencyInfoList.asStateFlow()

    fun onUserInputChange(input: String) {
        _userInput.value = input
    }

    fun onSearchCancel() {
        _userInput.value = ""
    }
}