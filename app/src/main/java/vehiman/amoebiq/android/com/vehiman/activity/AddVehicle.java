package vehiman.amoebiq.android.com.vehiman.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import vehiman.amoebiq.android.com.vehiman.utilities.Constants;
import vehiman.amoebiq.android.com.vehiman.utilities.SessioManager;

public class AddVehicle extends AppCompatActivity {

    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    Spinner itemSpinner = null;
    Spinner vehicleBrandSpinner = null;
    Spinner typeSpinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Add Vehicle</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemSpinner = (Spinner) findViewById(R.id.vehicle_no_of_wheels);
        vehicleBrandSpinner = (Spinner) findViewById(R.id.vehicle_brand);
        typeSpinner = (Spinner) findViewById(R.id.vehicle_type);

        loadWheels();

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String numOfWheels = itemSpinner.getItemAtPosition(i).toString();
                loadMake(Integer.valueOf(numOfWheels));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        vehicleBrandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String make = vehicleBrandSpinner.getItemAtPosition(i).toString();
                loadType(make);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

        //EditText vehicleMake = (EditText) findViewById(R.id.vehicle_number_et);
        EditText vehicleNumber = (EditText) findViewById(R.id.vehicle_number_et);
        //EditText vehicleType = (EditText) findViewById(R.id.vehicle_number_et);

        String make = vehicleBrandSpinner.getSelectedItem().toString();
        String number = vehicleNumber.getText().toString();
        String type = typeSpinner.getSelectedItem().toString();
        String email = sessioManager.get(Constants.SESSION_EMAIL);
        int noOfWheels = Integer.valueOf(itemSpinner.getSelectedItem().toString());

        Vehicle vehicle = new Vehicle();
        vehicle.setNumber(number);
        vehicle.setBrand(make);
        vehicle.setType(type);
        vehicle.setNoOfWheels(noOfWheels);

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

    private void loadWheels() {

        Call<List<Integer>> call = null;
        call = apiInterface.getAllVehicleWheels();

        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {

                if(response.code()==202 || response.code()==200) {
                    List<Integer> wheels = response.body();

                    ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(AddVehicle.this,android.R.layout.simple_spinner_item,wheels);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
                    itemSpinner.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });

    }

    private void loadMake(final int wheel) {

        Call<List<String>> call = null;
        call = apiInterface.getAllVehicleBrands(wheel);

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code()==202) {

                    List<String> brands = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddVehicle.this,android.R.layout.simple_spinner_item,brands);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
                    vehicleBrandSpinner.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(AddVehicle.this,VehicleItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadType(final String make) {

        Call<List<String>> call = null;
        call = apiInterface.getAllVehicleTypes(make);

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code()==202) {

                    List<String> type = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddVehicle.this,android.R.layout.simple_spinner_item,type);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
                    typeSpinner.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }

}
