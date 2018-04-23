package vehiman.amoebiq.android.com.vehiman.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vehiman.amoebiq.android.com.vehiman.R;
import vehiman.amoebiq.android.com.vehiman.model.WheelTypeDialogue;

/**
 * Created by skadavath on 4/22/18.
 */

public class WheelAdapter extends ArrayAdapter<WheelTypeDialogue>{

    private final List<WheelTypeDialogue> list;
    private final Activity context;


    static class ViewHolder {
        protected TextView name;
        protected ImageView flag;
    }



    public WheelAdapter(Activity context, List<WheelTypeDialogue> wheels) {
        super(context, R.layout.dashboard_item_list,wheels);
        this.list = wheels;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.wheel_select, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.wheel_text);
            viewHolder.flag = (ImageView) view.findViewById(R.id.wheel_image);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getType());
        holder.flag.setImageDrawable(list.get(position).getImage());
        return view;
    }
}
