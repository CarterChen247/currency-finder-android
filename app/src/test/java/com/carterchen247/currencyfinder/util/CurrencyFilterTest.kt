@file:Suppress("NonAsciiCharacters")

package com.carterchen247.currencyfinder.util

import com.carterchen247.currencyfinder.model.SearchableCurrency
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CurrencyFilterTest {

    private lateinit var currencyFilter: CurrencyFilter

    @Before
    fun setUp() {
        currencyFilter = CurrencyFilter()
    }

    @Test
    fun `match if the name of the coin starts with the search term`() {
        val list = listOf(
            object : SearchableCurrency {
                override val name = "Foobar"
                override val symbol = ""
            }
        )
        val result = currencyFilter.filter("foo", list)
        assertEquals(1, result.size)
        assertEquals("Foobar", result[0].name)
    }

    @Test
    fun `not match if the name of the coin not starts with the search term`() {
        val list = listOf(
            object : SearchableCurrency {
                override val name = "Barfoo"
                override val symbol = ""
            }
        )
        val result = currencyFilter.filter("foo", list)
        assertEquals(0, result.size)
    }

    @Test
    fun `match if The coin’s name contains a partial match with a ‘ ’ (space) prefixed to the search term`() {
        val list = listOf(
            object : SearchableCurrency {
                override val name = "Ethereum Classic"
                override val symbol = ""
            }
        )
        val result = currencyFilter.filter("Classic", list)
        assertEquals(1, result.size)
        assertEquals("Ethereum Classic", result[0].name)
    }

    @Test
    fun `not match if The coin’s does not contains a partial match with a ‘ ’ (space) prefixed to the search term`() {
        val list = listOf(
            object : SearchableCurrency {
                override val name = "Tronclassic"
                override val symbol = ""
            }
        )
        val result = currencyFilter.filter("Classic", list)
        assertEquals(0, result.size)
    }

    @Test
    fun `match if query == ET and coin’s symbol == ETH`() {
        val list = listOf(
            object : SearchableCurrency {
                override val name = ""
                override val symbol = "ETH"
            }
        )
        val result = currencyFilter.filter("ET", list)
        assertEquals(1, result.size)
        assertEquals("ETH", result[0].symbol)
    }

    @Test
    fun `match if query == ET and coin’s symbol == ETC`() {
        val list = listOf(
            object : SearchableCurrency {
                override val name = ""
                override val symbol = "ETC"
            }
        )
        val result = currencyFilter.filter("ET", list)
        assertEquals(1, result.size)
        assertEquals("ETC", result[0].symbol)
    }

    @Test
    fun `match if query == ET and coin’s symbol == ETN`() {
        val list = listOf(
            object : SearchableCurrency {
                override val name = ""
                override val symbol = "ETN"
            }
        )
        val result = currencyFilter.filter("ET", list)
        assertEquals(1, result.size)
        assertEquals("ETN", result[0].symbol)
    }

    @Test
    fun `not match if query == ET and coin’s symbol == BET`() {
        val list = listOf(
            object : SearchableCurrency {
                override val name = ""
                override val symbol = "BET"
            }
        )
        val result = currencyFilter.filter("ET", list)
        assertEquals(0, result.size)
    }
}