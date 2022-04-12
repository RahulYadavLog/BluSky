package com.blusky.blusky.constants;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

public class App extends Application {
    private static Gson gson;
    public static Gson getGson(){
        if(gson==null)
            gson=new Gson();
        return gson;
    }
    private static App instance;

    public App() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

}