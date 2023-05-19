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
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFromServer;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class SupplierRecyclerViewAdapter extends RecyclerView.Adapter<SupplierRecyclerViewAdapter.ViewHolder> {
    Context context;
//    ArrayList<Supplier> suppliers;
    ArrayList<SupplierFromServer> supplierEntityList;

    private OnItemSelected onItemSelected;

//    public SupplierRecyclerViewAdapter(Context context, ArrayList<Supplier> suppliers, OnItemSelected onItemSelected) {
//        this.context = context;
//        this.suppliers = suppliers;
//        this.onItemSelected = onItemSelected;
//    }

    public SupplierRecyclerViewAdapter(Context context, ArrayList<SupplierFromServer> supplierEntityList, OnItemSelected onItemSelected){
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
        SupplierFromServer selectedSupplier = supplierEntityList.get(position);
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
    public void addNewItem(SupplierFromServer supplierEntity) {
        supplierEntityList.add(supplierEntity);
        notifyItemInserted(supplierEntityList.size() - 1);
    }

    public SupplierFromServer getItem(int position) {
        // Get the Supplier at a specific position in the list
        return supplierEntityList.get(position);
    }

    public void filteredList(ArrayList<SupplierFromServer> filterList) {
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
                    SupplierFromServer clickedItem = supplierEntityList.get(position);
                    int supplierId = clickedItem.getSupplierId();
                    String supplierName =  clickedItem.getSupplierName();
//                    SupplierRetrofitService supplierRetrofitService = new SupplierRetrofitService();
//                    Retrofit retrofit = supplierRetrofitService.getRetrofit();
//                    SupplierService supplierService = retrofit.create(SupplierService.class);
//                    Call<SupplierFromServer> call = supplierService.getSupplierDetails(supplierId);
//                    call.enqueue(new Callback<SupplierFromServer>() {
//                        @Override
//                        public void onResponse(Call<SupplierFromServer> call, Response<SupplierFromServer> response) {
//                            if(response.isSuccessful()){
//                                SupplierFromServer getSupplierDetails = response.body();
//                                if(getSupplierDetails != null){
//
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<SupplierFromServer> call, Throwable t) {
//
//                        }
//                    });
                    Bundle bundle = new Bundle();
                    bundle.putString("supplierTxt", clickedItem.getSupplierName());
                    bundle.putString("supplierAddress", clickedItem.getSupplierAddress());
                    bundle.putString("supplierNumber", String.valueOf(clickedItem.getSupplierNumber()));
                    bundle.putString("supplierAltNumber",String.valueOf(clickedItem.getSupplierAltNumber()));
//
                    if (onItemSelected != null) {
                        onItemSelected.onItemClicked(Constants.SELECTED_TYPE_SUPPLIER, supplierId,supplierName, bundle);
                    }

//                    ((MasterInfoActivity)context).getSupportActionBar().setTitle(clickedItem.getSupplierName());

                }
            });
        }
    }
}
