package com.technosoul.milkwala.customerorder;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.helper.MyDbHelper;
import com.technosoul.milkwala.customer.Customer;
import com.technosoul.milkwala.delivery.Deliver;
import com.technosoul.milkwala.products.ProductDetails;
import com.technosoul.milkwala.products.ProductViewDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderFragment extends Fragment {
    Spinner receivedCustomerSpinner, receivedDelivery;
    TextView savedDate;
    ImageView selectedDate;
    CustomerOrderProductAdapter customerOrderProductAdapter;
    RecyclerView receivedCustomerView;
    ArrayList<ProductDetails> productDetailsList;

    ArrayList<String> receivedCustomer = new ArrayList<>();
    ArrayList<String> getDelivery = new ArrayList<>();

    public CustomerOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_order, container, false);



        receivedCustomerSpinner = view.findViewById(R.id.receivedCustomerSpinner);
        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        List<Customer> customersList =  myDbHelper.customerDao().getAllCustomers();
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
                } else if(i != 0) {
                    Toast.makeText(getContext(), "Selected Item is : " + selectedItem, Toast.LENGTH_SHORT).show();
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
        for(Deliver deliver : deliversList){
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
                } else if(i != 0) {
                    Toast.makeText(getContext(), "Selected Item is : " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do Nothing
            }
        });


        //show the item in the recyclerview
        receivedCustomerView = view.findViewById(R.id.receivedCustomerView);
        receivedCustomerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getAllProducts();
        for (int i = 0; i < productDetailsList.size(); i++) {
            customerOrderProductAdapter = new CustomerOrderProductAdapter(getContext(),productDetailsList);
            customerOrderProductAdapter.notifyDataSetChanged();
            receivedCustomerView.setAdapter(customerOrderProductAdapter);
        }




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
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                savedDate.setText(String.valueOf(year) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(day));
            }
        }, 2022, 1, 15);
        dialog.show();
    }

}