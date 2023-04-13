package com.technosoul.milkwala.Supplier;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;

public class SupplierDetailsFragment extends Fragment {
    TextView supplierName, supplierAddress, supplierNumber;
    Button deleteSupplier;
    String suppName, suppAddress, suppNumber;
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
        supplierAddress = view.findViewById(R.id.supplierAddress);
        supplierNumber = view.findViewById(R.id.supplierNumber);
        deleteSupplier = view.findViewById(R.id.deleteSupplier);

        Bundle bundle = getArguments();
        if(bundle != null){
            suppName = bundle.getString("supplierTxt");
            suppAddress = bundle.getString("supplierAddress");
            suppNumber = bundle.getString("supplierNumber");
            supplierName.setText(suppName);
            supplierAddress.setText(suppAddress);
            supplierNumber.setText(suppNumber);
        }

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
//       Long itemIdToDelete = getIntent().getLongExtra("itemId", -1);

        deleteSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog box buttons
                TextView cancelBtn, deleteBtn, deleteTxt, deleteInfo, dltMsg;

                Dialog dialog = new Dialog(getContext());

                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_design);

                cancelBtn = dialog.findViewById(R.id.cancelBtn);
                deleteBtn = dialog.findViewById(R.id.delteBtn);
                deleteTxt = dialog.findViewById(R.id.deleteTxt);
                deleteInfo = dialog.findViewById(R.id.deleteInfo);
                dltMsg = dialog.findViewById(R.id.dltMsg);

                deleteTxt.setText(" Delete " + "Supplier " + suppName);
                dltMsg.setText("are you sure want to delete \n this supplier..");
                deleteInfo.setText(new StringBuilder().append("Please note, if you delete the \n")
                        .append(suppName).append(" supplier, then all his \n")
                        .append("products & associated data will get removed.\n").toString());

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


