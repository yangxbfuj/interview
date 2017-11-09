package com.example.yxb.interview

import android.Manifest
import android.app.ActivityManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.example.yxb.interview.animation.AnimationActivity
import com.example.yxb.interview.scrollview.ScrollerViewActivity
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.BitmapFactory
import android.os.*
import android.support.v4.app.NotificationCompat
import android.view.LayoutInflater
import android.widget.RemoteViews
import android.widget.SeekBar
import com.example.yxb.interview.service.BinderS
import com.example.yxb.interview.service.BinderService
import com.example.yxb.interview.service.ForegroundService
import com.yxb.server.IMyAidlInterface
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val requestCode = 123131

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        remoteView = RemoteViews(packageName, R.layout.notification_layout)
        btnActivityLifeCycle.setOnClickListener {
            val intent = Intent(this, RotationLifeCycleActivity::class.java)
            startActivity(intent)
        }
        btnService.setOnClickListener {
            val intent = Intent(this, ServiceActivity::class.java)
            startActivity(intent)
        }
        btnHandler.setOnClickListener {
            val intent = Intent(this, HandlerActivity::class.java)
            startActivity(intent)
        }
        srcollView.setOnClickListener {
            val intent = Intent(this, ScrollerViewActivity::class.java)
            startActivity(intent)
        }
        btnAnimation.setOnClickListener {
            val intent = Intent(this, AnimationActivity::class.java)
            startActivity(intent)
        }
        btnSecond.setOnClickListener {
            val intent = Intent()
            intent.action = "1234567890yxb"
            Log.i("First ", "Pid is " + Process.myPid())
            val ss = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            Log.i("First ", "ss.appTasks " + ss.appTasks.map { it.taskInfo })
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        btnAIDL.setOnClickListener {
            val intent = Intent("yxb_aidl")
            intent.`package` = "com.yxb.server"
            var myservice: IMyAidlInterface?
            bindService(intent, object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    myservice = IMyAidlInterface.Stub.asInterface(service)
                    Log.i("First ", "onServiceConnected Pid is " + Process.myPid())
                    myservice?.doSomething()
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    myservice = null
                }

            }, Context.BIND_AUTO_CREATE)
        }
        btnBinderService.setOnClickListener {
            val intent = Intent(this, BinderService::class.java)
            bindService(intent, object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    (service as? BinderS)?.doSomething()
                }

                override fun onServiceDisconnected(name: ComponentName?) {

                }

            }, Context.BIND_AUTO_CREATE)
        }

        btnRequestPermission.setOnClickListener {
            checkPermission(this, requestCode, contactPermissionHandler, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
        }


        // 创建一个前台服务
        btnForegroundService.setOnClickListener {
            val intent = Intent(this, ForegroundService::class.java)
            startService(intent)
        }

        // 创建一个通知
        btnNotification.setOnClickListener {
            val nb = NotificationCompat.Builder(applicationContext, "n")

            nb.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_foreground)) // 设置大图标
                    .setContentText("这是一个通知")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("通知")
                    .setWhen(System.currentTimeMillis()) // 5秒后通知
                    .setCustomContentView(remoteView)
            val ns = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificaion = nb.build()
            ns.notify(100, notificaion)
            Thread {
                Thread.sleep(1000)
                remoteHandler.sendEmptyMessage(1000)
            }.start()

        }

        Log.i("First ", "bindService end Pid is " + Process.myPid())
    }

    lateinit var remoteView: RemoteViews
    lateinit var notificaion: Notification

    private val remoteHandler = object : Handler() {

        var count = 0;
        override fun handleMessage(msg: Message?) {
            if (msg?.what == 1000) {
//                remoteView.setInt(R.id.notificationSeekBar, "setProgress", count % 101)
                remoteView.setTextViewText(R.id.notificationTV, count.toString())
                val ns = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                ns.notify(100, notificaion) // 必须调用才能刷新通知信息
                count++
                Thread.sleep(1000)
                sendEmptyMessage(1000)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (this.requestCode == requestCode) {
            handlePermissions(permissions, grantResults, contactPermissionHandler)
        }
    }

    private val contactPermissionHandler = object : HandlePermissionResult {

        override fun doTask() {
            Log.i("First ", "contactPermissionHandler success doTask")
        }

        override fun doFail() {
            Log.i("First ", "contactPermissionHandler fail doFail")
        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
//            System.loadLibrary("yxb-lib")
        }
    }
}
