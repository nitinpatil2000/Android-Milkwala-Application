package com.technosoul.milkwala.ui.masterinfo.products;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.products.AddNewProductFragment;
import com.technosoul.milkwala.products.ProductViewDetailsAdapter;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

import java.util.ArrayList;

public class ProductListPerSupplierFragment extends Fragment {
    ProductViewDetailsAdapter productViewDetailsAdapter;
    RecyclerView productDetailRecyclerView;
    ArrayList<ProductDetails> productDetailsList;
    ImageView productImg;
    TextView addProductTxt;
    EditText searchProductDetails;
    private int supplierId = -1;

    private MasterInfoListener listener;

    public ProductListPerSupplierFragment(int supplierId) {
        // Required empty public constructor
        this.supplierId = supplierId;
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list_per_supplier, container, false);


//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            String name = bundle.getString("name");
//            String mrp = bundle.getString("mrp");
//            String unit = bundle.getString("unit");
//            //add the item in the recyclerView.
//            ProductDetails productDetails = new ProductDetails(name, mrp, unit);
//            productDetailsList.add(productDetails);
//            productViewDetailsAdapter.notifyDataSetChanged();
//        }
        productDetailRecyclerView = view.findViewById(R.id.productDetailRecylerView);
        productDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);

        for (int i = 0; i < productDetailsList.size(); i++) {
            productViewDetailsAdapter = new ProductViewDetailsAdapter(getContext(), productDetailsList);
            productDetailRecyclerView.setAdapter(productViewDetailsAdapter);
        }

        addProductTxt = view.findViewById(R.id.addProductTxt);
        addProductTxt.setOnClickListener(view1 -> {
            listener.addNewProduct(supplierId);
        });

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

        productViewDetailsAdapter.filteredList(filterProductDetails);
    }

}

