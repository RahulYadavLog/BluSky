package com.blusky.blusky.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blusky.blusky.model.ResetPasswordModel;
import com.blusky.blusky.repository.ResetPasswordApiRepository;

public class ResetPasswordViewModel extends AndroidViewModel {

    private ResetPasswordApiRepository resetPasswordApiRepository;
    private LiveData<ResetPasswordModel> resetPasswordModelLiveData;
   // String phone,password;




    public ResetPasswordViewModel(@NonNull Application application) {
        super(application);
        resetPasswordApiRepository = new ResetPasswordApiRepository();
    }

    public LiveData<ResetPasswordModel> getOtpResponseLiveData(String phone, String token, String password) {
        this.resetPasswordModelLiveData = resetPasswordApiRepository.getResetResponseLiveData(phone,token,password);
        return resetPasswordModelLiveData;
    }


}
