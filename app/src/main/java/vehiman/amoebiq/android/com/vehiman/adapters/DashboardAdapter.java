package vehiman.amoebiq.android.com.vehiman.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.model.ServiceDetails;

/**
 * Created by skadavath on 4/19/18.
 */

public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ServiceDetails> serviceDetails;

    public DashboardAdapter(List<ServiceDetails> serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item_list,parent,false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final DashboardViewHolder dashboardViewHolder = (DashboardViewHolder) holder;

        dashboardViewHolder.serviceType.setText(serviceDetails.get(position).getServiceType());

    }

    @Override
    public int getItemCount() {
        return serviceDetails.size();
    }
}


class DashboardViewHolder extends RecyclerView.ViewHolder {

    TextView serviceType;
    public  DashboardViewHolder(View view) {
        super(view);
        serviceType = (TextView) view.findViewById(R.id.service_type_tv);
    }



}