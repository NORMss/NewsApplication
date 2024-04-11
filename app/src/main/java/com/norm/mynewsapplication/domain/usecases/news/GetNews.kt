package com.norm.mynewsapplication.domain.usecases.news

import androidx.paging.PagingData
import com.norm.mynewsapplication.domain.model.Article
import com.norm.mynewsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(source: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = source)
    }
}