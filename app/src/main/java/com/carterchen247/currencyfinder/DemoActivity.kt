package com.carterchen247.currencyfinder

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_demo)
    }
}