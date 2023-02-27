package com.example.testzhuyin.ui.page

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.testzhuyin.R
import com.example.testzhuyin.data.DataProvide
import com.example.testzhuyin.data.Note
import com.example.testzhuyin.ui.theme.*
import com.example.testzhuyin.viewmodel.MineViewModel
import com.melody.map.gd_compose.overlay.Circle

@Composable
@OptIn(ExperimentalCoilApi::class, ExperimentalMaterialApi::class)
fun MinePage(modifier: Modifier=Modifier, navController: NavController, viewModel: MineViewModel =MineViewModel()) {

    val scope = rememberCoroutineScope ()
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        sheetContent = {
            Column(modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxWidth()
                .weight(1f)
                .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(
                    color = green1,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
            ){
                Column(Modifier.padding(top = 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = R.drawable.mbf1), contentDescription ="mbf", contentScale = ContentScale.Crop,modifier = Modifier.size(400.dp,150.dp))
                    Column(modifier = Modifier
                        .background(
                            color = white,
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )) {
                        MineNameBar()
                        CommunityContent()
                    }
                }
            }
        },
        scaffoldState = scaffoldState,

        floatingActionButtonPosition = FabPosition.End,
        sheetPeekHeight = 280.dp,
        sheetBackgroundColor = white,
        drawerBackgroundColor = black,


    ) { innerPadding ->
        Image(painter = painterResource(id = R.drawable.unity_people), contentDescription ="user", contentScale = ContentScale.Crop,modifier = Modifier.size(700.dp,700.dp))

    }
}

@Composable
fun GoalContent() {
        Card(modifier = Modifier
            .padding(8.dp, 8.dp)
            .fillMaxWidth(),
            elevation = 2.dp,
            backgroundColor = white,
            shape = RoundedCornerShape(corner = CornerSize(16.dp)
            )

        ){
            Row(Modifier.clickable {  }) {
                Image(
                    painter = painterResource(id = R.drawable.ac),
                    contentDescription = null,
                    Modifier
                        .padding(20.dp)
                        .size(30.dp, 40.dp)
                )
                Column(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                ) {
//                 RouteNavigationUtil.navigationTo(navHostController, destinationName = RouteKey.ARTICLE.toString())
                }

            }

        }
    }
//    Card(modifier = Modifier.height(100.dp).width(360.dp).padding(20.dp).clip(
//        RoundedCornerShape(16.dp))) {
//        Column() {
//            Text(text = "成就", fontSize = 20.sp)
//            Row() {
//                Image(
//                    painter = painterResource(id = R.drawable.ac),
//                    contentDescription = null, Modifier.padding(20.dp)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.ac),
//                    contentDescription = null, Modifier.padding(20.dp)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.ac),
//                    contentDescription = null, Modifier.padding(20.dp)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.ac),
//                    contentDescription = null, Modifier.padding(20.dp)
//                )
//            }
//        }
//        Box(modifier = Modifier.height(20.dp))
//    }


    @Composable
    fun MyGridContent() {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(DataProvide.ClothData) { item ->
                MYunityCloth(drawable = item.drawable, onButtonClick = {/*todo*/ })

            }
        }
        Box(modifier = Modifier.height(20.dp))
    }

    @Composable
    fun MYunityCloth(
        @DrawableRes drawable: Int,
        onButtonClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp, 16.dp),
            elevation = 2.dp,
            backgroundColor = white1,
            shape = RoundedCornerShape(
                corner = CornerSize(16.dp)
            )
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = (drawable)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(20.dp, 20.dp)
            )

        }
    }

    @Composable
    fun MineNameBar() {
        val names = listOf("动态", "收藏", "收藏")
        var selected by remember {
            mutableStateOf(0)
        }
        LazyRow(
            Modifier
                .padding(0.dp, 8.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
            contentPadding = PaddingValues(12.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            itemsIndexed(names) { index, name ->
                Column(
                    Modifier
                        .padding(70.dp, 4.dp)
                        .width(IntrinsicSize.Max)
                        .clickable { selected = index }) {
                    Text(
                        text = name,
                        fontSize = 15.sp,
                        color = if (index == selected) black else grey1
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp)
                            .height(4.dp)
                            .clip(
                                RoundedCornerShape(2.dp)
                            )
                            .background(if (index == selected) green2 else Color.Transparent)
                    )

                }
            }

        }
    }

