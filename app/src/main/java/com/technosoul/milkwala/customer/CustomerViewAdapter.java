package com.technosoul.milkwala.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;

import java.util.ArrayList;

public class CustomerViewAdapter extends RecyclerView.Adapter<CustomerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Customer> customers;

    public CustomerViewAdapter(Context context, ArrayList<Customer> customers){
        this.context = context;
        this.customers = customers;
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
        holder.customerNumber.setText(customers.get(position).getCustomerNumber());

    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView customerName, customerNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.customerTxt);
            customerNumber = itemView.findViewById(R.id.customerSubText);
        }
    }
}
