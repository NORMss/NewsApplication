package com.norm.mynewsapplication.di

import android.app.Application
import com.norm.mynewsapplication.data.manager.LocalUserManagerImpl
import com.norm.mynewsapplication.domain.manager.LocalUserManager
import com.norm.mynewsapplication.domain.usecases.AppEntryUseCases
import com.norm.mynewsapplication.domain.usecases.ReadAppEntry
import com.norm.mynewsapplication.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}