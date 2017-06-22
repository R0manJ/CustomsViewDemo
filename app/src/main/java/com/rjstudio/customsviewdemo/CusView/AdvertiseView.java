package com.rjstudio.customsviewdemo.CusView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rjstudio.customsviewdemo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by r0man on 2017/6/19.
 */

public class AdvertiseView extends RelativeLayout {
    private Context context;
    private AttributeSet attrs;

    private int ImageResourceId[];
    private String ImageTitle[];
    private int ContentSize;
    private int mLastPosition;
    private List<ImageView> imageViewList;

    private String TAG = "AdvertiseView :";
    private ViewPager vp_content;
    private  TextView tv_title;
    private  LinearLayout ll_indication;

    public AdvertiseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = getContext();
        imageViewList = new ArrayList<ImageView>();
        initializationView();
    }

    public void initializationView()
    {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_advertisement,this);
        vp_content = (ViewPager)view.findViewById(R.id.vp_imageContent);
        tv_title = (TextView)view.findViewById(R.id.tv_title);
        ll_indication = (LinearLayout)view.findViewById(R.id.ly_container);
    }

    public void setImage(int[] ImageResource,String[] ImageTitle)
    {
        this.ImageResourceId = ImageResource;
        this.ImageTitle = ImageTitle;
        if (ImageResource.length == ImageTitle.length)
        {
            ContentSize = ImageResourceId.length;
            initialization();
        }
        else
        {
            Log.d(TAG, "Can't not initial data,because the length of ImageResource don't equal the length of ImageTitle.");
        }
        vp_content.setAdapter(new CusPagerAdapter());
        vp_content.setOnPageChangeListener(new PagerChangeListener());
    }

    private void initialization()
    {
        mLastPosition = 0;
        LayoutParams layoutParams = new LayoutParams(20,20);
        for( int i = 0 ; i < ImageResourceId.length ; i ++)
        {
            View view = new View(context);

            if ( i != ContentSize - 1)
            {
                layoutParams.rightMargin = 5;
            }
            else
            {
                layoutParams.rightMargin = 0;
            }

            layoutParams.topMargin = 2;
            view.setLayoutParams(layoutParams);

            if (i != 0)
            {
                view.setBackgroundResource(R.drawable.dot_normal);
            }
            else
            {
                view.setBackgroundResource(R.drawable.dot_selected);
            }

            ll_indication.addView(view);
            ImageView imageView = new ImageView(context);
            imageViewList.add(imageView);
        }
    }



    private class PagerChangeListener implements ViewPager.OnPageChangeListener
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.d(TAG, "onPageSelected: initialization page is" + position);
            //TODO: 这里的中间值为 1,073,741,821
            position %= ContentSize;
            mLastPosition %= ContentSize;
            Log.d(TAG, "onPageSelected: " + position + "" );
           // Log.d(TAG, "onPageSelected: last -- "+mLastPosition +"---"+ImageTitle.length+"--"+ImageTitle[1]);
            //tv_title.setText("2");
            tv_title.setText(ImageTitle[position]);
            ll_indication.getChildAt(position).setBackgroundResource(R.drawable.dot_selected);
            ll_indication.getChildAt(mLastPosition).setBackgroundResource(R.drawable.dot_normal);
            mLastPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class CusPagerAdapter extends PagerAdapter
    {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= ContentSize;
            ImageView imageView = imageViewList.get(position);
            imageView.setImageResource(ImageResourceId[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            position %= ContentSize;
            ImageView imageView = imageViewList.get(position);
            container.removeView(imageView);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
