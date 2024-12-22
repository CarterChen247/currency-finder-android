package com.carterchen247.currencyfinder.util

import com.carterchen247.currencyfinder.model.SearchableCurrency
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyFilterTest {
    @Test
    fun `match if the name of the coin starts with the search term`() {
        val currencyFilter = CurrencyFilter(
            listOf(
                object : SearchableCurrency {
                    override val name = "Foobar"
                    override val symbol = ""
                }
            )
        )
        val result = currencyFilter.filter("foo")
        assertEquals(1, result.size)
        assertEquals("Foobar", result[0].name)
    }

    @Test
    fun `not match if the name of the coin not starts with the search term`() {
        val currencyFilter = CurrencyFilter(
            listOf(
                object : SearchableCurrency {
                    override val name = "Barfoo"
                    override val symbol = ""
                }
            )
        )
        val result = currencyFilter.filter("foo")
        assertEquals(0, result.size)
    }
}