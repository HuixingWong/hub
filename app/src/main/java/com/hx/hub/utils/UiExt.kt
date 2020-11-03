package com.hx.hub.utils

import android.content.Context
import android.content.res.Configuration

fun Context.isDarkTheme(): Boolean {
    val flag = this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return flag == Configuration.UI_MODE_NIGHT_YES
}