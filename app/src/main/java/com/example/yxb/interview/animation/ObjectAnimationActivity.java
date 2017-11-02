package com.example.yxb.interview.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yxb on 2017/10/16.
 */

public class ObjectAnimationActivity extends AppCompatActivity {

    float mAnimationValue = 0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObjectAnimator.ofFloat(this, "animationValue", 0f, 1f);
    }

    private void setAnimationValue(float value) {
        mAnimationValue = value;
    }

    public float getAnimationVaule() {
        return mAnimationValue;
    }
}
