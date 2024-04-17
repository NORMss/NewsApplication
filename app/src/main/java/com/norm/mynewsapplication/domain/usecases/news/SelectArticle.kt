package com.norm.mynewsapplication.domain.usecases.news

import com.norm.mynewsapplication.domain.model.Article
import com.norm.mynewsapplication.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}