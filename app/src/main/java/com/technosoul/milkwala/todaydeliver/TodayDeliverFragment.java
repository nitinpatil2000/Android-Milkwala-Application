package com.technosoul.milkwala.todaydeliver;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.customerorder.CustomerOrder;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.customer.CustomerFromServer;
import com.technosoul.milkwala.ui.masterinfo.customer.CustomerService;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TodayDeliverFragment extends Fragment {
    Spinner deliveryBoyCustomerSpinner;
    RecyclerView addProductRecyclerView;
    ArrayList<ProductFromServer> productFromServers;
    private List<CustomerFromServer> customerFromServers;
    private List<CustomerOrder> customerOrders;

    DeliveryProductsAdapter deliveryProductsAdapter;
    int customerId;

    View btnAboveViewLine;
    Button addProductInCustomer;


    public TodayDeliverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_deliver, container, false);

        deliveryBoyCustomerSpinner = view.findViewById(R.id.deliverCustomerSpinner);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        CustomerService customerService = retrofit.create(CustomerService.class);
        Call<List<CustomerFromServer>> getAllCustomers = customerService.getAllCustomers();
        getAllCustomers.enqueue(new Callback<List<CustomerFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<CustomerFromServer>> call, @NonNull Response<List<CustomerFromServer>> response) {
                if(response.isSuccessful()){
                    customerFromServers = response.body();
                    ArrayList<String> customerProductList = new ArrayList<>();
                    customerProductList.add("No Selected");

                    for (CustomerFromServer customer: customerFromServers){
                        customerProductList.add(customer.getCustomerName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line,customerProductList);
                    deliveryBoyCustomerSpinner.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CustomerFromServer>> call, @NonNull Throwable t) {

            }
        });

        btnAboveViewLine = view.findViewById(R.id.btnAboveViewLine);
        addProductInCustomer = view.findViewById(R.id.addProductsInCustomer);
        addProductRecyclerView = view.findViewById(R.id.addProductRecyclerView);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
