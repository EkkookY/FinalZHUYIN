package com.example.testzhuyin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.testzhuyin.ui.theme.black
import com.example.testzhuyin.ui.theme.green2

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier
        .height(AppBarHeight)
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.primary),
    title:String,
    searchContent:@Composable (()->Unit) ?= null,
    leftIcon: ImageVector = Icons.Default.ArrowBack,
    onLeftIconClickListener:()->Unit,
    showArrowBack:Boolean = true,
    rightContent:@Composable (()->Unit) ?= null
){
    ConstraintLayout(modifier = modifier) {
        val (titleView,searchView,leftView,rightView) = createRefs()

        if (showArrowBack){
            Icon(imageVector = leftIcon,
                contentDescription = "返回",
                tint = green2,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onLeftIconClickListener.invoke()
                    }
                    .constrainAs(leftView) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start, 12.dp)
                    }
            )
        }

        if (searchContent != null){
            Row(modifier = Modifier.constrainAs(searchView){
                centerTo(parent)
            }){
                searchContent()
            }
        }else{
            Text(text = title,
                fontSize = 20.sp,
                color = green2,
                modifier = Modifier.constrainAs(titleView){
                    centerTo(parent)
                }
            )
        }

        if (rightContent != null){
            Row(modifier = Modifier.constrainAs(rightView){
                centerVerticallyTo(parent)
                end.linkTo(parent.end, margin = 12.dp)
            }) {
                rightContent()
            }
        }
    }
}

private val AppBarHeight = 60.dp
