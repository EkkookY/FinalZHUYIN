package com.example.testzhuyin.ui

import android.Manifest
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import ccom.example.testzhuyin.utils.handlerGPSLauncher
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.melody.map.gd_compose.position.CameraPositionState
import com.example.testzhuyin.contract.DetailContract
import com.example.testzhuyin.dialog.ShowOpenGPSDialog
import com.example.testzhuyin.repo.DetailRepo
import com.example.testzhuyin.utils.SDKUtils
import com.example.testzhuyin.utils.SystemBroadcastReceiver
import com.example.testzhuyin.utils.requestMultiplePermission
import com.example.testzhuyin.viewmodel.DetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


@Composable
@OptIn(ExperimentalPermissionsApi::class)
internal fun InitDetailPageEffect(
    viewModel: DetailViewModel,
    uiState: DetailContract.State,
    cameraPositionState: CameraPositionState,
    latitude: Double,
    longitude: Double
) {
    val openGpsLauncher = handlerGPSLauncher(viewModel::checkGpsStatus)
    val reqGPSPermission = requestMultiplePermission(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ),
        onGrantAllPermission = viewModel::handleGrantLocationPermission,
        onNoGrantPermission = viewModel::handleNoGrantLocationPermission
    )

    LaunchedEffect(Unit) {
        snapshotFlow { reqGPSPermission.allPermissionsGranted }.collect {
            // 从app应用权限开关页面，打开权限，需要再检查一下GPS开关
            viewModel.checkGpsStatus()
        }
    }

    LaunchedEffect(uiState.isOpenGps, reqGPSPermission.allPermissionsGranted) {
        if (uiState.isOpenGps == true) {
            if (!reqGPSPermission.allPermissionsGranted) {
                reqGPSPermission.launchMultiplePermissionRequest()
            } else {
                // 初始化创建地理围栏
                viewModel.initGeoFence(latitude, longitude)
            }
        }
    }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.onEach {
            when(it) {
                is DetailContract.Effect.Toast -> {
                    if(it.msg?.isNotBlank() == true) {
                        Toast.makeText(SDKUtils.getApplicationContext(),it.msg,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }.collect()
    }

    if (uiState.isShowOpenGPSDialog) {
        ShowOpenGPSDialog(
            onDismiss = viewModel::hideOpenGPSDialog,
            onPositiveClick = {
                viewModel.openSysGPSPage(openGpsLauncher)
            }
        )
    }

    SystemBroadcastReceiver(
        systemActions = listOf(
            ConnectivityManager.CONNECTIVITY_ACTION,
            DetailRepo.GEOFENCE_BROADCAST_ACTION
        )
    ) { intent ->
        if (intent?.action.equals(DetailRepo.GEOFENCE_BROADCAST_ACTION)) {
            viewModel.handleGeoFenceReceiver(intent)
        }
    }
}