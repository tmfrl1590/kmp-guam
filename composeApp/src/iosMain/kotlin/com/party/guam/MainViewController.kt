package com.party.guam

import androidx.compose.ui.window.ComposeUIViewController
import com.party.guam.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }