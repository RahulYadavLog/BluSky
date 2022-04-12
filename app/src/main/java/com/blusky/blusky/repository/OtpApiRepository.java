package com.blusky.blusky.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blusky.blusky.model.OtpModel;
import com.blusky.blusky.retrofit.ApiRequest;
import com.blusky.blusky.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpApiRepository {

    private static final String TAG = OtpApiRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public OtpApiRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<OtpModel> getOtpResponseLiveData(String phone, String otp) {
        final MutableLiveData<OtpModel> data = new MutableLiveData<>();
        apiRequest.callOtpApi(phone, otp)
                .enqueue(new Callback<OtpModel>() {


                    @Override
                    public void onResponse(Call<OtpModel> call, Response<OtpModel> response) {

                        Log.e(TAG, "onResponse response:: " + response);
                        Log.e(TAG, "body:: " + response.body());



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<OtpModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }


}
