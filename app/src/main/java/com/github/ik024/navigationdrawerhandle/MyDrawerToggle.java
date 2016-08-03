package com.github.ik024.navigationdrawerhandle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by ismail.khan2 on 8/3/2016.
 */
public class MyDrawerToggle implements DrawerLayout.DrawerListener {

    private static final String TAG = MyDrawerToggle.class.getSimpleName();
    ImageView view;
    Context mContext;
    int minShadow;
    View mDrawerLayout;
    public MyDrawerToggle(Context context, ImageView view, int minShadow, View drawerLayout){
        this.view = view;
        this.mContext = context;
        this.minShadow = minShadow;
        this.mDrawerLayout = drawerLayout;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        Log.d(TAG, "onDrawerSlide - slideOffset: "+(mDrawerLayout.getRight()));
        view.setTranslationX(mDrawerLayout.getRight());
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        Log.d(TAG, "onDrawerOpened");
        view.setTranslationX(drawerView.getWidth());
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        Log.d(TAG, "onDrawerClosed");
        view.setTranslationX(0);
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    private int getScreenWidth(){

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        return width;
    }

}
