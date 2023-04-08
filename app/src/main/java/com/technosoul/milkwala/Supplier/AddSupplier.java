package com.technosoul.milkwala.Supplier;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;

public class AddSupplier extends Fragment {
    EditText editSupplierName;
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
        addNewSupplierBtn = view.findViewById(R.id.addNewSupplierBtn);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        addNewSupplierBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String supplierName = editSupplierName.getText().toString();
                if (!supplierName.isEmpty()) {
                    myDbHelper.supplierDao().addSupplier(
                            new Supplier(supplierName)
                    );
                    Toast.makeText(getContext(), "Supplier added successfully", Toast.LENGTH_SHORT).show();
                    // return to the supplierFragment
                    FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();
                }
            }
        });
        return view;
    }

}