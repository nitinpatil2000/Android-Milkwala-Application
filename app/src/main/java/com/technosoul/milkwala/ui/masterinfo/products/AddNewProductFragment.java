package com.technosoul.milkwala.ui.masterinfo.products;

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

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class AddNewProductFragment extends Fragment {
    Spinner unitsSpinner;
    ArrayList<String> arrayNames = new ArrayList<>();
    Button addNewProductBtn;
    EditText editProductName, editProductMrp;
    EditText editSupplierRate, editVenderRate;
    private final int supplierId;

    private MasterInfoListener listener;

    public AddNewProductFragment(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        unitsSpinner = view.findViewById(R.id.spinner);
        arrayNames.add(Constants.UNIT_LITRE_1);
        arrayNames.add(Constants.UNIT_LITRE_2);
        arrayNames.add(Constants.UNIT_ML_500);
        arrayNames.add(Constants.UNIT_ML_250);
        arrayNames.add(Constants.UNIT_ML_100);
        arrayNames.add(Constants.UNIT_KG_1);
        arrayNames.add(Constants.UNIT_KG_2);
        arrayNames.add(Constants.UNIT_GRAM_500);
        arrayNames.add(Constants.UNIT_GRAM_250);
        arrayNames.add(Constants.UNIT_GRAM_200);
        arrayNames.add(Constants.UNIT_GRAM_100);
        arrayNames.add(Constants.UNIT_GRAM_50);
        arrayNames.add(Constants.UNIT_PACKETS);
//        arrayNames.add("No Selected");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayNames);
        unitsSpinner.setAdapter(adapter);

        unitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String selectedItem = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(getContext(), "Selected Item is : " + selectedItem, Toast.LENGTH_SHORT).show();
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
        editVenderRate = view.findViewById(R.id.tv_Vendor_rate);

        addNewProductBtn = view.findViewById(R.id.addNewProductBtn);
        // Set click listener on add button

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        addNewProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productDetailsName = editProductName.getText().toString();
                String productDetailsMrp = editProductMrp.getText().toString();
                String productDetailsUnit = unitsSpinner.getSelectedItem().toString();
                String productSupplierRate = editSupplierRate.getText().toString();
                String productVenderRate = editVenderRate.getText().toString();

                if (!productDetailsName.isEmpty() && !productDetailsMrp.isEmpty() && !productDetailsUnit.isEmpty() && !productSupplierRate.isEmpty() && !productVenderRate.isEmpty()) {
                    //get the id of the selected supplier
                    ProductDetails productDetails = new ProductDetails(productDetailsName, productSupplierRate, productVenderRate, productDetailsUnit, productDetailsMrp);
                    productDetails.setSupplierId(supplierId);
                    myDbHelper.productDetailsDto().addProduct(productDetails);
                    Toast.makeText(getContext(), "Product added successfully", Toast.LENGTH_SHORT).show();
                    // return to the supplierFragment
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();
                }
            }
        });
        return view;
    }
}