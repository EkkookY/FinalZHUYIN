package com.example.testzhuyin.viewmodel

import android.content.Intent
import android.media.Ringtone
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.example.testzhuyin.utils.openAppPermissionDetailsPage
import com.example.testzhuyin.utils.safeLaunch
import com.amap.api.fence.GeoFenceClient
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.LocationSource
import com.amap.api.maps.LocationSource.OnLocationChangedListener
import com.example.testzhuyin.base.BaseViewModel
import com.example.testzhuyin.contract.DetailContract
import com.example.testzhuyin.repo.DetailRepo
import com.example.testzhuyin.utils.SDKUtils
import kotlinx.coroutines.Dispatchers


/**
 * 首页中民族跳转的详细页viewmodel层
 * 继承basevm的状态，方便统一
 *
 * */
class DetailViewModel: BaseViewModel<DetailContract.Event,DetailContract.State,DetailContract.Effect>(), LocationSource, AMapLocationListener {

    private val geoFenceClient = GeoFenceClient(SDKUtils.getApplicationContext())
    private var mLocationClient: AMapLocationClient? = null
    private var mLocationOption: AMapLocationClientOption? = null
    private var mListener: OnLocationChangedListener? = null
    private var  mRingtone: Ringtone?= null

    override fun createInitialState(): DetailContract.State {
        return DetailContract.State(
            mapProperties = DetailRepo.initMapProperties(),
            mapUiSettings = DetailRepo.initMapUiSettings(),
            isInitGeoFence = false,
            isShowOpenGPSDialog = false,
            grantLocationPermission = false,
            isOpenGps = null,
            geoFenceDrawList = mutableListOf())
    }

    override fun handleEvents(event: DetailContract.Event) {
        when(event) {
            is DetailContract.Event.ShowOpenGPSDialog -> {
                setState { copy(isShowOpenGPSDialog = true) }
            }
            is DetailContract.Event.HideOpenGPSDialog -> {
                setState { copy(isShowOpenGPSDialog = false) }
            }
            is DetailContract.Event.HandleGeoFenceReceiver -> {
                DetailRepo.handleGeoFenceReceiver(event.intent) {
                    if(it) {
                        asyncLaunch {
                            DetailRepo.repeatAction(3,1000) {
                                mRingtone?.play()
                            }}
                    }
                }
            }}
    }


    init {
        DetailRepo.initRingtone {
            mRingtone = it
        }
    }

    /**
     * 初始化地理围栏
     */
    fun initGeoFence(latitude: Double, longitude: Double) = asyncLaunch(Dispatchers.IO) {
        if(currentState.isInitGeoFence) return@asyncLaunch
        setState { copy(isInitGeoFence = true) }
        startMapLocation()
        val result = kotlin.runCatching {
            DetailRepo.initGeoFence(geoFenceClient, latitude, longitude, 1000F)
        }
        if(result.isSuccess) {
            Log.d("TAG",">>>>>>创建成功，size:"+result.getOrNull()?.size)
            setState { copy(geoFenceDrawList = result.getOrNull()?: mutableListOf()) }
        } else {
            Log.d("TAG",">>>>>>创建失败！！！！")
            setEffect {
                DetailContract.Effect.Toast("地理围栏创建失败，请退出页面重试！")
            }
        }
    }

    /**
     * 检查系统GPS开关是否打开
     */
    fun checkGpsStatus() = asyncLaunch(Dispatchers.IO) {
        val isOpenGps = DetailRepo.checkGPSIsOpen()
        setState { copy(isOpenGps = isOpenGps) }
        if(!isOpenGps) {
            setEvent(DetailContract.Event.ShowOpenGPSDialog)
        } else {
            hideOpenGPSDialog()
        }
    }

    /**
     * 隐藏GPS授权Dialog
     */
    fun hideOpenGPSDialog() {
        setEvent(DetailContract.Event.HideOpenGPSDialog)
    }

    /**
     * 手机开了GPS，app没有授予权限
     */
    fun handleNoGrantLocationPermission() {
        setState { copy(grantLocationPermission = false) }
        setEvent(DetailContract.Event.ShowOpenGPSDialog)
    }

    /**
     * 处理定位权限
     */
    fun handleGrantLocationPermission() {
        setState { copy(grantLocationPermission = true) }
        checkGpsStatus()
    }

    /**
     * 没有主动授权，需要跳转到GPS权限授权系统页面
     */
    fun openSysGPSPage(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        if(DetailRepo.checkGPSIsOpen()) {
            // 已打开系统GPS，APP还没授权，跳权限页面
            openAppPermissionDetailsPage()
        } else {
            // 打开系统GPS开关页面
            launcher.safeLaunch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

    /**
     * 处理地理围栏广播接收到的数据
     */
    fun handleGeoFenceReceiver(intent: Intent?) {
        setEvent(DetailContract.Event.HandleGeoFenceReceiver(intent))
    }

    /**
     * 展示当前用户的位置
     */
    private fun startMapLocation() {
        DetailRepo.initAMapLocationClient(mLocationClient,this) { client, option->
            mLocationClient = client
            mLocationOption = option
        }
    }

    override fun onLocationChanged(amapLocation: AMapLocation?) {
        DetailRepo.handleLocationChange(amapLocation) { aMapLocation, msg ->
            if(null != aMapLocation) {
                mListener?.onLocationChanged(aMapLocation)
            } else {
                setEffect { DetailContract.Effect.Toast(msg) }
            }
        }
    }

    override fun activate(listener: OnLocationChangedListener?) {
        mListener = listener
        if(DetailRepo.checkGPSIsOpen() && currentState.grantLocationPermission) {
            startMapLocation()
        }
    }

    override fun deactivate() {
        mListener = null
        mLocationClient?.stopLocation()
        mLocationClient?.onDestroy()
        mLocationClient = null
    }

    override fun onCleared() {
        kotlin.runCatching {
            mRingtone?.stop()
            mRingtone = null
        }
        deactivate()
        geoFenceClient.setGeoFenceListener(null)

        geoFenceClient.removeGeoFence()
        super.onCleared()
    }

}