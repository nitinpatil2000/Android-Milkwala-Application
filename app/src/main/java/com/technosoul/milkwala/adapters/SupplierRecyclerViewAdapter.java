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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.supplier.Supplier;
import com.technosoul.milkwala.supplier.SupplierDetailsFragment;
import com.technosoul.milkwala.ui.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.products.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;

import java.util.ArrayList;

public class SupplierRecyclerViewAdapter extends RecyclerView.Adapter<SupplierRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Supplier> suppliers;

    public SupplierRecyclerViewAdapter(Context context, ArrayList<Supplier>suppliers){
        this.context = context;
        this.suppliers = suppliers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.supplier_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Supplier selectedSupplier = suppliers.get(position);
        int supplierId = selectedSupplier.getSupplierId();
        holder.supplierTxt.setText(suppliers.get(holder.getAdapterPosition()).getSupplierName());
        MyDbHelper myDbHelper = MyDbHelper.getDB(context);
        ArrayList<ProductDetails> productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);
        int numCounterSuppliers = productDetailsList.size();
        holder.supplierCounter.setText(String.format(context.getString(R.string.products_of_supplier), numCounterSuppliers));
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
            suppliersImg.setOnClickListener(view -> {
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
                    ActionBar actionBar = ((MasterInfoActivity) context).getSupportActionBar();
                    if (actionBar != null) {
                        actionBar.setTitle(clickedItem.getSupplierName());
                    }
                }
            });
        }
    }
}
