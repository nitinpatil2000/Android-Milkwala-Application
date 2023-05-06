package com.technosoul.milkwala.ui.masterinfo.products;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.products.ProductViewAdapter;
import com.technosoul.milkwala.supplier.Supplier;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    //    ArrayList<Product> products = new ArrayList<>();
    ProductViewAdapter productViewAdapter;
    EditText searchProduct;
    ArrayList<Supplier> supplierList;
    Button btnAddNewProduct;
    private MasterInfoListener masterInfoListener;

    public ProductFragment() {
    }

    public void setListener(MasterInfoListener listener) {
        this.masterInfoListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        btnAddNewProduct = view.findViewById(R.id.btn_add_new_product);
        btnAddNewProduct.setOnClickListener(v -> onClickAddNewProduct());

        RecyclerView productRecyclerView = view.findViewById(R.id.recyclerView_product_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        productRecyclerView.setLayoutManager(gridLayoutManager);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();
        for (int i = 0; i < supplierList.size(); i++) {
            productViewAdapter = new ProductViewAdapter(getContext(), supplierList);
            productRecyclerView.setAdapter(productViewAdapter);
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

    private void onClickAddNewProduct() {
        if (masterInfoListener != null) {
            masterInfoListener.addNewProduct();
        }
    }

    private void filter(String text) {
        ArrayList<Supplier> filteredSupplier = new ArrayList<>();
        for (Supplier supplier : supplierList) {
            if (supplier.getSupplierName().toLowerCase().contains(text.toLowerCase())) {
                filteredSupplier.add(supplier);
            }
        }

        productViewAdapter.filteredList(filteredSupplier);
    }
}