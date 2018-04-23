package vehiman.amoebiq.android.com.vehiman.utilities;

import android.util.Log;

import java.util.Date;

/**
 * Created by skadavath on 4/23/18.
 */

public class VehimanUtils {

    private static final String TAG = VehimanUtils.class.getName();

    public static String getHumanReadableDate(Date d1, Date d2) {

        Log.e(TAG,"Starting date readable form");

        long diff = d2.getTime() - d1.getTime();

        long diffDays = (int) (diff / (1000 * 60 * 60 * 24));

        return diffDays+" days";

    }
}
