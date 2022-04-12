package com.blusky.blusky.view.landing;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.blusky.blusky.adapter.DrawerItemCustomAdapter;
import com.blusky.blusky.model.DataModel;
import com.blusky.blusky.view.history.HistoryFragment;
import com.blusky.blusky.view.vehicle.VehicleActivity;
import com.blusky.blusky.view.wallet.WalletFragment;

import com.blusky.blusky.R;
import com.fxn.BubbleTabBar;
import com.fxn.OnBubbleClickListener;

public class MainActivity extends AppCompatActivity {


    Activity activity;


    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;
    ImageView menu;
    ListView mDrawerList;
    BubbleTabBar bubbleTabBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=this;

        initView();
        setUpNavMenu();
        setUpMenu();
        setUpHome();









    }

    private void setUpMenu() {
        bubbleTabBar.addBubbleListener(i -> {

            if (i==R.id.home){
                Fragment fragment = new MapFragment();
                loadFragment(fragment);
            }

            if (i==R.id.profile){
                Fragment fragment = new MapFragment();
                loadFragment(fragment);
            }
            if (i==R.id.wallet){
                Fragment fragment = new WalletFragment();
                loadFragment(fragment);
            }
            if (i==R.id.history){
                Fragment fragment = new HistoryFragment();
                loadFragment(fragment);
            }
        });

    }

    private void setUpHome() {
        Fragment fragment = new MapFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, "Main");
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void loadFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, "Main");
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setUpNavMenu() {

        DataModel[] drawerItem = new DataModel[4];

        drawerItem[0] = new DataModel(R.drawable.ic_car, "Vehicle");
        drawerItem[1] = new DataModel(R.drawable.ic_about, "About");
        drawerItem[2] = new DataModel(R.drawable.ic_settings, "Setting");
        drawerItem[3] = new DataModel(R.drawable.ic_contact_form, "Contact Us");

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout =  findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        menu.setOnClickListener(v -> mDrawerLayout.openDrawer(GravityCompat.END));


    }

    private void initView() {

        menu = findViewById(R.id.menu);
        mDrawerLayout =  findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        toolbar =  findViewById(R.id.toolbar);
        bubbleTabBar =  findViewById(R.id.bubbleTabBar);

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position==0){
                startActivity(new Intent(activity, VehicleActivity.class));
                mDrawerLayout.closeDrawers();
                return;
            }
            if (position==1){

                startActivity(new Intent(activity, HistoryFragment.class));
                mDrawerLayout.closeDrawers();
                return;
            }

        }

    }

    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
        mDrawerToggle.setDrawerIndicatorEnabled(false);

    }








}