package com.norm.mynewsapplication.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norm.mynewsapplication.domain.usecases.app_entry.AppEntryUseCases
import com.norm.mynewsapplication.presentation.onboarding.components.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
) : ViewModel() {
    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}