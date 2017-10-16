package com.example.yxb.interview.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yxb on 2017/10/11.
 */

public class NormalService extends Service {

    String TAG = "NormalService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        doSyncJob(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return myBinder;
    }

    private void doSyncJob(Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "doSyncJob start " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.i(TAG, "doSyncJob interrupted " + Thread.currentThread().getName());
                }
                Log.i(TAG, "doSyncJob end " + Thread.currentThread().getName());
                stopSelf();
            }
        }).start();
    }

    public static class MyBinder extends Binder{

        private NormalService normalService;

        MyBinder(NormalService normalService){
            this.normalService = normalService;
        }

        public NormalService getServeice(){
            return normalService;
        }
    }

    private MyBinder myBinder = new MyBinder(this);
}
