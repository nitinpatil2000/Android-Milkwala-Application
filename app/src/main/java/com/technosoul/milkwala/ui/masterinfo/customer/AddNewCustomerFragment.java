package com.technosoul.milkwala.ui.masterinfo.customer;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class AddNewCustomerFragment extends Fragment {
    private EditText etCustomerName;
    private EditText etCustomerAddress;
    private EditText etCustomerCity;
    private EditText etCustomerContact1;
    private EditText etCustomerContact2;

    private MasterInfoListener masterInfoListener;

    public AddNewCustomerFragment() {
        // Required empty public constructor
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_customer, container, false);

        etCustomerName = view.findViewById(R.id.etCustomerName);
        etCustomerAddress = view.findViewById(R.id.etCustomerAddress);
        etCustomerCity = view.findViewById(R.id.etCustomerCity);
        etCustomerContact1 = view.findViewById(R.id.etCustomerMobile);
        etCustomerContact2 = view.findViewById(R.id.etCustomerAlternateMobile);

        Button btnAddNewCustomer = view.findViewById(R.id.btnAddNewCustomer);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        btnAddNewCustomer.setOnClickListener(view1 -> {
            String customerName = etCustomerName.getText().toString();
            if (TextUtils.isEmpty(customerName)) {
                Toast.makeText(getContext(), R.string.err_empty_customer_name, Toast.LENGTH_SHORT).show();
                return;
            } else if (customerName.length() < 3) {
                Toast.makeText(getContext(), R.string.err_min_length_customer_name, Toast.LENGTH_SHORT).show();
                return;
            }

            String customerAddress = etCustomerAddress.getText().toString();
            if (TextUtils.isEmpty(customerAddress)) {
                Toast.makeText(getContext(), R.string.err_empty_customer_address, Toast.LENGTH_SHORT).show();
                return;
            } else if (customerAddress.length() < 8) {
                Toast.makeText(getContext(), R.string.err_min_length_customer_address, Toast.LENGTH_SHORT).show();
                return;
            }

            String customerCity = etCustomerCity.getText().toString();
            if (TextUtils.isEmpty(customerCity)) {
                Toast.makeText(getContext(), R.string.err_empty_city_name, Toast.LENGTH_SHORT).show();
                return;
            } else if (customerCity.length() < 3) {
                Toast.makeText(getContext(), R.string.err_min_length_city, Toast.LENGTH_SHORT).show();
                return;
            }

            String customerContact1 = etCustomerContact1.getText().toString();
            if (TextUtils.isEmpty(customerContact1)) {
                Toast.makeText(getContext(), R.string.err_empty_mobile_number, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Patterns.PHONE.matcher(customerContact1).matches()) {
                Toast.makeText(getContext(), R.string.err_invalid_mobile_number, Toast.LENGTH_SHORT).show();
                return;
            }

            String customerContact2 = etCustomerContact2.getText().toString();
            if (!customerContact2.isEmpty() && !Patterns.PHONE.matcher(customerContact2).matches()) {
                Toast.makeText(getContext(), R.string.err_invalid_alternate_number, Toast.LENGTH_SHORT).show();
                return;
            }

            myDbHelper.customerDao().addCustomer(new Customer(customerName, customerAddress, customerCity, customerContact1, customerContact2));

            Toast.makeText(getContext(), R.string.msg_customer_added_success, Toast.LENGTH_SHORT).show();

            if (masterInfoListener != null) {
                masterInfoListener.onBackToPreviousScreen();
            }

        });

        if(getActivity() !=null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle("Add New Customer");
        }
        return view;
    }
}