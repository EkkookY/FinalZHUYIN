package com.example.testzhuyin.initializer

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.example.testzhuyin.utils.GDMapUtils
import com.example.testzhuyin.utils.SDKUtils

class AppDataInitStartup : Initializer<Boolean> {

    override fun create(context: Context): Boolean {
        SDKUtils.init(context as Application)
        GDMapUtils.updateMapViewPrivacy(context.applicationContext)
        return true
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}