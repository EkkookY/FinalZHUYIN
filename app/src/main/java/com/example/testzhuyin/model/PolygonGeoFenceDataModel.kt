package com.example.testzhuyin.model

import com.amap.api.maps.model.LatLng

class PolygonGeoFenceDataModel(
    override var type: Int = 1,
    val pointList:List<LatLng>
):GeoFenceDataModel()