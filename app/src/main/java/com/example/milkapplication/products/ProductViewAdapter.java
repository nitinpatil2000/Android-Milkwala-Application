package com.example.milkapplication.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milkapplication.MainActivity;
import com.example.milkapplication.R;
import com.example.milkapplication.Supplier.RecyclerViewAdapter;
import com.example.milkapplication.Supplier.Supplier;
import com.example.milkapplication.Supplier.SupplierDetailsFragment;

import java.util.ArrayList;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> products;


    public ProductViewAdapter(Context context, ArrayList<Product>products){
        this.context = context;
        this.products = products;
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
        holder.productTxt.setText(products.get(position).productTxt);
        holder.productSubText.setText(products.get(position).productSubText);


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productTxt, productSubText;
        ImageView productImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productTxt = itemView.findViewById(R.id.productTxt);
            productSubText = itemView.findViewById(R.id.productSubText);
            productImg = itemView.findViewById(R.id.productImg);

            productImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
                    FragmentManager fragmentManager = ((MainActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.container, productDetailsFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });


        }
    }
}
