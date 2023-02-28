package com.example.testzhuyin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
import com.example.testzhuyin.ui.components.AppTopBar
import com.example.testzhuyin.ui.page.GridContent
import com.example.testzhuyin.ui.page.NameBar
import com.example.testzhuyin.ui.theme.black
import com.example.testzhuyin.ui.theme.green1
import com.example.testzhuyin.ui.theme.green1plus
import com.example.testzhuyin.ui.theme.green2
import com.example.testzhuyin.ui.theme.green2plus
import com.example.testzhuyin.ui.theme.grey1
import com.example.testzhuyin.ui.theme.white
import com.example.testzhuyin.ui.theme.whiteplus
import com.example.testzhuyin.viewmodel.DetailViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun DetailMapScreen(latitude: Double, longitude: Double, targetAssets: String,onBack:()->Unit) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(LatLng(latitude, longitude), 14F, 0F, 0F)
    }
    val viewModel: DetailViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    InitDetailPageEffect(viewModel, uiState, cameraPositionState, latitude, longitude)


    Box(modifier = Modifier
        .fillMaxSize()
        .background(green1)) {
        BottomSheetScaffold(


            sheetContent = {
                DetailNameBar()
                Box(){
                    Column(modifier = Modifier
                        .fillMaxWidth()
//                .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                        .background(
                            color = white,
//                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                    ){
                        Row(Modifier
                            .padding(top = 15.dp)) {
                            androidx.compose.foundation.Image(
                                painter = painterResource(id = R.drawable.icon_redd),
                                contentDescription = null,
                                Modifier
                                    .padding(start = 30.dp, top = 15.dp)
                                    .size(20.dp)
                            )
                            Column() {
                                Text(text = "人数", fontSize = 24.sp, color= black , modifier = Modifier.padding(start = 6.dp, top = 4.dp))
                                Text(text = "我是人数内容我是人数内容我是人数内容我是人数内容我是人数内容我是人数内容我是人数内容", fontSize = 12.sp, color= black, modifier = Modifier.padding(end = 40.dp) )
                                Row() {

                                }
                            }
                        }
                        Row(Modifier
                            .padding( top = 15.dp)) {
                            androidx.compose.foundation.Image(
                                painter = painterResource(id = R.drawable.icon_redd),
                                contentDescription = null,
                                Modifier
                                    .padding(start = 30.dp, top = 15.dp)
                                    .size(20.dp)
                            )
                            Column() {
                                Text(text = "特色", fontSize = 24.sp, color= black , modifier = Modifier.padding(start = 6.dp, top = 4.dp))
                                Text(text = "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容", fontSize = 12.sp, color= black, modifier = Modifier.padding(end = 40.dp) )
                                Row() {

                                }
                            }
                        }
                        Row(Modifier
                            .padding( top = 15.dp)) {
                            androidx.compose.foundation.Image(
                                painter = painterResource(id = R.drawable.icon_redd),
                                contentDescription = null,
                                Modifier
                                    .padding(start = 30.dp, top = 15.dp)
                                    .size(20.dp)
                            )
                            Column() {
                                Text(text = "文艺", fontSize = 24.sp, color= black , modifier = Modifier.padding(start = 6.dp, top = 4.dp))
                                Text(text = "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容", fontSize = 12.sp, color= black, modifier = Modifier.padding(end = 40.dp) )
                            }
                        }
                        Row() {
                            androidx.compose.foundation.Image(
                                painter = painterResource(id = R.drawable.image_nation_achang),
                                contentDescription = null,
                                Modifier
                                    .padding(start = 70.dp, top = 20.dp)
                                    .size(200.dp)
                            )
                            androidx.compose.foundation.Image(
                                painter = painterResource(id = R.drawable.image_ac_name),
                                contentDescription = null,
                                Modifier
                                    .padding(start = 20.dp, top = 20.dp)
                                    .size(200.dp)
                            )

                        }
                    }
                }
            },


            sheetPeekHeight = 270.dp,
            sheetBackgroundColor = whiteplus,
        ) { innerPadding ->
            AppTopBar(title = "民族详细页", onLeftIconClickListener = {onBack() })
            GDMap(
                modifier = Modifier
                    .matchParentSize()
                    .height(500.dp),
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
                    icon = BitmapDescriptorFactory.fromAsset(targetAssets),
                    state = rememberMarkerState(position = LatLng(latitude,longitude))
                )
            }
        }

    }
}
@Preview
@Composable
fun DetailNameBar(){
    val names= listOf("人口","特色","文艺")
    var selected by remember {
        mutableStateOf(0)
    }
    LazyRow(
        Modifier
            .padding(0.dp, 0.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(green1), contentPadding = PaddingValues(20.dp,0.dp)

    ) {
        itemsIndexed(names){index , name ->
            Column(
                Modifier
                    .padding(44.dp, 8.dp)
                    .width(IntrinsicSize.Max)
                    .clickable { selected = index }) {
                Text(text = name, fontSize = 18.sp, color = if (index == selected) green2 else black)
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
                    .height(4.dp)
                    .clip(
                        RoundedCornerShape(2.dp)
                    )
                    .background(if (index == selected) green2 else Color.Transparent))

            }
        }

    }

}

