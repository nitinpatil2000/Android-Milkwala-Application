package com.technosoul.milkwala.customer;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.technosoul.milkwala.helper.MyDbHelper;
import com.technosoul.milkwala.R;

public class CustomerDetailsFragment extends Fragment {
    TextView txtCustomerName, txtCustomerAdd, txtCustomerCity, txtCustomerNo;
    Button deleteCustomerBtn;

    String customerName, customerAdd, customerCity, customerNo;
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
             customerName = bundle.getString("customerName");
             customerAdd = bundle.getString("customerAdd");
             customerCity = bundle.getString("customerCity");
             customerNo = bundle.getString("customerNo");

            txtCustomerName.setText(customerName);
            txtCustomerAdd.setText(customerAdd);
            txtCustomerCity.setText(customerCity);
            txtCustomerNo.setText(customerNo);
        }

        deleteCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView cancelBtn, deleteBtn, deleteTxt, deleteInfo, dltMsg;

                Dialog dialog = new Dialog(getContext());

                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_design);

                cancelBtn = dialog.findViewById(R.id.cancelBtn);
                deleteBtn = dialog.findViewById(R.id.delteBtn);
                deleteTxt = dialog.findViewById(R.id.deleteTxt);
                deleteInfo = dialog.findViewById(R.id.deleteInfo);
                dltMsg = dialog.findViewById(R.id.dltMsg);

                deleteTxt.setText(" Delete Customer");
                deleteInfo.setText(customerName);
                dltMsg.setText("are you sure to delete this \n customer?");


                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
//                        myDbHelper.customerDao().deleteCustomer(
//                                new Customer()
//                        );

                    }
                });

                dialog.show();

            }
        });
        return view;
    }
}