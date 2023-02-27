package com.example.testzhuyin.utils

import android.content.Context
import com.amap.api.maps.MapsInitializer

object GDMapUtils {

    fun updateMapViewPrivacy(context: Context) {
        MapsInitializer.updatePrivacyShow(context.applicationContext, true, true)
        MapsInitializer.updatePrivacyAgree(context.applicationContext, true)
    }
}