package com.example.yxb.interview

import android.Manifest
import android.app.ActivityManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.widget.Button
import com.example.yxb.interview.animation.AnimationActivity
import com.example.yxb.interview.scrollview.ScrollerViewActivity
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import com.example.yxb.interview.service.BinderS
import com.example.yxb.interview.service.BinderService
import com.yxb.server.IMyAidlInterface


class MainActivity : AppCompatActivity() {

    val requestCode = 123131

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnActivityLifeCycle).setOnClickListener {
            val intent = Intent(this, RotationLifeCycleActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnService).setOnClickListener {
            val intent = Intent(this, ServiceActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnHandler).setOnClickListener {
            val intent = Intent(this, HandlerActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.srcollView).setOnClickListener {
            val intent = Intent(this, ScrollerViewActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnAnimation).setOnClickListener {
            val intent = Intent(this, AnimationActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnSecond).setOnClickListener {
            val intent = Intent()
            intent.action = "1234567890yxb"
            Log.i("First ", "Pid is " + Process.myPid())
            val ss = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            Log.i("First ", "ss.appTasks " + ss.appTasks.map { it.taskInfo })
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnAIDL).setOnClickListener {
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
        findViewById<Button>(R.id.btnBinderService).setOnClickListener {
            val intent = Intent(this, BinderService::class.java)
            bindService(intent, object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    (service as? BinderS)?.doSomething()
                }

                override fun onServiceDisconnected(name: ComponentName?) {

                }

            }, Context.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.btnRequestPermission).setOnClickListener {
            checkPermission(this, requestCode, contactPermissionHandler, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
        }
        Log.i("First ", "bindService end Pid is " + Process.myPid())
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
