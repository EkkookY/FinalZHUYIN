package com.example.testzhuyin.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testzhuyin.R
import com.example.testzhuyin.data.Note
import com.example.testzhuyin.ui.components.AppTopBar

@Composable
fun ArticlePage(navController: NavController){
    Column() {
        AppTopBar(title = "大理生皮生肉", onLeftIconClickListener = { navController.popBackStack() })
        Image(painter = painterResource(id = R.drawable.mbf8), contentDescription ="mbf", contentScale = ContentScale.Crop,modifier = Modifier
            .size(420.dp,720.dp)
        )
    }

}