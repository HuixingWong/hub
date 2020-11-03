package com.hx.hub.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.hx.hub.utils.fromJson
import com.hx.hub.utils.toJson

@Entity(tableName = "trending_repos")
@TypeConverters(TrendingReposConverter::class)
data class TrendingRepo(
        val author: String,
        val avatar: String,
        val builtBy: List<BuiltBy>,
        val currentPeriodStars: Int,
        val description: String,
        val forks: Int,
        val language: String,
        val languageColor: String,
        val name: String,
        val stars: Int,
        @PrimaryKey
        val url: String
)

class TrendingReposConverter {

    // BuiltBy
    @TypeConverter
    fun storeBuiltByToString(data: List<BuiltBy>): String = data.toJson()

    @TypeConverter
    fun storeStringToBuiltBy(value: String): List<BuiltBy> = value.fromJson()
}