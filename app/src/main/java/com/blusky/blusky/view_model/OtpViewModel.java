package com.blusky.blusky.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blusky.blusky.model.OtpModel;
import com.blusky.blusky.repository.LoginApiRepository;
import com.blusky.blusky.repository.OtpApiRepository;

public class OtpViewModel extends AndroidViewModel {

    private OtpApiRepository otpApiRepository;
    private LiveData<OtpModel> otpModelLiveData;
   // String phone,password;




    public OtpViewModel(@NonNull Application application) {
        super(application);
        otpApiRepository = new OtpApiRepository();
    }

    public LiveData<OtpModel> getOtpResponseLiveData(String phone, String otp) {
        this.otpModelLiveData = otpApiRepository.getOtpResponseLiveData(phone,otp);
        return otpModelLiveData;
    }


}
