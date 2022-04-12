package com.blusky.blusky.view.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blusky.blusky.R;

public class BookingActivity extends AppCompatActivity {

    Button bookNow;
    RelativeLayout bill;
    CardView card2;
    ImageView circle1,circle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        circle1=findViewById(R.id.circle1);
        circle2=findViewById(R.id.circle2);
        bill=findViewById(R.id.bill);
        bookNow=findViewById(R.id.bookNow);
        card2=findViewById(R.id.card2);
        bookNow.setOnClickListener(v -> {
            card2.setVisibility(View.GONE);
            bill.setVisibility(View.VISIBLE);
            circle1.setBackground(getResources().getDrawable(R.drawable.circle_track));
            circle2.setBackground(getResources().getDrawable(R.drawable.circle_track_highlighted));
        });
    }
}