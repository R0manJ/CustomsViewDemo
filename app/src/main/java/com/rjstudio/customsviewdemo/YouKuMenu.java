package com.rjstudio.customsviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class YouKuMenu extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_ku_menu);
        com.rjstudio.customsviewdemo.CusView.YouKuMenu youKuMenu = (com.rjstudio.customsviewdemo.CusView.YouKuMenu)findViewById(R.id.youku);

    }




}
