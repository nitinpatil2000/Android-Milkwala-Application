package com.technosoul.milkwala.products;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.helper.MyDbHelper;
import com.technosoul.milkwala.R;

public class ProductViewDetailsFragment extends Fragment {
    TextView txtProductName, txtUnit, txtProductMrp;
    Button deleteNewProductBtn;
    String viewProductName, viewProductUnit;
    ProductDetails productDetails;
    EditText editVenderRate, editSupplierRate;


    public ProductViewDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_view_details, container, false);

        txtProductName = view.findViewById(R.id.txtProductName);
        txtUnit = view.findViewById(R.id.txtUnit);
        txtProductMrp = view.findViewById(R.id.txtProductMrp);
        editSupplierRate = view.findViewById(R.id.editSupplierRate);
        editVenderRate = view.findViewById(R.id.editVenderRate);
        deleteNewProductBtn = view.findViewById(R.id.deleteNewProductBtn);



        Bundle bundle = getArguments();
        if (bundle != null) {
            viewProductName = bundle.getString("viewProductName");
            viewProductUnit = bundle.getString("viewProductUnit");
            String viewProductMrp = bundle.getString("viewProductMrp");
            String viewPSupplierRate = bundle.getString("viewSupplierRate");
            String viewPVenderRate = bundle.getString("viewVenderRate");

            txtProductName.setText(viewProductName);
            txtUnit.setText(viewProductUnit + " pack");
            txtProductMrp.setText(viewProductMrp);
            editSupplierRate.setText(viewPSupplierRate);
            editVenderRate.setText(viewPVenderRate);
        }

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        deleteNewProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView cancelBtn, deleteBtn, deleteTxt, deleteInfo, deleteMsg;

                Dialog dialog = new Dialog(getContext());
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_design);

                cancelBtn = dialog.findViewById(R.id.cancelBtn);
                deleteBtn = dialog.findViewById(R.id.delteBtn);
                deleteTxt = dialog.findViewById(R.id.deleteTxt);
                deleteInfo = dialog.findViewById(R.id.deleteInfo);
                deleteMsg = dialog.findViewById(R.id.dltMsg);


//                brandName = dialog.findViewById(R.id.brandName);

                deleteTxt.setText(" Delete " + viewProductName);
                deleteInfo.setText(viewProductName + " \n" + viewProductUnit);
                deleteMsg.setText("Are you sure want to delete this");

//                Bundle bundle = getArguments();
//                if(bundle != null) {
//                    String dltBrandName = bundle.getString("brandName");
//                    brandName.setText(dltBrandName + viewProductName);
//                }

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position;
//                      ProductDetails productDetails=  myDbHelper.productDetailsDto().getProductById();
                        if(productDetails != null){
                            myDbHelper.productDetailsDto().deleteProduct(productDetails);
                            Toast.makeText(getContext(), "Product Deleted Successfully !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        return view;

    }

}