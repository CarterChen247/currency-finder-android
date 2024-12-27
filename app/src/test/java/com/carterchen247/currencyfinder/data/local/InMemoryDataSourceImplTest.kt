package com.carterchen247.currencyfinder.data.local

import androidx.test.core.app.ApplicationProvider
import com.carterchen247.currencyfinder.util.resource.CurrencyResourceProviderImpl
import com.google.gson.Gson
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class InMemoryDataSourceImplTest : BaseLocalDataSourceTest() {
    override fun provideLocalDataSource(): InMemoryDataSourceImpl {
        val resourceProvider = CurrencyResourceProviderImpl(ApplicationProvider.getApplicationContext(), Gson())
        return InMemoryDataSourceImpl(resourceProvider)
    }
}