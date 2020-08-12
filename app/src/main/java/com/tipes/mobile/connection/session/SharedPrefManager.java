package com.tipes.mobile.connection.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.tipes.mobile.R;

public class SharedPrefManager {
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(String.valueOf(R.string.SP_FOR_LOGIN_APP), context.MODE_PRIVATE );
        spEditor = sp.edit();
    }
    public void saveSPString(String keySP, String value)
    {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value)
    {
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value)
    {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPIDUsr() { return sp.getString(String.valueOf(R.string.SP_ID_USER_APP),""); }

    public String getSPUsername(){
        return sp.getString(String.valueOf(R.string.SP_USERNAME_APP), "");
    }

    public int getSPRole(){
        return sp.getInt(String.valueOf(R.string.SP_ROLE_APP), 0);
    }

    public String getSPEmail(){
        return sp.getString(String.valueOf(R.string.SP_EMAIL_APP), "");
    }
    public String getSPPswwd(){
        return sp.getString(String.valueOf(R.string.SP_PASSWORD_APP), "");
    }
    public Boolean getSPStillLogin(){
        return sp.getBoolean(String.valueOf(R.string.SP_STILL_LOGIN_APP), false);
    }
}
