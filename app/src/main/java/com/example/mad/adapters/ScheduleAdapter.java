package com.example.mad.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.R;
import com.example.mad.models.ScheduleItem;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private Context context;
    private List<ScheduleItem> scheduleItems;

    public ScheduleAdapter(Context context, List<ScheduleItem> scheduleItems) {
        this.context = context;
        this.scheduleItems = scheduleItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleItem scheduleItem = scheduleItems.get(position);

        holder.scheduleIdTextView.setText(scheduleItem.getScheduleId());
        holder.startCityTextView.setText(scheduleItem.getStartCity());
        holder.endCityTextView.setText(scheduleItem.getEndCity());
        holder.timeTextView.setText(scheduleItem.getTime());
        holder.trainNameTextView.setText(scheduleItem.getTrainName());
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView scheduleIdTextView;
        TextView startCityTextView;
        TextView endCityTextView;
        TextView timeTextView;
        TextView trainNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            scheduleIdTextView = itemView.findViewById(R.id.customer_name_txt);
            startCityTextView = itemView.findViewById(R.id.customer_start_city_txt);
            endCityTextView = itemView.findViewById(R.id.customer_end_city_txt);
            timeTextView = itemView.findViewById(R.id.customer_Time_txt);
            trainNameTextView = itemView.findViewById(R.id.customer_phone_txt);
        }
    }
}
