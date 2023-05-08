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
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class ProductListViewPerSupplierAdapter extends RecyclerView.Adapter<ProductListViewPerSupplierAdapter.ViewHolder> {
    private final OnItemSelected onItemSelected;
    Context context;
    ArrayList<ProductDetails> productDetails;

    public ProductListViewPerSupplierAdapter(Context context, ArrayList<ProductDetails> productDetails, OnItemSelected onItemSelected) {
        this.context = context;
        this.productDetails = productDetails;
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
        holder.productId = productDetails.get(position).getProductDetailsId();
        holder.productItems.setText(productDetails.get(position).getProductDetailsName());
        holder.productUnit.setText(productDetails.get(position).getProductDetailsUnit());
        holder.productMrp.setText(productDetails.get(position).getProductDetailsMrp());
    }

    @Override
    public int getItemCount() {
        return productDetails.size();
    }

    public void filteredList(ArrayList<ProductDetails> filterProductDetails) {
        productDetails = filterProductDetails;
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
                    if (onItemSelected != null) {
                        onItemSelected.onItemClicked(Constants.SELECTED_TYPE_PRODUCT,
                                productDetails.get(position).getProductDetailsId(), null);
                    }
                }
            });

        }
    }
}
