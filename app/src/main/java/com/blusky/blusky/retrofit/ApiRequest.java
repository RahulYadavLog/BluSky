package com.blusky.blusky.retrofit;

import android.database.Observable;

import com.blusky.blusky.model.AddVehicleModel;
import com.blusky.blusky.model.ForgetModel;
import com.blusky.blusky.model.ForgetOtpModel;
import com.blusky.blusky.model.LoginModel;
import com.blusky.blusky.model.OtpModel;
import com.blusky.blusky.model.ResetPasswordModel;
import com.blusky.blusky.model.SignupModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiRequest {

    @POST("login")
    Call<LoginModel> callLoginApi(
            @Query("phone") String phone,
            @Query("password") String password
    );

    @POST("register")
    Call<SignupModel> callSignupApi(
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("password") String password
    );

    @POST("verify-otp")
    Call<OtpModel> callOtpApi(
            @Query("phone") String phone,
            @Query("otp") String otp
    );

    @POST("forgot-password")
    Call<ForgetModel> callForgetApi(
            @Query("phone") String phone
    );


    @POST("verify-forgot-otp")
    Call<ForgetOtpModel> callForgetOtpApi(
            @Query("phone") String phone,
            @Query("otp") String otp
    );

    @POST("reset-password")
    Call<ResetPasswordModel> callResetPasswordApi(
            @Query("phone") String phone,
            @Query("token") String token,
            @Query("password") String password
    );

    @Multipart
    @POST("user/vehicle/add")
    Call<AddVehicleModel> callAddVehicleApi(
            @Header("Authorization") String Authorization,
            @Query("registration_no") String registration_no,
            @Query("vehicle_type") String vehicle_type,
            @Part MultipartBody.Part image,
            @Query("make") String make,
            @Query("color") String color,
            @Query("primary") String primary
    );
}