package com.technosoul.milkwala.Supplier;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;

import java.util.ArrayList;

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
        holder.supplierTxt.setText(suppliers.get(holder.getAdapterPosition()).getSupplierName());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView supplierTxt, supplierSubText;
        ImageView suppliersImg;
        LinearLayout supplierLinear;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            supplierTxt = itemView.findViewById(R.id.suppliersTxt);
            supplierSubText = itemView.findViewById(R.id.supplierSubText);
            suppliersImg = itemView.findViewById(R.id.suppliersImg);
            supplierLinear = itemView.findViewById(R.id.supplierLinear);


            //show details of the supplier and changing the fragment
            suppliersImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Supplier clickedItem = suppliers.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("supplierTxt", clickedItem.getSupplierName());
                        bundle.putString("supplierAddress", clickedItem.getSupplierAddress());
                        bundle.putString("supplierNumber", clickedItem.getSupplierNumber());

                        SupplierDetailsFragment supplierDetailsFragment  = new SupplierDetailsFragment();
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