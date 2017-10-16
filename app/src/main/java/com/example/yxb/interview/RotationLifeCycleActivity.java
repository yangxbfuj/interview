package com.example.yxb.interview;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * 横竖屏生命周期变化
 */
public class RotationLifeCycleActivity extends Activity {

    private static final String sSaveTag = "save";
    private TextView mInfoTextView;
    private Button mButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratation_lifecycler);
        mInfoTextView = findViewById(R.id.rotationLifeCycleTextView);
        mButton = findViewById(R.id.rotationLifeCycleBtn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RotationLifeCycleActivity.this,RotationLifeCycleActivity.class);
                startActivity(intent);
            }
        });
        printInfo("onCreate(Bundle savedInstanceState)");
        if (savedInstanceState != null) {
            String saveInfo = savedInstanceState.getString(sSaveTag);
            if (!TextUtils.isEmpty(saveInfo)) {
                printInfo("onCreate -> get save info " + saveInfo);
            } else {
                savedInstanceState.putString(sSaveTag, "info from onCreate");
                printInfo("onCreate -> save info " + "info from onCreate");
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        printInfo("onNewIntent");
        super.onNewIntent(intent);
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
        String saveInfo = null;
        if (savedInstanceState != null)
            saveInfo = savedInstanceState.getString(sSaveTag);
        if (!TextUtils.isEmpty(saveInfo)) {
            printInfo("onCreate -> get save info " + saveInfo);
        }
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        printInfo("onConfigurationChanged(Configuration newConfig)");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        printInfo("onSaveInstanceState(Bundle outState)");
        outState.putString(sSaveTag, "info from onCreate");
        printInfo("onSaveInstanceState -> save info " + "info from onSaveInstanceState");
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
