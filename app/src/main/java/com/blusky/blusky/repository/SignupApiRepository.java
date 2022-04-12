package com.blusky.blusky.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blusky.blusky.model.SignupModel;
import com.blusky.blusky.retrofit.ApiRequest;
import com.blusky.blusky.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupApiRepository {

    private static final String TAG = SignupApiRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public SignupApiRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<SignupModel> getSignupResponseLiveData(String name, String phone, String password) {
        final MutableLiveData<SignupModel> data = new MutableLiveData<>();
        apiRequest.callSignupApi(name, phone, password)
                .enqueue(new Callback<SignupModel>() {


                    @Override
                    public void onResponse(Call<SignupModel> call, Response<SignupModel> response) {

                        Log.e(TAG, "onResponse response:: " + response);
                        Log.e(TAG, "body:: " + response.body());



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<SignupModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }


}
