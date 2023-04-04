package com.example.milkapplication.Supplier;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.milkapplication.MainActivity;
import com.example.milkapplication.R;

import java.util.ArrayList;
import java.util.Random;

public class SupplierDetailsFragment extends Fragment {
    TextView supplierName;
    TextView deleteSupplier;
    ArrayList<Supplier> suppliers = new ArrayList<>();


    //dialog box buttons
    TextView cancelBtn, deleteBtn;
    TextView suppName;


    public SupplierDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplier_details, container, false);
        supplierName = view.findViewById(R.id.supplierName);
        deleteSupplier = view.findViewById(R.id.deleteSupplier);



        Bundle bundle = getArguments();
        if(bundle != null){
            String supplierTxtValue = bundle.getString("supplierTxt");
            supplierName.setText(supplierTxtValue);
        }

        deleteSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());

                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_design);

                cancelBtn = dialog.findViewById(R.id.cancelBtn);
                deleteBtn = dialog.findViewById(R.id.delteBtn);

                //user click the delete btn then set the value in the suppName of the supplierTxt.
                Bundle bundle = getArguments();
                String supplierTxt = "";
                if(bundle != null){
                    supplierTxt = bundle.getString("supplierTxt");
                }
                suppName = dialog.findViewById(R.id.suppName);
                suppName.setText(supplierTxt);

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("SupplierDetailsFragment", "Clicked delete button");
                        Log.d("SupplierDetailsFragment", "Suppliers list size: " + suppliers.size());

                        if (suppliers.size() > 0) {
                            int position = new Random().nextInt(suppliers.size());
                            Log.d("SupplierDetailsFragment", "Generated random position: " + position);

                            Bundle bundle = new Bundle();
                            bundle.putInt("position", position);
                            getParentFragmentManager().setFragmentResult("delete", bundle);

                            getParentFragmentManager().popBackStack();
                            dialog.dismiss();
                        } else {
                            Log.d("SupplierDetailsFragment", "Suppliers list is empty, cannot delete");
                        }
                    }
                });
                dialog.show();
            }
        });
        return view;
    }
}


