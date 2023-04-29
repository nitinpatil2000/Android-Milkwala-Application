package com.technosoul.milkwala.todaydeliver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.products.ProductDetails;

import java.util.ArrayList;

public class DeliveryProductsAdapter extends RecyclerView.Adapter<DeliveryProductsAdapter.ViewHolder>{
    Context context;
    ArrayList<ProductDetails> productDetailsList;

    public DeliveryProductsAdapter(Context context, ArrayList<ProductDetails> productDetailsList){
        this.context = context;
        this.productDetailsList = productDetailsList;
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
        holder.customerProductName.setText(productDetailsList.get(position).getProductDetailsName());
        holder.customerProductUnit.setText(productDetailsList.get(position).getProductDetailsUnit());


    }

    @Override
    public int getItemCount() {
        return productDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView customerProductName, customerProductUnit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            customerProductName = itemView.findViewById(R.id.customerProductsName);
            customerProductUnit = itemView.findViewById(R.id.customerProductUnit);
        }
    }
}
