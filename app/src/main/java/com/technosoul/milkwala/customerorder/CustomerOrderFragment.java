package com.technosoul.milkwala.customerorder;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.delivery.Deliver;
import com.technosoul.milkwala.products.ProductDetails;
import com.technosoul.milkwala.supplier.Supplier;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderFragment extends Fragment {
    Spinner receivedCustomerSpinner, receivedDelivery;
    TextView savedDate;
    ImageView selectedDate;
    CustomerOrderProductAdapter customerOrderProductAdapter;
    RecyclerView receivedCustomerView;
    ArrayList<ProductDetails> productDetailsList;
    FloatingActionButton btnOpenDialog;

    ArrayList<String> receivedCustomer = new ArrayList<>();
    ArrayList<String> getDelivery = new ArrayList<>();

    private int supplierId;

    public CustomerOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_order, container, false);

        //TODO CHANGE THE TITLE OF THE ACTION BAR
        ActionBar actionBar = ((AppCompatActivity)getContext()).getSupportActionBar();
        actionBar.setTitle("Today's Customer Order");


        receivedCustomerSpinner = view.findViewById(R.id.receivedCustomerSpinner);
        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        List<Customer> customersList = myDbHelper.customerDao().getAllCustomers();
        receivedCustomer.add("No Selected");
//        using for loop to iterate the element of the customer and added in the spinner;
        for (Customer customer : customersList) {
            receivedCustomer.add(customer.getCustomerName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, receivedCustomer);
        receivedCustomerSpinner.setAdapter(adapter);
        receivedCustomerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem.equals("No Selected")) {
                    receivedCustomerSpinner.setVerticalScrollbarPosition(0);
//                    Toast.makeText(getContext(), "Please select an Item", Toast.LENGTH_SHORT).show();
                } else if (i != 0) {
                    Toast.makeText(getContext(), "Selected Customer is : " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do Nothing
            }
        });


        //Delivery Spinner View
        receivedDelivery = view.findViewById(R.id.receivedDelivery);
        ArrayList<Deliver> deliversList = (ArrayList<Deliver>) myDbHelper.deliveryDetailDao().getAllDeliveryBoys();
        getDelivery.add("No Selected");
        //using for loop to iterate the item and add the spinner;
        for (Deliver deliver : deliversList) {
            getDelivery.add(deliver.getDeliveryBoyName());
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, getDelivery);
        receivedDelivery.setAdapter(adapter1);
        receivedDelivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem.equals("No Selected")) {
                    receivedCustomerSpinner.setVerticalScrollbarPosition(0);
//                    Toast.makeText(getContext(), "Please select an Item", Toast.LENGTH_SHORT).show();
                } else if (i != 0) {
                    Toast.makeText(getContext(), "Selected Delivery Boy is : " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do Nothing
            }
        });


//        productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getAllProducts();
//        for (int i = 0; i < productDetailsList.size(); i++) {
//            customerOrderProductAdapter = new CustomerOrderProductAdapter(getContext(),productDetailsList);
//            customerOrderProductAdapter.notifyDataSetChanged();
//            receivedCustomerView.setAdapter(customerOrderProductAdapter);
//        }


        //open the dialog button.
        receivedCustomerView = view.findViewById(R.id.receivedCustomerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        receivedCustomerView.setLayoutManager(gridLayoutManager);

        btnOpenDialog = view.findViewById(R.id.btnOpenDialog);
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                lp.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.4);
                window.setAttributes(lp);

                cancelBtn = dialog.findViewById(R.id.cancelCustomerDialogButton);
                selectBtn = dialog.findViewById(R.id.selectCustomerDialogBtn);

                selectSupplierInCustomer = dialog.findViewById(R.id.selectSupplierInCustomer);
                selectProductInSupplier = dialog.findViewById(R.id.selectProductBySupplier);

                ArrayList<String> supplierNames = new ArrayList<>();
                List<Supplier> suppliersList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();

                for (Supplier supplier : suppliersList) {
                    supplierNames.add(supplier.getSupplierName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, supplierNames);
                selectSupplierInCustomer.setAdapter(arrayAdapter);

                selectSupplierInCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String selectedItem = adapterView.getItemAtPosition(i).toString();
//                        if (selectedItem.equals("No Selected")) {
//                                selectProductInSupplier.setVisibility(View.GONE);
//                        } else {
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
                        //do nothing.
                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                selectBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String selectItem = selectProductInSupplier.getSelectedItem().toString();
                            customerOrderProductAdapter.addItem(selectItem);
                            receivedCustomerView.setAdapter(customerOrderProductAdapter);
                            dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });


        //select the date
        selectedDate = view.findViewById(R.id.selectedDate);
        savedDate = view.findViewById(R.id.savedDate);
        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        return view;
    }

    private void openDialog() {
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                savedDate.setText(String.valueOf(day) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(year));
            }
        }, 24, 4, 2023);
        dialog.show();
    }

}