package vehiman.amoebiq.android.com.vehiman.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.model.ServiceDetails;
import vehiman.amoebiq.android.com.vehiman.model.ServiceTypes;
import vehiman.amoebiq.android.com.vehiman.model.Vehicle;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiClient;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiInterface;
import vehiman.amoebiq.android.com.vehiman.utilities.SessioManager;

public class AddService extends AppCompatActivity {

    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    Map<String,Long> vehicleMap = new HashMap<>();
    SessioManager sessioManager = null;

    Spinner vehicleSpinner;
    Spinner serviceTypeSpinner;

    EditText currentServiceDate;
    EditText nextServiceDate;
    EditText currentOdo;
    EditText nextOdo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Add Service for vehicle</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessioManager = new SessioManager(getApplicationContext());
        vehicleSpinner = (Spinner) findViewById(R.id.vehicle);
        serviceTypeSpinner = (Spinner) findViewById(R.id.service_type);
        currentServiceDate = (EditText) findViewById(R.id.current_service_date);
        nextServiceDate = (EditText) findViewById(R.id.next_service_date);
        currentOdo = (EditText) findViewById(R.id.current_odo_et);
        nextOdo = (EditText) findViewById(R.id.next_odo_et);

        prepareVehicle();
        prepareServiceTypes();

        Button saveButton = (Button) findViewById(R.id.button_add_service);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ServiceDetails serviceDetails = new ServiceDetails();
                try {
                    serviceDetails.setServiceDate(new SimpleDateFormat("dd-MMM-yyyy").parse(currentServiceDate.getText().toString()));
                    serviceDetails.setNextServiceDate(new SimpleDateFormat("dd-MMM-yyyy").parse(nextServiceDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                serviceDetails.setCurrentOdo(Long.parseLong(currentOdo.getText().toString()));
                serviceDetails.setNextOdo(Long.parseLong(nextOdo.getText().toString()));
                serviceDetails.setServiceType(serviceTypeSpinner.getSelectedItem().toString());

                Long vehicleId = vehicleMap.get(vehicleSpinner.getSelectedItem());

                Call<List<ServiceDetails>> call = apiInterface.addService(sessioManager.get("email"),vehicleId, Collections.singletonList(serviceDetails));
                call.enqueue(new Callback<List<ServiceDetails>>() {
                    @Override
                    public void onResponse(Call<List<ServiceDetails>> call, Response<List<ServiceDetails>> response) {
                        if(response.code()==202) {
                            Toast.makeText(AddService.this,"Successfully Added Service",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AddService.this,Dashboard.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ServiceDetails>> call, Throwable t) {

                        Toast.makeText(AddService.this,"Error saving the service!",Toast.LENGTH_LONG).show();
                        t.printStackTrace();

                    }
                });



            }
        });




    }


    private void prepareVehicle() {


        Call<List<Vehicle>> call = apiInterface.getAllVehicles(sessioManager.get("email"));

        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                if(response.code()==202 || response.code()==200) {
                    List<Vehicle> vehicles = response.body();

                    List<String> vehicleNumbers = vehicles.stream().map(v->v.getNumber()).collect(Collectors.toList());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddService.this,android.R.layout.simple_spinner_item,vehicleNumbers);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
                    vehicleSpinner.setAdapter(adapter);

                    for(Vehicle vehicle : vehicles) {
                        vehicleMap.put(vehicle.getNumber(),vehicle.getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {

            }
        });


    }

    private void prepareServiceTypes() {

        Call<List<ServiceTypes>> call = apiInterface.getServiceTypes();
        call.enqueue(new Callback<List<ServiceTypes>>() {
            @Override
            public void onResponse(Call<List<ServiceTypes>> call, Response<List<ServiceTypes>> response) {
                if(response.code()==202 || response.code()==200) {

                    List<ServiceTypes> servicesTypes = response.body();
                    List<String> services = servicesTypes.stream().map(service -> service.getDescription()).collect(Collectors.toList());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddService.this,android.R.layout.simple_spinner_item,services);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
                    serviceTypeSpinner.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<ServiceTypes>> call, Throwable t) {

            }
        });
    }


    public void dateClick(View view) {
        final TextView et = (TextView) view;

        String tag = (String) view.getTag();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this,
                new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker arg0,
                                                  int arg1, int arg2, int arg3) {

                                String month = new DateFormatSymbols().getMonths()[arg2];

                                et.setText(arg3 + "-" + (month.substring(0,3)) + "-" + arg1);


                            }
                        }, year, month, day);

        if(tag.equals("0"))
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
        if(tag.equals("1"))
            dpd.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        dpd.show();


    }

}
