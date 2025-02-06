package com.kmp.guam

import androidx.compose.ui.window.ComposeUIViewController
import com.kmp.guam.di.initKoin
import com.kmp.guam.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }