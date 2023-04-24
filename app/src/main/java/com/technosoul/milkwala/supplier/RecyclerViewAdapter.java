package com.technosoul.milkwala.supplier;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.helper.MyDbHelper;
import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.products.ProductDetails;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Supplier> suppliers;

    public RecyclerViewAdapter(Context context, ArrayList<Supplier>suppliers){
        this.context = context;
        this.suppliers = suppliers;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.supplier_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Supplier selectedSupplier = suppliers.get(position);
        int supplierId = selectedSupplier.getSupplierId();
        holder.supplierTxt.setText(suppliers.get(holder.getAdapterPosition()).getSupplierName());
        MyDbHelper myDbHelper = MyDbHelper.getDB(context);
        ArrayList<ProductDetails> productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);
        int numCounterSuppliers = productDetailsList.size();
        holder.supplierCounter.setText(String.format("%d Suppliers", numCounterSuppliers));
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    //method to add new item to the list
    public void addNewItem(Supplier supplier) {
        suppliers.add(supplier);
        notifyItemInserted(suppliers.size() - 1);
    }

    public Supplier getItem(int position) {
        // Get the Supplier at a specific position in the list
            return suppliers.get(position);
    }



    public void filteredList(ArrayList<Supplier> filterList) {
        suppliers = filterList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView supplierTxt, supplierCounter;
        ImageView suppliersImg;
        LinearLayout supplierLinear;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            supplierTxt = itemView.findViewById(R.id.suppliersTxt);
            supplierCounter = itemView.findViewById(R.id.supplierCounter);
            suppliersImg = itemView.findViewById(R.id.suppliersImg);
            supplierLinear = itemView.findViewById(R.id.supplierLinear);


            //show details of the supplier and changing the fragment
            suppliersImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Supplier clickedItem = suppliers.get(position);
                        int supplierId = clickedItem.getSupplierId();
                        Bundle bundle = new Bundle();
                        bundle.putString("supplierTxt", clickedItem.getSupplierName());
                        bundle.putString("supplierAddress", clickedItem.getSupplierAddress());
                        bundle.putString("supplierNumber", clickedItem.getSupplierNumber());

                        SupplierDetailsFragment supplierDetailsFragment  = new SupplierDetailsFragment(supplierId);
                        supplierDetailsFragment.setArguments(bundle);
                        //Replace the current fragment with the SupplierDetails Fragment
                        FragmentManager fragmentManager = ((MainActivity)context).getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();

                        ft.replace(R.id.container, supplierDetailsFragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        ((AppCompatActivity) view.getContext()).getSupportActionBar().setTitle(clickedItem.getSupplierName());
                    }
                }
            });
        }
    }
}
