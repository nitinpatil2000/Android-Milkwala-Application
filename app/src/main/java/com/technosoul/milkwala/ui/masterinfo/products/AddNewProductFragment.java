package com.technosoul.milkwala.ui.masterinfo.products;

import android.os.Bundle;
import android.text.TextUtils;
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

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

public class AddNewProductFragment extends Fragment {
    Spinner unitsSpinner;
    ArrayList<String> unitNamesArrayList = new ArrayList<>();
    Button btnAddNewProduct;
    EditText etProductName;
    EditText etProductMrp;
    EditText etSupplierRate;
    EditText edtVendorRate;
    private final int supplierId;

    private MasterInfoListener listener;

    public AddNewProductFragment(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        unitsSpinner = view.findViewById(R.id.spinner);
        unitNamesArrayList.add(Constants.UNIT_LITRE_1);
        unitNamesArrayList.add(Constants.UNIT_LITRE_2);
        unitNamesArrayList.add(Constants.UNIT_ML_500);
        unitNamesArrayList.add(Constants.UNIT_ML_250);
        unitNamesArrayList.add(Constants.UNIT_ML_100);
        unitNamesArrayList.add(Constants.UNIT_KG_1);
        unitNamesArrayList.add(Constants.UNIT_KG_2);
        unitNamesArrayList.add(Constants.UNIT_GRAM_500);
        unitNamesArrayList.add(Constants.UNIT_GRAM_250);
        unitNamesArrayList.add(Constants.UNIT_GRAM_200);
        unitNamesArrayList.add(Constants.UNIT_GRAM_100);
        unitNamesArrayList.add(Constants.UNIT_GRAM_50);
        unitNamesArrayList.add(Constants.UNIT_PACKETS);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, unitNamesArrayList);
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
        etProductName = view.findViewById(R.id.editProductName);
        etProductMrp = view.findViewById(R.id.editProductMrp);
        etSupplierRate = view.findViewById(R.id.editSupplierRate);
        edtVendorRate = view.findViewById(R.id.tv_Vendor_rate);

        btnAddNewProduct = view.findViewById(R.id.addNewProductBtn);
        // Set click listener on add button

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        btnAddNewProduct.setOnClickListener(v -> {
            String name = etProductName.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), R.string.err_empty_product_name, Toast.LENGTH_SHORT).show();
                return;
            }
            if (name.length() < 3) {
                Toast.makeText(getContext(), R.string.err_min_length_product_name, Toast.LENGTH_SHORT).show();
                return;
            }

            String productMrp = etProductMrp.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), R.string.err_empty_product_mrp, Toast.LENGTH_SHORT).show();
                return;
            }

            String productDetailsUnit = unitsSpinner.getSelectedItem().toString();
            if (TextUtils.isEmpty(productDetailsUnit)) {
                Toast.makeText(getContext(), R.string.err_empty_unit, Toast.LENGTH_SHORT).show();
                return;
            }

            String productSupplierRate = etSupplierRate.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), R.string.err_empty_supplier_rate, Toast.LENGTH_SHORT).show();
                return;
            }

            String productVendorRate = edtVendorRate.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), R.string.err_empty_vendor_rate, Toast.LENGTH_SHORT).show();
                return;
            }

            //get the id of the selected supplier
            ProductDetails productDetails = new ProductDetails(name, productSupplierRate, productVendorRate, productDetailsUnit, productMrp);
            productDetails.setSupplierId(supplierId);
            myDbHelper.productDetailsDto().addProduct(productDetails);
            Toast.makeText(getContext(), R.string.msg_product_added_success, Toast.LENGTH_SHORT).show();

            if (listener != null) {
                listener.onBackToPreviousScreen();
            }
        });
        return view;
    }
}