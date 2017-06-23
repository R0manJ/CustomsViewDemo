package com.rjstudio.customsviewdemo;





import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bu_adv = (Button)findViewById(R.id.bu_AdDemo);
        Button bu_youku = (Button)findViewById(R.id.bu_YK);
        Button bu_slideMenu = (Button)findViewById(R.id.bu_SlideMenu);
        Button bu_slideDelete = (Button)findViewById(R.id.bu_SlideDelete);
        Button bu_slideButton = (Button)findViewById(R.id.bu_SlideButton);
        Button bu_Spinner = (Button)findViewById(R.id.bu_Spinner);

        bu_adv.setOnClickListener(this);
        bu_youku.setOnClickListener(this);
        bu_slideButton.setOnClickListener(this);
        bu_slideMenu.setOnClickListener(this);
        bu_Spinner.setOnClickListener(this);
        bu_slideDelete.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bu_AdDemo:
                Toast.makeText(this, "Advertisement", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,AdvertisementDemo.class);
                startActivity(intent);
                break;
            case R.id.bu_YK:
                Toast.makeText(this, "YoukuMenu", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this,YouKuMenu.class);
                startActivity(intent1);
                break;
            case R.id.bu_SlideMenu:
                Intent intent3 = new Intent(this,SlideMenuDemo.class);
                startActivity(intent3);
                break;
            case R.id.bu_SlideDelete:
                break;
            case R.id.bu_Spinner:
                Intent intent5 = new Intent(this,SpinnerDemo.class);
                startActivity(intent5);
                break;
            case R.id.bu_SlideButton:
                Intent intent2 = new Intent(this,ToggleButtonDemo.class);
                startActivity(intent2);
                break;
        }
    }

    private void GoTo(Object demo)
    {
        Intent intent = new Intent(this,demo.getClass());
        startActivity(intent);
    }
}