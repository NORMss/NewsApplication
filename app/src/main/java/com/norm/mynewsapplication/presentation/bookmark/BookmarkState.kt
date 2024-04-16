package com.norm.mynewsapplication.presentation.bookmark

import com.norm.mynewsapplication.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
