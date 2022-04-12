package com.blusky.blusky.view.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.blusky.blusky.R;
import com.blusky.blusky.constants.App;
import com.blusky.blusky.view_model.SignupViewModel;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{


    Activity activity;
    Button signUp;
    EditText name,phone,password,confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        activity = this;

        initialization();



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signUp) {
            validateField();
        }
    }

    private void validateField() {
        if (name.getText().toString().length()==0){
            name.setError("name can't be empty");
        }
        else if (phone.getText().toString().length()!=10){
            phone.setError("phone must be 10 digit");
        }else if (password.getText().toString().length()<5){
            password.setError("password must contain at least 5 characters");
        }else if (!password.getText().toString().equals(confirmPassword.getText().toString())){
            confirmPassword.setError("password mismatch");
        }else {
            callApiLogin();
        }

    }

    private void callApiLogin() {

        // View Model
        SignupViewModel signupViewModel = ViewModelProviders.of(this).get(SignupViewModel.class);
        signupViewModel.getSignupResponseLiveData(name.getText().toString().trim(),phone.getText().toString().trim(),password.getText().toString().trim()).observe(this, registerResponse -> {
            if (registerResponse != null) {
                Log.e("registerResponse ", App.getGson().toJson(registerResponse)+"  -->");
                if (registerResponse.getSuccess()){

                    Intent intent=new Intent(activity, OtpActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", phone.getText().toString().trim());
                    intent.putExtras(bundle);
                    startActivity(intent);

                }else {

                }

            }
        });
    }

    private void initialization() {

        signUp=findViewById(R.id.signUp);
        signUp.setOnClickListener(this);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
    }
}
