package com.technosoul.milkwala.receiveProduct;

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
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;
import com.technosoul.milkwala.ui.masterinfo.products.ProductService;

import java.util.ArrayList;

public class ReceivedProductAdapter extends RecyclerView.Adapter<ReceivedProductAdapter.ViewHolder> {
    Context context;
    ArrayList<ProductFromServer> productFromServers;

    public ReceivedProductAdapter(Context context, ArrayList<ProductFromServer> productFromServers){
        this.context = context;
        this.productFromServers = productFromServers;
    }

    @NonNull
    @Override
    public ReceivedProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.received_product_design, parent, false );
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //TODO this method is used for save button.
//    public ArrayList<ProductFromServer> getProductDetails() {
//        return productFromServers;
//    }

    @Override
    public void onBindViewHolder(@NonNull ReceivedProductAdapter.ViewHolder holder, int position) {
        holder.receivedProductName.setText(productFromServers.get(position).getProductName());
        holder.receivedProductUnit.setText(productFromServers.get(position).getProductUnit());
        holder.receivedProductMrp.setText(String.valueOf(productFromServers.get(position).getProductMrpRetailerRate()));
        holder.totalAmount.setText(String.valueOf(productFromServers.get(position).getProductMrpRetailerRate()));
        holder.setProductFromServer(productFromServers.get(position));
        }

    @Override
    public int getItemCount() {
        return productFromServers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView receivedProductName, receivedProductUnit, receivedProductMrp;
        EditText editQuantity;
        TextView totalAmount;
//        ProductDetails productDetails;
        ProductFromServer productFromServer;

        public void setProductFromServer(ProductFromServer productFromServer) {
            this.productFromServer = productFromServer;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receivedProductName = itemView.findViewById(R.id.receivedProductName);
            receivedProductUnit = itemView.findViewById(R.id.receivedProductUnit);
            receivedProductMrp = itemView.findViewById(R.id.receivedProductMrp);
            editQuantity = itemView.findViewById(R.id.editQuantity);
            totalAmount = itemView.findViewById(R.id.totalAmout);

            String receivedMrp = receivedProductMrp.getText().toString();
            long mrp = Long.parseLong(receivedMrp);

//            editQuantity.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // not needed
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {


                    // calculate the new total amount based on the value of editQuantity
//                    long newQuantity = 0L;
//                    if (!TextUtils.isEmpty(s)) {
//                        newQuantity = Long.parseLong(s.toString());
//                    }
//                    double newTotalAmount = newQuantity * mrp;

                    // update the totalAmount TextView
//                    totalAmount.setText(String.valueOf("Rs " + newTotalAmount));
//                    productDetails.setProductDetailsQuantity(newQuantity);
//                }

//                @Override
//                public void afterTextChanged(Editable s) {
//                     not needed
//                }
//            });
        }
    }
}