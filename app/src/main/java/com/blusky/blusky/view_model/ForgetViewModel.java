package com.blusky.blusky.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blusky.blusky.model.ForgetModel;
import com.blusky.blusky.repository.ForgetApiRepository;

public class ForgetViewModel extends AndroidViewModel {

    private ForgetApiRepository forgetApiRepository;
    private LiveData<ForgetModel> forgetModelLiveData;




    public ForgetViewModel(@NonNull Application application) {
        super(application);
        forgetApiRepository = new ForgetApiRepository();
    }

    public LiveData<ForgetModel> getForgetResponseLiveData(String phone) {
        this.forgetModelLiveData = forgetApiRepository.getOtpResponseLiveData(phone);
        return forgetModelLiveData;
    }


}
