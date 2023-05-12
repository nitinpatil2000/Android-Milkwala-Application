package com.technosoul.milkwala.todaydeliver;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.db.Supplier;

import java.util.ArrayList;
import java.util.List;

public class TodayDeliverFragment extends Fragment {
    Button addProductInCustomer;
    int supplierId;
    Spinner deliveryCustomerSpinner;
    ArrayList<String> customerProductList = new ArrayList<>();
    RecyclerView addProductRecyclerView;
    ArrayList<ProductDetails> productDetailsList;
    DeliveryProductsAdapter deliveryProductsAdapter;
    MyDbHelper myDbHelper;

    public TodayDeliverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_deliver, container, false);
//        addProductRecyclerView = view.findViewById(R.id.addProductRecyclerView);


        deliveryCustomerSpinner = view.findViewById(R.id.deliverCustomerSpinner);
//        show spinner
        myDbHelper = MyDbHelper.getDB(getActivity());
        List<Customer> customerList = (ArrayList<Customer>) myDbHelper.customerDao().getAllCustomers();
        customerProductList.add("No Selected");
        //iterate the value using for loop and show in the spinner
        for (Customer customer : customerList) {
            customerProductList.add(customer.getCustomerName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, customerProductList);
        deliveryCustomerSpinner.setAdapter(adapter);


        addProductRecyclerView = view.findViewById(R.id.addProductRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        addProductRecyclerView.setLayoutManager(gridLayoutManager);


        addProductInCustomer = view.findViewById(R.id.addProductsInCustomer);
        addProductInCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDbHelper = MyDbHelper.getDB(getActivity());
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.customer_dialog_design);
                dialog.setCancelable(false);

                //set the width and height of the dialog.
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                lp.width = (int) (getResources().getDisplayMetrics().widthPixels * 1.0);
                lp.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.4);
                window.setAttributes(lp);

                Button cancelCustomerDialogBtn, addCustomerDialogBtn;
                Spinner selectSupplierInCustomer, selectProductInSupplier;
                EditText editProductsQuantity;

                cancelCustomerDialogBtn = dialog.findViewById(R.id.cancelCustomerDialogButton);
                addCustomerDialogBtn = dialog.findViewById(R.id.selectCustomerDialogBtn);

                selectSupplierInCustomer = dialog.findViewById(R.id.selectSupplierInCustomer);
                selectProductInSupplier = dialog.findViewById(R.id.selectProductBySupplier);

                editProductsQuantity = dialog.findViewById(R.id.selectProductQuantity);

                ArrayList<String> supplierNameList = new ArrayList<>();
                List<Supplier> suppliersList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();

                for (Supplier supplier : suppliersList) {
                    supplierNameList.add(supplier.getSupplierName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, supplierNameList);
                selectSupplierInCustomer.setAdapter(arrayAdapter);

                selectSupplierInCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedItem = adapterView.getItemAtPosition(i).toString();
                        for (Supplier supplier : suppliersList) {
                            if (supplier != null && TextUtils.equals(supplier.getSupplierName(), selectedItem)) {
                                supplierId = supplier.getSupplierId();
                                break;
                            }
                        }
                        ArrayList<String> productNames = new ArrayList<>();
                        List<ProductDetails> productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);

                        for (ProductDetails productDetails : productDetailsList) {
                            productNames.add(productDetails.getProductDetailsName());
                        }
                        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, productNames);
                        selectProductInSupplier.setAdapter(arrayAdapter1);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                cancelCustomerDialogBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                addCustomerDialogBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView customerProductUnit, customerProductRate, customerProductMrp;
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        View anotherView = inflater.inflate(R.layout.today_deliver_product, null);
//                        customerProductName = anotherView.findViewById(R.id.customerProductsName);
                        customerProductUnit = anotherView.findViewById(R.id.customerProductUnit);
//                        customerProductRate = anotherView.findViewById(R.id.customerProductRate);
//                        customerProductMrp = anotherView.findViewById(R.id.customerProductAmount);

//                        String productUnit = customerProductUnit.getText().toString();
//                        int productUnitt = 0;
//                        for(ProductDetails productDetails : productDetailsList) {
//                            int productDetailsId = productDetails.getProductDetailsId();
                        String productDetailsName = selectProductInSupplier.getSelectedItem().toString();
                        long quantity = Long.parseLong(editProductsQuantity.getText().toString());

                        ArrayList<ProductDetails> productDetailsList = new ArrayList<>();
                        DeliveryProductsAdapter deliveryProductsAdapter = new DeliveryProductsAdapter(getContext(), productDetailsList);
                        addProductRecyclerView.setAdapter(deliveryProductsAdapter);
                        productDetailsList.add(new ProductDetails(productDetailsName, quantity));
                        deliveryProductsAdapter.notifyDataSetChanged();
                        dialog.dismiss();
//                            String productName = customerProductName.getText().toString();
//                            String productUnit = customerProductUnit.getText().toString();
//                            String productRate = customerProductRate.getText().toString();
//                            String productMrp = customerProductMrp.getText().toString();
//                            ProductDetails productDetails1 = new ProductDetails();
//                            productDetails1.setProductDetailsId(productDetailsId);
//                            productDetails1.setProductDetailsName(productName);
//                            productDetails1.setProductDetailsUnit(productUnit);
//                            productDetails1.setProductSupplierRate(productRate);
//                            productDetails1.setProductDetailsMrp(productMrp);
//                        }
                    }
                });
                dialog.show();


            }
        });

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Today's DeliveryPerson");
        return view;
    }
}