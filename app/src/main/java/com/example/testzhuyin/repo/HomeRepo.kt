package com.example.testzhuyin.repo

import android.util.Log
import com.melody.map.gd_compose.poperties.MapProperties
import com.melody.map.gd_compose.poperties.MapUiSettings
import com.example.testzhuyin.model.EthnicityDataModel
import com.example.testzhuyin.utils.SDKUtils
import com.example.testzhuyin.utils.GsonUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object HomeRepo {
    private const val TAG = "MainRepo"

    fun initMapUISettings(): MapUiSettings {
        return MapUiSettings(
            isZoomGesturesEnabled = true,
            isScrollGesturesEnabled = true,
            isZoomEnabled = false
        )
    }

    fun initMapProperties(): MapProperties {
        return MapProperties(
            isShowMapLabels = true,
            maxZoomPreference = 15F,
            minZoomPreference = 4F
        )
    }

    /**
     * 初始化民族数据
     */
    fun initEthnicityDataList(): List<EthnicityDataModel> {
        var dataJSON = ""
        try {
            val assetManager = SDKUtils.getApplicationContext().assets
            val inputStream = InputStreamReader(assetManager.open("ethnicity_data.json"))
            dataJSON = inputStream.buffered().use(BufferedReader::readText)
        } catch (e: IOException) {
            Log.e(TAG, "ReadAssertFileError", e)
        }
        return GsonUtils.json2List(dataJSON, Array<EthnicityDataModel>::class.java)
    }
}