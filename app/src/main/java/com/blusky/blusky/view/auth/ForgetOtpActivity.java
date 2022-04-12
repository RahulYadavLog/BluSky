package com.blusky.blusky.view.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.blusky.blusky.R;
import com.blusky.blusky.ShowCustomAlert;
import com.blusky.blusky.constants.App;
import com.blusky.blusky.constants.SessionManager;
import com.blusky.blusky.view.landing.MainActivity;
import com.blusky.blusky.view_model.ForgetOtpViewModel;
import com.blusky.blusky.view_model.OtpViewModel;
import com.goodiebag.pinview.Pinview;
import com.marozzi.roundbutton.RoundButton;


public class ForgetOtpActivity extends AppCompatActivity implements View.OnClickListener{

    RoundButton next;
    String phone;
    TextView messagePhn;
    Pinview pinview;
    Activity activity;
    String pinValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        activity=this;

        Bundle bundle = getIntent().getExtras();
        phone = bundle.getString("phone");

        initialization();

        pinview.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                pinValue=pinview.getValue();
                //Make api calls here or what not
               // Toast.makeText(activity, pinview.getValue(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initialization() {
        next=findViewById(R.id.next);
        next.setOnClickListener(this);
        messagePhn=findViewById(R.id.messagePhn);
        messagePhn.setText("We have sent you SMS with a code to your number +971- "+phone);
        pinview=findViewById(R.id.pinview);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.next:

                validate();


                break;

            default:
                break;
        }
    }

    private void validate() {

        if (pinValue.length()!=6){
            ShowCustomAlert.showErrorAlert(activity,"Error","otp must be 6 digit","Ok");
        }else {
            next.startAnimation();
            callApiOtp();
        }
    }

    private void callApiOtp() {

        // View Model
        ForgetOtpViewModel otpViewModel = ViewModelProviders.of(this).get(ForgetOtpViewModel.class);
        otpViewModel.getOtpResponseLiveData(phone,pinValue).observe(this, response -> {
            if (response != null) {
                Log.e("articleResponse ", App.getGson().toJson(response)+"  -->");
                if (response.getSuccess()){

                    Intent intent=new Intent(activity, ResetPasswordActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", phone);
                    bundle.putString("token",response.getToken());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {

                }

            }
        });
    }
}