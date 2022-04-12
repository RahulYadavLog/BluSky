package com.blusky.blusky.constants;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String name = "nameKey";
    public static final String photo = "photo";
    public static final String pass = "passwordKey";
    public static final String UserId = "Id";
    public static final String mobile = "mobile";
    public static boolean SESSION_FLAG = false;
    Context _session_acti;
    SharedPreferences sharedpreferences;

    public SessionManager(Context _session_acti) {
        super();
        this._session_acti = _session_acti;
    }

    public void SessionStore(String userid1, String name1, String photo1, String mobile1) {
        sharedpreferences = _session_acti.getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(name, name1);
        editor.putString(photo, photo1);
        editor.putString(UserId, userid1);
        editor.putString(mobile, mobile1);
        editor.commit();
    }


    public void SessionClear() {

        SharedPreferences sharedpreferences = _session_acti
                .getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }


    public boolean IsSessionCheckOrCreated() {

        sharedpreferences = _session_acti.getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(name)) {
            if (sharedpreferences.contains(pass)) {
                SESSION_FLAG = true;
            }
        }
        return SESSION_FLAG;
    }

    public String PickValue(String Key) {
        String output = "";
        sharedpreferences = _session_acti.getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        output = sharedpreferences.getString(Key, null);
        return output;
    }

    public String PickUserId(String Key) {
        String output = "0";
        sharedpreferences = _session_acti.getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        output = sharedpreferences.getString(Key, null);
        return output;
    }

}
