package com.technosoul.milkwala.ui.masterinfo.suppliers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.supplier.Supplier;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class AddNewSupplierFragment extends Fragment {
    EditText supplierName;
    EditText supplierAddress;
    EditText supplierNumber;
    EditText supplierAlternateNumber;
    Button btnAddNewSupplier;

    MasterInfoListener listener;

    int position;

    public AddNewSupplierFragment() {
        // Required empty public constructor
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    // Define an interface to communicate with the parent activity
    public interface onNewSupplierAddedListner {
        void onNewSupplierAdded(String name, int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_supplier, container, false);
        supplierName = view.findViewById(R.id.editSupplierName);
        supplierAddress = view.findViewById(R.id.editSupplierAddress);
        supplierNumber = view.findViewById(R.id.editSupplierNumber);
        btnAddNewSupplier = view.findViewById(R.id.btnAddNewSupplier);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        btnAddNewSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String supplierName = AddNewSupplierFragment.this.supplierName.getText().toString();
                String supplierAddress = AddNewSupplierFragment.this.supplierAddress.getText().toString();
                String supplierNumber = AddNewSupplierFragment.this.supplierNumber.getText().toString();

                if (!supplierName.isEmpty() && !supplierAddress.isEmpty() && !supplierNumber.isEmpty()) {
                    myDbHelper.supplierDao().addSupplier(
                            new Supplier(supplierName, supplierAddress, supplierNumber)
                    );

                    Toast.makeText(getContext(), "Supplier added successfully", Toast.LENGTH_SHORT).show();

                    if (listener != null) {
                        listener.onBackToPreviousScreen();
                    }
                }
            }
        });
        return view;
    }


}