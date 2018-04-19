package vehiman.amoebiq.android.com.vehiman.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.adapters.DashboardAdapter;
import vehiman.amoebiq.android.com.vehiman.fragment.DashboardItemFragment;
import vehiman.amoebiq.android.com.vehiman.model.ServiceDetails;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiClient;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiInterface;
import vehiman.amoebiq.android.com.vehiman.utilities.SessioManager;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = Dashboard.class.getName();
    private NavigationView navigationView;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>My Dashboard</font>"));
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //loadServices();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_close, R.string.drawer_open) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        toggle.syncState();

        fragment(new DashboardItemFragment(),"Dashbpard");

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.e(TAG,"Nav item clicked");
        int id = item.getItemId();
        if(id==R.id.vehicle_nav_item) {
            Log.e(TAG,"Vehicle selected");
            Intent intent = new Intent(Dashboard.this,VehicleItemListActivity.class);
            startActivity(intent);
        }
        if(id==R.id.service_nav_item) {
            Intent intent = new Intent(Dashboard.this,ServiceItemListActivity.class);
            startActivity(intent);
        }
        return false;
    }



    public void fragment(Fragment fragment, String transaction) {
        tag = transaction;
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_transaction, fragment, transaction);
        fragmentTransaction.addToBackStack(transaction);
        fragmentTransaction.commit();
        Log.d("backFragment", tag);
    }




}
