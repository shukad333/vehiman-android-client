package vehiman.amoebiq.android.com.vehiman.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import vehiman.amoebiq.android.com.vehiman.model.Owner;
import vehiman.amoebiq.android.com.vehiman.model.Vehicle;

/**
 * Created by skadavath on 4/13/18.
 */

public interface ApiInterface {

    @GET("owner/{email}/vehicles")
    public Call<List<Vehicle>> getAllVehicles(@Path("email") String email);

    @POST("auth/signin")
    public Call<Owner> signInOrSignUp(@Body Owner owner);

    @PUT("owner/{email}/vehicles")
    public Call<List<Vehicle>> addVehicles(@Path("email") String email ,@Body List<Vehicle> vehicles);
}
