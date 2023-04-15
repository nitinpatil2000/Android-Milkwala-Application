package com.technosoul.milkwala.ReceivedProduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.products.ProductDetails;

import java.util.ArrayList;

public class ReceivedProductAdapter extends RecyclerView.Adapter<ReceivedProductAdapter.ViewHolder> {
    Context context;
    ArrayList<ProductDetails> productDetails;

    public ReceivedProductAdapter(Context context, ArrayList<ProductDetails> productDetails){
        this.context = context;
        this.productDetails = productDetails;
    }

    @NonNull
    @Override
    public ReceivedProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.received_product_design, parent, false );
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceivedProductAdapter.ViewHolder holder, int position) {
        holder.receivedProductName.setText(productDetails.get(position).getProductDetailsName());
        holder.receivedProductUnit.setText(productDetails.get(position).getProductDetailsUnit());
        holder.receivedProductMrp.setText(productDetails.get(position).getProductDetailsMrp());
    }

    @Override
    public int getItemCount() {
        return productDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView receivedProductName, receivedProductUnit, receivedProductMrp;
        EditText editQuantity;
        TextView totalAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            receivedProductName = itemView.findViewById(R.id.receivedProductName);
            receivedProductUnit = itemView.findViewById(R.id.receivedProductUnit);
            receivedProductMrp = itemView.findViewById(R.id.receivedProductMrp);

        }
    }
}