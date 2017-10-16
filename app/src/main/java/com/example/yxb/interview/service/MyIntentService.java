package com.example.yxb.interview.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yxb on 2017/10/11.
 */

public class MyIntentService extends IntentService {

    private String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "doSyncJob start " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Log.i(TAG, "doSyncJob interrupted " + Thread.currentThread().getName());
        }
        Log.i(TAG, "doSyncJob end " + Thread.currentThread().getName());
        stopSelf();
    }

}
