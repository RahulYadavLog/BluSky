package com.blusky.blusky.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blusky.blusky.model.ForgetModel;
import com.blusky.blusky.retrofit.ApiRequest;
import com.blusky.blusky.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetApiRepository {

    private static final String TAG = ForgetApiRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public ForgetApiRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ForgetModel> getOtpResponseLiveData(String phone) {
        final MutableLiveData<ForgetModel> data = new MutableLiveData<>();
        apiRequest.callForgetApi(phone)
                .enqueue(new Callback<ForgetModel>() {


                    @Override
                    public void onResponse(Call<ForgetModel> call, Response<ForgetModel> response) {

                        Log.e(TAG, "onResponse response:: " + response);
                        Log.e(TAG, "body:: " + response.body());



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ForgetModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }


}
