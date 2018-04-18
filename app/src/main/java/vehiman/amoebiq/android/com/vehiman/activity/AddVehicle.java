package vehiman.amoebiq.android.com.vehiman.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.model.Vehicle;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiClient;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiInterface;
import vehiman.amoebiq.android.com.vehiman.utilities.SessioManager;

public class AddVehicle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button_add_vehicle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVehicle();

            }
        });
    }

    private void addVehicle() {

        final SessioManager sessioManager = new SessioManager(getApplicationContext());
        final ProgressDialog pd = new ProgressDialog(AddVehicle.this);
        pd.setMessage("Saving!!!");
        pd.show();

        EditText vehicleMake = (EditText) findViewById(R.id.vehicle_make_et);
        EditText vehicleNumber = (EditText) findViewById(R.id.vehicle_number_et);
        EditText vehicleType = (EditText) findViewById(R.id.vehicle_type_et);

        String make = vehicleMake.getText().toString();
        String number = vehicleNumber.getText().toString();
        String type = vehicleType.getText().toString();
        String email = sessioManager.get("email");

        Vehicle vehicle = new Vehicle();
        vehicle.setNumber(number);
        vehicle.setBrand(make);
        vehicle.setType(type);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Vehicle>> call = apiInterface.addVehicles(email,Collections.singletonList(vehicle));

        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                if(response.code()==200 || response.code()==201 || response.code()==202) {
                    pd.dismiss();
                    Toast.makeText(AddVehicle.this,"Vehicle Saved!!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddVehicle.this,VehicleItemListActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                    pd.dismiss();
                Toast.makeText(AddVehicle.this,"Error Saving Vehicle!!!",Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });


    }

}
