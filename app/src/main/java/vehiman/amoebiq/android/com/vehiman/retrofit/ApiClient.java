package vehiman.amoebiq.android.com.vehiman.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by skadavath on 4/12/18.
 */

public class ApiClient {

    private static final String BASE_URL = "http://10.0.2.2:8080";

    private static final String TAG = ApiClient.class.getName();

    private static Retrofit retrofit = null;

    private static Gson gson = null;

    static {

        gson = new GsonBuilder()
                .setDateFormat("dd-MMM-yyyy")
                .create();

    }
    public static Retrofit getClient() {


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}
