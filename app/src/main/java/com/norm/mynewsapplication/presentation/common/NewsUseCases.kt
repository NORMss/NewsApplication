package com.norm.mynewsapplication.presentation.common

import com.norm.mynewsapplication.domain.usecases.news.GetNews

data class NewsUseCases(
    val getNews: GetNews,
)
