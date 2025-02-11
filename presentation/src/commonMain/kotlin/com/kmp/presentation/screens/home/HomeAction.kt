package com.kmp.presentation.screens.home

sealed interface HomeAction {
    data class OnTabClick(val tabText: String) : HomeAction
}