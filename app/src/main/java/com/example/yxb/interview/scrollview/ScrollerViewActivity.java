package com.example.yxb.interview.scrollview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.yxb.interview.R;

/**
 * Created by yxb on 2017/10/16.
 */

public class ScrollerViewActivity extends AppCompatActivity {
    private RulerScrollerView mRulerScrollerView;
    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_view);
        mRulerScrollerView = findViewById(R.id.srcollView);
        mEditText = findViewById(R.id.scrollEdit);
        findViewById(R.id.scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indexString = mEditText.getText().toString();
                if (TextUtils.isEmpty(indexString)) {
                    return;
                }
                int index = Integer.valueOf(indexString);
                mRulerScrollerView.scrollToIndex(index);
            }
        });
    }

}
