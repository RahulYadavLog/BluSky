package com.blusky.blusky.view.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.anden.panningview.PanningView;
import com.blusky.blusky.R;
import com.blusky.blusky.ShowCustomAlert;
import com.blusky.blusky.constants.App;
import com.blusky.blusky.constants.DataBridge;
import com.blusky.blusky.constants.SessionManager;
import com.blusky.blusky.model.ForgetModel;
import com.blusky.blusky.view.booking.BookingActivity;
import com.blusky.blusky.view.landing.MainActivity;
import com.blusky.blusky.view_model.ForgetViewModel;
import com.blusky.blusky.view_model.LoginViewModel;
import com.blusky.blusky.view_model.OtpViewModel;
import com.marozzi.roundbutton.RoundButton;

import org.jetbrains.annotations.NotNull;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressPie;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Activity activity;

    PanningView panningView;
    ImageView loginBottomImage;
    TextView signUp,forgetPassword;
    RoundButton login;
    LoginViewModel loginViewModel;
    EditText phone,password;
    ACProgressPie dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activity = this;
        initialization();
        checkOrientation();




    }

    private void checkOrientation() {
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            panningView.setVisibility(View.GONE);
            loginBottomImage.setVisibility(View.GONE);
        } else {
            panningView.setVisibility(View.VISIBLE);
            loginBottomImage.setVisibility(View.VISIBLE);
        }



    }

    private void initialization() {

        panningView=findViewById(R.id.panningView);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        loginBottomImage=findViewById(R.id.loginBottomImage);
        signUp=findViewById(R.id.signup);
        signUp.setOnClickListener(this);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        forgetPassword=findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(this);


    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            panningView.setVisibility(View.GONE);
            loginBottomImage.setVisibility(View.GONE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            panningView.setVisibility(View.VISIBLE);
            loginBottomImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signup:
                startActivity(new Intent(activity,SignupActivity.class));
                break;
            case R.id.login:
                validateField();
                break;
            case R.id.forgetPassword:
                showForgetPassword();
                break;
            default:
                break;
        }
    }

    private void validateField() {

        if (phone.getText().toString().length()!=10){
            phone.setError("phone must be 10 digit");
        }else if (password.getText().toString().length()<5){
            password.setError("password must contain at least 5 characters");
        }else {
            login.startAnimation();
            callApiLogin();
        }

    }

    private void callApiLogin() {

        // View Model
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getLoginResponseLiveData(phone.getText().toString().trim(),password.getText().toString().trim()).observe(this, loginResponse -> {
            if (loginResponse != null) {
                Log.e("articleResponse ", App.getGson().toJson(loginResponse)+"  -->");
                if (loginResponse.getSuccess()){
                    SessionManager saveData = new SessionManager(activity);
                    saveData.SessionStore(loginResponse.getToken(), loginResponse.getToken(), loginResponse.getToken(), phone.getText().toString());

                    startActivity(new Intent(activity, MainActivity.class));
                    finishAffinity();
                }else {

                }

            }
        });
    }


    private void showForgetPassword() {

        Dialog bottomDialog = new Dialog(activity, R.style.BottomDialog);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.forget_password, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = activity.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        Button bookNow=bottomDialog.findViewById(R.id.bookNow);
        EditText forgetPhone=bottomDialog.findViewById(R.id.forgetPhone);
        ImageView hideSheet=bottomDialog.findViewById(R.id.hideSheet);

        hideSheet.setOnClickListener(v -> {
            bottomDialog.dismiss();
        });

        bookNow.setOnClickListener(v -> {
            if (forgetPhone.getText().toString().length()!=10){
                forgetPhone.setError("Mobile must be 10 digit");
            }else {
                bottomDialog.dismiss();
                dialog = new ACProgressPie.Builder(this)
                        .ringColor(Color.WHITE)
                        .pieColor(Color.WHITE)
                        .updateType(ACProgressConstant.PIE_AUTO_UPDATE)
                        .build();
                dialog.show();

                callApiForget(forgetPhone.getText().toString());
            }

        });

        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();



    }


    private void callApiForget(String forgetPhone) {

        // View Model
        ForgetViewModel forgetModel = ViewModelProviders.of(this).get(ForgetViewModel.class);
        forgetModel.getForgetResponseLiveData(forgetPhone).observe(this, response -> {
            if (response != null) {
                Log.e("articleResponse ", App.getGson().toJson(response)+"  -->");
                if (response.getSuccess()){
                    dialog.dismiss();

                    Intent intent=new Intent(activity, ForgetOtpActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", forgetPhone);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }else {

                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (DataBridge.resetPassword){
            ShowCustomAlert.showPositiveAlert(activity,"Success","Your password has been updated successfully please log in to continue ","Ok");
        }
    }
}
