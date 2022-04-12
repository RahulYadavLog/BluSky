package com.blusky.blusky.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blusky.blusky.model.SignupModel;
import com.blusky.blusky.repository.SignupApiRepository;

public class SignupViewModel extends AndroidViewModel {

    private SignupApiRepository signupApiRepository;
    private LiveData<SignupModel> signupModelLiveData;

    public SignupViewModel(@NonNull Application application) {
        super(application);
        signupApiRepository = new SignupApiRepository();


    }

    public LiveData<SignupModel> getSignupResponseLiveData(String name,String phone, String password) {
        this.signupModelLiveData = signupApiRepository.getSignupResponseLiveData(name,phone,password);
        return signupModelLiveData;
    }


}
