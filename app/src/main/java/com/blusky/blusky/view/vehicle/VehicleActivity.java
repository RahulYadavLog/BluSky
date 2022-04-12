package com.blusky.blusky.view.vehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blusky.blusky.R;
import com.blusky.blusky.view.auth.SignupActivity;

public class VehicleActivity extends AppCompatActivity implements View.OnClickListener{

    TextView addVehicle;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        activity=this;
        initView();

    }

    private void initView() {

        addVehicle=findViewById(R.id.addVehicle);
        addVehicle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addVehicle) {
            startActivity(new Intent(activity, AddVehicleActivity.class));
        }
    }
}