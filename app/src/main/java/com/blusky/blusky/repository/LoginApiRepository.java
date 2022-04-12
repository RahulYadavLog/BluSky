package com.blusky.blusky.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blusky.blusky.model.LoginModel;
import com.blusky.blusky.retrofit.ApiRequest;
import com.blusky.blusky.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginApiRepository {

    private static final String TAG = LoginApiRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public LoginApiRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<LoginModel> getLoginDetails(String query, String key) {
        final MutableLiveData<LoginModel> data = new MutableLiveData<>();
        apiRequest.callLoginApi(query, key)
                .enqueue(new Callback<LoginModel>() {


                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                        Log.e(TAG, "onResponse response:: " + response);
                        Log.e(TAG, "body:: " + response.body());



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }


}
