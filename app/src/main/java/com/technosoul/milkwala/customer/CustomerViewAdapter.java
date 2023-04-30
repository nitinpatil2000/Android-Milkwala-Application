package com.technosoul.milkwala.customer;

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

import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.Customer;

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
        ImageView customerImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.customerTxt);
            customerNumber = itemView.findViewById(R.id.customerSubText);
            customerImg = itemView.findViewById(R.id.customerImg);

            customerImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Customer clickedItem = customers.get(position);
                        int customerId = clickedItem.getCustomerId();
                        Bundle bundle = new Bundle();
                        bundle.putString("customerName", clickedItem.getCustomerName());
                        bundle.putString("customerAdd", clickedItem.getCustomerAddress());
                        bundle.putString("customerCity", clickedItem.getCustomerCity());
                        bundle.putString("customerNo", clickedItem.getCustomerNumber());
                        CustomerDetailsFragment customerDetailsFragment = new CustomerDetailsFragment(customerId);
                        customerDetailsFragment.setArguments(bundle);
                        FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.container, customerDetailsFragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        ((AppCompatActivity) view.getContext()).getSupportActionBar().setTitle(clickedItem.getCustomerName());
                    }
                }
            });
        }
    }
}
