package com.norm.mynewsapplication.domain.usecases.news

import com.norm.mynewsapplication.domain.model.Article
import com.norm.mynewsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectedArticles()
    }
}