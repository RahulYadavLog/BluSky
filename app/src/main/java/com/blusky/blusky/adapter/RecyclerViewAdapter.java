package com.blusky.blusky.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.blusky.blusky.R;
import com.blusky.blusky.model.ParkingImageModel;
import com.blusky.blusky.view.booking.BookingActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyView> implements OnMapReadyCallback{

    private List<ParkingImageModel> list;
    Activity activity;

    MapView mapView;
    GoogleMap map;

    public class MyView extends RecyclerView.ViewHolder {

        public RelativeLayout bookNow;


        public MyView(View view) {
            super(view);

            bookNow =  view.findViewById(R.id.bookNow);


        }
    }


    public RecyclerViewAdapter(List<ParkingImageModel> horizontalList, Activity activity) {
        this.activity=activity;
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.bookNow.setOnClickListener(v -> {
            showFirstBottomDialog();


        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private void showFirstBottomDialog() {

        Dialog bottomDialog = new Dialog(activity, R.style.BottomDialog);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.rectangular_dialog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = activity.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        Button bookNow=bottomDialog.findViewById(R.id.bookNow);
        mapView = bottomDialog.findViewById(R.id.lite_listrow_map);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.getMapAsync(this);
        }
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
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(activity);
        map = googleMap;
        setMapLocation();
    }

    private void setMapLocation() {
        if (map == null) return;


        LatLng sydney = new LatLng(23.159559, 79.915817);
        map.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,6));
        map.animateCamera(CameraUpdateFactory.zoomIn());
        map.animateCamera(CameraUpdateFactory.zoomTo(6), 2000, null);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }





}