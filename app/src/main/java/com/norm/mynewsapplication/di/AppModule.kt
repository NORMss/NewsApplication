package com.norm.mynewsapplication.di

import android.app.Application
import com.norm.mynewsapplication.data.manager.LocalUserManagerImpl
import com.norm.mynewsapplication.data.remote.NewsApi
import com.norm.mynewsapplication.data.repository.NewsRepositoryImpl
import com.norm.mynewsapplication.domain.manager.LocalUserManager
import com.norm.mynewsapplication.domain.repository.NewsRepository
import com.norm.mynewsapplication.domain.usecases.app_entry.AppEntryUseCases
import com.norm.mynewsapplication.domain.usecases.app_entry.ReadAppEntry
import com.norm.mynewsapplication.domain.usecases.app_entry.SaveAppEntry
import com.norm.mynewsapplication.domain.usecases.news.GetNews
import com.norm.mynewsapplication.presentation.home.NewsUseCases
import com.norm.mynewsapplication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application,
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager,
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager),
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }

}