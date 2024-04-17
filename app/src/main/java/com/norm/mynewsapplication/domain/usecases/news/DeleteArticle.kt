package com.norm.mynewsapplication.domain.usecases.news

import com.norm.mynewsapplication.domain.model.Article
import com.norm.mynewsapplication.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}