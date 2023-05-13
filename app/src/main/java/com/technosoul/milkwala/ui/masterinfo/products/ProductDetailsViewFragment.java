package com.technosoul.milkwala.ui.masterinfo.products;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class ProductDetailsViewFragment extends Fragment {
    TextView tvProductName;
    TextView tvProductUnit;
    TextView tvProductMrp;
    Button deleteNewProductBtn;
    String viewProductName;
    String viewProductUnit;
    ProductDetails productDetails;
    TextView tvVendorRate;
    TextView tvSupplierRate;
    private final int productDetailsId;

    private MasterInfoListener masterInfoListener;

    public ProductDetailsViewFragment(int productDetailsId) {
        this.productDetailsId = productDetailsId;
        // Required empty public constructor
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_view_details, container, false);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        productDetails = myDbHelper.productDetailsDto().getProductById(productDetailsId);

        tvProductName = view.findViewById(R.id.tv_product_name);
        tvProductUnit = view.findViewById(R.id.tv_product_unit);
        tvProductMrp = view.findViewById(R.id.tv_product_mrp);
        tvSupplierRate = view.findViewById(R.id.tv_supplier_rate);
        tvVendorRate = view.findViewById(R.id.tv_Vendor_rate);
        deleteNewProductBtn = view.findViewById(R.id.btnDeleteProduct);

        tvProductName.setText(viewProductName = productDetails.getProductDetailsName());
        tvProductUnit.setText(viewProductUnit = productDetails.getProductDetailsUnit());
        tvProductMrp.setText(productDetails.getProductDetailsMrp());
        tvSupplierRate.setText(productDetails.getProductSupplierRate());
        tvVendorRate.setText(productDetails.getProductVenderRate());

        deleteNewProductBtn.setOnClickListener(view1 -> {
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

            dialogTitle.setText(String.format(getString(R.string.title_delete_dialog), viewProductName));
            dialogDesc.setText(R.string.msg_delete_product_desc);
            deleteConfirmation.setText(R.string.msg_delete_product_confirmation);

            btnCancel.setOnClickListener(view11 -> dialog.dismiss());

            deleteBtn.setOnClickListener(view112 -> {
                myDbHelper.productDetailsDto().deleteProductById(productDetailsId);
                Toast.makeText(getContext(), R.string.msg_delete_product_success, Toast.LENGTH_SHORT).show();
                if (masterInfoListener != null) {
                    masterInfoListener.onBackToPreviousScreen();
                }
                dialog.dismiss();
            });
            dialog.show();
        });

        return view;

    }

}