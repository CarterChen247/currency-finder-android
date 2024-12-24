package com.carterchen247.currencyfinder.ui

import com.carterchen247.currencyfinder.ui.model.CurrencyInfo
import com.carterchen247.currencyfinder.ui.model.FilterType

data class CurrencyListUiState(
    val currencyInfoList: List<CurrencyInfo> = emptyList(),
    val selectedFilterType: FilterType = FilterType.ALL,
    val filterTypes: List<FilterType> = listOf(
        FilterType.ALL,
        FilterType.CRYPTO,
        FilterType.FIAT,
    ),
    val isLoading: Boolean = false,
)