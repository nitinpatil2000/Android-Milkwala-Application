package com.technosoul.milkwala.HomeScreen;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.Supplier.Supplier;
import com.technosoul.milkwala.Supplier.SupplierFragment;
import com.technosoul.milkwala.customer.Customer;
import com.technosoul.milkwala.customer.CustomerFragment;
import com.technosoul.milkwala.delivery.Deliver;
import com.technosoul.milkwala.delivery.FragmentDeliver;

import com.technosoul.milkwala.products.ProductFragment;
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

        supplierSubText = view.findViewById(R.id.supplierSubText);
        productSubText  = view.findViewById(R.id.productSubText);
        deliverySubText = view.findViewById(R.id.deliverySubText);
        customerSubText = view.findViewById(R.id.customerSubText);





        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        ArrayList<Supplier> supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();

        int numSuppliers = supplierList.size();
        supplierSubText.setText(String.format("%d Suppliers ", numSuppliers));
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


        int numProducts = supplierList.size();
        productSubText.setText(String.format("%d Products ", numProducts));
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

        ArrayList<Deliver> deliverList = (ArrayList<Deliver>) myDbHelper.deliveryDetailDao().getAllDeliveryBoys();
        int numDelivers = deliverList.size();
        deliverySubText.setText(String.format("%d Delivery Boys", numDelivers));
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

        ArrayList<Customer> customerList = (ArrayList<Customer>)myDbHelper.customerDao().getAllCustomers();
        int numCustomers = customerList.size();
        customerSubText.setText(String.format("%d Customers", numCustomers));
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

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Master Info :");
    }
}