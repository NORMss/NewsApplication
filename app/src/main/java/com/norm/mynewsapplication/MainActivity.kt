package com.norm.mynewsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.ViewCompat
import com.norm.mynewsapplication.presentation.nvgraph.NavGraph
import com.norm.mynewsapplication.ui.theme.MyNewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition(
                condition = { viewModel.splashCondition }
            )
        }
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyNewsApplicationTheme {
                Scaffold { padding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = padding.calculateTopPadding(),
                                bottom = padding.calculateBottomPadding()
                            )
                    ) {
                        val startDestination = viewModel.startDestination
                        NavGraph(
                            startDestination = startDestination,
                        )
                    }
                }
            }
        }
    }
}