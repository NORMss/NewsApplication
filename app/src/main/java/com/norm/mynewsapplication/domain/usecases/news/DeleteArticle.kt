package com.norm.mynewsapplication.domain.usecases.news

import com.norm.mynewsapplication.data.local.NewsDao
import com.norm.mynewsapplication.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao,
) {
    suspend operator fun invoke(article: Article) {
        newsDao.delete(article)
    }
}