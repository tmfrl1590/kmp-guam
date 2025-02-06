package com.kmp.guam

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.kmp.presentation.TestColumn
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        TestColumn()
    }
}