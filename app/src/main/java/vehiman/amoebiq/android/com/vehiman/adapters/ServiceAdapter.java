package vehiman.amoebiq.android.com.vehiman.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.model.ServiceDetails;

/**
 * Created by skadavath on 4/18/18.
 */

public class ServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ServiceDetails> serviceDetails;

    public ServiceAdapter(List<ServiceDetails> details) {
        this.serviceDetails = details;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class ServiceViewHolder extends RecyclerView.ViewHolder{

    public ServiceViewHolder(View view) {
        super(view);
    }

}
