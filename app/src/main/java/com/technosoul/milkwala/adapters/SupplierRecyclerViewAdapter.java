package com.technosoul.milkwala.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.db.Supplier;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierEntity;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class SupplierRecyclerViewAdapter extends RecyclerView.Adapter<SupplierRecyclerViewAdapter.ViewHolder> {
    Context context;
//    ArrayList<Supplier> suppliers;
    ArrayList<SupplierEntity> supplierEntityList;

    private OnItemSelected onItemSelected;

//    public SupplierRecyclerViewAdapter(Context context, ArrayList<Supplier> suppliers, OnItemSelected onItemSelected) {
//        this.context = context;
//        this.suppliers = suppliers;
//        this.onItemSelected = onItemSelected;
//    }

    public SupplierRecyclerViewAdapter(Context context, ArrayList<SupplierEntity> supplierEntityList, OnItemSelected onItemSelected){
        this.context = context;
        this.supplierEntityList = supplierEntityList;
        this.onItemSelected = onItemSelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.supplier_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SupplierEntity selectedSupplier = supplierEntityList.get(position);
        int supplierId = selectedSupplier.getSupplierId();
        holder.supplierTxt.setText(supplierEntityList.get(holder.getAdapterPosition()).getSupplierName());
        MyDbHelper myDbHelper = MyDbHelper.getDB(context);
        ArrayList<ProductDetails> productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);
        int numCounterSuppliers = productDetailsList.size();
        holder.supplierCounter.setText(String.format(context.getString(R.string.products_of_supplier), numCounterSuppliers));
    }

    @Override
    public int getItemCount() {
        return supplierEntityList.size();
    }

    //method to add new item to the list
    public void addNewItem(SupplierEntity supplierEntity) {
        supplierEntityList.add(supplierEntity);
        notifyItemInserted(supplierEntityList.size() - 1);
    }

    public SupplierEntity getItem(int position) {
        // Get the Supplier at a specific position in the list
        return supplierEntityList.get(position);
    }

    public void filteredList(ArrayList<SupplierEntity> filterList) {
        supplierEntityList = filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView supplierTxt;
        TextView supplierCounter;
        ImageView suppliersImg;
        LinearLayout supplierLinear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            supplierTxt = itemView.findViewById(R.id.suppliersTxt);
            supplierCounter = itemView.findViewById(R.id.supplierCounter);
            suppliersImg = itemView.findViewById(R.id.suppliersImg);
            supplierLinear = itemView.findViewById(R.id.supplierLinear);

            //show details of the supplier and changing the fragment
            suppliersImg.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    SupplierEntity clickedItem = supplierEntityList.get(position);
                    int supplierId = clickedItem.getSupplierId();
                    String supplierName =  clickedItem.getSupplierName();
                    Bundle bundle = new Bundle();
                    bundle.putString("supplierTxt", clickedItem.getSupplierName());
                    bundle.putString("supplierAddress", clickedItem.getSupplierAddress());
                    bundle.putString("supplierNumber", clickedItem.getSupplierNumber());
                    bundle.putString("supplierAltNumber", clickedItem.getSupplierAltNumber());

                    if (onItemSelected != null) {
                        onItemSelected.onItemClicked(Constants.SELECTED_TYPE_SUPPLIER, supplierId,supplierName, bundle);
                    }

                    ((MasterInfoActivity)context).getSupportActionBar().setTitle(clickedItem.getSupplierName());

                }
            });
        }
    }
}
