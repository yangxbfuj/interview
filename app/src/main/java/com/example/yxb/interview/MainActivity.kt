package com.example.yxb.interview

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.yxb.interview.animation.AnimationActivity
import com.example.yxb.interview.scrollview.ScrollerViewActivity
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import com.yxb.server.IMyAidlInterface


class MainActivity : AppCompatActivity() {

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
                    Log.i("First ", "Pid is " + Process.myPid())
                    myservice?.doSomething()
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    myservice = null
                }

            }, Context.BIND_AUTO_CREATE)
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
