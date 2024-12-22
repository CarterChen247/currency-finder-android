package com.carterchen247.currencyfinder.util

import com.carterchen247.currencyfinder.model.SearchableCurrency

class CurrencyFilter(private val searchableCurrencyList: List<SearchableCurrency>) {

    private val ruleStartWith by lazy { StartWithRule() }
    private val rulePartialMatch by lazy { PartialMatchRule() }

    fun filter(input: String): List<SearchableCurrency> {
        return searchableCurrencyList.filter { searchableCurrency ->
            ruleStartWith.isMatched(input, searchableCurrency)
                    || rulePartialMatch.isMatched(input, searchableCurrency)
        }
    }
}

interface MatchingRule {
    fun isMatched(input: String, searchableCurrency: SearchableCurrency): Boolean
}

class StartWithRule : MatchingRule {
    override fun isMatched(input: String, searchableCurrency: SearchableCurrency): Boolean {
        return searchableCurrency.name.startsWith(input, ignoreCase = true)
    }
}

class PartialMatchRule : MatchingRule {
    override fun isMatched(input: String, searchableCurrency: SearchableCurrency): Boolean {
        return searchableCurrency.name.contains(" $input", ignoreCase = true)
    }
}