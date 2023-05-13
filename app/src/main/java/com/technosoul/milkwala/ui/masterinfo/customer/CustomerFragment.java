package com.technosoul.milkwala.ui.masterinfo.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.CustomerViewAdapter;
import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;

import java.util.ArrayList;

public class CustomerFragment extends Fragment {
    private CustomerViewAdapter customerViewAdapter;

    private MasterInfoListener masterInfoListener;
    private OnItemSelected onItemSelected;

    public CustomerFragment() {
        // Required empty public constructor
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    public void setOnItemSelected(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        RecyclerView customerRecyclerView = view.findViewById(R.id.recyclerview_customer_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        customerRecyclerView.setLayoutManager(gridLayoutManager);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        ArrayList<Customer> customerList = (ArrayList<Customer>) myDbHelper.customerDao().getAllCustomers();
        if (customerList == null || customerList.size() == 0) {
            Toast.makeText(getContext(), "Customer list is empty. Let's start adding new Customers", Toast.LENGTH_SHORT).show();
        } else {
            customerViewAdapter = new CustomerViewAdapter(getContext(), customerList, onItemSelected);
            customerRecyclerView.setAdapter(customerViewAdapter);
        }

        Button addCustomerBtn = view.findViewById(R.id.btn_add_new_customer);
        addCustomerBtn.setOnClickListener(view1 -> {
            if (masterInfoListener != null) {
                masterInfoListener.addNewCustomer();
            }
        });
        return view;
    }
}