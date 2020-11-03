package com.hx.hub.ui.main.trending

import com.hx.architecture.core.base.repository.BaseRepositoryBoth
import com.hx.hub.base.Results
import com.hx.hub.entity.TrendingRepo
import com.hx.hub.entity.request.TrendingRequestData
import com.hx.hub.ui.main.trending.dataSource.LocalDataSource
import com.hx.hub.ui.main.trending.dataSource.RemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingRepository @Inject constructor
(remote: RemoteDataSource, local: LocalDataSource):
        BaseRepositoryBoth<RemoteDataSource, LocalDataSource>(remote, local){

    fun fetchTrending(trendingRequestData: TrendingRequestData) = flow {
        val trendings = remoteDataSource.queryTrendings(trendingRequestData)
        if (trendings is Results.Success){
            emit(trendings.data)
            clearAndInsertNewData(trendings.data)
        }else{
            emit(fetchLocalTrending())
        }
    }

    suspend fun  fetchLocalTrending(): List<TrendingRepo>{
        return localDataSource.fetchQueryTrendingFactory()
    }

    suspend fun clearAndInsertNewData(items: List<TrendingRepo>){
        localDataSource.clearOldAndInsertNewData(items)
    }

}