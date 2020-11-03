package com.hx.hub.ui.main.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hx.architecture.core.base.viewmodel.BaseViewModel

class ProfileViewModel @ViewModelInject constructor(
        private val repo: ProfileRepository
) : BaseViewModel() {

    private val _viewStateLiveData: MutableLiveData<ProfileViewState> = MutableLiveData(ProfileViewState.initial())
    val viewStateLiveData: LiveData<ProfileViewState> = _viewStateLiveData
}
