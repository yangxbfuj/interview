package com.example.yxb.interview

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.yxb.interview.scrollview.ScrollerViewActivity

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
