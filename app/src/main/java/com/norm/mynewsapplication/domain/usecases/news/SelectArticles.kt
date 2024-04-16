package com.norm.mynewsapplication.domain.usecases.news

import com.norm.mynewsapplication.data.local.NewsDao
import com.norm.mynewsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao,
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}