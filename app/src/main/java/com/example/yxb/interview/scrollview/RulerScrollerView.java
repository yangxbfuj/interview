package com.example.yxb.interview.scrollview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

import com.example.yxb.interview.R;

public class RulerScrollerView extends View {

    long lastTime;

    private String TAG = "ScrollerView";

    private Scroller mScroller;

    private int mLength = 100;
    private int mIntervalLength = 100;

    private Paint mLinePaint;
    private Paint mTextPaint;
    private int mTextHeight = 10;
    private int mTextWidth = 10;
    private int mLineHeight = 50;
    private int mGap = 50;
    private int totalWidth = 0;

    public RulerScrollerView(Context context) {
        this(context, null);
    }

    public RulerScrollerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RulerScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RulerScrollerView, defStyleAttr, 0);
        mIntervalLength = typedArray.getDimensionPixelSize(R.styleable.RulerScrollerView_ruler_unit_length, mIntervalLength);
        mLength = typedArray.getInt(R.styleable.RulerScrollerView_ruler_length, mLength);
        typedArray.recycle();
        // 初始化文本绘制笔
        mTextPaint = new Paint();
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(context.getColor(android.R.color.black));

        // 计算文本的高度
        String test = "1";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(test, 0, test.length(), rect);
        mTextHeight = rect.height();
        mTextWidth = rect.width();

        // 设置划线笔
        mLinePaint = new Paint();
        mLinePaint.set(mTextPaint);
        mLinePaint.setStrokeWidth(4);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureHeight = mTextHeight + mGap + mLineHeight + getPaddingTop() + getPaddingBottom();
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(measureHeight, heightMode);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    int mLocationCLickFirst = 0;
    VelocityTracker mVelocityTracker;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                    mVelocityTracker.addMovement(event);
                }
                mScroller.abortAnimation();
                Log.i(TAG, "action down ");
                mLocationCLickFirst = (int) event.getX();
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "action move ");
                mScroller.startScroll(getScrollX(), getScrollY(), mLocationCLickFirst - (int) event.getX(), 0, 0);
                invalidate();
                mLocationCLickFirst = (int) event.getX();
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "action up ");
                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000);
                Log.i(TAG, "mVelocityTracker.getXVelocity() = " + mVelocityTracker.getXVelocity());
                mScroller.fling(getScrollX(), getScrollY(), -(int) mVelocityTracker.getXVelocity(), 0, 0, totalWidth - getMeasuredWidth(), 0, getMeasuredHeight());
                invalidate();
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        totalWidth = getPaddingLeft() + getPaddingRight() + mLength * mIntervalLength;
        for (int i = 0; i <= mLength; i++) {
            int offSet = i * mIntervalLength;
            if (i % 5 == 0) {
                String text = String.valueOf(i);
                int textWith = text.length() * mTextWidth;
                canvas.drawText(text, getPaddingLeft() + offSet - textWith, getPaddingTop() + mTextHeight, mTextPaint);
            }
            float coefficient = i % 5 == 0 ? 1 : 0.5f;
            canvas.drawLine(offSet + getPaddingLeft() + mLinePaint.getStrokeWidth() / 2,
                    getMeasuredHeight() - getPaddingBottom(),
                    offSet + getPaddingLeft() + mLinePaint.getStrokeWidth() / 2,
                    getMeasuredHeight() - getPaddingBottom() - mLineHeight * coefficient,
                    mLinePaint);
        }
        canvas.drawLine(getPaddingLeft(), getMeasuredHeight() - getPaddingBottom(), mLength * mIntervalLength + getPaddingLeft(), getMeasuredHeight() - getPaddingBottom(), mLinePaint);
    }

    /**
     * 界面每次绘制时,系统调用该方法?
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        // 是否滚动完毕
        if (mScroller.computeScrollOffset()) {
            // 界面来进行滚动
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            long current = System.currentTimeMillis();
            Log.i(TAG, "scroll to " + mScroller.getCurrX() + "," + mScroller.getCurrY() + " interval time " + (current - lastTime));
            lastTime = current;
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        if (x < 0) {
            x = 0;
        }
        if (x > totalWidth - getMeasuredWidth()) {
            x = totalWidth - getMeasuredWidth();
        }
        super.scrollTo(x, y);
    }

    public void scrollTo(int x) {
        // 开始滚动,其中要传入当前View的滚动状态
        mScroller.startScroll(getScrollX(), getScrollY(), x - mScroller.getCurrX(), 0);
        // 重绘
        invalidate();
    }

    public void scrollToIndex(int x) {
        scrollTo(x * mIntervalLength);
        Log.i(TAG, "scrollToIndex pixel is  " + (x * mIntervalLength));
    }
}
