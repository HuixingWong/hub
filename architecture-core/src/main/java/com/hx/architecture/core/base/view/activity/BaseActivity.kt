package com.hx.architecture.core.base.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hx.architecture.core.base.view.IView

abstract class BaseActivity : AppCompatActivity(), IView {

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }
}
