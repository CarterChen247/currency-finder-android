package com.carterchen247.currencyfinder.util

import com.carterchen247.currencyfinder.model.SearchableCurrency

class CurrencyFilter {

    private val matchingRules = listOf(
        NameStartWithRule(),
        NamePartialMatchRule(),
        SymbolStartWithRule(),
    )

    fun filter(
        input: String,
        searchableCurrencyList: List<SearchableCurrency>
    ): List<SearchableCurrency> {
        return searchableCurrencyList.filter { searchableCurrency ->
            matchingRules.any { it.isMatched(input, searchableCurrency) }
        }
    }
}

interface MatchingRule {
    fun isMatched(input: String, searchableCurrency: SearchableCurrency): Boolean
}

class NameStartWithRule : MatchingRule {
    override fun isMatched(input: String, searchableCurrency: SearchableCurrency): Boolean {
        return searchableCurrency.name.startsWith(input, ignoreCase = true)
    }
}

class NamePartialMatchRule : MatchingRule {
    override fun isMatched(input: String, searchableCurrency: SearchableCurrency): Boolean {
        return searchableCurrency.name.contains(" $input", ignoreCase = true)
    }
}

class SymbolStartWithRule : MatchingRule {
    override fun isMatched(input: String, searchableCurrency: SearchableCurrency): Boolean {
        return searchableCurrency.symbol.startsWith(input, ignoreCase = true)
    }
}