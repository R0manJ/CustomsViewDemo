package com.rjstudio.customsviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rjstudio.customsviewdemo.CusView.AdvertiseView;

public class AdvertisementDemo extends AppCompatActivity {

    private String TAG = "TEST";

        private int image[] = {
                R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.icon_1
        };

        private String title[] = {
                "aaa","bbb","ccc","ddd","eee"
        };


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_advertisement_demo);

            AdvertiseView ad = (AdvertiseView)findViewById(R.id.cus_advertisement);
            ad.setImage(image,title);
        }


}
