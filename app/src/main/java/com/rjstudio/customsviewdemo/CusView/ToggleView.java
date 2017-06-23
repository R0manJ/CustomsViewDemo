package com.rjstudio.customsviewdemo.CusView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.rjstudio.customsviewdemo.R;

/**
 * Created by r0man on 2017/6/22.
 */

public class ToggleView extends View {
    private Bitmap bitmapBackground;
    private Bitmap bitmapButton;
    private Paint mpaint;

    private boolean isMove;
    private float mMoveX;
    private float mMinMoveX;
    private float mMaxMoveX;
    private float mLeft;
    private OnSwitchChangeListener onSwitchChangeListener;

    public ToggleView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initialization();
    }


    public void setOnSwitchChangeListener(OnSwitchChangeListener onSwitchChangeListener) {
        this.onSwitchChangeListener = onSwitchChangeListener;
    }
    private void initialization()
    {
        bitmapBackground = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        bitmapButton = BitmapFactory.decodeResource(getResources(),R.drawable.slide_button_background);
        mpaint = new Paint();
        mMoveX = 0;
        mMaxMoveX = bitmapBackground.getWidth() - bitmapButton.getWidth();
        mMinMoveX = 0;
        mLeft = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapBackground,0,0,mpaint);
        canvas.drawBitmap(bitmapButton,mLeft,0,mpaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(bitmapBackground.getWidth(),bitmapBackground.getHeight());
    }
    private float downx;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
            case MotionEvent.ACTION_MOVE:
                mMoveX = event.getX();
                //mLeft = mMoveX - bitmapButton.getWidth();
                mLeft = mMoveX - bitmapButton.getWidth()/2f; //为什么要除2?
                break;
            case MotionEvent.ACTION_UP:
                //这里要让他自动滑动
                float mMiddle = bitmapBackground.getWidth()/2f;
                float upX = event.getX();
                if (downx != upX)
                {
                    isMove = true;
                }
                else
                {
                    isMove = false;
                }
                Log.d("Move",downx+"--/---"+mMoveX+"--"+upX);
                if (isMove)
                {
                    //移动
                    if ( upX < mMiddle)
                    {
                        mLeft = mMinMoveX;
                        if (onSwitchChangeListener != null)
                        {
                            onSwitchChangeListener.onSwitch(true);
                        }
                    }
                    else
                    {
                        mLeft = mMaxMoveX;
                        if (onSwitchChangeListener != null)
                        {
                            onSwitchChangeListener.onSwitch(false);
                        }
                    }
                }
                else
                {
                    //只是点击事件
                    if (mLeft == mMinMoveX)
                    {
                        mLeft = mMaxMoveX;
                        if (onSwitchChangeListener != null)
                        {
                            onSwitchChangeListener.onSwitch(false);
                        }
                    }
                    else if (mLeft == mMaxMoveX)
                    {
                        mLeft = mMinMoveX;
                        if (onSwitchChangeListener != null)
                        {
                            onSwitchChangeListener.onSwitch(true);
                        }
                    }
                }

                break;
        }

        if (mLeft > mMaxMoveX)
        {
            mLeft = mMaxMoveX;
        }
        else if(mLeft <mMinMoveX)
        {
            mLeft = mMinMoveX;
        }
        invalidate();//这里会调用onDraw方法
        return true;
    }

    public interface OnSwitchChangeListener {
        public void onSwitch(boolean isOpened);
    }
}
