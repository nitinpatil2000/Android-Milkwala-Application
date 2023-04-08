package com.technosoul.milkwala.Supplier;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;

import java.util.ArrayList;
import java.util.Random;

public class SupplierDetailsFragment extends Fragment {
    TextView supplierName;
    Button deleteSupplier;
    String supplierTxtValue;
    RecyclerViewAdapter recyclerViewAdapter;

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
            supplierTxtValue = bundle.getString("supplierTxt");
            supplierName.setText(supplierTxtValue);
        }

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
//       Long itemIdToDelete = getIntent().getLongExtra("itemId", -1);

        deleteSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog box buttons
                TextView cancelBtn, deleteBtn, deleteTxt;
                Dialog dialog = new Dialog(getContext());

                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_design);

                cancelBtn = dialog.findViewById(R.id.cancelBtn);
                deleteBtn = dialog.findViewById(R.id.delteBtn);
                deleteTxt = dialog.findViewById(R.id.deleteTxt);
                deleteTxt.setText(" Delete " + supplierTxtValue );

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                    deleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDbHelper.supplierDao().deleteById(getId());
                            SupplierFragment supplierFragment = new SupplierFragment();

                            //remove the item from the recyclerVIew.
                            int position = getArguments().getInt("position");
                            supplierFragment.showDetailsFragmentt(position);

                            Toast.makeText(getContext(), "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
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


