package com.technosoul.milkwala.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.delivery.Deliver;
import com.technosoul.milkwala.delivery.DeliveryDetailsFragment;
import com.technosoul.milkwala.ui.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;

import java.util.ArrayList;

public class DeliverPersonListViewAdapter extends RecyclerView.Adapter<DeliverPersonListViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Deliver> deliver;
    private OnItemSelected onItemSelected;

    public DeliverPersonListViewAdapter(Context context, ArrayList<Deliver> deliver, OnItemSelected onItemSelected) {
        this.context = context;
        this.deliver = deliver;
        this.onItemSelected = onItemSelected;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delivery_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.deliveryBoyName.setText(deliver.get(position).getDeliveryBoyName());
        holder.deliveryBoyNumber.setText(deliver.get(position).getDeliveryBoyNumber());
    }

    @Override
    public int getItemCount() {
        return deliver.size();
    }

    public void filteredList(ArrayList<Deliver> filterDeliver) {
        deliver = filterDeliver;
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

            deliverImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Deliver clickedItem = deliver.get(position);
                        int deliveryId = clickedItem.getDeliverBoyId();
                        Bundle bundle = new Bundle();
                        bundle.putString("Name", clickedItem.getDeliveryBoyName());
                        bundle.putString("Address", clickedItem.getDeliveryBoyAddress());
                        bundle.putString("City", clickedItem.getDeliveryBoyCity());
                        bundle.putString("Contact", clickedItem.getDeliveryBoyNumber());
                        bundle.putString("Alter", clickedItem.getDeliveryBoyAlterNo());
                        DeliveryDetailsFragment deliveryDetailsFragment = new DeliveryDetailsFragment(deliveryId);
                        deliveryDetailsFragment.setArguments(bundle);
                        FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.container, deliveryDetailsFragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        ((AppCompatActivity) itemView.getContext()).getSupportActionBar().setTitle(clickedItem.getDeliveryBoyName());
                    }
                }
            });
        }
    }
}
