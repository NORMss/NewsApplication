package com.norm.mynewsapplication.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    val news = newsUseCases.getNews(
        source = listOf("google-news-ru", "lenta", "rbc")
    ).cachedIn(viewModelScope)
}