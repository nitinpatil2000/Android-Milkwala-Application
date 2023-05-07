package com.technosoul.milkwala.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.db.Supplier;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder> {
    private final OnItemSelected onItemSelected;
    Context context;
    ArrayList<Supplier> suppliers;


    public ProductViewAdapter(Context context, ArrayList<Supplier> suppliers, OnItemSelected onItemSelected) {
        this.context = context;
        this.suppliers = suppliers;
        this.onItemSelected = onItemSelected;
    }

    @NonNull
    @Override
    public ProductViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewAdapter.ViewHolder holder, int position) {
        Supplier selectedSupplier = suppliers.get(position);
        int supplierId = selectedSupplier.getSupplierId();
        holder.productTxt.setText(suppliers.get(position).getSupplierName());
        MyDbHelper myDbHelper = MyDbHelper.getDB(context);
        ArrayList<ProductDetails> productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);
        int numCounterProducts = productDetailsList.size();
        holder.productCounter.setText(String.format(context.getString(R.string.products_of_supplier), numCounterProducts));
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    public void filteredList(ArrayList<Supplier> filteredSupplier) {
        suppliers = filteredSupplier;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productTxt, productCounter;
        ImageView productImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productTxt = itemView.findViewById(R.id.productTxt);
            productCounter = itemView.findViewById(R.id.productCounter);
            productImg = itemView.findViewById(R.id.productImg);

            productImg.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Supplier selectedSupplier = suppliers.get(position);
                    int supplierId = selectedSupplier.getSupplierId();
                    if (onItemSelected != null) {
                        onItemSelected.onItemClicked(Constants.SELECTED_SUPPLIER_FOR_PRODUCT_LIST, supplierId, null);
                    }
                }
            });
        }
    }
}
