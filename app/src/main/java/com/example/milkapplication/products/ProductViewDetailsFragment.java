package com.example.milkapplication.products;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milkapplication.R;

public class ProductViewDetailsFragment extends Fragment {
    TextView txtProductName, txtUnit, txtProductMrp;
    TextView suppName;
    TextView deleteNewProductBtn, cancelBtn, deleteBtn;

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
        deleteNewProductBtn = view.findViewById(R.id.deleteNewProductBtn);



        Bundle bundle = getArguments();
        if (bundle != null) {
            String viewProductName = bundle.getString("viewProductName");
            String viewProductUnit = bundle.getString("viewProductUnit");
            String viewProductMrp = bundle.getString("viewProductMrp");

            txtProductName.setText(viewProductName);
            txtUnit.setText(viewProductUnit);
            txtProductMrp.setText(viewProductMrp);
        }

        deleteNewProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_design);

                cancelBtn = dialog.findViewById(R.id.cancelBtn);
                deleteBtn = dialog.findViewById(R.id.delteBtn);

                //user click the delete btn then set the value in the suppName of the supplierTxt.
                Bundle bundle = new Bundle();
                String productTxt = "";
                if(bundle!=null){
                    productTxt = bundle.getString("productName");
                }
                suppName =dialog.findViewById(R.id.suppName);
                suppName.setText(productTxt);

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int positionToDelete = 0 /* set this to the position of the item to be deleted */;
                        FragmentManager fragmentManager = getParentFragmentManager();
                        Fragment fragment = fragmentManager.findFragmentById(R.id.container);
                        if (fragment instanceof ProductDetailsFragment) {
                            ProductDetailsFragment productDetailsFragment = (ProductDetailsFragment) fragment;
                            productDetailsFragment.removeItem(positionToDelete);
                            getActivity().getSupportFragmentManager().popBackStack();
//                        } else {
//                            Toast.makeText(getActivity(), "Error: Fragment is not an instance of ProductDetailsFragment", Toast.LENGTH_SHORT).show();
//                        }
                        }
                    }
                });
                dialog.show();
            }
        });

        return view;

    }

}