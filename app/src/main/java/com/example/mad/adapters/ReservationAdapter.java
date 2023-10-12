// ReservationAdapter.java
package com.example.mad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mad.models.ReservationItem;
import com.example.mad.R;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    private Context context;
    private List<ReservationItem> reservationItems;

    public ReservationAdapter(Context context, List<ReservationItem> reservationItems) {
        this.context = context;
        this.reservationItems = reservationItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.resevation_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReservationItem reservationItem = reservationItems.get(position);

        // Bind data to the views in the ViewHolder
        holder.reservationIdTextView.setText(reservationItem.getReservationId());
        holder.startCityTextView.setText(reservationItem.getStartCity());
        holder.endCityTextView.setText(reservationItem.getEndCity());
        holder.timeTextView.setText(reservationItem.getTime());
        holder.trainNameTextView.setText(reservationItem.getTrainName());
    }

    @Override
    public int getItemCount() {
        return reservationItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView reservationIdTextView;
        TextView startCityTextView;
        TextView endCityTextView;
        TextView timeTextView;
        TextView trainNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            reservationIdTextView = itemView.findViewById(R.id.customer_name_txt);
            startCityTextView = itemView.findViewById(R.id.customer_start_city_txt);
            endCityTextView = itemView.findViewById(R.id.customer_end_city_txt);
            timeTextView = itemView.findViewById(R.id.customer_Time_txt);
            trainNameTextView = itemView.findViewById(R.id.customer_phone_txt);
        }
    }
}
