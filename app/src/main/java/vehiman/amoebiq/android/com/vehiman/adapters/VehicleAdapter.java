package vehiman.amoebiq.android.com.vehiman.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.model.Vehicle;

/**
 * Created by skadavath on 4/12/18.
 */

public class VehicleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = VehicleAdapter.class.getName();
    private List<Vehicle> vehicles;

    public VehicleAdapter(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicleitem_list,parent,false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final VehicleViewHolder vehicleViewHolder = (VehicleViewHolder)holder;

        String brand = vehicles.get(position).getBrand();
        Log.d(TAG,brand);
        vehicleViewHolder.vehicleName.setText(brand);
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }
}

class VehicleViewHolder extends RecyclerView.ViewHolder {

    public TextView vehicleName;
    public VehicleViewHolder(View view) {
        super(view);
        vehicleName = (TextView) view.findViewById(R.id.id_vehicle_brand);
    }
}
