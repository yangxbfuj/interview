package com.example.yxb.interview.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.yxb.interview.MainActivity
import com.example.yxb.interview.R


class ForegroundService : Service() {

    companion object {
        val TAG = ForegroundService::class.java.simpleName!!
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")
        val nb = NotificationCompat.Builder(applicationContext,"service")
        val nfIntent = Intent(this, MainActivity::class.java)
        nb.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_foreground)) // 设置大图标
                .setContentText("这是一个前台服务")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("前台服务")
                .setWhen(System.currentTimeMillis() + 5000) // 5秒后通知

        val notification = nb.build()
        startForeground(999, notification)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        throw UnsupportedOperationException("该Service不支持绑定")
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        stopForeground(true)
        super.onDestroy()
    }

}