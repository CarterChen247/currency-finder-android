package com.carterchen247.currencyfinder.data.local.db.entity

import com.carterchen247.currencyfinder.model.CurrencyType

object CurrencyTypeStringConverter {

    fun toString(currencyType: CurrencyType): String {
        return currencyType.columnName()
    }

    fun toCurrencyType(string: String): CurrencyType {
        return CurrencyType.entries.find { it.columnName() == string } ?: throw IllegalArgumentException("Unknown column name: $string")
    }
}

private fun CurrencyType.columnName(): String {
    return when (this) {
        CurrencyType.CRYPTO -> "CRYPTO"
        CurrencyType.FIAT -> "FIAT"
    }
}