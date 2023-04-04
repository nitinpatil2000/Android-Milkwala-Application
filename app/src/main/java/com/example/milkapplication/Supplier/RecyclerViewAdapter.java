package com.example.milkapplication.Supplier;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milkapplication.MainActivity;
import com.example.milkapplication.R;

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
        holder.supplierTxt.setText(suppliers.get(holder.getAdapterPosition()).supplierTxt);
//        holder.supplierSubText.setText(suppliers.get(holder.getAdapterPosition()).supplierSubText);

//        update the item;
        holder.supplierLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.update_supplier_design);
                EditText editSupplierName = dialog.findViewById(R.id.updateSupplierName);
                TextView btnAction = dialog.findViewById(R.id.btnAction);
                TextView btnCancel = dialog.findViewById(R.id.btnCancel);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                editSupplierName.setText(suppliers.get(holder.getAdapterPosition()).supplierTxt);
                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = editSupplierName.getText().toString().trim();
                        if (name.isEmpty()) {
                            Toast.makeText(context, "Please enter the supplier name", Toast.LENGTH_SHORT).show();
                        } else {
                            suppliers.set(position, new Supplier(name));
                            notifyItemChanged(position);
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });
    }

//        holder.supplierLinear.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.dialog_design);
//                dialog.setCancelable(false);
//
//                TextView cancelBtn, deleteBtn, suppName;
//                cancelBtn = dialog.findViewById(R.id.cancelBtn);
//                deleteBtn = dialog.findViewById(R.id.delteBtn);
//                suppName = dialog.findViewById(R.id.suppName);
//
//
////                suppName.text = supplierList.get(position);
//
//                cancelBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//                deleteBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        suppliers.remove(position);
//                        notifyItemRemoved(position);
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//
//                return false;
//            }
//        });


    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    //method to add new item to the list
    public void addNewItem(Supplier supplier) {
        suppliers.add(supplier);
        notifyItemInserted(suppliers.size() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView supplierTxt, supplierSubText, suppName;
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
                        bundle.putString("supplierTxt", clickedItem.supplierTxt);

                        SupplierDetailsFragment supplierDetailsFragment  = new SupplierDetailsFragment();
                        supplierDetailsFragment.setArguments(bundle);
                        //Replace the current fragment with the SupplierDetails Fragment
                        FragmentManager fragmentManager = ((MainActivity)context).getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();

                        ft.replace(R.id.container, supplierDetailsFragment);
                        ft.addToBackStack(null);
                        ft.commit();

                    }
                }
            });
        }
    }
}
