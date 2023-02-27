package ccom.example.testzhuyin.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.example.testzhuyin.utils.SDKUtils

fun <T:Any> ActivityResultLauncher<T>.safeLaunch(input:T?){
    val launchResult = kotlin.runCatching {
        launch(input)
    }
    if (launchResult.isFailure) {
        Log.e("AppUtils", "safeLaunch(T),Exception:${launchResult.exceptionOrNull()?.message}")
    }
}


fun openAppPermissionDetailsPage() {
    val packageName = SDKUtils.getApplicationContext().packageName
    try {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.parse("package:$packageName")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        SDKUtils.getApplicationContext().startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        try {
            // 往设置页面跳
            SDKUtils.getApplicationContext().startActivity(Intent(Settings.ACTION_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        } catch (ignore: ActivityNotFoundException) {
            // 有些手机跳系统设置也会崩溃
        }
    }
}