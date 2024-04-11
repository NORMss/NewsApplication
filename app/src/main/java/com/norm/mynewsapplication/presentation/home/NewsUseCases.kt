package com.norm.mynewsapplication.presentation.home

import com.norm.mynewsapplication.domain.usecases.news.GetNews

data class NewsUseCases(
    val getNews: GetNews,
)
