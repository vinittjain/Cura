package com.example.unknown_joker.cura;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Unknown-Joker on 12/12/2017.
 */
public class function {

    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static int getIntDefaults(String key, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(isInteger(preferences.getString(key, null)))
        {
           return Integer.parseInt(preferences.getString(key, null));
        }
        else
        {
            return -1;
        }

    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

}
