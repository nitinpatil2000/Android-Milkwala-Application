package com.technosoul.milkwala.ui.masterinfo.customer;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CustomerDetailsFragment extends Fragment {
    private final int customerId;
    private final String customerName;
    private MasterInfoListener masterInfoListener;
    CustomerFromServer customerFromServer;
    FloatingActionButton btnUpdateCustomerDetails;
    ArrayList<String> customerTypeNamesArrayList = new ArrayList<>();
    Button assignProductForCustomer;


    public CustomerDetailsFragment(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_details, container, false);

        TextView tvCustomerName = view.findViewById(R.id.tvCustomerName);
        TextView tvCustomerAdd = view.findViewById(R.id.tvCustomerAddress);
        TextView tvCustomerEmail = view.findViewById(R.id.txtCustomerEmail);
        TextView tvCustomerType = view.findViewById(R.id.txtCustomerType);
        TextView tvCustomerAltNo = view.findViewById(R.id.txtCustomerAltNo);
        TextView tvCustomerNo = view.findViewById(R.id.tvContactNo);
        TextView tvTitleCustomer = view.findViewById(R.id.tv_title_customer);

        Button deleteCustomerBtn = view.findViewById(R.id.btnDeleteCustomer);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        CustomerService customerService = retrofit.create(CustomerService.class);
        Call<CustomerFromServer> getCustomerByCustomerId = customerService.getAllCustomersById(customerId);
        getCustomerByCustomerId.enqueue(new Callback<CustomerFromServer>() {
            @Override
            public void onResponse(@NonNull Call<CustomerFromServer> call, @NonNull Response<CustomerFromServer> response) {
                if (response.isSuccessful()) {
                    customerFromServer = response.body();
                    if (customerFromServer != null) {
                        tvCustomerName.setText(customerFromServer.getCustomerName());
                        tvCustomerEmail.setText(customerFromServer.getCustomerEmail());
                        tvCustomerAdd.setText(customerFromServer.getCustomerAddress());
                        tvCustomerType.setText(customerFromServer.getCustomerType());
                        tvCustomerNo.setText(String.valueOf(customerFromServer.getCustomerContactNo()));
                        tvCustomerAltNo.setText(String.valueOf(customerFromServer.getCustomerAlterNo()));
                        tvTitleCustomer.setText(getString(R.string.title_customer_details, customerFromServer.getCustomerName()));

                    } else {
                        Toast.makeText(getContext(), R.string.failed_get_customer_data, Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CustomerFromServer> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        btnUpdateCustomerDetails = view.findViewById(R.id.btnUpdateCustomerDetails);
        btnUpdateCustomerDetails.setOnClickListener(view1 -> {
            EditText edtUpdateCustomerName, edtUpdateCustomerEmail, edtUpdateCustomerAddress, edtUpdateCustomerNo, edtUpdateCustomerAltNo;
            TextView  txtUpdateCustomerDialogTitle;
            Spinner updateCustomerType;
            Button btnActionCancel, btnActionUpdate;

            Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.update_customer_dialog_design);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());

//             Set the width to match the parent
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);

            edtUpdateCustomerName = dialog.findViewById(R.id.edtUpdateCustomerName);
            edtUpdateCustomerAddress = dialog.findViewById(R.id.edtUpdateCustomerAddress);
            edtUpdateCustomerEmail = dialog.findViewById(R.id.edtUpdateCustomerEmail);
            updateCustomerType = dialog.findViewById(R.id.spUpdateCustomerType);
            edtUpdateCustomerNo = dialog.findViewById(R.id.edtUpdateCustomerNo);
            edtUpdateCustomerAltNo = dialog.findViewById(R.id.edtUpdateCustomerAltNo);

            btnActionCancel = dialog.findViewById(R.id.btnActionCancel);
            btnActionUpdate = dialog.findViewById(R.id.btnActionUpdate);
            txtUpdateCustomerDialogTitle = dialog.findViewById(R.id.tv_update_customer_dialog_title);

            edtUpdateCustomerName.setText(customerFromServer.getCustomerName());
            edtUpdateCustomerAddress.setText(customerFromServer.getCustomerAddress());
            edtUpdateCustomerEmail.setText(customerFromServer.getCustomerEmail());

            customerTypeNamesArrayList.add(Constants.WHOLESALE);
            customerTypeNamesArrayList.add(Constants.RETAILER);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, customerTypeNamesArrayList);
            updateCustomerType.setAdapter(adapter);
            int defaultProductName = customerTypeNamesArrayList.indexOf(customerFromServer.getCustomerType());
            updateCustomerType.setSelection(defaultProductName);

            edtUpdateCustomerNo.setText(String.valueOf(customerFromServer.getCustomerContactNo()));
            edtUpdateCustomerAltNo.setText(String.valueOf(customerFromServer.getCustomerAlterNo()));
            txtUpdateCustomerDialogTitle.setText(String.format(getString(R.string.str_update_login_title), customerFromServer.getCustomerName()));


            btnActionCancel.setOnClickListener(view2 -> dialog.dismiss());

            btnActionUpdate.setOnClickListener(view22 -> {
                CustomerFromServer updateCustomerFromServer = new CustomerFromServer(
//                        updateCustomerType.getSelectedItem().toString(),
                        edtUpdateCustomerName.getText().toString(),
                        edtUpdateCustomerEmail.getText().toString(),
                        edtUpdateCustomerAddress.getText().toString(),
                        updateCustomerType.getSelectedItem().toString(),
                        Long.parseLong(edtUpdateCustomerNo.getText().toString()),
                        Long.parseLong(edtUpdateCustomerAltNo.getText().toString())
                );

                Call<CustomerFromServer> updateCustomerList = customerService.updateCustomer(customerId, updateCustomerFromServer);
                updateCustomerList.enqueue(new Callback<CustomerFromServer>() {
                    @Override
                    public void onResponse(@NonNull Call<CustomerFromServer> call, @NonNull Response<CustomerFromServer> response) {
                        if (response.isSuccessful()) {
                            CustomerFromServer updateCustomer = response.body();
                            if(masterInfoListener != null){
                                masterInfoListener.onBackToPreviousScreen();
                            }
                            Toast.makeText(getContext(), R.string.success_update_customer, Toast.LENGTH_SHORT).show();
                            customerFromServer = updateCustomer;
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CustomerFromServer> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });

            });
            dialog.show();
        });


        assignProductForCustomer = view.findViewById(R.id.assignProductForCustomer);
        assignProductForCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masterInfoListener.addNewProductForCustomer(customerId);
            }
        });

        deleteCustomerBtn.setOnClickListener(view1 -> {
            Button btnCancel;
            Button deleteBtn;
            TextView dialogTitle;
            TextView dialogDesc;
            TextView deleteConfirmation;

            Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_design);

            btnCancel = dialog.findViewById(R.id.btn_action_cancel);
            deleteBtn = dialog.findViewById(R.id.btn_action_delete);
            dialogTitle = dialog.findViewById(R.id.tv_delete_dialog_title);
            dialogDesc = dialog.findViewById(R.id.tv_delete_dialog_desc);
            deleteConfirmation = dialog.findViewById(R.id.tv_delete_dialog_confirmation_msg);

            dialogTitle.setText(String.format(getString(R.string.title_delete_dialog), customerFromServer.getCustomerName()));
            dialogDesc.setText(R.string.msg_delete_customer_desc);
            deleteConfirmation.setText(R.string.msg_delete_customer_confirmation);

            btnCancel.setOnClickListener(view11 -> dialog.dismiss());

            deleteBtn.setOnClickListener(view12 -> {
                Call<ResponseBody> deleteCustomerById = customerService.deleteCustomer(customerId);
                deleteCustomerById.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), R.string.msg_delete_customer_success, Toast.LENGTH_SHORT).show();
                            if (masterInfoListener != null) {
                                masterInfoListener.onBackToPreviousScreen();
                            }
                        } else {
                            Toast.makeText(getContext(), R.string.no_customer_found, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            });
            dialog.show();
        });


        if (getActivity() != null) {
            ((MasterInfoActivity) getActivity()).setActionBarTitle(customerName);
        }


        return view;
    }
}