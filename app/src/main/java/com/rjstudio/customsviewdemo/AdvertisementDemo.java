package com.rjstudio.customsviewdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementDemo extends AppCompatActivity {

        private TextView tv_title;
        private LinearLayout ly_indication;
        private ViewPager vp_imageContent;
        private String TAG = "TEST";

        private List<ImageView> imageList;
        private int mLastPosition = 0;

        private int image[] = {
                R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d
        };

        private String title[] = {
                "aaa","bbb","ccc","ddd"
        };


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_advertisement_demo);

            imageList= new ArrayList<ImageView>();

            vp_imageContent = (ViewPager)findViewById(R.id.vp_imageContent);
            ly_indication = (LinearLayout)findViewById(R.id.ly_container);
            tv_title = (TextView)findViewById(R.id.tv_title);


            initialization();

            vp_imageContent.setAdapter(new CusPagerAdapter());

            vp_imageContent.setCurrentItem(mLastPosition);

            vp_imageContent.setOnPageChangeListener(new PagerChangeListener());


        }

        private void initialization()
        {
            mLastPosition = Integer.MAX_VALUE / 2 - 3 ;
            tv_title.setText(title[0]);
            for (int i = 0; i < image.length ; i++)
            {

                ImageView imageView = new ImageView(this);
                imageList.add(imageView);

                View view = new View(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30,30);

                if( i != 3){
                    layoutParams.rightMargin = 15;
                }
                layoutParams.topMargin = 2;
                view.setLayoutParams(layoutParams);
                if ( i == 0 )
                {
                    view.setBackgroundResource(R.drawable.dot_selected);
                }
                else
                {
                    view.setBackgroundResource(R.drawable.dot_normal);
                }
                ly_indication.addView(view);
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
                position %= image.length;
                mLastPosition %= image.length;
                //Log.d(TAG, "onPageSelected: " + position + "----" );
                //Log.d(TAG, "onPageSelected: last -- "+mLastPosition);
                tv_title.setText(title[position]);
                ly_indication.getChildAt(position).setBackgroundResource(R.drawable.dot_selected);
                ly_indication.getChildAt(mLastPosition).setBackgroundResource(R.drawable.dot_normal);
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
                position %= image.length;
                ImageView imageView = imageList.get(position);
                imageView.setImageResource(image[position]);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                position %= image.length;
                ImageView imageView = imageList.get(position);
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
