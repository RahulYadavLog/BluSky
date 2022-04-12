package com.blusky.blusky.view.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.blusky.blusky.R;
import com.blusky.blusky.constants.App;
import com.blusky.blusky.constants.DataBridge;
import com.blusky.blusky.constants.SessionManager;
import com.blusky.blusky.view.landing.MainActivity;
import com.blusky.blusky.view_model.OtpViewModel;
import com.blusky.blusky.view_model.ResetPasswordViewModel;
import com.marozzi.roundbutton.RoundButton;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText password,confirmPassword;
    RoundButton submit;
    String phone,token;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        activity=this;

        Bundle bundle = getIntent().getExtras();
        phone = bundle.getString("phone");
        token = bundle.getString("token");


        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(v -> {
            validate();
        });

    }

    private void validate() {
        if (password.getText().toString().length()<5){
            password.setError("password must contain at least 5 characters");
        }else if (!password.getText().toString().equals(confirmPassword.getText().toString())){
            confirmPassword.setError("password mismatch");
        }else {
            submit.startAnimation();
            callApiReset();
        }
    }

    private void callApiReset() {
        ResetPasswordViewModel otpViewModel = ViewModelProviders.of(this).get(ResetPasswordViewModel.class);
        otpViewModel.getOtpResponseLiveData(phone,token,password.getText().toString().trim()).observe(this, response -> {
            if (response != null) {
                Log.e("articleResponse ", App.getGson().toJson(response)+"  -->");
                if (response.getSuccess()){
                    DataBridge.resetPassword=true;
                    startActivity(new Intent(activity, LoginActivity.class));
                    finishAffinity();
                }else {

                }

            }
        });
    }
}