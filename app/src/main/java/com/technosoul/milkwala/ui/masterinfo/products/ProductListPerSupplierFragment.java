package com.technosoul.milkwala.ui.masterinfo.products;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.ProductListViewPerSupplierAdapter;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;

import java.util.ArrayList;

public class ProductListPerSupplierFragment extends Fragment {
    private final int supplierId;
    ProductListViewPerSupplierAdapter productListViewPerSupplierAdapter;
    RecyclerView productListPerSupplierRecyclerView;
    ArrayList<ProductDetails> productDetailsList;
    TextView addProductTxt;
    EditText searchProductDetails;
    private MasterInfoListener listener;
    private OnItemSelected onItemSelected;

    public ProductListPerSupplierFragment(int supplierId) {
        // Required empty public constructor
        this.supplierId = supplierId;
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    public void setOnItemSelected(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list_per_supplier, container, false);

        productListPerSupplierRecyclerView = view.findViewById(R.id.productDetailRecylerView);
        productListPerSupplierRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);

        for (int i = 0; i < productDetailsList.size(); i++) {
            productListViewPerSupplierAdapter = new ProductListViewPerSupplierAdapter(getContext(), productDetailsList, onItemSelected);
            productListPerSupplierRecyclerView.setAdapter(productListViewPerSupplierAdapter);
        }

        addProductTxt = view.findViewById(R.id.addProductTxt);
        addProductTxt.setOnClickListener(view1 -> listener.addNewProduct(supplierId));

        searchProductDetails = view.findViewById(R.id.searchProdutctDetails);
        searchProductDetails.clearFocus();
        searchProductDetails.addTextChangedListener(new TextWatcher() {
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
        ArrayList<ProductDetails> filterProductDetails = new ArrayList<>();
        for (ProductDetails productDetails : productDetailsList) {
            if (productDetails.getProductDetailsName().toLowerCase().contains(text.toLowerCase())) {
                filterProductDetails.add(productDetails);
            }
        }

        productListViewPerSupplierAdapter.filteredList(filterProductDetails);
    }

}

