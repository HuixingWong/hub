package com.hx.hub.db

import androidx.room.*
import com.hx.hub.entity.TrendingRepo

@Dao
interface TrendingReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repos: List<TrendingRepo>)

    @Query("SELECT * FROM trending_repos")
    suspend fun queryRepos(): List<TrendingRepo>

    @Query("DELETE FROM trending_repos")
    suspend fun deleteAllTrendings()


}