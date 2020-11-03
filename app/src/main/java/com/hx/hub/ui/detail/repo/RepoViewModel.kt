package com.hx.hub.ui.detail.repo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hx.architecture.core.base.viewmodel.BaseViewModel
import com.hx.architecture.core.ext.setNext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class RepoViewModel @ViewModelInject
constructor(reposDetailReposity: ReposDetailReposity) : BaseViewModel() {

    val url: MutableLiveData<String> = MutableLiveData()

    private val _viewStateLiveData: MutableLiveData<RepoDetailViewState> = MutableLiveData(RepoDetailViewState.initial())
    val viewStateLiveData: LiveData<RepoDetailViewState> = _viewStateLiveData

    @ExperimentalCoroutinesApi
    val repoDetail = Transformations.switchMap(url) {
        val split = it.split("/")
        reposDetailReposity.queryRepoDetail(split[split.size - 2],
                split[split.size - 1]).onStart {
            _viewStateLiveData.setNext {
                RepoDetailViewState(true, null)
            }
        }.onCompletion {
            _viewStateLiveData.setNext {
                RepoDetailViewState(false, null)
            }
        }.catch { err ->
            _viewStateLiveData.setNext {
                RepoDetailViewState(false, err)
            }
        }.asLiveData()
    }

}