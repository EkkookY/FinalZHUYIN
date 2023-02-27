package com.example.testzhuyin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.model.LatLng
import com.example.testzhuyin.R
import com.melody.map.gd_compose.GDMap
import com.melody.map.gd_compose.overlay.Circle
import com.melody.map.gd_compose.overlay.Marker
import com.melody.map.gd_compose.overlay.Polygon
import com.melody.map.gd_compose.overlay.rememberMarkerState
import com.melody.map.gd_compose.position.rememberCameraPositionState
import com.example.testzhuyin.model.CircleGeoFenceDataModel
import com.example.testzhuyin.model.PolygonGeoFenceDataModel
import com.example.testzhuyin.ui.theme.black
import com.example.testzhuyin.ui.theme.green1plus
import com.example.testzhuyin.ui.theme.green2
import com.example.testzhuyin.ui.theme.green2plus
import com.example.testzhuyin.ui.theme.green4
import com.example.testzhuyin.ui.theme.whiteplus
import com.example.testzhuyin.viewmodel.DetailViewModel

@Composable
internal fun JourneyMapScreen(citylatitude: Double, citylongitude: Double, citytargetAssets: String) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(LatLng(citylatitude, citylongitude), 14F, 0F, 0F)
    }
    val viewModel: DetailViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    InitDetailPageEffect(viewModel, uiState, cameraPositionState, citylatitude, citylongitude)


    Box(modifier = Modifier
        .fillMaxSize()
        .background(green2)) {
        GDMap(
            modifier = Modifier.matchParentSize(),
            uiSettings = uiState.mapUiSettings,
            properties = uiState.mapProperties,
            locationSource = viewModel,
            cameraPositionState = cameraPositionState
        ) {

            uiState.geoFenceDrawList.onEach { data ->

                if (data.type == 1) {
                    Polygon(
                        points = (data as PolygonGeoFenceDataModel).pointList,
                        strokeWidth = 4F,
                        fillColor = green1plus,
                        strokeColor = green2plus
                    )
                } else {
                    Circle(
                        center = (data as CircleGeoFenceDataModel).point,
                        radius = data.radius.toDouble(),
                        strokeWidth = 4F,
                        fillColor = green1plus,
                        strokeColor = green2plus
                    )
                }
            }
            Marker(
                icon = BitmapDescriptorFactory.fromAsset(citytargetAssets),
                state = rememberMarkerState(position = LatLng(citylatitude,citylongitude))
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(18.dp, 28.dp, 0.dp, 0.dp)){
            Column (
                Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
                    .background(whiteplus)){
                Row() {
                    Text(text ="旅途", fontSize = 20.sp, color = green4 ,fontWeight = FontWeight.Bold)
                    var voiced by remember { mutableStateOf(true) }
                    Image(painter = if(voiced)painterResource(id = R.drawable.icon_voice_on)else painterResource(id = R.drawable.icon_voice_off), contentDescription =null, modifier = Modifier
                        .padding(6.dp, 2.dp, 16.dp, 0.dp)
                        .size(30.dp)
                        .clickable { voiced = !voiced })
                }

                Row {
                    Image(
                        painter = painterResource(id = R.drawable.icon_location),
                        contentDescription = "位置",
                        Modifier
                            .size(20.dp)
                    )
                    Text(text = "华南大学牲自治区", fontSize = 16.sp, color = green4)
                }
            }
        }
    }
}

