package com.technosoul.milkwala.ui.masterinfo.suppliers;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class SupplierDetailsFragment extends Fragment {
    TextView tvSupplierName;
    TextView tvSupplierAddress;
    TextView tvSupplierNumber;
    TextView tvSupplierAltNumber;
    Button deleteSupplier;
    String suppName, suppAddress, suppNumber, suppAltNumber;
    int supplierId;

    MasterInfoListener listener;

    public SupplierDetailsFragment(int supplierId) {
        this.supplierId = supplierId;
        // Required empty public constructor
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplier_details, container, false);
        tvSupplierName = view.findViewById(R.id.supplierName);
        tvSupplierAddress = view.findViewById(R.id.supplierAddress);
        tvSupplierNumber = view.findViewById(R.id.supplierNumber);
        tvSupplierAltNumber = view.findViewById(R.id.supplier_alt_number);
        deleteSupplier = view.findViewById(R.id.deleteSupplier);

        Bundle bundle = getArguments();
        if (bundle != null) {
            suppName = bundle.getString("supplierTxt");
            suppAddress = bundle.getString("supplierAddress");
            suppNumber = bundle.getString("supplierNumber");
            suppAltNumber = bundle.getString("supplierAltNumber");
            tvSupplierName.setText(suppName);
            tvSupplierAddress.setText(suppAddress);
            tvSupplierNumber.setText(suppNumber);
            if (suppAltNumber == null) {
                suppAltNumber = "";
            }
            tvSupplierAltNumber.setText(suppAltNumber);
        }

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());

        deleteSupplier.setOnClickListener(view12 -> {
            //dialog box buttons
            Button btnCancelDeleteSupplier;
            Button btnDeleteSupplier;
            TextView titleDeleteSupplier;
            TextView msgDeleteSupplier;
            TextView confirmationDeleteSupplier;

            Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_design);

            btnCancelDeleteSupplier = dialog.findViewById(R.id.btn_action_cancel);
            btnDeleteSupplier = dialog.findViewById(R.id.btn_action_delete);
            titleDeleteSupplier = dialog.findViewById(R.id.tv_delete_dialog_title);
            msgDeleteSupplier = dialog.findViewById(R.id.tv_delete_dialog_desc);
            confirmationDeleteSupplier = dialog.findViewById(R.id.tv_delete_dialog_confirmation_msg);

            titleDeleteSupplier.setText(String.format(getString(R.string.title_delete_dialog), suppName));
            confirmationDeleteSupplier.setText(R.string.msg_delete_supplier_confirmation);
            msgDeleteSupplier.setText(R.string.msg_delete_supplier_desc);

            btnCancelDeleteSupplier.setOnClickListener(view1 -> dialog.dismiss());

            btnDeleteSupplier.setOnClickListener(v -> {
                myDbHelper.supplierDao().deleteById(supplierId);
                Toast.makeText(getContext(), R.string.msg_delete_supplier_success, Toast.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.onBackToPreviousScreen();
                }
                dialog.dismiss();
            });

            dialog.show();
        });
        return view;
    }
}


