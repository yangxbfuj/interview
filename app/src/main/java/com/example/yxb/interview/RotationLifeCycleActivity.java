package com.example.yxb.interview;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

/**
 * 横竖屏生命周期变化
 */
public class RotationLifeCycleActivity extends Activity {

    private TextView mInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratation_lifecycler);
        mInfoTextView = findViewById(R.id.rotationLifeCycleTextView);
        printInfo("onCreate(Bundle savedInstanceState)");
    }

    @Override
    protected void onStart() {
        super.onStart();
        printInfo("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        printInfo("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        printInfo("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        printInfo("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printInfo("onDestroy()");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        printInfo("onPostCreate(@Nullable Bundle savedInstanceState)");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        printInfo("onPostResume()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        printInfo("onRestoreInstanceState(Bundle savedInstanceState)");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        printInfo("onSaveInstanceState(Bundle outState)");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        printInfo("onRestart()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        printInfo("onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState)");
    }

    private void printInfo(String info) {
        mInfoTextView.append(info);
        mInfoTextView.append("\n");
        Log.i("面试 屏幕旋转生命周期", info);
    }
}
