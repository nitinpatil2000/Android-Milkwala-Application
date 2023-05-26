package com.technosoul.milkwala.ui.masterinfo.customer;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class CustomerDetailsFragment extends Fragment {
    private final int customerId;
    private String customerName;
    private MasterInfoListener masterInfoListener;

    public CustomerDetailsFragment(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
        // Required empty public constructor
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_customer_details, container, false);
        TextView tvCustomerName = view.findViewById(R.id.tvCustomerName);
        TextView tvCustomerAdd = view.findViewById(R.id.tvCustomerAddress);
        TextView tvCustomerCity = view.findViewById(R.id.tvCustomerCity);
        TextView tvCustomerNo = view.findViewById(R.id.tvContactNo);
        TextView tvTitleCustomer = view.findViewById(R.id.tv_title_customer);

        Button deleteCustomerBtn = view.findViewById(R.id.btnDeleteCustomer);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        Customer customer = myDbHelper.customerDao().getCustomerById(customerId);

        tvCustomerName.setText(customer.getCustomerName());
        tvTitleCustomer.setText(getString(R.string.title_customer_details, customer.getCustomerName()));
        tvCustomerAdd.setText(customer.getCustomerAddress());
        tvCustomerCity.setText(customer.getCustomerCity());
        tvCustomerNo.setText(String.valueOf(customer.getCustomerNumber()));

        deleteCustomerBtn.setOnClickListener(view1 -> {
            Button btnCancel;
            Button btnDelete;
            TextView title;
            TextView message;
            TextView confirmationMsg;

            Dialog dialog = new Dialog(getContext());

            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_design);

            btnCancel = dialog.findViewById(R.id.btn_action_cancel);
            btnDelete = dialog.findViewById(R.id.btn_action_delete);
            title = dialog.findViewById(R.id.tv_delete_dialog_title);
            message = dialog.findViewById(R.id.tv_delete_dialog_desc);
            confirmationMsg = dialog.findViewById(R.id.tv_delete_dialog_confirmation_msg);

            title.setText(getString(R.string.title_delete_dialog, customer.getCustomerName()));
            message.setText(R.string.msg_delete_customer_desc);
            confirmationMsg.setText(R.string.msg_delete_customer_confirmation);

            btnCancel.setOnClickListener(view2 -> dialog.dismiss());

            btnDelete.setOnClickListener(v1 -> {
                myDbHelper.customerDao().deleteCustomerById(customerId);
                Toast.makeText(getContext(), R.string.msg_delete_customer_success, Toast.LENGTH_SHORT).show();
                if (masterInfoListener != null) {
                    masterInfoListener.onBackToPreviousScreen();
                }
                dialog.dismiss();
            });

            dialog.show();

        });

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle(customerName);
        }


        return view;
    }
}