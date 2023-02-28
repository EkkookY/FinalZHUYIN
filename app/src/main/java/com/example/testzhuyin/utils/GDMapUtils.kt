package com.example.testzhuyin.utils

import android.content.Context
import com.amap.api.maps.MapsInitializer

object GDMapUtils {

/**
 * 调用SDK任何接口前先调用更新隐私合规updatePrivacyShow、updatePrivacyAgree两个接口（高德地图开发文档2021.11.23）
 * startup初始化
 */
    fun updateMapViewPrivacy(context: Context) {
        MapsInitializer.updatePrivacyShow(context.applicationContext, true, true)
        MapsInitializer.updatePrivacyAgree(context.applicationContext, true)
    }
}