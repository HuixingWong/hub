package com.hx.hub.ui.main.trending.dataSource

import androidx.room.withTransaction
import com.hx.architecture.core.base.repository.ILocalDataSource
import com.hx.hub.db.UserDatabase
import com.hx.hub.entity.TrendingRepo
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val db: UserDatabase) : ILocalDataSource{

    suspend fun fetchQueryTrendingFactory(): List<TrendingRepo>{
        return db.trendingDao().queryRepos()
    }

    suspend fun clearOldAndInsertNewData(newPage: List<TrendingRepo>){
        db.withTransaction {
            db.trendingDao().deleteAllTrendings()
            insertDataInternal(newPage)
        }
    }

    private suspend fun insertDataInternal(newPage: List<TrendingRepo>){
        db.trendingDao().insert(newPage)
    }

}