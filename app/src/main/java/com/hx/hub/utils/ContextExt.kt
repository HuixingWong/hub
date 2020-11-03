package com.hx.hub.utils

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.hx.hub.ui.detail.repo.RepoDetailActivity
import com.hx.hub.ui.web.CommonWebViewActivity

fun Context.jumpBrowser(url: String) {
    val intent = Intent(this, CommonWebViewActivity::class.java).apply {
        putExtra("url", url)
    }
    if (url.contains("repo")){
        RepoDetailActivity.launch(this as FragmentActivity, url)
    }else{
        startActivity(intent)
    }
}