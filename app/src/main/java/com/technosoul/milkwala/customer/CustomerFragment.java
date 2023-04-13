package com.technosoul.milkwala.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;

import java.util.ArrayList;

public class CustomerFragment extends Fragment {
    ArrayList<Customer> customers = new ArrayList<>();
    CustomerViewAdapter customerViewAdapter;
    Button addCustomerBtn;

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

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        ArrayList<Customer>customerList = (ArrayList<Customer>) myDbHelper.customerDao().getAllCustomers();
        for(int i = 0; i<customerList.size(); i++){
            customerViewAdapter = new CustomerViewAdapter(getContext(), customerList);
            customerViewAdapter.notifyDataSetChanged();
            customerRecyclerView.setAdapter(customerViewAdapter);
        }

        addCustomerBtn = view.findViewById(R.id.addCustomerBtn);
        addCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCustomer addCustomer = new AddCustomer();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, addCustomer);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        if(getActivity()!= null){
            ((MainActivity) getActivity()).setActionBarTitle("Customer");
        }
        return  view;
    }
}