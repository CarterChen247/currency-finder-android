package com.carterchen247.currencyfinder.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carterchen247.currencyfinder.R
import com.carterchen247.currencyfinder.ui.model.CurrencyInfo
import com.carterchen247.currencyfinder.ui.theme.CurrencyfinderTheme

@Composable
fun CurrencyListScreen(
    userInput: String,
    onUserInputChange: (String) -> Unit,
    onSearchCancel: () -> Unit,
    currencyInfoList: List<CurrencyInfo>,
) {
    Scaffold(
        topBar = {
            SearchBar(
                userInput = userInput,
                onUserInputChange = { onUserInputChange(it) },
                onSearchCancel = { onSearchCancel() },
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    ActionButton("Clear") { /*TODO*/ }
                    ActionButton("Insert") { /*TODO*/ }
                    ActionButton("Crypto") { /*TODO*/ }
                    ActionButton("Fiat") { /*TODO*/ }
                    ActionButton("All") { /*TODO*/ }
                }
                CurrencyListView(currencyInfoList)
            }
        }
    }
}

@Composable
private fun CurrencyListView(currencyInfoList: List<CurrencyInfo>) {
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
                    CurrencyItem(currencyInfo)
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
                CurrencyInfo(
                    simpleCode = "C",
                    name = "Crypto.com Chain",
                    fullCode = "CRO",
                ),
                CurrencyInfo(
                    simpleCode = "C",
                    name = "Crypto.com Chain",
                    fullCode = "CRO",
                )
            )
        )
    }
}

@Preview(name = "CurrencyListView Empty State")
@Composable
fun CurrencyListViewPreviewEmptyState() {
    CurrencyfinderTheme {
        CurrencyListView(
            currencyInfoList = listOf()
        )
    }
}


@Composable
fun CurrencyItem(currencyInfo: CurrencyInfo) {
    Surface(modifier = Modifier.height(72.dp)) {
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
                    text = currencyInfo.simpleCode,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = currencyInfo.name,
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = currencyInfo.fullCode)
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
            currencyInfo = CurrencyInfo(
                simpleCode = "C",
                name = "Crypto.com Chain",
                fullCode = "CRO",
            )
        )
    }
}

@Composable
private fun ActionButton(
    label: String, onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(text = label)
    }
}

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
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = userInput,
                onValueChange = onUserInputChange,
                modifier = Modifier.weight(1f),
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.aspectRatio(1f),
                        onClick = { onSearchCancel() },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.img_close),
                            contentDescription = ""
                        )
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
            userInput = "123",
            onUserInputChange = {},
            onSearchCancel = {},
            currencyInfoList = listOf(
                CurrencyInfo(
                    simpleCode = "C",
                    name = "Crypto.com Chain",
                    fullCode = "CRO",
                )
            ),
        )
    }
}