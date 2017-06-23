package com.rjstudio.customsviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rjstudio.customsviewdemo.CusView.*;

public class SlideMenuDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);

        SliedMenu sliedMenu = (SliedMenu)findViewById(R.id.slideMenu);
    }

    public void onTabClick(View view)
    {
        TextView textView = (TextView)view;
        Toast.makeText(this, textView.getText(), Toast.LENGTH_SHORT).show();
    }

}
