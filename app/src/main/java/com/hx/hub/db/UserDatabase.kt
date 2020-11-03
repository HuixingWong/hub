package com.hx.hub.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hx.hub.entity.ReceivedEvent
import com.hx.hub.entity.Repo
import com.hx.hub.entity.TrendingRepo

@Database(
        entities = [ReceivedEvent::class, Repo::class, TrendingRepo::class],
        version = 2
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userReceivedEventDao(): UserReceivedEventDao

    abstract fun userReposDao(): UserReposDao

    abstract fun trendingDao(): TrendingReposDao
}