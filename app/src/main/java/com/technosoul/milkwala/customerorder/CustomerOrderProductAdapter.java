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
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;

import java.util.ArrayList;

public class CustomerOrderProductAdapter extends RecyclerView.Adapter<CustomerOrderProductAdapter.ViewHolder> {

    Context context;
    ArrayList<ProductFromServer> productFromServers;

    public CustomerOrderProductAdapter(Context context, ArrayList<ProductFromServer> productFromServers){
        this.context = context;
        this.productFromServers = productFromServers;
    }

    public CustomerOrderProductAdapter(Context context){
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.customer_order_design, parent, false );
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

//    public List<ProductFromServer> getProductFromServers() {
//        return productFromServers;
//    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cProductDetailsName.setText(productFromServers.get(position).getProductName());
        holder.cProductDetailsUnit.setText(productFromServers.get(position).getProductUnit());
        holder.cProductTotalAmount.setText(String.valueOf(productFromServers.get(position).getProductMrpRetailerRate()));
        holder.setProductListFromServer(productFromServers.get(position));

    }


    @Override
    public int getItemCount() {
        return productFromServers.size();
    }





    public void addItem(ProductFromServer product) {
        productFromServers.add(product);
        notifyItemInserted(productFromServers.size () - 1);
    }

    public ArrayList<ProductFromServer> getProductFromServers() {
        return productFromServers;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cProductDetailsName, cProductDetailsUnit;
        EditText cProductTotalAmount, cProductEditQuantity;
        ProductFromServer productListFromServer;

//        public void setProductFromServer(ProductFromServer productListFromServer) {
//            this.productListFromServer = productListFromServer;
//        }

        public void setProductListFromServer(ProductFromServer productListFromServer){
            this.productListFromServer = productListFromServer;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cProductDetailsName = itemView.findViewById(R.id.cProductDetailsName);
            cProductDetailsUnit = itemView.findViewById(R.id.cProductDetailsUnit);
            cProductTotalAmount = itemView.findViewById(R.id.customerTotalAmount);
            cProductEditQuantity = itemView.findViewById(R.id.cProductEditQuantity);

            cProductEditQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String quantity = editable.toString();
                    if(!TextUtils.isEmpty(quantity)){
                        int receivedProductQuantity = Integer.parseInt(quantity);
                        Float calculateReceivedTotalAmount = receivedProductQuantity * productListFromServer.getProductMrpRetailerRate();
                        cProductTotalAmount.setText(String.valueOf(calculateReceivedTotalAmount));
                    }
                }
            });



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