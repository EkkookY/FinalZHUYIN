package com.example.testzhuyin.repo

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.location.LocationManager
import android.media.AudioAttributes
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import com.amap.api.fence.GeoFence
import com.amap.api.fence.GeoFenceClient
import com.amap.api.location.*
import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MyLocationStyle
import com.example.testzhuyin.R
import com.melody.map.gd_compose.poperties.MapProperties
import com.melody.map.gd_compose.poperties.MapUiSettings

import com.example.testzhuyin.model.CircleGeoFenceDataModel
import com.example.testzhuyin.model.GeoFenceDataModel
import com.example.testzhuyin.model.PolygonGeoFenceDataModel
import com.example.testzhuyin.utils.SDKUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine

object DetailRepo {
    const val TAG = "DetailRepo"
    const val GEOFENCE_BROADCAST_ACTION = "com.melody.geofence.location.broadcast"

    fun checkGPSIsOpen(): Boolean {
        val locationManager: LocationManager? = SDKUtils.getApplicationContext()
            .getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        return locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER)?: false
    }

    fun initMapProperties() : MapProperties {
        val iconBitmap = BitmapFactory.decodeResource(SDKUtils.getApplicationContext().resources, R.drawable.ic_map_location_self)
        val locationIcon = BitmapDescriptorFactory.fromBitmap(iconBitmap)
        return MapProperties(
            isMyLocationEnabled = true,
            myLocationStyle = MyLocationStyle().apply {
                myLocationIcon(locationIcon)
                myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER)
                strokeColor(Color.TRANSPARENT)
                radiusFillColor(Color.TRANSPARENT)
            }
        )
    }

    fun initMapUiSettings(): MapUiSettings {
        return MapUiSettings(
            // 高德地图右上角：显示【定位按钮】
            myLocationButtonEnabled = true,
            isZoomEnabled = true,
            isZoomGesturesEnabled = true,
            isScrollGesturesEnabled = true,
        )
    }

    /**
     * 创建一个地理围栏
     */
    suspend fun initGeoFence(geoFenceClient: GeoFenceClient, latitude: Double, longitude: Double, radius: Float): MutableList<GeoFenceDataModel> {
        return suspendCancellableCoroutine { coroutine ->
            geoFenceClient.setActivateAction(
                GeoFenceClient.GEOFENCE_IN
                        or GeoFenceClient.GEOFENCE_OUT
                        or GeoFenceClient.GEOFENCE_STAYED
            )
            val centerPoint = DPoint()
            centerPoint.latitude = latitude
            centerPoint.longitude = longitude
            geoFenceClient.addGeoFence(centerPoint, radius, "100")
            geoFenceClient.createPendingIntent(GEOFENCE_BROADCAST_ACTION)
            geoFenceClient.setGeoFenceListener { geoFenceList, errorCode, _ ->
                if (errorCode == GeoFence.ADDGEOFENCE_SUCCESS) {
                    Log.d(TAG,">>>>>>添加围栏成功")
                    val customEntitys: MutableMap<String,Any> = mutableMapOf()
                    val geoFenceDataList: MutableList<GeoFenceDataModel> = mutableListOf()
                    for (fence in geoFenceList) {
                        if (!customEntitys.containsKey(fence.fenceId)) {
                            if(fence.type == GeoFence.TYPE_ROUND || fence.type == GeoFence.TYPE_AMAPPOI) {
                                geoFenceDataList.add(
                                    CircleGeoFenceDataModel(
                                        radius = fence.radius,
                                        point = LatLng(
                                            fence.center.latitude,
                                            fence.center.longitude
                                        )
                                    )
                                )
                                customEntitys[fence.fenceId] = geoFenceDataList
                            } else if(fence.type == GeoFence.TYPE_POLYGON || fence.type == GeoFence.TYPE_DISTRICT) {
                                val latLngList : MutableList<LatLng> = mutableListOf()
                                fence.pointList.forEach { subList ->
                                    subList.forEach { dPoint ->
                                        latLngList.add(LatLng(dPoint.latitude,dPoint.longitude))
                                    }
                                }
                                geoFenceDataList.add(PolygonGeoFenceDataModel(pointList = latLngList))
                                customEntitys[fence.fenceId] = geoFenceDataList
                            }
                        }
                    }
                    coroutine.resumeWith(Result.success(geoFenceDataList))
                } else {
                    Log.d(TAG,">>>>>>添加围栏失败")
                    coroutine.resumeWith(Result.failure(NullPointerException()))
                }
            }
        }
    }

    inline fun handleGeoFenceReceiver(intent: Intent?, onStatus: (Boolean)->Unit) {
        val bundle = intent?.extras
        val status = bundle?.getInt(GeoFence.BUNDLE_KEY_FENCESTATUS)
        val customId = bundle?.getString(GeoFence.BUNDLE_KEY_CUSTOMID)
        val fenceId = bundle?.getString(GeoFence.BUNDLE_KEY_FENCEID)
        val code = bundle?.getInt(GeoFence.BUNDLE_KEY_LOCERRORCODE)
        when (status) {
            GeoFence.STATUS_LOCFAIL -> {
                Log.e(TAG, "定位失败$code")
            }
            GeoFence.STATUS_IN -> {
                Log.e(TAG, "进入围栏$fenceId")
            }
            GeoFence.STATUS_OUT -> {
                Log.e(TAG, "离开围栏$fenceId")
            }
            GeoFence.STATUS_STAYED -> {
                Log.e(TAG, "停留在围栏内$fenceId")
            }
            else -> {}
        }
        onStatus.invoke(status == GeoFence.STATUS_IN || status == GeoFence.STATUS_STAYED)
    }

    /**
     * 初始化LocationClient并启动定位蓝点定位
     */
    inline fun initAMapLocationClient(
        locationClient: AMapLocationClient?,
        listener: AMapLocationListener,
        block: (AMapLocationClient, AMapLocationClientOption) -> Unit
    ) {
        if(null == locationClient) {
            val newLocationClient = AMapLocationClient(SDKUtils.getApplicationContext())
            val locationClientOption = AMapLocationClientOption()
            newLocationClient.setLocationListener(listener)
            locationClientOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
            newLocationClient.setLocationOption(locationClientOption)
            block.invoke(newLocationClient.apply { startLocation() }, locationClientOption)
        }
    }

    inline fun handleLocationChange(amapLocation: AMapLocation?, block: (AMapLocation?, String?) -> Unit) {
        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
                block.invoke(amapLocation, null)
            } else {
                block.invoke(null, "定位失败," + amapLocation.errorCode + ": " + amapLocation.errorInfo)
            }
        }
    }

    fun initRingtone(block: (Ringtone) -> Unit) {
        val sb = StringBuilder()
        sb.append("android.resource://")
        sb.append(SDKUtils.getApplicationContext().packageName)
        sb.append("/")
        sb.append(R.raw.yinxiangyuan)
        val ringtone: Ringtone = RingtoneManager.getRingtone(SDKUtils.getApplicationContext(), Uri.parse(sb.toString()))
        ringtone.audioAttributes =
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).build()
        block.invoke(ringtone)
    }

    suspend fun repeatAction(count: Int, timeMills: Long, block:()-> Unit) {
        repeat(count){
            delay(timeMills)
            block.invoke()
        }
    }
}