package com.example.testzhuyin.ui.page

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testzhuyin.ui.components.SearchBar
import com.example.testzhuyin.viewmodel.CommunityViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.testzhuyin.data.DataProvide
import com.example.testzhuyin.model.UserContent
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.testzhuyin.R
import com.example.testzhuyin.ui.theme.*
import androidx.compose.material.Surface
import java.nio.file.WatchEvent


@Composable
fun CommunityPage(modifier: Modifier = Modifier, navController: NavController, viewModel: CommunityViewModel = CommunityViewModel()){
    Column(modifier = Modifier
        .padding(top = 10.dp)
        .fillMaxWidth()) {
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
                    text = "社区",
                    fontSize = 20.sp,
                    color = green2,
                    fontWeight = FontWeight.Bold
                )
            }
//                        var iconRes = painterResource(id = R.drawable.icon_voice_on)
//                        if( == ){
//                            iconRes = painterResource(id = R.drawable.icon_voice_off)
//                        }// 音量开关
            androidx.compose.foundation.Image(
                painter = painterResource(id = R.drawable.icon_chat),
                contentDescription = "聊天",
                Modifier
                    .padding(0.dp, 0.dp, 20.dp, 0.dp)
                    .size(24.dp)
                    .clickable { navController.navigate(route = "TestPage") }
            )
        }
        SearchBar(modifier)
        Firebar()
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.quanzi),
            contentDescription = "msg_read",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 12.dp, start = 10.dp,end=10.dp)
                .fillMaxWidth()
                .clickable { navController.navigate(route="GroupPage") }

        )
        CommunityNameBar()
    }

}
@Composable
fun CommunityContent(){
    val comcontents= remember { DataProvide.comcontentlist1}
    LazyColumn(contentPadding = PaddingValues(16.dp,8.dp)){
        items(
            items = comcontents,
            itemContent = { ContentListItem( comcontent =it ) {}
            }
        )
    }
}
@Composable
fun Firebar(){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(18.dp, 14.dp, 0.dp, 0.dp)
    ) {
        Column(
            Modifier
                .padding(start = 12.dp)
        ) {
            androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.ic_fire),
            contentDescription = "火",
                Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .size(24.dp)
        )
        }
        //赶时间这样写了
        Column(modifier = Modifier
            .padding(start = 15.dp, top = 3.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = green1)
            .width(45.dp)
        ) {
            Text(text = "白族",fontSize = 12.sp, color = green2, modifier = Modifier.padding(start = 12.dp) )
        }
        Column(modifier = Modifier
            .padding(start = 15.dp, top = 3.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = green1)
            .width(45.dp)

        ) {
            Text(text = "回族",fontSize = 12.sp, color = green2, modifier = Modifier.padding(start = 12.dp) )
        }
        Column(modifier = Modifier
            .padding(start = 15.dp, top = 3.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = green1)
            .width(45.dp)
        ) {
            Text(text = "苗族",fontSize = 12.sp, color = green2, modifier = Modifier.padding(start =12.dp) )
        }
    }
}
@Composable
fun ContentListItem(comcontent: UserContent, onButtonClick:()->Unit){
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onButtonClick.invoke() }) {
        Column(modifier = Modifier
            .padding(start = 16.dp)
            .align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(23.dp))
        ) {
            Image(painter = painterResource(id = comcontent.user), contentDescription ="user", contentScale = ContentScale.Crop,modifier = Modifier.size(45.dp,45.dp))
        }
        Column(modifier = Modifier
            .padding(start = 8.dp)
            .align(Alignment.CenterVertically)
        ) {
            Text(text = comcontent.name, fontSize = 20.sp)
            Text(text = comcontent.time, fontSize = 10.sp)
        }
        Column(modifier = Modifier
            .padding(start = 70.dp)
            .align(Alignment.CenterVertically)
            .clip(RoundedCornerShape(8.dp))
            .background(color = green1)
            .height(30.dp)
            .width(110.dp)
//            .padding(end = 10.dp)
        ) {
            Row(modifier = Modifier.padding(top = 2.dp)) {
                Image(painter = painterResource(id = R.drawable.icon_location), contentDescription ="location", contentScale = ContentScale.Crop,modifier = Modifier.size(20.dp,20.dp))
                Text(text = comcontent.Slocation, fontSize = 14.sp, color = green4)
                Text(text = comcontent.Nationality, fontSize = 14.sp, color = green4,modifier = Modifier.padding(start =20.dp ))
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
    Row(Modifier.clickable { onButtonClick.invoke() }) {
        Column(modifier = Modifier
            .padding(start = 40.dp, top = 20.dp)
            .fillMaxWidth()
            .align(Alignment.CenterVertically)
        ) {
            Text(text = comcontent.text, fontSize = 16.sp,modifier = Modifier.padding( top = 3.dp ) )
            Spacer(modifier = Modifier.height(18.dp))
            Image(painter = painterResource(id = comcontent.drawable), contentDescription ="content", contentScale = ContentScale.Crop,modifier = Modifier
                .size(280.dp, 180.dp)
                .clip(
                    RoundedCornerShape(20.dp)
                ))
//                 RouteNavigationUtil.navigationTo(navHostController, destinationName = RouteKey.ARTICLE.toString())
        }
    }
    scdzpl()

}


//@Composable
//fun CommunityContentCard(
//    @DrawableRes drawable: Int,
//    @StringRes text: Int,
//
//    onButtonClick:()->Unit,
//    modifier: Modifier = Modifier
//) {
//    Card(modifier = Modifier
//        .padding(16.dp, 16.dp)
//        .fillMaxWidth(1f),
//        border = BorderStroke(1.dp, green2),
//        elevation = 2.dp,
//        backgroundColor = white1,
//        shape = RoundedCornerShape(corner = CornerSize(16.dp))
//    ){
//        Text(text = stringResource(id = text), fontSize = 40.sp, modifier = Modifier.padding(start = 70.dp, bottom = 20.dp))
//        Row(modifier = Modifier
//            .fillMaxWidth(1f)
//            .clickable { onButtonClick.invoke() },
//            horizontalArrangement = Arrangement.Center
//
//        ) {
//            Image(painter = painterResource(id = (drawable)), contentDescription =null,contentScale = ContentScale.Crop, modifier = Modifier.size(180.dp,100.dp) )
//
//        }
//    }
//}
@Composable
fun CommunityNameBar(){
    val names= listOf("推荐","附近")
    var selected by remember {
        mutableStateOf(0)
    }
    LazyRow(
        Modifier
            .padding(0.dp, 8.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)), contentPadding = PaddingValues(90.dp,0.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        itemsIndexed(names){index , name ->
            Column(
                Modifier
                    .padding(40.dp, 4.dp)
                    .width(IntrinsicSize.Max)
                    .clickable { selected = index }) {
                Text(text = name, fontSize = 15.sp, color = if (index == selected) black else grey1)

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
    val comcontent1= remember { DataProvide.comcontentlist1}
    val comcontent2= remember { DataProvide.comcontentlist2}
    LazyColumn(contentPadding = PaddingValues(16.dp,8.dp)){
        if(selected==0)
            items(
                items = comcontent1,
                itemContent = { ContentListItem( comcontent =it ) {}
                }
            )
        else
            items(
                items = comcontent2,
                itemContent = { ContentListItem( comcontent =it ) {}
                }
            )
    }
}
@Composable
fun scdzpl(){
    var dzed by remember { mutableStateOf(false) }
    var sced by remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .padding(start = 44.dp, top = 10.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.Center
        
    ) {
        Image(painter = if(sced)painterResource(id = R.drawable.icon_star_on)else painterResource(id = R.drawable.icon_star_off), contentDescription =null, modifier = Modifier
            .size(22.dp, 22.dp)
            .clickable { sced = !sced })
        Spacer(modifier = Modifier.width(180.dp))
        Image(painter = if(dzed)painterResource(id = R.drawable.icon_like_on)else painterResource(id = R.drawable.icon_like_off), contentDescription =null, modifier = Modifier
            .size(22.dp, 22.dp)
            .clickable { dzed = !dzed })
        Spacer(modifier = Modifier.width(30.dp))
        Image(painter = painterResource(id = R.drawable.icon_chat), contentDescription =null, modifier = Modifier
            .size(21.dp, 21.dp)
            .clickable { })

    }
}