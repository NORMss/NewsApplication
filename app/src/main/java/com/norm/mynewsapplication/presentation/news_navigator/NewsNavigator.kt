package com.norm.mynewsapplication.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.norm.mynewsapplication.R
import com.norm.mynewsapplication.domain.model.Article
import com.norm.mynewsapplication.presentation.bookmark.BookmarkScreen
import com.norm.mynewsapplication.presentation.bookmark.BookmarkViewModel
import com.norm.mynewsapplication.presentation.details.DetailViewModel
import com.norm.mynewsapplication.presentation.details.DetailsEvent
import com.norm.mynewsapplication.presentation.details.DetailsScreen
import com.norm.mynewsapplication.presentation.home.HomeScreen
import com.norm.mynewsapplication.presentation.home.HomeViewModel
import com.norm.mynewsapplication.presentation.news_navigator.components.BottomNavigationItem
import com.norm.mynewsapplication.presentation.news_navigator.components.NewsBottomNavigation
import com.norm.mynewsapplication.presentation.nvgraph.Route
import com.norm.mynewsapplication.presentation.search.SearchScreen
import com.norm.mynewsapplication.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val textNavigationItems = listOf(
        stringResource(R.string.home),
        stringResource(R.string.search),
        stringResource(R.string.bookmark),

        )
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(
                icon = Icons.Default.Home,
                text = textNavigationItems[0],
            ),
            BottomNavigationItem(
                icon = Icons.Default.Search,
                text = textNavigationItems[1],
            ),
            BottomNavigationItem(
                icon = Icons.Default.Star,
                text = textNavigationItems[2],
            )
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by remember {
        mutableStateOf(0)
    }

    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.SearchScreen.route ||
                backstackState?.destination?.route == Route.BookmarkScreen.route

    }

    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route,
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route,
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route,
                            )
                        }
                    },
                )
            } else {
                return@Scaffold
            }
        }
    ) { padding ->
        val bottomPadding = padding.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding)
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route,
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article,
                        )
                    }
                )
            }
            composable(
                route = Route.SearchScreen.route,
            ) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article,
                        )
                    },
                )
            }
            composable(
                route = Route.DetailScreen.route
            ) {
                val viewModel: DetailViewModel = hiltViewModel()

                if (viewModel.sideEffect != null) {
                    Toast.makeText(
                        LocalContext.current,
                        viewModel.sideEffect,
                        Toast.LENGTH_SHORT,
                    ).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
            }
            composable(
                route = Route.BookmarkScreen.route
            ) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val sate = viewModel.state.value
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                BookmarkScreen(
                    state = sate,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article,
                        )
                    }
                )
            }
        }
    }
}

private fun navigateToTab(
    navController: NavController,
    route: String,
) {
    //logic for receiving an article in DetailScreen
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}


//logic for transferring an article to DetailScreen
private fun navigateToDetails(
    navController: NavController,
    article: Article,
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailScreen.route
    )
}