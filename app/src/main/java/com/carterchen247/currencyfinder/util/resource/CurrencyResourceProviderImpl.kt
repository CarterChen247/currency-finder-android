package com.carterchen247.currencyfinder.util.resource

import android.content.Context
import com.carterchen247.currencyfinder.R
import com.carterchen247.currencyfinder.data.local.model.CurrencyDataModel
import com.carterchen247.currencyfinder.data.local.model.toCurrencyData
import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType
import com.google.gson.Gson

class CurrencyResourceProviderImpl(
    private val context: Context,
    private val gson: Gson
) : CurrencyResourceProvider {

    override fun getDataset(currencyType: CurrencyType): List<CurrencyData> {
        val resourceId = when (currencyType) {
            CurrencyType.CRYPTO -> R.raw.dataset_crypto
            CurrencyType.FIAT -> R.raw.dataset_fiat
        }
        return context.resources
            .openRawResource(resourceId)
            .bufferedReader()
            .use { reader ->
                gson.fromJson(reader, Array<CurrencyDataModel>::class.java).toList()
            }.map {
                it.toCurrencyData(currencyType)
            }
    }
}