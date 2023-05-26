package com.technosoul.milkwala.ui.masterinfo.products;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddNewProductFragment extends Fragment {
    Spinner unitsSpinner;
    Spinner typeSpinner;
    ArrayList<String> unitNamesArrayList = new ArrayList<>();
    ArrayList<String> typeNamesArrayList = new ArrayList<>();
    Button btnAddNewProduct;
    EditText etProductName;
    EditText etProductMrp;
    EditText etSupplierRate;
    EditText edtWholesaleRate;
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
        unitNamesArrayList.add(Constants.CARET);

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

        typeSpinner = view.findViewById(R.id.typeSpinner);
        typeNamesArrayList.add(Constants.MILK);
        typeNamesArrayList.add(Constants.BUTTERMILK);
        typeNamesArrayList.add(Constants.PANEER);
        typeNamesArrayList.add(Constants.GHEE);
        typeNamesArrayList.add(Constants.BUTTER);
        typeNamesArrayList.add(Constants.CURD);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, typeNamesArrayList);
        typeSpinner.setAdapter(typeAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String selectedItem = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(getContext(), "Selected Type is : " + selectedItem, Toast.LENGTH_SHORT).show();
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
        edtWholesaleRate = view.findViewById(R.id.edit_wholesale_rate);

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
            double productPrice = Double.parseDouble(productMrp);

            String productDetailsUnit = unitsSpinner.getSelectedItem().toString();
            if (TextUtils.isEmpty(productDetailsUnit)) {
                Toast.makeText(getContext(), R.string.err_empty_unit, Toast.LENGTH_SHORT).show();
                return;
            }

            String productDetailType = typeSpinner.getSelectedItem().toString();
            if (TextUtils.isEmpty(productDetailType)) {
                Toast.makeText(getContext(), R.string.err_empty_type, Toast.LENGTH_SHORT).show();
                return;
            }

            String productSupplierRate = etSupplierRate.getText().toString();
            if (TextUtils.isEmpty(productSupplierRate)) {
                Toast.makeText(getContext(), R.string.err_empty_supplier_rate, Toast.LENGTH_SHORT).show();
                return;
            }
            double supplierRate = Double.parseDouble(productSupplierRate);

            String productWholesaleRate = edtWholesaleRate.getText().toString();
            if (TextUtils.isEmpty(productWholesaleRate)) {
                Toast.makeText(getContext(), R.string.err_empty_vendor_rate, Toast.LENGTH_SHORT).show();
                return;
            }
            double wholesaleRate = Double.parseDouble(productWholesaleRate);

            ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
            Retrofit retrofit = apiRetrofitService.getRetrofit();
            ProductService productService = retrofit.create(ProductService.class);
            ProductFromServer productFromServer = new ProductFromServer();
            productFromServer.setProductName(name);
            productFromServer.setProductUnit(productDetailsUnit);
            productFromServer.setProductType(productDetailType);
            productFromServer.setProductSupplierRate(supplierRate);
            productFromServer.setProductWholesaleRate(wholesaleRate);
            productFromServer.setProductMrpRetailerRate(productPrice);
            productFromServer.setSupplierId(supplierId);

            Call<String> createProductFromSupplier = productService.createProduct(productFromServer);
            createProductFromSupplier.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        String productMessage = response.body();
                        if(productMessage != null) {
                            if (listener != null) {
                                listener.onBackToPreviousScreen();
                            }
                        }

                    } else {
                        Toast.makeText(getContext(), "Failed to add product. Error: " + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                        // Log the request body
                        Request request = call.request();
                        try {
                            Buffer buffer = new Buffer();
                            request.body().writeTo(buffer);
                            String requestBody = buffer.readUtf8();
                            Log.d("API Request Body", requestBody);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getContext(), "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log the request body
                    Request request = call.request();
                    try {
                        Buffer buffer = new Buffer();
                        request.body().writeTo(buffer);
                        String requestBody = buffer.readUtf8();
                        Log.d("API Request Body", requestBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


            //get the id of the selected supplier
//            ProductDetails productDetails = new ProductDetails(name, productSupplierRate, productVendorRate, productDetailsUnit, productMrp);
//            productDetails.setSupplierId(supplierId);
//            myDbHelper.productDetailsDto().addProduct(productDetails);
//            Toast.makeText(getContext(), R.string.msg_product_added_success, Toast.LENGTH_SHORT).show();
        });

//        if (listener != null) {
//            listener.onBackToPreviousScreen();
//        }

        if (getActivity() != null) {
            ((MasterInfoActivity) getActivity()).setActionBarTitle("Add New Product");
        }
        return view;
    }
}