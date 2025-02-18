package com.party.presentation.screens.login.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.party.core.presentation.T2
import com.party.core.presentation.scaffold.ScaffoldCenterBar

@Composable
fun LoginScaffoldArea() {
    ScaffoldCenterBar(
        title = {
            Text(
                text = "로그인",
                fontWeight = FontWeight.Bold,
                fontSize = T2
            )
        },
    )
}