//        addProductRecyclerView.setLayoutManager(gridLayoutManager);


        deliveryBoyCustomerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if(selectedItem.equals("No Selected")){
                    addProductRecyclerView.setVisibility(View.GONE);
                    btnAboveViewLine.setVisibility(View.GONE);
                    addProductInCustomer.setVisibility(View.GONE);
                }else {
                    addProductRecyclerView.setVisibility(View.VISIBLE);
                    btnAboveViewLine.setVisibility(View.VISIBLE);
                    addProductInCustomer.setVisibility(View.VISIBLE);

                    for (CustomerFromServer customer : customerFromServers) {
                        if (customer != null && TextUtils.equals(customer.getCustomerName(), selectedItem)) {
                            for (CustomerOrder customerOrder : customerOrders) {
                                customerId = customerOrder.getCustomerId();

                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                                addProductRecyclerView.setLayoutManager(gridLayoutManager);
                                Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
                                LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
                                addProductRecyclerView.setLayoutAnimation(animationController);

                            }
                        }
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







        addProductInCustomer.setOnClickListener(view1 -> {
            HashMap<EditText, ProductFromServer> editTextMap = new HashMap<>();
            ArrayList<ProductFromServer> productFromServers = deliveryProductsAdapter.getProductListFromServers();

        });


//        addProductInCustomer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myDbHelper = MyDbHelper.getDB(getActivity());
//                Dialog dialog = new Dialog(getContext());
//                dialog.setContentView(R.layout.customer_dialog_design);
//                dialog.setCancelable(false);
//
//                //set the width and height of the dialog.
//                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                Window window = dialog.getWindow();
//                lp.copyFrom(window.getAttributes());
//                lp.width = (int) (getResources().getDisplayMetrics().widthPixels * 1.0);
//                lp.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.4);
//                window.setAttributes(lp);
//
//                Button cancelCustomerDialogBtn, addCustomerDialogBtn;
//                Spinner selectSupplierInCustomer, selectProductInSupplier;
////                EditText editProductsQuantity;
//
//                cancelCustomerDialogBtn = dialog.findViewById(R.id.cancelCustomerDialogButton);
//                addCustomerDialogBtn = dialog.findViewById(R.id.selectCustomerDialogBtn);
//
//                selectSupplierInCustomer = dialog.findViewById(R.id.selectSupplierInCustomer);
//                selectProductInSupplier = dialog.findViewById(R.id.selectProductBySupplier);
//
////                editProductsQuantity = dialog.findViewById(R.id.selectProductQuantity);
//
//                ArrayList<String> supplierNameList = new ArrayList<>();
//                List<Supplier> suppliersList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();
//
//                for (Supplier supplier : suppliersList) {
//                    supplierNameList.add(supplier.getSupplierName());
//                }
//
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, supplierNameList);
//                selectSupplierInCustomer.setAdapter(arrayAdapter);
//
//                selectSupplierInCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        String selectedItem = adapterView.getItemAtPosition(i).toString();
//                        for (Supplier supplier : suppliersList) {
//                            if (supplier != null && TextUtils.equals(supplier.getSupplierName(), selectedItem)) {
//                                supplierId = supplier.getSupplierId();
//                                break;
//                            }
//                        }
//                        ArrayList<String> productNames = new ArrayList<>();
//                        List<ProductDetails> productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);
//
//                        for (ProductDetails productDetails : productDetailsList) {
//                            productNames.add(productDetails.getProductDetailsName());
//                        }
//                        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, productNames);
//                        selectProductInSupplier.setAdapter(arrayAdapter1);
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//
//
//                cancelCustomerDialogBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//
//                addCustomerDialogBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        TextView customerProductUnit, customerProductRate, customerProductMrp;
//                        LayoutInflater inflater = LayoutInflater.from(getContext());
//                        View anotherView = inflater.inflate(R.layout.today_deliver_product, null);
////                        customerProductName = anotherView.findViewById(R.id.customerProductsName);
//                        customerProductUnit = anotherView.findViewById(R.id.customerProductUnit);
////                        customerProductRate = anotherView.findViewById(R.id.customerProductRate);
////                        customerProductMrp = anotherView.findViewById(R.id.customerProductAmount);
//
////                        String productUnit = customerProductUnit.getText().toString();
////                        int productUnitt = 0;
////                        for(ProductDetails productDetails : productDetailsList) {
////                            int productDetailsId = productDetails.getProductDetailsId();
//                        String productDetailsName = selectProductInSupplier.getSelectedItem().toString();
////                        long quantity = Long.parseLong(editProductsQuantity.getText().toString());
//
//                        ArrayList<ProductDetails> productDetailsList = new ArrayList<>();
//                        DeliveryProductsAdapter deliveryProductsAdapter = new DeliveryProductsAdapter(getContext(), productDetailsList);
//                        addProductRecyclerView.setAdapter(deliveryProductsAdapter);
////                        productDetailsList.add(new ProductDetails(productDetailsName, quantity));
//                        deliveryProductsAdapter.notifyDataSetChanged();
//                        dialog.dismiss();
////                            String productName = customerProductName.getText().toString();
////                            String productUnit = customerProductUnit.getText().toString();
////                            String productRate = customerProductRate.getText().toString();
////                            String productMrp = customerProductMrp.getText().toString();
////                            ProductDetails productDetails1 = new ProductDetails();
////                            productDetails1.setProductDetailsId(productDetailsId);
////                            productDetails1.setProductDetailsName(productName);
////                            productDetails1.setProductDetailsUnit(productUnit);
////                            productDetails1.setProductSupplierRate(productRate);
////                            productDetails1.setProductDetailsMrp(productMrp);
////                        }
//                    }
//                });
//                dialog.show();
//
//
//            }
//        });

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("Today's DeliveryPerson");
        }
        return view;
    }
}