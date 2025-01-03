package com.carterchen247.currencyfinder.data.local

import com.carterchen247.currencyfinder.model.CurrencyType
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Test

abstract class BaseLocalDataSourceTest {

    private lateinit var localDataSource: LocalDataSource

    abstract fun provideLocalDataSource(): LocalDataSource

    @Before
    open fun setUp() {
        localDataSource = provideLocalDataSource()
    }

    @Test
    fun `result will be empty before load data`() = runTest {
        val result = localDataSource.searchCurrency("")
        assertTrue(result.isEmpty())
    }

    @Test
    fun `result will be not empty after load data`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("")
        assertTrue(result.isNotEmpty())
        assertTrue(result.any { currencyData ->
            currencyData.type == CurrencyType.CRYPTO
        })
        assertTrue(result.any { currencyData ->
            currencyData.type == CurrencyType.FIAT
        })
    }

    @Test
    fun `result type will be all CRYPTO if query type == CRYPTO`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("", CurrencyType.CRYPTO)
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { currencyData -> currencyData.type == CurrencyType.CRYPTO })
    }

    @Test
    fun `result type will be all FIAT if query type == FIAT`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("", CurrencyType.FIAT)
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { currencyData -> currencyData.type == CurrencyType.FIAT })
    }

    @Test
    fun `result will be empty if query space ' '`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency(" ")
        assertTrue(result.isEmpty())
    }

    @Test
    fun `result will be empty after clear data`() = runTest {
        localDataSource.loadData()
        localDataSource.clearData()
        val result = localDataSource.searchCurrency("")
        assertTrue(result.isEmpty())
    }

    @Test
    fun `query data which name start with 'Ethereum'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("Ethereum")
        assertEquals(2, result.size)
        assertArrayEquals(
            result.map { currencyData -> currencyData.name }.toTypedArray(),
            arrayOf(
                "Ethereum",
                "Ethereum Classic",
            )
        )
    }

    @Test
    fun `query data which name partial match 'Classic'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("Classic")
        assertEquals(1, result.size)
        assertArrayEquals(
            result.map { currencyData -> currencyData.name }.toTypedArray(),
            arrayOf(
                "Ethereum Classic",
            )
        )
    }

    @Test
    fun `query data which name partial match 'Coin'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("Coin")
        assertEquals(2, result.size)
        assertArrayEquals(
            result.map { currencyData -> currencyData.name }.toTypedArray(),
            arrayOf(
                "Binance Coin",
                "USD Coin",
            )
        )
    }

    @Test
    fun `query data which name partial match 'Dollar'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("Dollar")
        assertEquals(4, result.size)
        assertArrayEquals(
            result.map { currencyData -> currencyData.name }.toTypedArray(),
            arrayOf(
                "Singapore Dollar",
                "Hong Kong Dollar",
                "Australian Dollar",
                "United States Dollar",
            )
        )
    }

    @Test
    fun `query data which name match 'Chain'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("Chain")
        assertEquals(2, result.size)
        assertArrayEquals(
            result.map { currencyData -> currencyData.name }.toTypedArray(),
            arrayOf(
                "Chainlink",
                "Crypto.com Chain",
            )
        )
    }

    @Test
    fun `query data which symbol start with '$'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("$")
        assertEquals(4, result.size)
        assertArrayEquals(
            result.map { currencyData -> currencyData.name }.toTypedArray(),
            arrayOf(
                "Singapore Dollar",
                "Hong Kong Dollar",
                "Australian Dollar",
                "United States Dollar",
            )
        )
    }

    @Test
    fun `query data which symbol start with '$' and type == CRYPTO`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("$", CurrencyType.CRYPTO)
        assertEquals(0, result.size)
    }


    @Test
    fun `query data which symbol start with 'ET'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("ET")
        assertEquals(2, result.size)
        assertArrayEquals(
            result.map { currencyData -> currencyData.symbol }.toTypedArray(),
            arrayOf(
                "ETH",
                "ETC",
            )
        )
    }

    @Test
    fun `query data with 'USD'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("USD")
        assertEquals(1, result.size)
        assertEquals("USD Coin", result[0].name)
        assertEquals("USDC", result[0].symbol)
        assertEquals(CurrencyType.CRYPTO, result[0].type)
    }

    @Test
    fun `cannot query data with code field`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("SGD", CurrencyType.FIAT)
        assertEquals(0, result.size)
    }

    @Test
    fun `query data with 'b'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("b")
        assertEquals(4, result.size)
        assertArrayEquals(
            result.map { currencyData -> currencyData.name }.toTypedArray(),
            arrayOf(
                "Bitcoin",
                "Bitcoin Cash",
                "Binance Coin",
                "British Pound",
            )
        )
    }

    @Test
    fun `query data with 'be'`() = runTest {
        localDataSource.loadData()
        val result = localDataSource.searchCurrency("be")
        assertEquals(0, result.size)
    }
}