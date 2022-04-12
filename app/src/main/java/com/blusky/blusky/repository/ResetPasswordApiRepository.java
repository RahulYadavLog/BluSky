package com.blusky.blusky.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blusky.blusky.model.ResetPasswordModel;
import com.blusky.blusky.retrofit.ApiRequest;
import com.blusky.blusky.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordApiRepository {

    private static final String TAG = ResetPasswordApiRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public ResetPasswordApiRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ResetPasswordModel> getResetResponseLiveData(String phone, String token, String password) {
        final MutableLiveData<ResetPasswordModel> data = new MutableLiveData<>();
        apiRequest.callResetPasswordApi(phone, token, password)
                .enqueue(new Callback<ResetPasswordModel>() {


                    @Override
                    public void onResponse(Call<ResetPasswordModel> call, Response<ResetPasswordModel> response) {

                        Log.e(TAG, "onResponse response:: " + response);
                        Log.e(TAG, "body:: " + response.body());



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<ResetPasswordModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }


}
