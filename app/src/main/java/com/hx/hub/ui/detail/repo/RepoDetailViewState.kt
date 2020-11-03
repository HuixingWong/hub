package com.hx.hub.ui.detail.repo

class RepoDetailViewState (val isLoading: Boolean,
                           val throwable: Throwable?){
    companion object {

        fun initial(): RepoDetailViewState {
            return RepoDetailViewState(
                    isLoading = false,
                    throwable = null
            )
        }
    }
}