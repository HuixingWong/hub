package com.hx.hub.ui.detail.repo

import com.hx.architecture.core.base.repository.BaseRepositoryRemote
import com.hx.hub.base.Results
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReposDetailReposity @Inject constructor
(remote: RemoteRepoDetailDataSource):
        BaseRepositoryRemote<RemoteRepoDetailDataSource>(remote) {



    fun queryRepoDetail(owner: String, repo: String) = flow {
        val repoDetail = remoteDataSource.queryRepoDetail(owner, repo)
        if (repoDetail is Results.Success){
            emit(repoDetail.data)
        }else{
//            emit()
        }
    }

}