package com.technosoul.milkwala.ui.masterinfo.suppliers;

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
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddNewSupplierFragment extends Fragment {
    EditText etSupplierName;
    EditText etSupplierAddress;
    EditText etSupplierNumber;
    EditText etSupplierAlternateNumber;
    EditText etSupplierEmail;
    Button btnAddNewSupplier;

    MasterInfoListener listener;
    SupplierService supplierService;

    public AddNewSupplierFragment() {
        // Required empty public constructor
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_supplier, container, false);

        etSupplierName = view.findViewById(R.id.editSupplierName);
        etSupplierEmail = view.findViewById(R.id.editSupplierEmail);
        etSupplierAddress = view.findViewById(R.id.editSupplierAddress);
        etSupplierNumber = view.findViewById(R.id.et_supplier_mobile);
        etSupplierAlternateNumber = view.findViewById(R.id.et_supplier_alt_mobile);
        etSupplierEmail = view.findViewById(R.id.editSupplierEmail);
        btnAddNewSupplier = view.findViewById(R.id.btnAddNewSupplier);
        btnAddNewSupplier.setOnClickListener(view1 -> onClickAddNewSupplier());

        if (getActivity() != null) {
            ((MasterInfoActivity) getActivity()).setActionBarTitle("Add New Supplier");
        }

        return view;
    }

    private void onClickAddNewSupplier() {
        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        String supplierName = etSupplierName.getText().toString();
        if (TextUtils.isEmpty(supplierName)) {
            Toast.makeText(getContext(), R.string.err_empty_supplier_name, Toast.LENGTH_LONG).show();
            etSupplierName.requestFocus();
            return;
        } else if (supplierName.length() < 3) {
            Toast.makeText(getContext(), R.string.err_min_length_supplier_name, Toast.LENGTH_LONG).show();
            etSupplierName.requestFocus();
            return;
        }

        String supplierEmail = etSupplierEmail.getText().toString();
        if(TextUtils.isEmpty(supplierEmail)){
            Toast.makeText(getContext(), R.string.err_empty_supplier_email, Toast.LENGTH_SHORT).show();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(supplierEmail).matches()) {
            Toast.makeText(getContext(), R.string.err_valid_supplier_email, Toast.LENGTH_SHORT).show();
            etSupplierEmail.requestFocus();
            return;
        }


        String supplierAddress = etSupplierAddress.getText().toString();
        if (TextUtils.isEmpty(supplierAddress)) {
            Toast.makeText(getContext(), R.string.err_empty_supplier_addr, Toast.LENGTH_LONG).show();
            etSupplierAddress.requestFocus();
            return;
        } else if (supplierAddress.length() < 8) {
            Toast.makeText(getContext(), R.string.err_min_length_supplier_addr, Toast.LENGTH_LONG).show();
            etSupplierAddress.requestFocus();
            return;
        }

        String supplierNumber = etSupplierNumber.getText().toString();
        if (TextUtils.isEmpty(supplierNumber)) {
            Toast.makeText(getContext(), R.string.err_empty_supplier_number, Toast.LENGTH_LONG).show();
            etSupplierNumber.requestFocus();
            return;
        } else if (!Patterns.PHONE.matcher(supplierNumber).matches()) {
            Toast.makeText(getContext(), R.string.err_invalid_mobile_number, Toast.LENGTH_LONG).show();
            etSupplierNumber.requestFocus();
            return;
        }else if (supplierNumber.length() > 10) {
            Toast.makeText(getContext(), R.string.err_max_digits_supplier_no, Toast.LENGTH_LONG).show();
            etSupplierNumber.requestFocus();
            return;
        }
        long supplierNo = Long.parseLong(supplierNumber);


        String supplierAltNumber = etSupplierAlternateNumber.getText().toString();
        if (TextUtils.isEmpty(supplierAltNumber)) {
            supplierAltNumber = "";
        } else if (!Patterns.PHONE.matcher(supplierAltNumber).matches()) {
            Toast.makeText(getContext(), R.string.err_invalid_alternate_number, Toast.LENGTH_LONG).show();
            etSupplierAlternateNumber.requestFocus();
            return;
        }else if (supplierAltNumber.length() > 10) {
            Toast.makeText(getContext(), R.string.err_max_digits_supplier_alt_no, Toast.LENGTH_LONG).show();
            etSupplierAlternateNumber.requestFocus();
            return;
        }
        long supplierAlterNo = Long.parseLong(supplierAltNumber);

//        myDbHelper.supplierDao().addSupplier(new Supplier(supplierName, supplierAddress, supplierNumber, supplierAltNumber));
//        Toast.makeText(getContext(), R.string.supplier_added_success, Toast.LENGTH_LONG).show();
//
        ApiRetrofitService retrofitService = new ApiRetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        SupplierService supplierService = retrofit.create(SupplierService.class);


        SupplierFromServer supplierFromServer = new SupplierFromServer();
        supplierFromServer.setSupplierName(supplierName);
        supplierFromServer.setSupplierEmail(supplierEmail);
        supplierFromServer.setSupplierAddress(supplierAddress);
        supplierFromServer.setSupplierNumber(supplierNo);
        supplierFromServer.setSupplierAltNumber(supplierAlterNo);

        Call<SupplierFromServer> call = supplierService.addSupplier(supplierFromServer);
        call.enqueue(new Callback<SupplierFromServer>() {
            @Override
            public void onResponse(Call<SupplierFromServer> call, Response<SupplierFromServer> response) {
                if (response.isSuccessful()) {
                    SupplierFromServer createSupplier = response.body();
                    if(createSupplier != null) {
                        Toast.makeText(getContext(), "Supplier Created Successfully!!", Toast.LENGTH_SHORT).show();
                        if (listener != null) {
                            listener.onBackToPreviousScreen();
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to create Supplier !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SupplierFromServer> call, Throwable t) {
            t.printStackTrace();
            }
        });



    }

}