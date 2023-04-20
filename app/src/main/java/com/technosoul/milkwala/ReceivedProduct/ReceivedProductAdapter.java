package com.technosoul.milkwala.ReceivedProduct;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
        holder.totalAmount.setText(productDetails.get(position).getProductDetailsMrp());
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
            editQuantity = itemView.findViewById(R.id.editQuantity);
            totalAmount = itemView.findViewById(R.id.totalAmout);

            String mrpString = receivedProductMrp.getText().toString();
            Long mrp = Long.parseLong(mrpString);

            editQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // not needed
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // calculate the new total amount based on the value of editQuantity
                    Long newQuantity = 0L;
                    if (!TextUtils.isEmpty(s)) {
                        newQuantity = Long.parseLong(s.toString());
                    }
                    double newTotalAmount = newQuantity * mrp;

                    // update the totalAmount TextView
                    totalAmount.setText(String.valueOf("Rs " + newTotalAmount));
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // not needed
                }
            });

        }
    }
}