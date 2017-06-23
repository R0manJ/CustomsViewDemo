package com.rjstudio.customsviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.rjstudio.customsviewdemo.CusView.ToggleView;

public class ToggleButtonDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_button_demo);

        ToggleView toggleView = (ToggleView)findViewById(R.id.toggle);
        toggleView.setOnSwitchChangeListener(new ToggleView.OnSwitchChangeListener()
        {
            @Override
            public void onSwitch(boolean isOpened) {
                if (isOpened)
                {
                    Toast.makeText(getApplicationContext(), "True", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ToggleButtonDemo.this, "False", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
