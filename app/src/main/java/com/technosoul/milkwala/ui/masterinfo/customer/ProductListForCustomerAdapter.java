package com.technosoul.milkwala.ui.masterinfo.customer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;

import java.util.ArrayList;
import java.util.List;

public class ProductListForCustomerAdapter extends RecyclerView.Adapter<ProductListForCustomerAdapter.ViewHolder> {
    Context context;
    ArrayList<ProductFromServer> productFromServerArrayList;
    List<Integer> selectedProductIds = new ArrayList<>();



    public ProductListForCustomerAdapter(Context context, ArrayList<ProductFromServer> productFromServerArrayList){
        this.context = context;
        this.productFromServerArrayList = productFromServerArrayList;
    }

    @NonNull
    @Override
    public ProductListForCustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_for_customer_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListForCustomerAdapter.ViewHolder holder, int position) {
        ProductFromServer productPositionFromServer = productFromServerArrayList.get(position);
        holder.productNameForCustomer.setText(productPositionFromServer.getProductName());
        holder.productUnitForCustomer.setText(productPositionFromServer.getProductUnit());
        holder.productMrpForCustomer.setText(String.valueOf(productPositionFromServer.getProductMrpRetailerRate()));

        holder.productCheckBox.setChecked(selectedProductIds.contains(productPositionFromServer.getProductId()));

        holder.productCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            productPositionFromServer.setChecked(isChecked); // Update the isChecked status of the item
            if (isChecked) {
                selectedProductIds.add(productPositionFromServer.getProductId());
                holder.itemView.setBackgroundColor(Color.YELLOW);
            } else {
                selectedProductIds.remove(Integer.valueOf(productPositionFromServer.getProductId()));
                holder.itemView.setBackgroundColor(Color.WHITE);
            }
        });
    }

    public List<Integer> getSelectedProductIds(){
        return selectedProductIds;
    }


    @Override
    public int getItemCount() {
        return productFromServerArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productNameForCustomer, productUnitForCustomer, productMrpForCustomer;
        CheckBox productCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productNameForCustomer = itemView.findViewById(R.id.productNameForCustomer);
            productUnitForCustomer = itemView.findViewById(R.id.productUnitForCustomer);
            productMrpForCustomer = itemView.findViewById(R.id.productMrpForCustomer);
            productCheckBox = itemView.findViewById(R.id.product_checkbox);
        }
    }
}
