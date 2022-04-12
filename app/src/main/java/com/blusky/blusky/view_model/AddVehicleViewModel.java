package com.blusky.blusky.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blusky.blusky.model.AddVehicleModel;
import com.blusky.blusky.repository.AddVehicleApiRepository;

import okhttp3.MultipartBody;

public class AddVehicleViewModel extends AndroidViewModel {

    private AddVehicleApiRepository addVehicleApiRepository;
    private LiveData<AddVehicleModel> addVehicleModelLiveData;



    public AddVehicleViewModel(@NonNull Application application) {
        super(application);
        addVehicleApiRepository = new AddVehicleApiRepository();
    }

    public LiveData<AddVehicleModel> getAddVehicleResponseLiveData(String token, String registration_no, String vehicle_type, MultipartBody.Part image, String make, String color, String primary) {
        this.addVehicleModelLiveData = addVehicleApiRepository.getAddVehicleResponseLiveData(token,registration_no,vehicle_type,image,make,color,primary);
        return addVehicleModelLiveData;
    }


}
