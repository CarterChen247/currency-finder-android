package com.carterchen247.currencyfinder.ui.model

import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType

data class CurrencyInfo(
    val avatarCode: String,
    val name: String,
    val displayCode: String,
)

fun CurrencyData.toCurrencyInfo(): CurrencyInfo {
    val displayCode = when (type) {
        CurrencyType.CRYPTO -> symbol
        CurrencyType.FIAT -> code
    }
    val avatarCode = when {
        displayCode.isNotEmpty() -> displayCode.substring(0, 1)
        else -> ""
    }
    return CurrencyInfo(
        avatarCode = avatarCode,
        name = name,
        displayCode = displayCode,
    )
}