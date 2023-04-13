package com.technosoul.milkwala.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.technosoul.milkwala.R;

public class CustomerDetailsFragment extends Fragment {
    TextView txtCustomerName, txtCustomerAdd, txtCustomerCity, txtCustomerNo;
    Button deleteCustomerBtn;

    public CustomerDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_customer_details, container, false);
        txtCustomerName = view.findViewById(R.id.txtCustomerName);
        txtCustomerAdd = view.findViewById(R.id.txtCustomerAdd);
        txtCustomerCity = view.findViewById(R.id.txtCustomerCity);
        txtCustomerNo = view.findViewById(R.id.txtContactNo);
        deleteCustomerBtn = view.findViewById(R.id.deleteCustomerBtn);

        Bundle bundle = getArguments();
        if(bundle!= null){
            String customerName = bundle.getString("customerName");
            String customerAdd = bundle.getString("customerAdd");
            String customerCity = bundle.getString("customerCity");
            String customerNo = bundle.getString("customerNo");

            txtCustomerName.setText(customerName);
            txtCustomerAdd.setText(customerAdd);
            txtCustomerCity.setText(customerCity);
            txtCustomerNo.setText(customerNo);
        }
        return view;
    }
}