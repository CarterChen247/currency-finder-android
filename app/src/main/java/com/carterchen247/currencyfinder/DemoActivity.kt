package com.carterchen247.currencyfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.carterchen247.currencyfinder.ui.CurrencyListScreen
import com.carterchen247.currencyfinder.ui.theme.CurrencyfinderTheme

class DemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyfinderTheme {
                CurrencyListScreen()
            }
        }
    }
}