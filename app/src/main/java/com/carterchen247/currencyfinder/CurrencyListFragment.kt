package com.carterchen247.currencyfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.carterchen247.currencyfinder.ui.CurrencyListScreen
import com.carterchen247.currencyfinder.ui.CurrencyListViewModel
import com.carterchen247.currencyfinder.ui.model.FilterType
import com.carterchen247.currencyfinder.ui.model.UserAction
import com.carterchen247.currencyfinder.ui.theme.CurrencyfinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private val viewModel: CurrencyListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_currency_list, container, false).apply {
            val composeView = findViewById<ComposeView>(R.id.compose_view)
            composeView.setContent {
                CurrencyfinderTheme {
                    val uiState by viewModel.uiState.collectAsState()
                    val userInput by viewModel.userInput.collectAsState()
                    CurrencyListScreen(
                        uiState = uiState,
                        userInput = userInput,
                        onUserInputChange = { viewModel.onUserInputChange(it) },
                        onSearchCancel = { viewModel.onSearchCancel() },
                        onUserClick = { action ->
                            when (action) {
                                UserAction.CLEAR_DATA -> {
                                    viewModel.onUserClearData()
                                }

                                UserAction.INSERT_DATA -> {
                                    viewModel.onUserInsertData()
                                }

                                UserAction.SEARCH_CURRENCY_CRYPTO -> {
                                    viewModel.onFilterTypeChange(FilterType.CRYPTO)
                                }

                                UserAction.SEARCH_CURRENCY_FIAT -> {
                                    viewModel.onFilterTypeChange(FilterType.FIAT)
                                }

                                UserAction.SEARCH_CURRENCY_ALL -> {
                                    viewModel.onFilterTypeChange(FilterType.ALL)
                                }
                            }
                        },
                    )
                }
            }
        }
    }
}