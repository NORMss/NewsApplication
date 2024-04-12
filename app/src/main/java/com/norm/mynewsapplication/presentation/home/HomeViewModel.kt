package com.norm.mynewsapplication.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.norm.mynewsapplication.presentation.common.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsUseCases: NewsUseCases
) : ViewModel() {
    val news = newsUseCases.getNews(
        source = listOf("google-news-ru", "lenta", "rbc")
    ).cachedIn(viewModelScope)
}