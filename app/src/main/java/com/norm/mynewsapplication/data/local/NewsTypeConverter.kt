package com.norm.mynewsapplication.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.norm.mynewsapplication.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
//        return Source(
//            id = source.substringBefore(","),
//            name = source.substringAfter(","),
//        )
        return source.split(",").let {
            Source(
                id = it[0],
                name = it[1],
            )
        }
    }
}