package com.example.testzhuyin.model

import com.amap.api.maps.model.LatLng

class CircleGeoFenceDataModel(
    override var type: Int = 0,
    val radius:Float,
    val point:LatLng
):GeoFenceDataModel()

