package com.example.yxb.interview.animation;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.yxb.interview.R;

/**
 * Created by yxb on 2017/10/17.
 */

public class FrameAnimationActivity extends AppCompatActivity {
    AnimationDrawable animationDrawableMXL;
    AnimationDrawable animationDrawableCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        // XML 帧动画
        ImageView imageView = findViewById(R.id.imageFrameXML);
        Drawable drawable = imageView.getBackground();
        animationDrawableMXL = (AnimationDrawable) drawable;

        // 代码帧动画
        animationDrawableCode = new AnimationDrawable();
        for (int i = 0; i < 10; i++) {
            int id = getResources().getIdentifier("img0000" + i, "drawable", getPackageName());
            Drawable d = getDrawable(id);
            animationDrawableCode.addFrame(d, 100);
        }
        animationDrawableCode.setOneShot(false);
        ImageView imageViewCode = findViewById(R.id.imageFrameCode);
        imageViewCode.setBackground(animationDrawableCode);
        TypeEvaluator s;
    }

    @Override
    protected void onResume() {
        super.onResume();
        animationDrawableMXL.start();
        animationDrawableCode.start();
    }
}
