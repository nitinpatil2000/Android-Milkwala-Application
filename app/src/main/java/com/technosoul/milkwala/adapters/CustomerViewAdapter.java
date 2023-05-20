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
import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class CustomerViewAdapter extends RecyclerView.Adapter<CustomerViewAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Customer> customers;
    private final OnItemSelected onItemSelected;

    public CustomerViewAdapter(Context context, ArrayList<Customer> customers, OnItemSelected onItemSelected) {
        this.context = context;
        this.customers = customers;
        this.onItemSelected = onItemSelected;
    }

    @Override
    public CustomerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewAdapter.ViewHolder holder, int position) {
        holder.customerName.setText(customers.get(position).getCustomerName());
        holder.customerNumber.setText(String.valueOf(customers.get(position).getCustomerNumber()));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView customerName;
        TextView customerNumber;
        ImageView customerImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.customerTxt);
            customerNumber = itemView.findViewById(R.id.masterinfo_customer_subtext);
            customerImg = itemView.findViewById(R.id.customerImg);

            customerImg.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Customer clickedItem = customers.get(position);
                    int customerId = clickedItem.getCustomerId();
                    String customerName = clickedItem.getCustomerName();
                    if (onItemSelected != null) {
                        onItemSelected.onItemClicked(Constants.SELECTED_TYPE_CUSTOMER, customerId,customerName,null);
                    }
                }
            });
        }
    }
}
