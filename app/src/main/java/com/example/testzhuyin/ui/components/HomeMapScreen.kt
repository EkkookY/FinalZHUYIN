package com.example.testzhuyin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.model.LatLng
import com.example.testzhuyin.R
import com.example.testzhuyin.ui.page.HomeUIbutton
import com.melody.map.gd_compose.GDMap
import com.melody.map.gd_compose.overlay.Marker
import com.melody.map.gd_compose.overlay.rememberMarkerState
import com.melody.map.gd_compose.position.rememberCameraPositionState
import com.example.testzhuyin.contract.HomeContract
import com.example.testzhuyin.ui.components.SearchBar
import com.example.testzhuyin.ui.theme.green2
import com.example.testzhuyin.ui.theme.white
import com.example.testzhuyin.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.nio.file.WatchEvent


@Composable
internal fun HomeMapScreen(openDetailPage: (LatLng, String)-> Unit) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(LatLng(34.3227, 108.5525), 3.5F, 0F, 0F)
    }
    val viewModel: HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.effect){
        viewModel.effect.onEach {
            when(it) {
                is HomeContract.Effect.Navigation.OpenDetailPage -> {
                    openDetailPage.invoke(it.latLng,it.iconName)
                }
            }
        }.collect()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GDMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiState.uiSettings,
            properties = uiState.mapProperties
        ) {
            uiState.ethnicityDataList.forEach {
                Marker(
                    icon = BitmapDescriptorFactory.fromAsset(it.assets_name),
                    tag = it.assets_name,
                    state = rememberMarkerState(position = LatLng(it.latitude,it.longitude)),
                    onClick = { marker ->
                        viewModel.openDetailPage(marker.position, marker.`object` as String)
                        true
                    }
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(18.dp, 28.dp, 0.dp, 0.dp)

        ) {
            Column(
                Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "民族百科地图",
                    fontSize = 20.sp,
                    color = green2,
                    fontWeight = FontWeight.Bold
                )
                SearchBar(modifier = Modifier.padding(top = 40.dp))

            }
//                        var iconRes = painterResource(id = R.drawable.icon_voice_on)
//                        if( == ){
//                            iconRes = painterResource(id = R.drawable.icon_voice_off)
//                        }// 音量开关
            var voiced by remember { mutableStateOf(true) }
            Image(painter = if(voiced) painterResource(id = R.drawable.icon_voice_on) else painterResource(id = R.drawable.icon_voice_off), contentDescription =null, modifier = Modifier
                .padding(0.dp, 0.dp, 20.dp, 0.dp)
                .size(30.dp)
                .clickable { voiced = !voiced })
        }
        Spacer(modifier = Modifier.height(300.dp))
        HomeUIbutton(modifier =Modifier.padding(top=400.dp).background(white))
    }
}