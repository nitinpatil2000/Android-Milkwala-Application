package com.technosoul.milkwala.customerorder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.customer.CustomerFromServer;
import com.technosoul.milkwala.ui.masterinfo.customer.CustomerService;
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.DeliveryFromServer;
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.DeliveryPersonService;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;
import com.technosoul.milkwala.ui.masterinfo.products.ProductService;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFromServer;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomerOrderFragment extends Fragment {
    Spinner receivedCustomerSpinner, receivedDelivery;
    TextView savedDate;
    ImageView selectedDate;
    CustomerOrderProductAdapter customerOrderProductAdapter;
    RecyclerView recyclerReceivedCustomerView;
    FloatingActionButton btnOpenDialog;
    Button saveCustomerOrder;
    TextView txtCustomerOrderTotalAmount;

    private List<CustomerFromServer> customerFromServers;
    private List<DeliveryFromServer> deliveryFromServers;
    private List<SupplierFromServer> supplierFromServers;
    private List<ProductFromServer> productFromServers = new ArrayList<>();
    private int supplierId;
    private int customerId;
    private int deliveryPersonId;
    private int productId;

    private float totalAmountOfReceivedCustomer = 0.0f;

    public CustomerOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_order, container, false);

        //TODO CHANGE THE TITLE OF THE ACTION BAR
        ActionBar actionBar = ((AppCompatActivity) getContext()).getSupportActionBar();
        actionBar.setTitle("Today's Customer Order");


        receivedCustomerSpinner = view.findViewById(R.id.receivedCustomerSpinner);
        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        CustomerService customerService = retrofit.create(CustomerService.class);
        Call<List<CustomerFromServer>> customerListFromServer = customerService.getAllCustomers();

        customerListFromServer.enqueue(new Callback<List<CustomerFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<CustomerFromServer>> call, @NonNull Response<List<CustomerFromServer>> response) {
                if (response.isSuccessful()) {
                    customerFromServers = response.body();
                    if (customerFromServers != null) {
                        ArrayList<String> receivedCustomer = new ArrayList<>();
                        receivedCustomer.add("No Selected");

                        for (CustomerFromServer customerListForSpinner : customerFromServers) {
                            receivedCustomer.add(customerListForSpinner.getCustomerName());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, receivedCustomer);
                        receivedCustomerSpinner.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CustomerFromServer>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        receivedDelivery = view.findViewById(R.id.receivedDelivery);
        DeliveryPersonService deliveryPersonService = retrofit.create(DeliveryPersonService.class);
        Call<List<DeliveryFromServer>> deliveryListFromServer = deliveryPersonService.getAllDeliveryPersons();
        deliveryListFromServer.enqueue(new Callback<List<DeliveryFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<DeliveryFromServer>> call, @NonNull Response<List<DeliveryFromServer>> response) {
                if (response.isSuccessful()) {
                    deliveryFromServers = response.body();
                    if (deliveryFromServers != null) {
                        ArrayList<String> receivedDeliveryPerson = new ArrayList<>();
                        receivedDeliveryPerson.add("No Selected");

                        for (DeliveryFromServer deliveryListFromServer : deliveryFromServers) {
                            receivedDeliveryPerson.add(deliveryListFromServer.getDeliveryPersonName());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, receivedDeliveryPerson);
                        receivedDelivery.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DeliveryFromServer>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });


        //open the dialog button.
        recyclerReceivedCustomerView = view.findViewById(R.id.recyclerReceivedCustomerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerReceivedCustomerView.setLayoutManager(gridLayoutManager);

        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        recyclerReceivedCustomerView.setLayoutAnimation(animationController);

        customerOrderProductAdapter = new CustomerOrderProductAdapter(getContext(), (ArrayList<ProductFromServer>) productFromServers);
        recyclerReceivedCustomerView.setAdapter(customerOrderProductAdapter);

        btnOpenDialog = view.findViewById(R.id.btnOpenDialog);
        btnOpenDialog.setOnClickListener(view1 -> {
            Button cancelBtn, selectBtn;
            Spinner selectSupplierInCustomer, selectProductInSupplier;

            Dialog dialog = new Dialog(getContext());
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.customer_dialog_design);

            //set the width and height of the dialog.
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = (int) (getResources().getDisplayMetrics().widthPixels * 1.0);
            lp.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.3);
            window.setAttributes(lp);

            cancelBtn = dialog.findViewById(R.id.cancelCustomerDialogButton);
            selectBtn = dialog.findViewById(R.id.selectCustomerDialogBtn);

            selectSupplierInCustomer = dialog.findViewById(R.id.selectSupplierInCustomer);
            selectProductInSupplier = dialog.findViewById(R.id.selectProductBySupplier);
//            selectProductQuantity = dialog.findViewById(R.id.selectProductQuantity);

            SupplierService supplierService = retrofit.create(SupplierService.class);
            Call<List<SupplierFromServer>> supplierListFromServer = supplierService.getAllSuppliers();

            supplierListFromServer.enqueue(new Callback<List<SupplierFromServer>>() {
                @Override
                public void onResponse(@NonNull Call<List<SupplierFromServer>> call, @NonNull Response<List<SupplierFromServer>> response) {
                    if (response.isSuccessful()) {
                        supplierFromServers = response.body();
                        if (supplierFromServers != null) {
                            ArrayList<String> receivedSupplierPerson = new ArrayList<>();
                            receivedSupplierPerson.add("No Selected");

                            for (SupplierFromServer supplierListFromServer : supplierFromServers) {
                                receivedSupplierPerson.add(supplierListFromServer.getSupplierName());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, receivedSupplierPerson);
                            selectSupplierInCustomer.setAdapter(adapter);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<SupplierFromServer>> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });

            selectSupplierInCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view1, int i, long l) {
                    String selectedItem = adapterView.getItemAtPosition(i).toString();
                    if (selectedItem.equals("No Selected")) {
                        selectProductInSupplier.setVisibility(View.GONE);
                    } else {
                        selectProductInSupplier.setVisibility(View.VISIBLE);
                        for (SupplierFromServer supplier : supplierFromServers) {
                            if (supplier != null && TextUtils.equals(supplier.getSupplierName(), selectedItem)) {
                                supplierId = supplier.getSupplierId();

                                ProductService productService = retrofit.create(ProductService.class);
                                Call<List<ProductFromServer>> getProductListBySupplierId = productService.getProductsBySupplierId(supplierId);
                                getProductListBySupplierId.enqueue(new Callback<List<ProductFromServer>>() {
                                    @Override
                                    public void onResponse(@NonNull Call<List<ProductFromServer>> call, @NonNull Response<List<ProductFromServer>> response) {
                                        if (response.isSuccessful()) {
                                            productFromServers = response.body();
                                            if (productFromServers == null || productFromServers.isEmpty()) {
                                                Toast.makeText(getContext(), R.string.empty_product_list, Toast.LENGTH_SHORT).show();
                                            } else {
                                                ArrayList<String> receivedProductList = new ArrayList<>();
                                                for (ProductFromServer productListFromServer : productFromServers) {
                                                    receivedProductList.add(productListFromServer.getProductName());
                                                }

                                                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, receivedProductList);
                                                selectProductInSupplier.setAdapter(adapter);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<List<ProductFromServer>> call, @NonNull Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                            }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            cancelBtn.setOnClickListener(view11 -> dialog.dismiss());

            selectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
//                    String productQuantity = selectProductQuantity.getText().toString();
//                    if (TextUtils.isEmpty(productQuantity)) {
//                        Toast.makeText(getContext(), "Please Enter Quantity for Item: ", Toast.LENGTH_SHORT).show();
//                        selectProductQuantity.requestFocus();
//                        return;
//                    }
                    String selectItem = selectProductInSupplier.getSelectedItem().toString();
                    ProductFromServer selectedProduct = getSelectedProduct(selectItem);
                    if (selectedProduct != null) {
                        customerOrderProductAdapter.addItem(selectedProduct);
                        dialog.dismiss();
                    }
                }

                private ProductFromServer getSelectedProduct(String selectItem) {
                    for (ProductFromServer product : productFromServers) {
                        if (product.getProductName().equals(selectItem)) {
                            return product;
                        }
                    }
                    return null;
                }
            });
            dialog.show();

        });

        //select the date
//        selectedDate = view.findViewById(R.id.selectedDate);
//        savedDate = view.findViewById(R.id.savedDate);
//        selectedDate.setOnClickListener(view12 -> showDatePicker());


        txtCustomerOrderTotalAmount = view.findViewById(R.id.txtCustomerOrderTotalAmount);
        saveCustomerOrder = view.findViewById(R.id.savedCustomerOrder);
        saveCustomerOrder.setOnClickListener(view1 -> {

            HashMap<EditText, ProductFromServer> editTextMap = new HashMap<>();
            ArrayList<ProductFromServer> saveProductFromServer = customerOrderProductAdapter.getProductFromServers();
            for(ProductFromServer getProductListFromServer : saveProductFromServer){
                int position = saveProductFromServer.indexOf(getProductListFromServer);
                RecyclerView.ViewHolder viewHolder = recyclerReceivedCustomerView.findViewHolderForAdapterPosition(position);
                if(viewHolder != null){
                    View itemView = viewHolder.itemView;
                    EditText quantity = itemView.findViewById(R.id.cProductEditQuantity);
                    String editQuantity = quantity.getText().toString();
                    if(TextUtils.isEmpty(editQuantity)){
                        Toast.makeText(getContext(), "Please Enter the quantity for item :" + getProductListFromServer.getProductName(), Toast.LENGTH_SHORT).show();
                        quantity.requestFocus();
                        return;
                    }

                    String selectCustomerForReceivedCustomer = receivedCustomerSpinner.getSelectedItem().toString();
                    if (selectCustomerForReceivedCustomer.equals("No Selected")) {
                        Toast.makeText(getContext(), R.string.err_empty_customer_spinner, Toast.LENGTH_SHORT).show();
                        receivedCustomerSpinner.requestFocus();
                        return;
                    }

                    String selectDeliveryPersonForReceivedCustomer = receivedDelivery.getSelectedItem().toString();
                    if (selectDeliveryPersonForReceivedCustomer.equals("No Selected")) {
                        Toast.makeText(getContext(), R.string.err_empty_delivery_spinner, Toast.LENGTH_SHORT).show();
                        receivedDelivery.requestFocus();
                        return;
                    }



                    int receivedCustomerQuantity = Integer.parseInt(editQuantity);
                    editTextMap.put(quantity, getProductListFromServer);
                    productId = getProductListFromServer.getProductId();

                    //set the customerId
                    for(CustomerFromServer customer: customerFromServers){
                        if(customer.getCustomerName().equals(selectCustomerForReceivedCustomer)){
                            customerId = customer.getCustomerId();
                        }
                    }

                    //set the deliveryPersonId
                    for(DeliveryFromServer delivery : deliveryFromServers){
                        if(delivery.getDeliveryPersonName().equals(selectDeliveryPersonForReceivedCustomer)){
                            deliveryPersonId = delivery.getDeliveryPersonId();
                        }
                    }

                    CustomerOrder customerOrder = new CustomerOrder();
                    customerOrder.setProductId(productId);
                    customerOrder.setCustomerId(customerId);
                    customerOrder.setDeliveryPersonId(deliveryPersonId);
                    customerOrder.setOrderedQuantity(receivedCustomerQuantity);
//                    customerOrder.setCustomerOrderDate(selectedLocalDate);

                    CustomerOrderService customerOrderService = retrofit.create(CustomerOrderService.class);
                    Call<CustomerOrder> receivedCustomerOrder = customerOrderService.createCustomerOrder(customerOrder);
                    receivedCustomerOrder.enqueue(new Callback<CustomerOrder>() {
                        @Override
                        public void onResponse(@NonNull Call<CustomerOrder> call, @NonNull Response<CustomerOrder> response) {
                            CustomerOrder customerOrderList = response.body();
                            if(customerOrderList != null){
                                Toast.makeText(getContext(), R.string.success_customer_order, Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(), R.string.error_text, Toast.LENGTH_SHORT).show();
                                Request request = call.request();
                                try {
                                    Buffer buffer = new Buffer();
                                    Objects.requireNonNull(request.body()).writeTo(buffer);
                                    String requestBody = buffer.readUtf8();
                                    Log.d("API Request Body", requestBody);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<CustomerOrder> call, @NonNull Throwable t) {
                            t.printStackTrace();
                        }
                    });

                    totalAmountOfReceivedCustomer += receivedCustomerQuantity * getProductListFromServer.getProductMrpRetailerRate();
                }
            }
        });

        txtCustomerOrderTotalAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txtCustomerOrderTotalAmount.setText(String.valueOf(totalAmountOfReceivedCustomer));
            }
        });



        if (getActivity() != null) {
            ((CustomerActivity) getActivity()).setActionBarTitle("Today's Customer Order");
        }

        return view;
    }


}




