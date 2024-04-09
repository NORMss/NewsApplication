package com.norm.mynewsapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.norm.mynewsapplication.domain.usecases.AppEntryUseCases
import com.norm.mynewsapplication.presentation.onboarding.OnBoardingScreen
import com.norm.mynewsapplication.presentation.onboarding.OnBoardingViewModel
import com.norm.mynewsapplication.ui.theme.MyNewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var useCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        lifecycleScope.launch {
            useCases.readAppEntry().collect {
                Log.d("MyLog", it.toString())
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyNewsApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(
                        event = {
                            viewModel.onEvent(it)
                        }
                    )
                }
            }
        }
    }
}