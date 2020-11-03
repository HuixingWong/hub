package com.hx.hub.ui.web

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import com.hx.hub.R
import com.hx.hub.utils.isDarkTheme
import kotlinx.android.synthetic.main.activity_common_web_view.*

class CommonWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_web_view)
        intent.extras?.getString("url")?.let {
            webview.loadUrl(it)
        }
        if (isDarkTheme()){
            webview.setBackgroundColor(ContextCompat.getColor(this,android.R.color.transparent));
            webview.setBackgroundResource(android.R.color.black)
        }
        webview.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                mLoading.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                mLoading.visibility = View.GONE
            }

        }
    }
}