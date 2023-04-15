package com.technosoul.milkwala.ReceivedProduct;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.Supplier.Supplier;
import com.technosoul.milkwala.products.ProductDetails;
import com.technosoul.milkwala.products.ProductViewDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReceivedProductFragment extends Fragment {
    TextView savedDate;
    ImageView selectDate;
    Spinner receivedSpinner;
    ArrayList<String> arryNames = new ArrayList<>();

    ReceivedProductAdapter receivedProductAdapter;
    RecyclerView receivedRecyclerView;
    ArrayList<ProductDetails> productDetails = new ArrayList<>();


    public ReceivedProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_received_product, container, false);
        savedDate = view.findViewById(R.id.saveDate);
        selectDate = view.findViewById(R.id.selectDate);


        //show date picker
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


//        show spinner
        receivedSpinner = view.findViewById(R.id.receivedSpinner);
        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        List<Supplier> suppliersList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();
        arryNames.add("No Selected");
        //iterate the value using for loop and show in the spinner
        for (Supplier supplier : suppliersList) {
            arryNames.add(supplier.getSupplierName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, arryNames);
        receivedSpinner.setAdapter(adapter);


        receivedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem.equals("No Selected")) {
                    receivedSpinner.setVerticalScrollbarPosition(0);
//                    Toast.makeText(getContext(), "Please select an Item", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Selected Item is : " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do Nothing
            }
        });


//        show the list in the recyclerView
        receivedRecyclerView = view.findViewById(R.id.receivedRecyclerView);
        receivedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        productDetails = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getAllProducts();
        for (int i = 0; i < productDetails.size(); i++) {
            receivedProductAdapter = new ReceivedProductAdapter(getContext(), productDetails);
            receivedProductAdapter.notifyDataSetChanged();
            receivedRecyclerView.setAdapter(receivedProductAdapter);
        }

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