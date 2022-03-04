package com.mahmoudbashir.onyxdelivery.local;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPreference {
    Context context;
    private static final String SHARED_PREF_USER = "OnyxDelivery";

    private static SharedPreference sharedPrefranceManager;

    private SharedPreference(Context context) {
        this.context = context;
    }

    public synchronized static SharedPreference getInastance(Context context) {
        if (sharedPrefranceManager == null) {
            sharedPrefranceManager = new SharedPreference(context);
        }
        return sharedPrefranceManager;
    }


    public void saveDeliveryInfo(String deliveryName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //editor.clear();
        editor.putString("deliveryName", deliveryName);

        editor.putBoolean("userLogged", true);
        editor.apply();
    }

    public String getDeliveryName(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_USER, Context.MODE_PRIVATE);
        return sharedPreferences.getString("deliveryName", "");
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_USER, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("userLogged", false);
    }
}