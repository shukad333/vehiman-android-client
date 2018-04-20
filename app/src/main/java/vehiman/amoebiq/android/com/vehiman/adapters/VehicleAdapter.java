package vehiman.amoebiq.android.com.vehiman.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.model.Vehicle;

/**
 * Created by skadavath on 4/12/18.
 */

public class VehicleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = VehicleAdapter.class.getName();
    private List<Vehicle> vehicles;
    private View mView;
    private static Map<Integer,Integer> iconMap = new HashMap<>();

    static {
        iconMap.put(0,R.drawable.not_found);
        iconMap.put(2,R.drawable.two_wheeler);
        iconMap.put(3,R.drawable.rickshaw);
        iconMap.put(4,R.drawable.car);
    }

    public VehicleAdapter(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicleitem_list,parent,false);
        mView = view;
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final VehicleViewHolder vehicleViewHolder = (VehicleViewHolder)holder;

        String brand = vehicles.get(position).getBrand();
        String type = vehicles.get(position).getType();
        String number = vehicles.get(position).getNumber();

        vehicleViewHolder.vehicleMake.setText(brand);
        vehicleViewHolder.vehicleType.setText(type);
        vehicleViewHolder.vehicleNumber.setText(number);
        Log.e(TAG,vehicles.get(position).getNoOfWheels()+"");
        vehicleViewHolder.wheelTypeImage.setBackground(mView.getContext().getDrawable(iconMap.get(vehicles.get(position).getNoOfWheels())));
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }
}

class VehicleViewHolder extends RecyclerView.ViewHolder {

    TextView vehicleMake,vehicleType,vehicleNumber;
    ImageView wheelTypeImage;

    public VehicleViewHolder(View view) {
        super(view);
        vehicleMake = (TextView) view.findViewById(R.id.id_vehicle_make);
        vehicleType = (TextView) view.findViewById(R.id.id_vehicle_type);
        vehicleNumber = (TextView) view.findViewById(R.id.id_vehicle_number);
        wheelTypeImage = (ImageView) view.findViewById(R.id.wheel_type_image);
    }
}
