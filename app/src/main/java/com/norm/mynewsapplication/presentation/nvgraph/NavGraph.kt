package com.norm.mynewsapplication.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.norm.mynewsapplication.presentation.onboarding.OnBoardingScreen
import com.norm.mynewsapplication.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route,
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = {
                        viewModel.onEvent(it)
                    }
                )
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route,
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route,
            ) {
                Text(
                    text = Route.NewsNavigatorScreen.route,
                )
            }
        }
    }
}