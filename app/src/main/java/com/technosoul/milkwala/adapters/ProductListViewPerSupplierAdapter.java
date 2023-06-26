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
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class ProductListViewPerSupplierAdapter extends RecyclerView.Adapter<ProductListViewPerSupplierAdapter.ViewHolder> {
    private final OnItemSelected onItemSelected;
    Context context;
    ArrayList<ProductFromServer> productFromServers;

    public ProductListViewPerSupplierAdapter(Context context, ArrayList<ProductFromServer> productFromServers, OnItemSelected onItemSelected) {
        this.context = context;
        this.productFromServers = productFromServers;
        this.onItemSelected = onItemSelected;
    }

    @NonNull
    @Override
    public ProductListViewPerSupplierAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_details_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewPerSupplierAdapter.ViewHolder holder, int position) {
        holder.productId = productFromServers.get(position).getProductId();
        holder.productItems.setText(productFromServers.get(position).getProductName());
        holder.productUnit.setText(productFromServers.get(position).getProductUnit());
        holder.productMrp.setText(String.valueOf(productFromServers.get(position).getProductMrpRetailerRate()));
    }

    @Override
    public int getItemCount() {
        return productFromServers.size();
    }

    public void filteredList(ArrayList<ProductFromServer> filterProductDetails) {
        productFromServers = filterProductDetails;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        int productId;
        TextView productItems;
        TextView productUnit;
        TextView productMrp;
        ImageView productImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productItems = itemView.findViewById(R.id.productItems);
            productUnit = itemView.findViewById(R.id.productUnit);
            productMrp = itemView.findViewById(R.id.productMrp);
            productImg = itemView.findViewById(R.id.productImg);

            productImg.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ProductFromServer clickedItem = productFromServers.get(position);
                    int productId = clickedItem.getProductId();
                    String productDetailsName = clickedItem.getProductName();
                    if (onItemSelected != null) {
                        onItemSelected.onItemClicked(Constants.SELECTED_TYPE_PRODUCT,
                                productId, productDetailsName, null);
                    }
                }
            });
        }
    }
}
