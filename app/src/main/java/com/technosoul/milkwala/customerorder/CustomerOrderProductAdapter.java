package com.technosoul.milkwala.customerorder;

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

public class CustomerOrderProductAdapter extends RecyclerView.Adapter<CustomerOrderProductAdapter.ViewHolder> {

    Context context;
    ArrayList<ProductDetails> productDetails;
    private int mClickedPosition = RecyclerView.NO_POSITION;

    public CustomerOrderProductAdapter(Context context, ArrayList<ProductDetails> productDetails){
        this.context = context;
        this.productDetails = productDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.customer_order_design, parent, false );
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cProductDetailsName.setText(productDetails.get(position).getProductDetailsName());
        holder.cProductDetailsUnit.setText(productDetails.get(position).getProductDetailsMrp());
        holder.cProductTotalAmount.setText(productDetails.get(position).getProductDetailsMrp());
    }


    @Override
    public int getItemCount() {
        return productDetails.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cProductDetailsName, cProductDetailsUnit;
        EditText cProductTotalAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cProductDetailsName = itemView.findViewById(R.id.cProductDetailsName);
            cProductDetailsUnit = itemView.findViewById(R.id.cProductDetailsUnit);
            cProductTotalAmount = itemView.findViewById(R.id.customerTotalAmount);


//            String mrpString = receivedProductMrp.getText().toString();
//            Long mrp = Long.parseLong(mrpString);

//            editQuantity.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                     not needed
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                     calculate the new total amount based on the value of editQuantity
//                    Long newQuantity = 0L;
//                    if (!TextUtils.isEmpty(s)) {
//                        newQuantity = Long.parseLong(s.toString());
//                    }
//                    double newTotalAmount = newQuantity * mrp;
//
//                     update the totalAmount TextView
//                    totalAmount.setText(String.valueOf("Rs " + newTotalAmount));
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
                    // not needed
//                }
//            });
//        }
//
//        @Override
//        public void onClick(View view) {
//            int position = getAdapterPosition();
//            if(position != RecyclerView.NO_POSITION){
//                setClickedPosition(position, view);
//            }
        }
    }
}