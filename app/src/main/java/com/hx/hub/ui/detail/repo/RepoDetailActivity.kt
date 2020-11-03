package com.hx.hub.ui.detail.repo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.hx.hub.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repo_detail_activity)
        if (savedInstanceState == null) {
            val url = intent.getStringExtra("url")
            initFragment(url)
        }
    }

    private fun initFragment(url: String){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, RepoDetailFragment.newInstance(url))
                .commitNow()
    }

    companion object {
        private const val TAG = "SearchFragment"

        fun launch(activity: FragmentActivity, url: String) = activity.apply {
            val intent = Intent(this, RepoDetailActivity::class.java).apply {
                putExtra("url", url)
            }
            startActivity(intent)
        }
    }
}