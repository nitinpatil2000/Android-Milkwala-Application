package com.technosoul.milkwala.products;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.technosoul.milkwala.helper.MyDbHelper;
import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.supplier.Supplier;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    //    ArrayList<Product> products = new ArrayList<>();
    ProductViewAdapter productViewAdapter;
    EditText searchProduct;
    ArrayList<Supplier> supplierList;

    public ProductFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        RecyclerView productRecyclerView = view.findViewById(R.id.productRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        productRecyclerView.setLayoutManager(gridLayoutManager);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
         supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();
        for (int i = 0; i < supplierList.size(); i++) {
            productViewAdapter = new ProductViewAdapter(getContext(), supplierList);
            productViewAdapter.notifyDataSetChanged();
            productRecyclerView.setAdapter(productViewAdapter);
        }

        if (getActivity() != null) {
            ((MainActivity) getActivity()).setActionBarTitle("Products");
        }

        searchProduct = view.findViewById(R.id.searchProduct);
        searchProduct.clearFocus();
        searchProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        return view;
    }

    private void filter(String text) {
        ArrayList<Supplier> filteredSupplier = new ArrayList<>();
        for(Supplier supplier : supplierList){
            if(supplier.getSupplierName().toLowerCase().contains(text.toLowerCase())){
                filteredSupplier.add(supplier);
            }
        }

        productViewAdapter.filteredList(filteredSupplier);
    }
}