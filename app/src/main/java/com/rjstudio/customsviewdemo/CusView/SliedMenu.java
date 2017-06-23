package com.rjstudio.customsviewdemo.CusView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by r0man on 2017/6/23.
 */

public class SliedMenu extends ViewGroup {

    private View mChildLeft;
    private View mChildRight;
    private float mDownX;
    private float mDownY;

    private int mMinX;
    private int mMaxX;

    private Scroller mScroller;
    private boolean isOpened = false;

    //这个方法有什么用?
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mChildLeft.layout(-mChildLeft.getMeasuredWidth(),0,0,mChildLeft.getMeasuredHeight());
        mChildRight.layout(0,0,mChildRight.getMeasuredWidth(),mChildRight.getMeasuredHeight());

    }

    public SliedMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mChildLeft = getChildAt(0);
        mChildRight = getChildAt(1);

        String TAG = "Measure";
        //测量左边的控件
        LayoutParams layoutParams = mChildLeft.getLayoutParams();
        int width = layoutParams.width;
        int leftWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        Log.d(TAG, "onMeasure:--LeftChild-- " + width + "---" + leftWidthMeasureSpec);
        mChildLeft.measure(leftWidthMeasureSpec, heightMeasureSpec);

        //测量右边的控件
        mChildRight.measure(widthMeasureSpec , heightMeasureSpec);


        mMinX = - mChildLeft.getMeasuredWidth();
        mMaxX = 0;
        //因为左布局在一开始的时候不显示,位于屏幕的左边,因此其最小的x坐标是负值;
        //当左布局完全显示时,则x的最大坐标是0;
        //Log.d(TAG, "onMeasure:--mMinX-- " + mMinX + "--mMaxX-" + mMaxX);

        //设置宽度与高度的大小吗?
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));

    }

    //设置触控事件

    //Question:为什么左布局的宽度设置为填充负窗体后,左布局的textView的字体(内容)就无法显示?
    //Answer: 因为在设置宽度的时候,makMeasureSpec函数中,参数一代表的是长度,参数二代表的是模式,如果设置为精确的(EXACTLY),则狂堵要设置一个具体的值.

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         String TAG = "onTouchEvent";
        //return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                int diffX = (int) (mDownX - moveX + 0.5f);//这个代表
                Log.d(TAG, "onTouchEvent: "+diffX);
                //Question:为什么这里要实行四舍五入呢?而为什么要加0.5f代表来四舍五入??
                //Answer1: 举个例子 9.1与9.9在被强制类型转换成int型后,都会是9,那么值的差别就太大了,故此处需要四舍五入.
                //Answer2: 逢五进一,略

                int scrolledX = getScrollX();
                Log.d(TAG, "getScrollX"+scrolledX);
                //这个代表的事当前这个水平滚动条,所滑动的位置

                int finalX = scrolledX + diffX;

                //边界检查
                if (finalX < mMinX) {
                    scrollTo(mMinX, 0);
                    return true;
                } else if (finalX > mMaxX) {
                    scrollTo(mMaxX, 0);
                    return true;
                }

                scrollBy(diffX, 0);
                //scrollBy函数是一个增量函数,参数一代表的是变化的x坐标,参数代表的是模式.

                mDownX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                int middle = - mChildLeft.getWidth() / 2;
                Log.d(TAG, "middle:"+middle);//-394
                if (getScrollX() > middle) {
                    close();
                } else {
                    open();
                }
                break;
        }
        return true;
    }
    String TAG = "null";
    private void open() {
        Log.d(TAG, "open: ");
        scrollTo(mMinX);
//        scrollTo(mMinX,0);
        isOpened = true;
    }


    private void close() {
        Log.d(TAG, "close: ");
        scrollTo(mMaxX);
        //scrollTo(mMaxX,0);

        isOpened = false;
    }


    private void scrollTo(int endX) {
        int startX = getScrollX();
        int dx = endX - startX;
        int duration = 50;
        mScroller.startScroll(startX, 0, dx, 0, duration);
        //写这个方法时为什么必须重写computeScroll?
        //参数一表示水平可偏移的量,参数三表示水平实际偏移量
        invalidate();//invalidate -> 父容器dispatchDraw
    }



    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }
//-----

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mDownX = ev.getX();
//                mDownY = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float moveX = ev.getX();
//                float moveY = ev.getY();
//                float dx = Math.abs(moveX - mDownX);
//                float dy = Math.abs(moveY - mDownY);
//                if (dx > dy) {
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
//
//    public void toggle() {
//        if (isOpened) {
//            close();
//        } else {
//            open();
//        }
//    }


}
