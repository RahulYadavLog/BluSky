package com.blusky.blusky.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blusky.blusky.model.AddVehicleModel;
import com.blusky.blusky.retrofit.ApiRequest;
import com.blusky.blusky.retrofit.RetrofitRequest;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVehicleApiRepository {

    private static final String TAG = AddVehicleApiRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public AddVehicleApiRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<AddVehicleModel> getAddVehicleResponseLiveData(String token, String registration_no, String vehicle_type, MultipartBody.Part image,String make,String color,String primary) {
        final MutableLiveData<AddVehicleModel> data = new MutableLiveData<>();
        apiRequest.callAddVehicleApi(token,registration_no,vehicle_type,image,make,color,primary)
                .enqueue(new Callback<AddVehicleModel>() {

                    @Override
                    public void onResponse(Call<AddVehicleModel> call, Response<AddVehicleModel> response) {

                        Log.e(TAG, "onResponse response:: " + response);
                        Log.e(TAG, "body:: " + response.body());



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<AddVehicleModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }


}
