package com.yxb.server

import android.annotation.TargetApi
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log

class MainActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Server - MainActivity", "onCreate : my Pid is " + Process.myPid())
        val ss = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        Log.i("First ", "ss.appTasks " + ss.appTasks.map { it.taskInfo })
        Log.i("First ", "windowManager.defaultDisplay.refreshRate " + windowManager.defaultDisplay.refreshRate)
    }

}
