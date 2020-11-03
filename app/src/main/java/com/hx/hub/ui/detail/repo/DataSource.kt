package com.hx.hub.ui.detail.repo

import com.hx.architecture.core.base.repository.IRemoteDataSource
import com.hx.hub.base.Results
import com.hx.hub.entity.repodetail.RepoDetail
import com.hx.hub.http.service.ServiceManager
import com.hx.hub.utils.processApiResponse
import javax.inject.Inject


class RemoteRepoDetailDataSource @Inject constructor(private val searchManager: ServiceManager): IRemoteDataSource{

    suspend fun queryRepoDetail(owner: String, repo: String): Results<RepoDetail>{
        return processApiResponse {
            searchManager.userService.queryRepoDetail(owner, repo)
        }
    }

}