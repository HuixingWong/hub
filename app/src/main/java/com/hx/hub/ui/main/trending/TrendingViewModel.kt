package com.hx.hub.ui.main.trending

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.hx.architecture.core.base.viewmodel.BaseViewModel
import com.hx.architecture.core.ext.setNext
import com.hx.hub.entity.request.TrendingRequestData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class TrendingViewModel @ViewModelInject constructor(private val repository: TrendingRepository)
    : BaseViewModel() {

    private val _viewStateLiveData: MutableLiveData<TrendingViewState> = MutableLiveData(TrendingViewState.initial())
    val viewStateLiveData: LiveData<TrendingViewState> = _viewStateLiveData

    private val trendingRequestData = MutableLiveData<TrendingRequestData>(TrendingRequestData())

    fun search(key: String? = null, time: String? = null) {
        trendingRequestData.postValue(TrendingRequestData().apply {
            language = key ?: language
            since = time ?: since
        })
    }

    @ExperimentalCoroutinesApi
    val trendingList = Transformations.switchMap(trendingRequestData) {
        repository.fetchTrending(it).flowOn(Dispatchers.Default).onStart {
            _viewStateLiveData.setNext {
                TrendingViewState(true, null)
            }
        }.catch {
            _viewStateLiveData.setNext { _->
                TrendingViewState(false, it)
            }
        }.onCompletion {
            _viewStateLiveData.setNext {
                TrendingViewState(false, null)
            }
        }.asLiveData()
    }

}