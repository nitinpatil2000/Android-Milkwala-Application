package com.example.milkapplication.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RecoverySystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.milkapplication.MainActivity;
import com.example.milkapplication.R;
import com.example.milkapplication.delivery.Deliver;
import com.example.milkapplication.delivery.DeliverViewAdapter;

import java.util.ArrayList;

public class CustomerFragment extends Fragment {
    ArrayList<Customer> customers = new ArrayList<>();
    CustomerViewAdapter customerViewAdapter;

    public CustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        RecyclerView customerRecyclerView = view.findViewById(R.id.customerRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        customerRecyclerView.setLayoutManager(gridLayoutManager);

        customers = new ArrayList<>();
        customers.add(new Customer("Chitale", "16 Customers"));
        customers.add(new Customer("Gokul", "14 Customers"));
        customers.add(new Customer("Amul", "24 Customers"));
        customers.add(new Customer("Katraj", "16 Customers"));
//        customers.add(new Customer("Customer", "14 Customers"));
//        customers.add(new Customer("Customer" ,"20 Customers"));
//        customers.add(new Customer("Customer", "24 Customers"));
//        customers.add(new Customer("Customer", "16 Customers"));
//        customers.add(new Customer("Customer", "14 Customers"));
//        customers.add(new Customer("Customer", "20 Customers"));
//        customers.add(new Customer("Customer", "24 Customers"));

        CustomerViewAdapter customerViewAdapter = new CustomerViewAdapter(getContext(), customers);
        customerRecyclerView.setAdapter(customerViewAdapter);

        if(getActivity()!= null){
            ((MainActivity) getActivity()).setActionBarTitle("Customer");
        }


        return  view;
    }
}