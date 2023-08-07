package com.technosoul.milkwala.todaydeliver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.customerorder.CustomerOrder;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;

import java.util.ArrayList;

public class DeliveryProductsAdapter extends RecyclerView.Adapter<DeliveryProductsAdapter.ViewHolder>{
    Context context;
    ArrayList<ProductFromServer> productFromServers;

    public DeliveryProductsAdapter(Context context, ArrayList<ProductFromServer> productFromServers){
        this.context = context;
        this.productFromServers = productFromServers;
    }

    ArrayList<ProductFromServer> getProductListFromServers(){
        return productFromServers;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.today_deliver_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DeliveryProductsAdapter.ViewHolder holder, int position) {
        holder.deliveryBoyProductName.setText(productFromServers.get(position).getProductName());
        holder.deliveryBoyProductUnit.setText(productFromServers.get(position).getProductUnit());
        holder.deliveryBoyProductAmount.setText(String.valueOf(productFromServers.get(position).getProductMrpRetailerRate()));


    }

    @Override
    public int getItemCount() {
        return productFromServers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView deliveryBoyProductName, deliveryBoyProductUnit, deliveryBoyProductAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deliveryBoyProductName = itemView.findViewById(R.id.deliveryBoyProductName);
            deliveryBoyProductUnit = itemView.findViewById(R.id.deliveryBoyProductUnit);
            deliveryBoyProductAmount = itemView.findViewById(R.id.deliveryBoyProductAmount);
        }
    }
}
