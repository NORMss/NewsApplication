package com.norm.mynewsapplication.domain.repository

import androidx.paging.PagingData
import com.norm.mynewsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}