package com.kisetsu.story.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.kisetsu.story.R;
import com.kisetsu.story.adapters.ViewPagerAdapterLogin;

/**
 * Created by kisetsu on 18-9-21.
 * Login Activity
 */

public class ActivityLogin extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewPager=findViewById(R.id.vp_login);
        ViewPagerAdapterLogin adapter=new ViewPagerAdapterLogin(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
