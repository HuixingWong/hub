package com.hx.hub.ui.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.request.RequestOptions
import com.hx.architecture.core.base.view.fragment.BaseFragment
import com.hx.architecture.core.ext.observe
import com.hx.architecture.core.image.GlideApp
import com.hx.hub.R
import com.hx.hub.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    private val mViewModel: ProfileViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binds()
    }

    private fun binds() {
        observe(mViewModel.viewStateLiveData, this::onNewState)

        mBtnEdit.setOnClickListener { toast { "coming soon..." } }
    }

    private fun onNewState(state: ProfileViewState) {
        if (state.throwable != null) {
            // handle throwable.
        }

        if (state.userInfo != null) {
            GlideApp.with(requireContext())
                    .load(state.userInfo.avatarUrl)
                    .apply(RequestOptions().circleCrop())
                    .into(mIvAvatar)

            mTvNickname.text = "${state.userInfo.name}(${state.userInfo.login})"
            desc_tv.text = state.userInfo.blog
            join_time.text = state.userInfo.createdAt
        }
    }
}
