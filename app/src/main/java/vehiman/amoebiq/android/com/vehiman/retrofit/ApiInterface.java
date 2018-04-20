package vehiman.amoebiq.android.com.vehiman.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import vehiman.amoebiq.android.com.vehiman.model.Owner;
import vehiman.amoebiq.android.com.vehiman.model.ServiceDetails;
import vehiman.amoebiq.android.com.vehiman.model.Vehicle;

/**
 * Created by skadavath on 4/13/18.
 */

public interface ApiInterface {

    @GET("owner/{email}/vehicle")
    public Call<List<Vehicle>> getAllVehicles(@Path("email") String email);

    @POST("auth/signin")
    public Call<Owner> signInOrSignUp(@Body Owner owner);

    @PUT("owner/{email}/vehicle")
    public Call<List<Vehicle>> addVehicles(@Path("email") String email ,@Body List<Vehicle> vehicles);

    @GET("owner/{email}/service")
    public Call<List<ServiceDetails>> getAllServicesOfUser(@Path("email") String email);

    @GET("config/vehicle/wheel")
    public Call<List<Integer>> getAllVehicleWheels();

    @GET("config/vehicle/make/{wheel}")
    public Call<List<String>> getAllVehicleBrands(@Path("wheel") int wheel);

    @GET("config/vehicle/type/{make}")
    public Call<List<String>> getAllVehicleTypes(@Path("make") String make);

}
