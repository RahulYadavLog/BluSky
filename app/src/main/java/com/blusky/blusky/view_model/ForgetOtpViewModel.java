package com.blusky.blusky.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blusky.blusky.model.ForgetOtpModel;
import com.blusky.blusky.repository.ForgetOtpApiRepository;

public class ForgetOtpViewModel extends AndroidViewModel {

    private ForgetOtpApiRepository forgetOtpApiRepository;
    private LiveData<ForgetOtpModel> forgetOtpModelLiveData;




    public ForgetOtpViewModel(@NonNull Application application) {
        super(application);
        forgetOtpApiRepository = new ForgetOtpApiRepository();
    }

    public LiveData<ForgetOtpModel> getOtpResponseLiveData(String phone, String otp) {
        this.forgetOtpModelLiveData = forgetOtpApiRepository.getOtpResponseLiveData(phone,otp);
        return forgetOtpModelLiveData;
    }


}
