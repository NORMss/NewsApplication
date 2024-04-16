package com.norm.mynewsapplication.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.norm.mynewsapplication.presentation.bookmark.BookmarkScreen
import com.norm.mynewsapplication.presentation.bookmark.BookmarkViewModel
import com.norm.mynewsapplication.presentation.home.HomeScreen
import com.norm.mynewsapplication.presentation.home.HomeViewModel
import com.norm.mynewsapplication.presentation.onboarding.OnBoardingScreen
import com.norm.mynewsapplication.presentation.onboarding.OnBoardingViewModel
import com.norm.mynewsapplication.presentation.search.SearchScreen
import com.norm.mynewsapplication.presentation.search.SearchViewModel

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
//        navigation(
//            route = Route.NewsNavigation.route,
//            startDestination = Route.NewsNavigatorScreen.route,
//        ) {
//            composable(
//                route = Route.NewsNavigatorScreen.route,
//            ) {
//                val viewModel: HomeViewModel = hiltViewModel()
//                val articles = viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(
//                    articles = articles,
//                    navigate = {}
//                )
//            }
//        }

//        navigation(
//            route = Route.NewsNavigation.route,
//            startDestination = Route.NewsNavigatorScreen.route,
//        ) {
//            composable(
//                route = Route.NewsNavigatorScreen.route,
//            ) {
//                val viewModel: SearchViewModel = hiltViewModel()
//                SearchScreen(
//                    state = viewModel.state.value,
//                    event = viewModel::onEvent,
//                    navigate = { }
//                )
//            }
//        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route,
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route,
            ) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(
                    state = viewModel.state.value,
                    navigate = { Route.DetailScreen.route },
                )
            }
        }
    }
}