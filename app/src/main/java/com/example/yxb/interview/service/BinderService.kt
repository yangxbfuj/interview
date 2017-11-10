package com.example.yxb.interview.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Process
import android.util.Log

class BinderService : Service() {

    override fun onBind(intent: Intent?): IBinder {
        return BinderS
    }


}

object BinderS : Binder() {
    fun doSomething() {
        Log.i("BinderS", "doSomething")
        Log.i("BinderS", "Process id is ${Process.myPid()} , Thread is ${Thread.currentThread().name}")
    }
}