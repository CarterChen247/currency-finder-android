package com.carterchen247.currencyfinder.data.local

import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class InMemoryDataSourceImplTest : BaseLocalDataSourceTest() {
    override fun provideLocalDataSource() = InMemoryDataSourceImpl()
}