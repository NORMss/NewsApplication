package com.norm.mynewsapplication.data.remote

import com.norm.mynewsapplication.data.remote.dto.NewsResponse
import com.norm.mynewsapplication.domain.model.Source
import com.norm.mynewsapplication.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY,
    ): NewsResponse
}