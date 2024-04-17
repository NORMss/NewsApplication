package com.norm.mynewsapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.norm.mynewsapplication.domain.model.Article

@Database(
    entities = [Article::class], version = 2
)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newDao: NewsDao
}