package com.technosoul.milkwala.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.ui.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.supplier.Supplier;

import java.util.ArrayList;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Supplier> suppliers;


    public ProductViewAdapter(Context context, ArrayList<Supplier> suppliers) {
        this.context = context;
        this.suppliers = suppliers;

    }

    @NonNull
    @Override
    public ProductViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewAdapter.ViewHolder holder, int position) {
        Supplier selectedSupplier = suppliers.get(position);
        int supplierId = selectedSupplier.getSupplierId();
        holder.productTxt.setText(suppliers.get(position).getSupplierName());
        MyDbHelper myDbHelper = MyDbHelper.getDB(context);
        ArrayList<ProductDetails> productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);
        int numCounterProducts = productDetailsList.size();
        holder.productCounter.setText(String.format("%d Products", numCounterProducts));
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

            productImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Supplier selectedSupplier = suppliers.get(position);
                        int supplierId = selectedSupplier.getSupplierId();
                        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment(supplierId);
                        FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.replace(R.id.container, productDetailsFragment);
                        ft.addToBackStack(null);
                        ft.commit();
                        ((AppCompatActivity) view.getContext()).getSupportActionBar().setTitle(selectedSupplier.getSupplierName() + " Products");
                    }
                }
            });
        }
    }
}
