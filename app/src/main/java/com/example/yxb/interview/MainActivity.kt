package com.example.yxb.interview

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainList = findViewById<RecyclerView>(R.id.mainList)
        val adapter = MainAdapter(object : MainOnClickListener {
            override fun onClick(position: Int) = click(position)
        })
        mainList.adapter = adapter
    }

    private fun click(position: Int) {
        when (position) {
            0 -> {
                val intent = Intent(this, RotationLifeCycleActivity::class.java)
                startActivity(intent)
            }
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

class MainAdapter(private val onClickListener: MainOnClickListener) : RecyclerView.Adapter<MainVH>() {
    val data: List<String>

    init {
        data = listOf("横竖屏转换的生命周期", "more")
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainVH?, position: Int) {
        val textView = holder?.itemView as TextView
        textView.text = "$position : ${data[position]}"
        textView.setOnClickListener {
            onClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainVH =
            MainVH(TextView(parent?.context))


}

class MainVH(itemView: View) : RecyclerView.ViewHolder(itemView)

interface MainOnClickListener {
    fun onClick(position: Int)
}
