package com.technosoul.milkwala.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.technosoul.milkwala.helper.MyDbHelper;
import com.technosoul.milkwala.R;

public class AddCustomer extends Fragment {
    EditText editCustomerName, editCustomerAdd, editCustomerCity, editCustomerContact1, editCustomerContact2;
    Button addNewCustomerBtn;

    public AddCustomer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_customer, container, false);

        editCustomerName = view.findViewById(R.id.editCustomerName);
        editCustomerAdd = view.findViewById(R.id.editCutomerAdd);
        editCustomerCity = view.findViewById(R.id.editCustomerCity);
        editCustomerContact1 = view.findViewById(R.id.editContactNo1);
        editCustomerContact2 = view.findViewById(R.id.editContactNo2);

        addNewCustomerBtn = view.findViewById(R.id.addNewCustomerBtn);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        addNewCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = editCustomerName.getText().toString();
                String customerAddress = editCustomerAdd.getText().toString();
                String customerCity = editCustomerCity.getText().toString();
                String customerContact1 = editCustomerContact1.getText().toString();
                String customerContact2 = editCustomerContact2.getText().toString();

                if(!customerName.isEmpty() && !customerAddress.isEmpty() && !customerCity.isEmpty() && !customerContact1.isEmpty() && !customerContact2.isEmpty()){
                    myDbHelper.customerDao().addCustomer(
                            new Customer(customerName, customerAddress, customerCity, customerContact1, customerContact2)
                    );


                    Toast.makeText(getContext(), "Customer added successfully", Toast.LENGTH_SHORT).show();
                    // return to the supplierFragment
                    FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();

                }
            }
        });
        return  view;
    }
}