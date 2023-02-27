package com.example.testzhuyin.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testzhuyin.ui.theme.green2
import androidx.compose.ui.text.font.FontWeight
import com.example.testzhuyin.R
import com.example.testzhuyin.ui.components.AppTopBar
import com.example.testzhuyin.ui.theme.white

@Composable
fun SingleGroup(modifier: Modifier = Modifier, navController: NavController){


        Column(
            ) {
            AppTopBar(title = "", onLeftIconClickListener = { navController.popBackStack() })
            Column {
                Image(painter = painterResource(id = R.drawable.mbf7), contentDescription ="mbf", contentScale = ContentScale.Crop,modifier = Modifier
                    .size(420.dp,180.dp)
                )
                Image(painter = painterResource(id = R.drawable.mbf4), contentDescription ="mbf", contentScale = ContentScale.Crop,modifier = Modifier
                    .size(420.dp,50.dp)
                )
                Image(painter = painterResource(id = R.drawable.mbf2), contentDescription ="mbf", contentScale = ContentScale.Crop,modifier = Modifier
                    .size(300.dp, 200.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clickable { navController.navigate(route = "HuatiPage") }
                )

                CommunityContent()
//                    Surface(
//                        modifier
//                            .padding(start = 120.dp, top = 80.dp)
//                            .background(white)
//                            .clip(RoundedCornerShape(4.dp))) {
//                        Text(
//                            text = "加入圈子",
//                            fontSize = 16.sp,
//                            color = green2,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
            }

        }
    }
