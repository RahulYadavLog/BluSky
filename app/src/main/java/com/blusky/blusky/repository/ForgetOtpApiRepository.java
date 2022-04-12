package com.blusky.blusky.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blusky.blusky.model.ForgetOtpModel;
import com.blusky.blusky.retrofit.ApiRequest;
import com.blusky.blusky.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetOtpApiRepository {

    private static final String TAG = ForgetOtpApiRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public ForgetOtpApiRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ForgetOtpModel> getOtpResponseLiveData(String phone, String otp) {
        final MutableLiveData<ForgetOtpModel> data = new MutableLiveData<>();
        apiRequest.callForgetOtpApi(phone, otp)
                .enqueue(new Callback<ForgetOtpModel>() {


                    @Override
                    public void onResponse(Call<ForgetOtpModel> call, Response<ForgetOtpModel> response) {

                        Log.e(TAG, "onResponse response:: " + response);
                        Log.e(TAG, "body:: " + response.body());



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ForgetOtpModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }


}
