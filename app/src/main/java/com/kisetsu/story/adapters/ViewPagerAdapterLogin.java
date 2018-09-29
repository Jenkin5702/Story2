package com.kisetsu.story.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kisetsu.story.fragments.FragmentSignIn;
import com.kisetsu.story.fragments.FragmentSignUp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by kisetsu on 18-9-21.
 */
public class ViewPagerAdapterLogin extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public ViewPagerAdapterLogin(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new FragmentSignIn();
        }else{
            return new FragmentSignUp();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
