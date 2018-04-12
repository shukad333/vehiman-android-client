package vehiman.amoebiq.android.com.vehiman.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import vehiman.amoebiq.android.com.vehiman.model.Vehicle;

/**
 * Created by skadavath on 4/13/18.
 */

public interface ApiInterface {

    @GET("owner/21/vehicles")
    public Call<List<Vehicle>> getAllVehicles();
}
