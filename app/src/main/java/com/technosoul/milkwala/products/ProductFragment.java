package com.technosoul.milkwala.products;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.Supplier.RecyclerViewAdapter;
import com.technosoul.milkwala.Supplier.Supplier;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    ArrayList<Product> products = new ArrayList<>();
    ProductViewAdapter productViewAdapter;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_product, container, false);
        RecyclerView productRecyclerView = view.findViewById(R.id.productRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        productRecyclerView.setLayoutManager(gridLayoutManager);

        products = new ArrayList<>();
        products.add(new Product("Chitale", "16 Products"));
        products.add(new Product("Gokul", "14 Products"));
        products.add(new Product("Amul","20 Products"));
        products.add(new Product("Katraj", "24 Products"));

        productViewAdapter = new ProductViewAdapter(getContext(), products);
        productRecyclerView.setAdapter(productViewAdapter);

        if(getActivity() != null){
            ((MainActivity) getActivity()).setActionBarTitle("Products");
        }

        return view;
    }
}