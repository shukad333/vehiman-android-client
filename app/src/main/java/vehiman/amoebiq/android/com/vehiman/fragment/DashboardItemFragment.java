package vehiman.amoebiq.android.com.vehiman.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.activity.Dashboard;
import vehiman.amoebiq.android.com.vehiman.adapters.DashboardAdapter;
import vehiman.amoebiq.android.com.vehiman.fragment.dummy.DummyContent;
import vehiman.amoebiq.android.com.vehiman.fragment.dummy.DummyContent.DummyItem;
import vehiman.amoebiq.android.com.vehiman.model.ServiceDetails;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiClient;
import vehiman.amoebiq.android.com.vehiman.retrofit.ApiInterface;
import vehiman.amoebiq.android.com.vehiman.utilities.SessioManager;

import java.util.List;

public class DashboardItemFragment extends Fragment {

    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private static final String TAG = DashboardItemFragment.class.getName();


    public DashboardItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboarditem_list, container, false);

        // Set the adapter

        recyclerView = (RecyclerView) view.findViewById(R.id.service_card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadServices();
        return view;
    }

    private void loadServices() {

        SessioManager sessioManager = new SessioManager(getContext());
        final String email = sessioManager.get("email");
        Log.e(TAG,"Email "+email);
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Loading services");
        pd.show();

        Call<List<ServiceDetails>> call = null;
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);



        call = apiInterface.getAllServicesOfUser(email);
        call.enqueue(new Callback<List<ServiceDetails>>() {
            @Override
            public void onResponse(Call<List<ServiceDetails>> call, Response<List<ServiceDetails>> response) {
                if(response.code()==200 || response.code()==201 || response.code()==202) {
                    pd.dismiss();
                    Log.e(TAG,"Got successful response");
                    List<ServiceDetails> serviceDetails = response.body();
                    DashboardAdapter dashboardAdapter = new DashboardAdapter(serviceDetails);
                    recyclerView.setAdapter(dashboardAdapter);
                    Log.e(TAG,"Total Size "+serviceDetails.size());
                }
            }

            @Override
            public void onFailure(Call<List<ServiceDetails>> call, Throwable t) {

                pd.dismiss();
                Toast.makeText(getContext(),"Error loading from server",Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

    }



}
