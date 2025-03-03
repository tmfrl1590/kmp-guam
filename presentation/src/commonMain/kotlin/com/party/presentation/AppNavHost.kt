package com.party.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.party.core.Screens
import com.party.presentation.screens.home.HomeScreenRoute
import com.party.presentation.screens.login.LoginScreenRoute
import com.party.presentation.screens.profile.ProfileScreenRoute
import com.party.presentation.screens.search.SearchRoute
import com.party.presentation.screens.state.StateScreenRoute

const val ANIMATION_DURATION = 500

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    NavHost(
        navController = navController,
        startDestination = Screens.Login,
        modifier = Modifier
            .fillMaxSize(),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(ANIMATION_DURATION)
            )

        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
    ){
        composable<Screens.Login> {
            LoginScreenRoute(
                navController = navController,
                snackBarHostState = snackBarHostState
            )
        }
        composable<Screens.Home> {
            HomeScreenRoute(
                navController = navController
            )
        }
        composable<Screens.State> {
            StateScreenRoute(
                navController = navController
            )
        }
        composable<Screens.Profile> {
            ProfileScreenRoute(
                navController = navController
            )
        }
        composable<Screens.Search> {
            SearchRoute(
                navController = navController,
            )
        }
    }
}