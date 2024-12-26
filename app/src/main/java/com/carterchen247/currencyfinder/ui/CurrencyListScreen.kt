package com.carterchen247.currencyfinder.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carterchen247.currencyfinder.R
import com.carterchen247.currencyfinder.model.CurrencyData
import com.carterchen247.currencyfinder.model.CurrencyType
import com.carterchen247.currencyfinder.ui.model.CurrencyInfo
import com.carterchen247.currencyfinder.ui.model.FilterType
import com.carterchen247.currencyfinder.ui.model.UserAction
import com.carterchen247.currencyfinder.ui.model.toCurrencyInfo
import com.carterchen247.currencyfinder.ui.theme.CurrencyfinderTheme

@Composable
fun CurrencyListScreen(
    uiState: CurrencyListUiState,
    userInput: String,
    onUserInputChange: (String) -> Unit,
    onSearchCancel: () -> Unit,
    onUserClick: (UserAction) -> Unit,
    onClickItem: (CurrencyInfo) -> Unit,
) {
    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(8.dp)) {
                SearchBar(
                    userInput = userInput,
                    onUserInputChange = { onUserInputChange(it) },
                    onSearchCancel = { onSearchCancel() },
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                // make sure the content is not covered by the keyboard
                modifier = Modifier.imePadding()
            ) {
                ButtonRow(onUserClick, uiState.filterTypes, uiState.selectedFilterType)
                Spacer(modifier = Modifier.height(8.dp))
                Box {
                    CurrencyListView(uiState.currencyInfoList) { currencyInfo ->
                        onClickItem(currencyInfo)
                    }
                    LoadingLayout(uiState.isLoading)
                }
            }
        }
    }
}

@Composable
private fun ButtonRow(
    onUserClick: (UserAction) -> Unit,
    filterTypes: List<FilterType>,
    selectedFilterType: FilterType,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ActionButton("Clear") { onUserClick(UserAction.CLEAR_DATA) }
        ActionButton("Insert") { onUserClick(UserAction.INSERT_DATA) }

        filterTypes.forEach { filterType ->
            SearchFilter(filterType.label, selected = selectedFilterType == filterType) {
                val userAction = when (filterType) {
                    FilterType.ALL -> UserAction.SEARCH_CURRENCY_ALL
                    FilterType.CRYPTO -> UserAction.SEARCH_CURRENCY_CRYPTO
                    FilterType.FIAT -> UserAction.SEARCH_CURRENCY_FIAT
                }
                onUserClick(userAction)
            }
        }
    }
}

@Composable
private fun CurrencyListView(
    currencyInfoList: List<CurrencyInfo>,
    onClickItem: (CurrencyInfo) -> Unit = {},
) {
    if (currencyInfoList.isEmpty()) {
        // Empty state
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "No Results")
            }
        }
    } else {
        LazyColumn {
            currencyInfoList.map { currencyInfo ->
                item {
                    CurrencyItem(currencyInfo) {
                        onClickItem(currencyInfo)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CurrencyListViewPreviewNormalState() {
    CurrencyfinderTheme {
        CurrencyListView(
            currencyInfoList = listOf(
                previewCurrencyInfo,
                previewCurrencyInfo,
            )
        )
    }
}

@Preview(name = "CurrencyListView Empty State")
@Composable
fun CurrencyListViewPreviewEmptyState() {
    CurrencyfinderTheme {
        CurrencyListView(
            currencyInfoList = listOf(),
        )
    }
}


@Composable
fun CurrencyItem(currencyInfo: CurrencyInfo, onClick: () -> Unit = {}) {
    Surface(
        modifier = Modifier
            .height(72.dp)
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = currencyInfo.avatarCode,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = currencyInfo.name,
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = currencyInfo.displayCode)
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.img_chevron_right),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
            )
        }
    }
}

@Preview
@Composable
fun CurrencyItemPreview() {
    CurrencyfinderTheme {
        CurrencyItem(
            currencyInfo = previewCurrencyInfo,
        )
    }
}

@Composable
private fun ActionButton(
    label: String, onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
        shape = MaterialTheme.shapes.small,
    ) {
        Text(text = label)
    }
}


@Composable
private fun SearchFilter(
    label: String,
    selected: Boolean = false,
    onSelected: () -> Unit,
) {
    FilterChip(
        selected = selected,
        label = { Text(text = label) },
        onClick = { onSelected() },
        shape = MaterialTheme.shapes.small
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    userInput: String,
    onUserInputChange: (String) -> Unit,
    onSearchCancel: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = userInput,
                onValueChange = onUserInputChange,
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                    )
                },
                trailingIcon = {
                    if (userInput.isNotEmpty()) {
                        IconButton(
                            onClick = { onSearchCancel() },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "",
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    CurrencyfinderTheme {
        SearchBar(
            userInput = "123",
            onUserInputChange = {},
            onSearchCancel = {},
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CurrencyListScreenPreview() {
    CurrencyfinderTheme {
        CurrencyListScreen(
            uiState = CurrencyListUiState().copy(
                currencyInfoList = listOf(
                    previewCurrencyInfo,
                    previewCurrencyInfo,
                )
            ),
            userInput = "123",
            onUserInputChange = {},
            onSearchCancel = {},
            onUserClick = {},
            onClickItem = {},
        )
    }
}

@Composable
fun LoadingLayout(loading: Boolean) {
    if (loading) {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter,
            ) {
                Row(
                    modifier = Modifier.height(72.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Loading...")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingLayoutPreview() {
    CurrencyfinderTheme {
        LoadingLayout(true)
    }
}

private val previewCurrencyInfo = CurrencyData(
    id = "CRO",
    name = "Crypto.com Chain",
    symbol = "CRO",
    type = CurrencyType.CRYPTO,
).toCurrencyInfo()