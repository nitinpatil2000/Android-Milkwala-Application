package com.example.milkapplication.Supplier;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milkapplication.Helper.MyDbHelper;
import com.example.milkapplication.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;

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

        addNewSupplierBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editSupplierName.getText().toString();
                if (!name.isEmpty()) {
                    MyDbHelper myDbHelper = new MyDbHelper(getActivity());
                    myDbHelper.insertData(name);
                    Toast.makeText(getContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
                    editSupplierName.setText("");
                    // return to the supplierFragment
                   FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
                   fragmentManager.popBackStack();
                }
            }
        });
        return view;
    }

}