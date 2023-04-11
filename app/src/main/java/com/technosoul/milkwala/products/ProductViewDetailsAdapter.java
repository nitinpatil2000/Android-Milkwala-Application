package com.technosoul.milkwala.products;

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

import java.util.ArrayList;

public class ProductViewDetailsAdapter extends RecyclerView.Adapter<ProductViewDetailsAdapter.ViewHolder> {
    Context context;
    ArrayList<ProductDetails> productDetails;

    public ProductViewDetailsAdapter(Context context, ArrayList<ProductDetails>productDetails) {
    this.context = context;
    this.productDetails = productDetails;
    }

    @NonNull
    @Override
    public ProductViewDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_details_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewDetailsAdapter.ViewHolder holder, int position) {
        holder.productItems.setText(productDetails.get(position).getProductDetailsName());
        holder.productUnit.setText(productDetails.get(position).getProductDetailsUnit());
        holder.productMrp.setText(productDetails.get(position).getProductDetailsMrp());

    }

    @Override
    public int getItemCount() {
        return productDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productItems, productUnit, productMrp;
        TextView addProductTxt;
        ImageView productImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productItems = itemView.findViewById(R.id.productItems);
            productUnit = itemView.findViewById(R.id.productUnit);
            productMrp = itemView.findViewById(R.id.productMrp);
            productImg = itemView.findViewById(R.id.productImg);

            productImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        ProductDetails clickedItem = productDetails.get(position);

                        Bundle bundle = new Bundle();
                        bundle.putString("viewProductName", clickedItem.getProductDetailsName());
                        bundle.putString("viewProductUnit", clickedItem.getProductDetailsUnit());
                        bundle.putString("viewProductMrp", clickedItem.getProductDetailsMrp());
                        bundle.putString("viewSupplierRate", clickedItem.getProductSupplierRate());
                        bundle.putString("viewVenderRate", clickedItem.getProductVenderRate());

                        ProductViewDetailsFragment productViewDetailsFragment = new ProductViewDetailsFragment();
                        productViewDetailsFragment.setArguments(bundle);
                        FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.container, productViewDetailsFragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        ((AppCompatActivity) view.getContext()).getSupportActionBar().setTitle(clickedItem.getProductDetailsName());

                    }
                }
            });

        }
    }
}
