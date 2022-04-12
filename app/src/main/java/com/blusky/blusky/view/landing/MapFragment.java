package com.blusky.blusky.view.landing;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;


import com.blusky.blusky.R;
import com.blusky.blusky.view.booking.BookingActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    Activity activity;
    TabHost tabHost;
    View rootView;
    Button book;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_fragment, container, false);

        Log.e("--->", "inside frag");

        activity = getActivity();

        initView();
        setUpTabs();
        setUpMaps(savedInstanceState);

        book.setOnClickListener(v -> {

            showBookSheet();

        });

        return rootView;
    }

    private void initView() {
        tabHost = rootView.findViewById(R.id.tabHost);
        mMapView = rootView.findViewById(R.id.mapView);
        book = rootView.findViewById(R.id.book);
    }

    private void setUpMaps(Bundle savedInstanceState) {


        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(mMap -> {
            googleMap = mMap;
            LatLng sydney = new LatLng(25.364080, 55.388111);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Corniche Street").snippet("Sharjah United Arab Emirates"));

            LatLng latLng =new LatLng(25.328330,55.376900);
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Al Qasba Street 171").snippet("Sharjah United Arab Emirates"));

            LatLng latLng1 =new LatLng(25.333000,55.375090);
            googleMap.addMarker(new MarkerOptions().position(latLng1).title("Al Qasba Street 23-25").snippet("Sharjah United Arab Emirates"));

            LatLng latLng2 =new LatLng(25.352270,55.399300);
            googleMap.addMarker(new MarkerOptions().position(latLng2).title("Hassan Bin Thabet").snippet("Sharjah United Arab Emirates"));


            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(13).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        });

    }

    private void setUpTabs() {


        tabHost.setup();

        TabHost.TabSpec spec;
        spec = tabHost.newTabSpec("Mapview").setIndicator("Mapview")
                .setContent(R.id.general_tab);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("List").setIndicator("List")
                .setContent(R.id.tool_tab);
        tabHost.addTab(spec);


    }

    private void showBookSheet() {

        Dialog bottomDialog = new Dialog(activity, R.style.BottomDialog);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.rectangular_dialog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = activity.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        Button bookNow=bottomDialog.findViewById(R.id.bookNow);

        bookNow.setOnClickListener(v -> {
            bottomDialog.dismiss();
            activity.startActivity(new Intent(activity, BookingActivity.class));

        });

        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();



    }




    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}