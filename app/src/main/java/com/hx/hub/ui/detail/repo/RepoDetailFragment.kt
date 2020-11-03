package com.hx.hub.ui.detail.repo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hx.architecture.core.ext.observe
import com.hx.hub.R
import com.hx.hub.entity.repodetail.RepoDetail
import com.hx.hub.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.repo_detail_fragment.*

@AndroidEntryPoint
class RepoDetailFragment : Fragment() {

    lateinit var rePoUrl: String

    private  val viewModel: RepoViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.repo_detail_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binds()
    }

    fun binds(){
        viewModel.url.postValue(rePoUrl)
        observe(viewModel.repoDetail, this::show)
        observe(viewModel.viewStateLiveData, this::onNewState)
    }

    private fun show(repoDetail: RepoDetail){
        Log.e("fuck", repoDetail.toString())
    }

    private fun onNewState(state: RepoDetailViewState) {
        if (state.throwable != null) {
            toast { "network failure." }
        }
        if (state.isLoading != mRefreshLayout.isRefreshing) {
            mRefreshLayout.isRefreshing = state.isLoading
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String): RepoDetailFragment {
            val repoDetailFragment = RepoDetailFragment()
            repoDetailFragment.rePoUrl = url
            return repoDetailFragment
        }
    }

}