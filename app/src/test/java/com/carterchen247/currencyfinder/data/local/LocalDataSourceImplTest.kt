package com.carterchen247.currencyfinder.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.carterchen247.currencyfinder.data.local.db.AppDatabase
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

    override fun provideLocalDataSource() = LocalDataSourceImpl(database.currencyDataDao())

    @After
    fun tearDown() {
        database.close()
    }
}