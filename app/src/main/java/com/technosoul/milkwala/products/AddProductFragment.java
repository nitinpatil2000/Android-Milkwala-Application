package com.technosoul.milkwala.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.technosoul.milkwala.helper.MyDbHelper;
import com.technosoul.milkwala.R;

import java.util.ArrayList;

public class AddProductFragment extends Fragment {
Spinner spinner;
ArrayList<String>arrayNames = new ArrayList<>();
Button addNewProductBtn;
EditText editProductName, editProductMrp;
EditText editSupplierRate, editVenderRate;
private int supplierId;



    public AddProductFragment(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_add_product, container, false);
        spinner = view.findViewById(R.id.spinner);
        arrayNames.add("1 Litre");
        arrayNames.add("500 ml");
        arrayNames.add("1 kg");
        arrayNames.add("2 Litre");
        arrayNames.add("3 Litre");
//        arrayNames.add("No Selected");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, arrayNames);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String selectedItem = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(getContext(), "Selected Item is : "+ selectedItem, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //after click the add Btn then element is added in the recyclerView;
        editProductName = view.findViewById(R.id.editProductName);
        editProductMrp = view.findViewById(R.id.editProductMrp);
        editSupplierRate = view.findViewById(R.id.editSupplierRate);
        editVenderRate = view.findViewById(R.id.editVenderRate);

        addNewProductBtn = view.findViewById(R.id.addNewProductBtn);
        // Set click listener on add button

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        addNewProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productDetailsText = editProductName.getText().toString();
                String productDetailsMrp = editProductMrp.getText().toString();
                String productDetailsUnit = spinner.getSelectedItem().toString();
                String productSupplierRate = editSupplierRate.getText().toString();
                String productVenderRate = editVenderRate.getText().toString();

                if(!productDetailsText.isEmpty() && !productDetailsMrp.isEmpty() && !productDetailsUnit.isEmpty() && !productSupplierRate.isEmpty() && !productVenderRate.isEmpty()){
                    //get the id of the selected supplier
                    ProductDetails productDetails = new ProductDetails(productDetailsText, productDetailsMrp, productDetailsUnit, productSupplierRate, productVenderRate);
                    productDetails.setSupplierId(supplierId);
                    myDbHelper.productDetailsDto().addProduct(productDetails);
                    Toast.makeText(getContext(), "Product added successfully", Toast.LENGTH_SHORT).show();
                    // return to the supplierFragment
                    FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();
                }
            }
        });
        return view;
    }
}