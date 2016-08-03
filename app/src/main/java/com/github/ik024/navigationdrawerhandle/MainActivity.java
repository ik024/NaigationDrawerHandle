package com.github.ik024.navigationdrawerhandle;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    ListView lvDrawerList;
    String[] drawerListItems;
    ImageView ivDrawerToggle;
    MyDrawerToggle myDrawerToggle;
    private int mMinusShadow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        lvDrawerList  = (ListView) mDrawerLayout.findViewById(R.id.lv_drawer);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ivDrawerToggle = (ImageView) findViewById(R.id.iv_drawer_toggle);

        setSupportActionBar(mToolbar);

        getDrawerMinusShadow();
        myDrawerToggle = new MyDrawerToggle(this, ivDrawerToggle, mMinusShadow, lvDrawerList);

        /*ivDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(lvDrawerList)){
                    mDrawerLayout.closeDrawer(lvDrawerList);
                }else{
                    mDrawerLayout.openDrawer(lvDrawerList);
                }
            }
        });*/

        ivDrawerToggle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        drawerListItems = new String[]{"Home","Profile","Settings","Logout"};

        lvDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvDrawerList.setItemChecked(position, true);
                getSupportActionBar().setTitle(drawerListItems[position]);
                mDrawerLayout.closeDrawer(lvDrawerList);
            }
        });


        lvDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,drawerListItems));

        //initial state of drawer list
        lvDrawerList.setItemChecked(0, true);
        setTitle(drawerListItems[0]);

       /* mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                                        R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };*/

        mDrawerLayout.addDrawerListener(myDrawerToggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        /*boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);*/
        return super.onPrepareOptionsMenu(menu);
    }

    private void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    private void getDrawerMinusShadow(){

        if(mMinusShadow == 0.0f){
            for(int i = 0 ; i < mDrawerLayout.getChildCount();i++){
                mMinusShadow = mMinusShadow == 0.0f ? mDrawerLayout.getChildAt(i).getMeasuredWidth() : mMinusShadow;
                mMinusShadow = Math.min(mMinusShadow, mDrawerLayout.getChildAt(i).getMeasuredWidth());
            }
        }
        return;
    }
}
