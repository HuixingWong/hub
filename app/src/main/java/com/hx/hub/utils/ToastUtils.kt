package com.hx.hub.utils

import com.hx.architecture.core.ext.toast
import com.hx.hub.base.BaseApplication

inline fun toast(value: () -> String): Unit =
        BaseApplication.INSTANCE.toast(value)