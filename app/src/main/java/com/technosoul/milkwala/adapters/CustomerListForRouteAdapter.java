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
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.customer.CustomerFromServer;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class CustomerListForRouteAdapter extends RecyclerView.Adapter<CustomerListForRouteAdapter.ViewHolder> {
    private final OnItemSelected onItemSelected;
    Context context;
    ArrayList<CustomerFromServer> customerFromServers;

    public CustomerListForRouteAdapter(Context context, ArrayList<CustomerFromServer> customerFromServers,OnItemSelected onItemSelected) {
        this.context = context;
        this.customerFromServers = customerFromServers;
        this.onItemSelected = onItemSelected;
    }


    @NonNull
    @Override
    public CustomerListForRouteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListForRouteAdapter.ViewHolder holder, int position) {
        holder.customerNameForRoute.setText(customerFromServers.get(position).getCustomerName());
        holder.customerNoForRoute.setText(String.valueOf(customerFromServers.get(position).getCustomerContactNo()));

    }

    @Override
    public int getItemCount() {
        return customerFromServers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView customerNameForRoute, customerNoForRoute;
        ImageView customerImgForRoute;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerNameForRoute = itemView.findViewById(R.id.txt_customer_name);
            customerNoForRoute = itemView.findViewById(R.id.txt_customer_mobile_no);
            customerImgForRoute = itemView.findViewById(R.id.customer_for_route_img);
            customerImgForRoute.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    CustomerFromServer clickedCustomerItem = customerFromServers.get(position);
                    int customerId = clickedCustomerItem.getCustomerId();
                    String customerName = clickedCustomerItem.getCustomerName();
                    if(onItemSelected != null){
                        onItemSelected.onItemClicked(Constants.SELECTED_TYPE_CUSTOMER, customerId, customerName, null);
                    }

                }
            });
        }
    }
}
