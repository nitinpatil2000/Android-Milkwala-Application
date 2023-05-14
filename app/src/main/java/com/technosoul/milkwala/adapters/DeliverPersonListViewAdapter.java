package com.technosoul.milkwala.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.DeliveryPerson;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class DeliverPersonListViewAdapter extends RecyclerView.Adapter<DeliverPersonListViewAdapter.ViewHolder> {
    private final OnItemSelected onItemSelected;
    Context context;
    ArrayList<DeliveryPerson> deliveryPerson;

    public DeliverPersonListViewAdapter(Context context, ArrayList<DeliveryPerson> deliveryPerson, OnItemSelected onItemSelected) {
        this.context = context;
        this.deliveryPerson = deliveryPerson;
        this.onItemSelected = onItemSelected;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delivery_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.deliveryBoyName.setText(deliveryPerson.get(position).getDeliveryBoyName());
        holder.deliveryBoyNumber.setText(deliveryPerson.get(position).getDeliveryBoyNumber());
    }

    @Override
    public int getItemCount() {
        return deliveryPerson.size();
    }

    public void filteredList(ArrayList<DeliveryPerson> filterDeliveryPerson) {
        deliveryPerson = filterDeliveryPerson;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView deliveryBoyName, deliveryBoyNumber;
        ImageView deliverImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deliveryBoyName = itemView.findViewById(R.id.deliveryBoyName);
            deliveryBoyNumber = itemView.findViewById(R.id.deliveryBoyNumber);
            deliverImg = itemView.findViewById(R.id.deliverImg);

            deliverImg.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    DeliveryPerson clickedItem = deliveryPerson.get(position);
                    int deliveryId = clickedItem.getDeliverBoyId();
                    String deliveryBoyName = clickedItem.getDeliveryBoyName();
                    if (onItemSelected != null) {
                        onItemSelected.onItemClicked(Constants.SELECTED_TYPE_DELIVERY_PERSON, deliveryId, deliveryBoyName, null);
                    }
                }
            });
        }
    }
}
