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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carterchen247.currencyfinder.R
import com.carterchen247.currencyfinder.ui.theme.CurrencyfinderTheme

@Composable
fun CurrencyListScreen() {
    val userQuery = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchBar(
                value = userQuery.value,
                onValueChange = { userQuery.value = it },
                onCloseClicked = { userQuery.value = "" }
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
    value: String,
    onValueChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
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
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.aspectRatio(1f),
                        onClick = { onCloseClicked() },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.img_close),
                            contentDescription = ""
                        )
                    }
                },
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
            value = "123",
            onValueChange = {},
            onCloseClicked = {},
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CurrencyListScreenPreview() {
    CurrencyfinderTheme {
        CurrencyListScreen()
    }
}