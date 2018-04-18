package vehiman.amoebiq.android.com.vehiman.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vehiman.amoebiq.android.com.vehiman.R;

import vehiman.amoebiq.android.com.vehiman.adapters.VehicleAdapter;
import vehiman.amoebiq.android.com.vehiman.model.Vehicle;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiClient;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiInterface;
import vehiman.amoebiq.android.com.vehiman.utilities.SessioManager;

import java.util.List;

public class VehicleItemListActivity extends AppCompatActivity {

    private static final String TAG = VehicleItemListActivity.class.getName();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicleitem_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>My Vehicles</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_button_add_vehicle);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VehicleItemListActivity.this,AddVehicle.class);
                startActivity(intent);

            }
        });
        loadVehicles();

    }

    private void loadVehicles() {

        Log.d(TAG,"Starting vehicles load");

        final SessioManager sessioManager = new SessioManager(getApplicationContext());
        String email = sessioManager.get("email");

        Call<List<Vehicle>> call;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        call = apiInterface.getAllVehicles(email);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Log.d(TAG,"Recorded email is {} "+email);

        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                Log.d(TAG,response.code()+"");
                List<Vehicle> vehicles = response.body();
                VehicleAdapter vehicleAdapter = new VehicleAdapter(vehicles);
                recyclerView.setAdapter(vehicleAdapter);


            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(VehicleItemListActivity.this,Dashboard.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
