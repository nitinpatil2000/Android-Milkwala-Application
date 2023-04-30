package com.technosoul.milkwala.supplier;

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

public class AddSupplier extends Fragment {
    EditText editSupplierName, editSupplierAddress, editSupplierNumber;
    Button addNewSupplierBtn;
    int position;


    public AddSupplier() {
        // Required empty public constructor
    }

    // Define an interface to communicate with the parent activity
    public interface onNewSupplierAddedListner {
        void onNewSupplierAdded(String name, int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_supplier, container, false);
        editSupplierName = view.findViewById(R.id.editSupplierName);
        editSupplierAddress = view.findViewById(R.id.editSupplierAddress);
        editSupplierNumber = view.findViewById(R.id.editSupplierNumber);
        addNewSupplierBtn = view.findViewById(R.id.addNewSupplierBtn);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        addNewSupplierBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String supplierName = editSupplierName.getText().toString();
                String supplierAddress = editSupplierAddress.getText().toString();
                String supplierNumber = editSupplierNumber.getText().toString();

                if (!supplierName.isEmpty() && !supplierAddress.isEmpty() && !supplierNumber.isEmpty()) {
                    myDbHelper.supplierDao().addSupplier(
                            new Supplier(supplierName, supplierAddress, supplierNumber)
                    );

                    Toast.makeText(getContext(), "Supplier added successfully", Toast.LENGTH_SHORT).show();

                    // return to the supplierFragment
                    SupplierFragment supplierFragment = new SupplierFragment();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, supplierFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
//                    FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
//                    fragmentManager.popBackStack();
                }
            }
        });
        return view;
    }

}