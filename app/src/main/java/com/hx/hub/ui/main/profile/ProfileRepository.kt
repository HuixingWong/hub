package com.hx.hub.ui.main.profile

import com.hx.architecture.core.base.repository.BaseRepositoryRemote
import com.hx.architecture.core.base.repository.IRemoteDataSource
import com.hx.hub.http.service.ServiceManager
import javax.inject.Inject

interface IRemoteProfileDataSource : IRemoteDataSource

class ProfileRepository @Inject constructor(
        remoteDataSource: ProfileRemoteDataSource
) : BaseRepositoryRemote<IRemoteProfileDataSource>(remoteDataSource)

class ProfileRemoteDataSource @Inject constructor(
        val serviceManager: ServiceManager
) : IRemoteProfileDataSource
