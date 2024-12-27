package com.carterchen247.currencyfinder.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.carterchen247.currencyfinder.data.local.db.AppDatabase
import com.carterchen247.currencyfinder.util.resource.CurrencyResourceProviderImpl
import com.google.gson.Gson
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LocalDataSourceImplTest : BaseLocalDataSourceTest() {

    private lateinit var database: AppDatabase

    @Before
    override fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        super.setUp()
    }

    override fun provideLocalDataSource(): LocalDataSourceImpl {
        val resourceProvider = CurrencyResourceProviderImpl(ApplicationProvider.getApplicationContext(), Gson())
        return LocalDataSourceImpl(database.currencyDataDao(), resourceProvider)
    }

    @After
    fun tearDown() {
        database.close()
    }
}