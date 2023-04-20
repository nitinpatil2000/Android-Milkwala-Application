package com.technosoul.milkwala.ReceivedProduct;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.Supplier.RecyclerViewAdapter;
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
    Button saveReceiveProduct;
    View line;


    ReceivedProductAdapter receivedProductAdapter;
    RecyclerView receivedRecyclerView;
    ArrayList<ProductDetails> productDetails = new ArrayList<>();

    TextView rcProductName, rcProductUnit, rcProductMrp, rcProductAmount;

    public ReceivedProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //set the title in the fragment.
        View view = inflater.inflate(R.layout.fragment_received_product, container, false);
//        View anotherView = inflater.inflate(R.layout.received_product_design,container, false);
//        ViewGroup rootViewGroup = view.findViewById(R.id.root_view_group);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Today's Received Products ");

//       actionBar.setDisplayHomeAsUpEnabled(true);
//        DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawerLayout);
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//


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

        saveReceiveProduct = view.findViewById(R.id.saveReceivedProduct);
        line = view.findViewById(R.id.line);

        receivedRecyclerView = view.findViewById(R.id.receivedRecyclerView);
        receivedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i1, long l) {
                String selectedItem = adapterView.getItemAtPosition(i1).toString();
                if (selectedItem.equals("No Selected")) {
                    receivedSpinner.setVerticalScrollbarPosition(0);
                    receivedRecyclerView.setVisibility(View.GONE);
                    saveReceiveProduct.setVisibility(View.GONE);
                    line.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Selected Item is : " + selectedItem, Toast.LENGTH_SHORT).show();
                    receivedRecyclerView.setVisibility(View.VISIBLE);
                    saveReceiveProduct.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);
                    receivedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    productDetails = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getAllProducts();

                    for (int i = 0; i < productDetails.size(); i++) {
                        receivedProductAdapter = new ReceivedProductAdapter(getContext(), productDetails);
                        receivedProductAdapter.notifyDataSetChanged();
                        receivedRecyclerView.setAdapter(receivedProductAdapter);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do Nothing
            }
        });


        //save the value in the database
        rcProductName = view.findViewById(R.id.receivedProductName);
        rcProductUnit = view.findViewById(R.id.receivedProductUnit);
        rcProductMrp = view.findViewById(R.id.receivedProductMrp);
        rcProductAmount = view.findViewById(R.id.totalAmout);


        saveReceiveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String receivePname, receiveUnit, receiveMrp;
                long receiveAmount;

                CharSequence charSequence = rcProductName.getText();
                if (charSequence == null || charSequence.length() == 0) {
                    return;
                } else {
                    receivePname = charSequence.toString();
                }

                charSequence = rcProductUnit.getText();
                if (charSequence == null || charSequence.length() == 0) {
                    return;
                } else {
                    receiveUnit = charSequence.toString();
                }

                charSequence = rcProductMrp.getText();
                if (charSequence == null || charSequence.length() == 0) {
                    return;
                } else {
                    receiveMrp = charSequence.toString();
                }

                charSequence = rcProductAmount.getText();
                if (charSequence == null || charSequence.length() == 0) {
                    return;
                } else {
                    receiveAmount = Long.parseLong(charSequence.toString());
                }

                if (!receivePname.isEmpty() && !receiveUnit.isEmpty() && !receiveMrp.isEmpty() && receiveAmount != 0) {
                    myDbHelper.receiveProductDao().addReceiveProduct(
                            new ReceivedProduct(receivePname, receiveUnit, receiveMrp, receiveAmount)
                    );
                    Toast.makeText(getContext(), "Received Product added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    //    select the datePicker
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