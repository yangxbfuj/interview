package com.example.yxb.interview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.yxb.interview.util.Printer;

/**
 * 是否支持使用多个Handler,如果支持是否有顺序性?显然,谁(Handler)发送的消息,谁处理.
 * 通过使用 Message.target 属性保持 Handler 对象.
 */
public class HandlerActivity extends AppCompatActivity {

    private static final String TAG = "HandlerActivity";

    private Handler mHandler1 = new MyHandler("Handler 1");

    private Handler mHandler2 = new MyHandler("Handler 2");

    private Handler mHandler3 = new MyHandler1("Handler 3");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Looper.myLooper();
        this.getSystemService(Context.WINDOW_SERVICE);
        Thread s = new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler1.sendEmptyMessage(1);

//                mHandler1.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Printer.println(TAG, "Runnable running mHandler1");
//                    }
//                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler2.sendEmptyMessage(2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 发 Msg 给 Handler1
                Message msg = Message.obtain();
                msg.setTarget(mHandler1);
                msg.what = 3;
                mHandler3.sendMessage(msg);
            }
        });
        s.start();
    }

    private static class MyHandler extends Handler {

        private String mHandlerName = "default";

        MyHandler(String name) {
            super();
            mHandlerName = name;
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            Printer.println(TAG, "dispatchMessage " + mHandlerName + " msg is " + msg.what);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Printer.println(TAG, "handleMessage " + mHandlerName + " msg is " + msg.what);
        }

    }

    private static class MyHandler1 extends Handler {

        private String mHandlerName = "default";
        private int[] arr = new int[1024 * 1024];

        MyHandler1(String name) {
            super();
            mHandlerName = name;
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            Printer.println(TAG, "dispatchMessage " + mHandlerName + " msg is " + msg.what);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 故意造成垃圾，让系统清理
            arr = new int[1024 * 1024];
            Printer.println(TAG, "handleMessage " + mHandlerName + " msg is " + msg.what);
            this.sendEmptyMessageDelayed(0,10);
        }

    }

}
