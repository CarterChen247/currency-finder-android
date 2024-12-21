package com.carterchen247.currencyfinder.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carterchen247.currencyfinder.R
import com.carterchen247.currencyfinder.ui.theme.CurrencyfinderTheme

@Composable
fun CurrencyListScreen(
    userInput: String,
    onUserInputChange: (String) -> Unit,
    onSearchCancel: () -> Unit,
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
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
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
        )
    }
}