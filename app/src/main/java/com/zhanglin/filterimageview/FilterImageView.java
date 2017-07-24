package com.zhanglin.filterimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zhanglin on 2017/7/21.
 */

public class FilterImageView extends android.support.v7.widget.AppCompatImageView {
    private boolean isEnableGray = false;
    private ColorMatrix mColorMatrix;
    private ColorMatrixColorFilter colorMatrixColorFilter;
    private Paint mPaint;
    private Rect mRect;
    private int mColor = Color.TRANSPARENT;

    public FilterImageView(Context context) {
        this(context, null);
    }

    public FilterImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mColor);
        canvas.drawRect(mRect, mPaint);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRect = new Rect(left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnableGray) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mColor = Color.parseColor("#33000000");
                    invalidate();
                    return super.onTouchEvent(event);
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mColor = Color.TRANSPARENT;
                    invalidate();
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 是否允许在点击的时候显示灰色透明蒙层的点击效果
     *
     * @param isEnable
     */
    public void enableGrayOnClick(boolean isEnable) {
        isEnableGray = isEnable;
    }

    /**
     * 头像设置黑白
     *
     * @param isBlackWhite
     */
    public void setBlackWhite(boolean isBlackWhite) {
        this.setColorFilter(isBlackWhite ? getColorFilter() : null);
    }

    @NonNull
    public ColorMatrixColorFilter getColorFilter() {
        if (mColorMatrix == null) {
            mColorMatrix = new ColorMatrix();
            mColorMatrix.setSaturation(0);
            colorMatrixColorFilter = new ColorMatrixColorFilter(mColorMatrix);
        }
        return colorMatrixColorFilter;
    }
}
