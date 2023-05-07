package com.technosoul.milkwala.customer;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.R;

public class CustomerDetailsFragment extends Fragment {
    TextView txtCustomerName, txtCustomerAdd, txtCustomerCity, txtCustomerNo;
    Button deleteCustomerBtn;

    String customerName, customerAdd, customerCity, customerNo;
    int customerId;
    public CustomerDetailsFragment(int customerId) {
        this.customerId = customerId;
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

                cancelBtn = dialog.findViewById(R.id.btn_action_cancel);
                deleteBtn = dialog.findViewById(R.id.btn_action_delete);
                deleteTxt = dialog.findViewById(R.id.tv_title_delete_supplier);
                deleteInfo = dialog.findViewById(R.id.tv_msg_delete_desc);
                dltMsg = dialog.findViewById(R.id.tv_msg_delete_confirmation);

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
                     myDbHelper.customerDao().deleteCustomerById(customerId);
                        Toast.makeText(getContext(), "Customer Deleted Successfully !!", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().popBackStack();
                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });
        return view;
    }
}