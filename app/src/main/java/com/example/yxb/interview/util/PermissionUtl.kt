package com.example.yxb.interview

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log

fun Activity.checkPermission(context: Activity, requestCode: Int, handler: HandlePermissionResult, vararg permissions: String) {
    if (Build.VERSION.SDK_INT >= 23) {
        val a = permissions.filter { context.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
                .toTypedArray()
        if (a.isNotEmpty())
            context.requestPermissions(a, requestCode)
        else
            handler.doTask()
    }
}

fun Activity.handlePermissions(permissions: Array<out String>, grantResults: IntArray, handler: HandlePermissionResult) {
    Log.i("PermissionUtil", "permission size is ${permissions.size}")
    Log.i("PermissionUtil", "grantResults size is ${grantResults.size}")
    if (grantResults.none { it == PackageManager.PERMISSION_DENIED }) {
        handler.doTask()
    } else {
        handler.doFail()
    }
}

interface HandlePermissionResult {
    fun doTask()
    fun doFail()
}
