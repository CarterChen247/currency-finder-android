package com.carterchen247.currencyfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.carterchen247.currencyfinder.ui.CurrencyListScreen
import com.carterchen247.currencyfinder.ui.CurrencyListViewModel
import com.carterchen247.currencyfinder.ui.model.FilterType
import com.carterchen247.currencyfinder.ui.model.UserAction
import com.carterchen247.currencyfinder.ui.theme.CurrencyfinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : ComponentActivity() {

    private val viewModel: CurrencyListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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