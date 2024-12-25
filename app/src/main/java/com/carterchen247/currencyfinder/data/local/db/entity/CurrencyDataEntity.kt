package com.carterchen247.currencyfinder.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carterchen247.currencyfinder.data.local.model.CurrencyDataModel
import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType

@Entity
data class CurrencyDataEntity(
    val currencyId: String,
    val name: String,
    val symbol: String,
    val code: String = "",
    val type: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
)

fun CurrencyDataEntity.toCurrencyData() = CurrencyData(
    id = currencyId,
    name = name,
    symbol = symbol,
    code = code,
    type = CurrencyTypeStringConverter.toCurrencyType(type),
)

fun CurrencyDataModel.toCurrencyDataEntity(currencyType: CurrencyType) = CurrencyDataEntity(
    currencyId = id,
    name = name,
    symbol = symbol,
    code = code.orEmpty(),
    type = CurrencyTypeStringConverter.toString(currencyType),
)