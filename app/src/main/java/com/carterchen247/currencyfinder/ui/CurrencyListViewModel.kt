package com.carterchen247.currencyfinder.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor() : ViewModel() {
    private val _userInput = MutableStateFlow("")
    val userInput = _userInput.asStateFlow()

    fun onUserInputChange(input: String) {
        _userInput.value = input
    }

    fun onSearchCancel() {
        _userInput.value = ""
    }
}