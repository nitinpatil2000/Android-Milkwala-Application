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
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.DeliveryFromServer;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DeliverPersonListViewAdapter extends RecyclerView.Adapter<DeliverPersonListViewAdapter.ViewHolder> {
    private final OnItemSelected onItemSelected;
    Context context;
    List<DeliveryFromServer> deliveryListFromServer;

    public DeliverPersonListViewAdapter(Context context, List<DeliveryFromServer> deliveryListFromServer, OnItemSelected onItemSelected) {
        this.context = context;
        this.deliveryListFromServer = deliveryListFromServer;
        this.onItemSelected = onItemSelected;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delivery_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.deliveryBoyName.setText(deliveryListFromServer.get(position).getDeliveryPersonName());
        holder.deliveryBoyNumber.setText(String.valueOf(deliveryListFromServer.get(position).getDeliveryPersonContactNo()));
    }

    @Override
    public int getItemCount() {
        return deliveryListFromServer.size();
    }

    public void filteredList(ArrayList<DeliveryFromServer> filterDeliveryPerson) {
        deliveryListFromServer = filterDeliveryPerson;
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
                    DeliveryFromServer clickedItem = deliveryListFromServer.get(position);
                    int deliveryId = clickedItem.getDeliveryPersonId();
                    String deliveryBoyName = clickedItem.getDeliveryPersonName();
                    if (onItemSelected != null) {
                        onItemSelected.onItemClicked(Constants.SELECTED_TYPE_DELIVERY_PERSON, deliveryId, deliveryBoyName, null);
                    }
                }
            });
        }
    }
}
