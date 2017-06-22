package com.rjstudio.customsviewdemo.CusView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rjstudio.customsviewdemo.R;

/**
 * Created by r0man on 2017/6/19.
 */

public class YouKuMenu extends RelativeLayout implements View.OnClickListener{

    private boolean isLevel1Check = false;
    private boolean isLevel2Check = false;
    private boolean isLevel3Check = false;
    private boolean isEndDisplayAnimation = true;

    private RelativeLayout rl_level3;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level1;
    private ImageView iv_channel1;
    private ImageView iv_channel2;
    private ImageView iv_channel3;
    private ImageView iv_channel5;
    private ImageView iv_channel4;
    private ImageView iv_channel6;
    private ImageView iv_channel7;
    private ImageView iv_search;
    private ImageView iv_menu;
    private ImageView iv_myYouku;
    private ImageView iv_home;
    private Animation.AnimationListener animationListener;

    public YouKuMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialization();
    }

    private void initialization()
    {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_youku,this);
        rl_level3 = (RelativeLayout)view.findViewById(R.id.rl_level3);
        rl_level2 = (RelativeLayout)view.findViewById(R.id.rl_level2);
        rl_level1 = (RelativeLayout)view.findViewById(R.id.rl_level1);

        rl_level3.setVisibility(View.GONE);
        rl_level2.setVisibility(View.GONE);

        iv_channel1 = (ImageView)view.findViewById(R.id.iv_channel1);
        iv_channel2 = (ImageView)view.findViewById(R.id.iv_channel2);
        iv_channel3 = (ImageView)view.findViewById(R.id.iv_channel3);
        iv_channel4 = (ImageView)view.findViewById(R.id.iv_channel4);
        iv_channel5 = (ImageView)view.findViewById(R.id.iv_channel5);
        iv_channel6 = (ImageView)view.findViewById(R.id.iv_channel6);
        iv_channel7 = (ImageView)view.findViewById(R.id.iv_channel7);

        iv_search = (ImageView)view.findViewById(R.id.iv_search);
        iv_menu = (ImageView)view.findViewById(R.id.iv_menu);
        iv_myYouku = (ImageView)view.findViewById(R.id.iv_myYouku);
        iv_home = (ImageView)view.findViewById(R.id.iv_home);

        iv_channel1.setOnClickListener(this);
        iv_channel2.setOnClickListener(this);
        iv_channel3.setOnClickListener(this);
        iv_channel4.setOnClickListener(this);
        iv_channel5.setOnClickListener(this);
        iv_channel6.setOnClickListener(this);
        iv_channel7.setOnClickListener(this);

        iv_search.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
        iv_myYouku.setOnClickListener(this);

        iv_home.setOnClickListener(this);

        //设置动画监听,防止多次点击,导致动画重复
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isEndDisplayAnimation = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isEndDisplayAnimation = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_channel1:

                break;
            case R.id.iv_channel2:
                break;
            case R.id.iv_channel3:
                break;
            case R.id.iv_channel4:
                break;
            case R.id.iv_channel5:
                break;
            case R.id.iv_channel6:
                break;
            case R.id.iv_channel7:
                break;


            case R.id.iv_search:
                break;
            case R.id.iv_menu:
                if (!isLevel3Check && isLevel2Check)
                {
                    handleMenuButton(rl_level3,true);
                    isLevel3Check = true;
                }
                else if(isLevel3Check && isLevel2Check)
                {
                    handleMenuButton(rl_level3,false);
                    isLevel3Check = false;
                }
                break;
            case R.id.iv_myYouku:
                break;


            case R.id.iv_home:
                if (!(isLevel1Check && isLevel2Check))
                {
                    handleHomeButton(rl_level2,true);
                }
                else if (isLevel1Check)
                {
                    handleHomeButton(rl_level2,false);
                    if (isLevel3Check)
                    {
                        handleHomeButton(rl_level3,false);
                    }
                    rl_level3.setVisibility(View.GONE);
                    isLevel3Check = false;
                }
                break;
        }
    }

    public void handleHomeButton(final RelativeLayout relativeLayout, boolean isCheck)
    {
        if (!isEndDisplayAnimation)
        {
            return;
        }
        RotateAnimation rAnimation ;
        if(isCheck){
            rAnimation = new RotateAnimation(180,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,1f);
            rAnimation.setDuration(1000);
            rAnimation.setAnimationListener(animationListener);
            rAnimation.setStartOffset(200);
            relativeLayout.setAnimation(rAnimation);
            relativeLayout.setVisibility(View.VISIBLE);
            isLevel1Check = true;
            isLevel2Check = true;
        }
        else
        {
            rAnimation = new RotateAnimation(0,-180,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,1f);
            rAnimation.setDuration(1000);
            rAnimation.setAnimationListener(animationListener);
            relativeLayout.setAnimation(rAnimation);
            relativeLayout.setVisibility(View.GONE);
            isLevel1Check = false;
            isLevel2Check = false;
        }
    }

    public void handleMenuButton(RelativeLayout relativeLayout,boolean isCheck)
    {
        if (!isEndDisplayAnimation)
        {
            return;
        }
        RotateAnimation rotateAnimation;
        if (isCheck)
        {
            rotateAnimation = new RotateAnimation(180,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,1f);
            rotateAnimation.setDuration(1500);
            rotateAnimation.setAnimationListener(animationListener);
            relativeLayout.setAnimation(rotateAnimation);
            relativeLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            rotateAnimation = new RotateAnimation(0,-180,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,1f);
            rotateAnimation.setDuration(1500);
            rotateAnimation.setAnimationListener(animationListener);
            relativeLayout.setAnimation(rotateAnimation);
            relativeLayout.setVisibility(View.GONE);
        }
    }
}
