package com.example.milkapplication.HomeScreen;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milkapplication.R;
import com.example.milkapplication.Supplier.SupplierFragment;
import com.example.milkapplication.customer.CustomerFragment;
import com.example.milkapplication.delivery.FragmentDeliver;
import com.example.milkapplication.products.ProductFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
//    ArrayList<HomeItem> homeItems = new ArrayList<>();
//    RecyclerViewAdapter recyclerViewAdapter;
    TextView supplierTxt, productTxt, deliveryTxt, customerTxt;
    TextView supplierSubText, productSubText, deliverySubText, customerSubText;
    ImageView supplierImg, productImg, deliveryImg, customerImg;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        supplierTxt = view.findViewById(R.id.suppliersTxt);
        productTxt = view.findViewById(R.id.productTxt);
        deliveryTxt = view.findViewById(R.id.deliveryTxt);
        customerTxt = view.findViewById(R.id.customerTxt);

        supplierImg = view.findViewById(R.id.supplierImg);
        productImg = view.findViewById(R.id.productImg);
        deliveryImg = view.findViewById(R.id.deliveryImg);
        customerImg = view.findViewById(R.id.customerImg);

        supplierImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SupplierFragment supplierFragment = new SupplierFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.container, supplierFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductFragment productFragment = new ProductFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, productFragment);
                ft.commit();

            }
        });

        deliveryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentDeliver fragmentDeliver = new FragmentDeliver();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, fragmentDeliver);
                ft.commit();
            }
        });

        customerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerFragment customerFragment = new CustomerFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, customerFragment);
                ft.commit();
            }
        });



        return view;
    }
}