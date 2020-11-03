package com.hx.hub.ui.login

import com.hx.architecture.core.base.repository.BaseRepositoryBoth
import com.hx.architecture.core.base.repository.ILocalDataSource
import com.hx.architecture.core.base.repository.IRemoteDataSource
import com.hx.hub.base.Results
import com.hx.hub.db.UserDatabase
import com.hx.hub.entity.UserInfo
import com.hx.hub.http.service.ServiceManager
import com.hx.hub.http.service.bean.LoginRequestModel
import com.hx.hub.manager.UserManager
import com.hx.hub.repository.UserInfoRepository
import com.hx.hub.utils.processApiResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(
        remoteDataSource: LoginRemoteDataSource,
        localDataSource: LoginLocalDataSource
) : BaseRepositoryBoth<LoginRemoteDataSource, LoginLocalDataSource>(remoteDataSource, localDataSource) {

    suspend fun login(username: String, password: String): Results<UserInfo> {
        // 保存用户登录信息
        localDataSource.savePrefUser(username, password)
        val userInfo = remoteDataSource.login()

        // 如果登录失败，清除登录信息
        when (userInfo) {
            is Results.Failure -> localDataSource.clearPrefsUser()
            is Results.Success -> UserManager.INSTANCE = requireNotNull(userInfo.data)
        }
        return userInfo
    }

    fun fetchAutoLogin(): AutoLoginEvent {
        return localDataSource.fetchAutoLogin()
    }
}

class LoginRemoteDataSource @Inject constructor(
        private val serviceManager: ServiceManager
) : IRemoteDataSource {

    suspend fun login(): Results<UserInfo> {
        // 1.用户登录认证
        val userAccessTokenResult = processApiResponse {
            serviceManager.loginService.authorizations(LoginRequestModel.generate())
        }

        return when (userAccessTokenResult) {
            is Results.Success -> {
                // 2.获取用户详细信息
                processApiResponse { serviceManager.userService.fetchUserOwner() }
            }
            is Results.Failure -> userAccessTokenResult
        }
    }
}

class LoginLocalDataSource @Inject constructor(
        private val db: UserDatabase,
        private val userRepository: UserInfoRepository
) : ILocalDataSource {

    fun savePrefUser(username: String, password: String) {
        userRepository.username = username
        userRepository.password = password
    }

    fun clearPrefsUser() {
        userRepository.username = ""
        userRepository.password = ""
    }

    fun fetchAutoLogin(): AutoLoginEvent {
        val username = userRepository.username
        val password = userRepository.password
        val isAutoLogin = userRepository.isAutoLogin
        return when (username.isNotEmpty() && password.isNotEmpty() && isAutoLogin) {
            true -> AutoLoginEvent(true, username, password)
            false -> AutoLoginEvent(false, "", "")
        }
    }
}

data class AutoLoginEvent(
        val autoLogin: Boolean,
        val username: String,
        val password: String
)
