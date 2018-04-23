package vehiman.amoebiq.android.com.vehiman.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.model.ServiceDetails;
import vehiman.amoebiq.android.com.vehiman.utilities.Constants;
import vehiman.amoebiq.android.com.vehiman.utilities.VehimanUtils;

/**
 * Created by skadavath on 4/19/18.
 */

public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = DashboardAdapter.class.getName();

    private List<ServiceDetails> serviceDetails;

    public DashboardAdapter(List<ServiceDetails> serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item_list, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final DashboardViewHolder dashboardViewHolder = (DashboardViewHolder) holder;

        String vehicle = serviceDetails.get(position).getVehicle().getBrand() + " " + serviceDetails.get(position).getVehicle().getType();
        dashboardViewHolder.vehicle.setText(vehicle);
        dashboardViewHolder.serviceType.setText(serviceDetails.get(position).getServiceType());
        dashboardViewHolder.serviceDate.setText(new SimpleDateFormat(Constants.DATE_FORMAT).format(serviceDetails.get(position).getServiceDate()));
        dashboardViewHolder.nextServiceOdo.setText(String.valueOf(serviceDetails.get(position).getNextServiceOdo()));

        try {


            Date serviceDate = serviceDetails.get(position).getServiceDate();
            Date nextServiceDate = serviceDetails.get(position).getNextServiceDate();

            dashboardViewHolder.nextServiceDate.setText(VehimanUtils.getHumanReadableDate(serviceDate, nextServiceDate));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return serviceDetails.size();
    }
}


class DashboardViewHolder extends RecyclerView.ViewHolder {

    TextView serviceType, serviceDate, vehicle, nextServiceDate,nextServiceOdo;

    public DashboardViewHolder(View view) {
        super(view);
        serviceType = (TextView) view.findViewById(R.id.service_type_tv);
        serviceDate = (TextView) view.findViewById(R.id.service_date_tv);
        vehicle = (TextView) view.findViewById(R.id.service_vehicle_tv);
        nextServiceDate = (TextView) view.findViewById(R.id.next_service_date_tv);
        nextServiceOdo = (TextView) view.findViewById(R.id.next_service_odo);
    }


}