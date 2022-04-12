package com.blusky.blusky.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blusky.blusky.model.LoginModel;
import com.blusky.blusky.repository.LoginApiRepository;

public class LoginViewModel extends AndroidViewModel {

    private LoginApiRepository loginApiRepository;
    private LiveData<LoginModel> loginModelLiveData;
   // String phone,password;




    public LoginViewModel(@NonNull Application application) {
        super(application);
        Log.e("22","222");
        loginApiRepository = new LoginApiRepository();


    }

    public LiveData<LoginModel> getLoginResponseLiveData(String phone, String password) {

        Log.e("11","111");
        this.loginModelLiveData = loginApiRepository.getLoginDetails(phone,password);
       // this.phone=phone;
       // this.password=password;
        return loginModelLiveData;
    }


}
