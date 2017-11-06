package com.yxb.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import android.util.Log

class AIDLService : Service() {

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    private val mBinder = object : IMyAidlInterface.Stub() {
        override fun doSomething() {
            Log.i("AIDLService", "doSomething  pid is  " + Process.myPid())
        }

        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
            Log.i("AIDLService", "basicTypes  pid is  " + Process.myPid())
        }
    }
}