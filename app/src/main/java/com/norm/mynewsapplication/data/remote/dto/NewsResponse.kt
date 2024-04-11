package com.norm.mynewsapplication.data.remote.dto

import com.norm.mynewsapplication.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)