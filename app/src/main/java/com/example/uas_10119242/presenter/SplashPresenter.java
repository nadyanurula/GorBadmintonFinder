package com.example.uas_10119242.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.uas_10119242.activities.SlideActivity;
import com.example.uas_10119242.activities.SplashActivity;

public class SplashPresenter {
    Context context;

    public SplashPresenter(SplashActivity splashActivity) {
    }

    Intent getSlide = new Intent();

    public Intent setSlide() {
        Intent intent = new Intent(context, SlideActivity.class);
        return intent;
    }
}
