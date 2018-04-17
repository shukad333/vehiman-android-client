package vehiman.amoebiq.android.com.vehiman.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by skadavath on 4/17/18.
 */

public class SessioManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "VehiMan";

    public SessioManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void put(String key , String val) {
        editor.putString(key,val);
        editor.commit();
    }

    public String get(String key) {
        return sharedPreferences.getString(key,null);
    }
}
