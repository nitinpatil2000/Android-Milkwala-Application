package com.technosoul.milkwala.products;

import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.Supplier.RecyclerViewAdapter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ProductDetailsFragment extends Fragment {
    ProductViewDetailsAdapter productViewDetailsAdapter;
    RecyclerView productDetailRecyclerView;
    ArrayList<ProductDetails> productDetailsList = new ArrayList<>();
    ImageView productImg;
    TextView addProductTxt;
    private int supplierId = -1;

    public ProductDetailsFragment(int supplierId) {
        // Required empty public constructor
        this.supplierId = supplierId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);


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
        productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getAllProducts();

        for (int i = 0; i < productDetailsList.size(); i++) {
            productViewDetailsAdapter = new ProductViewDetailsAdapter(getContext(),productDetailsList);
            productViewDetailsAdapter.notifyDataSetChanged();
            productDetailRecyclerView.setAdapter(productViewDetailsAdapter);
        }

        addProductTxt = view.findViewById(R.id.addProductTxt);
        addProductTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProductFragment addProductFragment = new AddProductFragment(supplierId);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, addProductFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

}

