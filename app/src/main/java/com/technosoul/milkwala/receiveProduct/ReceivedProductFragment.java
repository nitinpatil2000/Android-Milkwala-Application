package com.technosoul.milkwala.receiveProduct;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
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

import com.technosoul.milkwala.helper.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.supplier.Supplier;
import com.technosoul.milkwala.products.ProductDetails;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceivedProductFragment extends Fragment {
    TextView savedDate;
    Spinner receivedSpinner;
    ArrayList<String> arryNames = new ArrayList<>();
    View line;


    ReceivedProductAdapter receivedProductAdapter;
    RecyclerView receivedRecyclerView;
    ArrayList<ProductDetails> productDetails = new ArrayList<>();


    ImageView receiveProductDate;
    Button saveReceiveProduct;

    private int supplierId;

    public ReceivedProductFragment() {
//        this.supplierId = supplierId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_received_product, container, false);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Today's Received Products ");

        savedDate = view.findViewById(R.id.saveDate);
        receiveProductDate = view.findViewById(R.id.selectDate);
        //show date picker
        receiveProductDate.setOnClickListener(new View.OnClickListener() {
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
                    for (Supplier supplier : suppliersList) {
                        if (supplier != null && TextUtils.equals(supplier.getSupplierName(), selectedItem)) {
                            supplierId = supplier.getSupplierId();
                            break;
                        }
                    }
                    receivedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    productDetails = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);

//                    show the list if select the item
                    receivedProductAdapter = new ReceivedProductAdapter(getContext(), productDetails);
                    receivedProductAdapter.notifyDataSetChanged();
                    receivedRecyclerView.setAdapter(receivedProductAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do Nothing
            }
        });


        saveReceiveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int receiveQuantity;
                long receiveAmount;
                int clickedPosition = receivedProductAdapter.getClickedPosition();
                if(clickedPosition == RecyclerView.NO_POSITION){
                    return;
                }

                View clickedView = receivedRecyclerView.getLayoutManager().findViewByPosition(clickedPosition);

//                Inflate the layout
//                View layout = LayoutInflater.from(getContext()).inflate(R.layout.received_product_design, null);
//                View layout = receivedProductAdapter.getClickedView();
//                if(layout == null){
//                    return;
//                }

                TextView receiveProductTotalAmount = clickedView.findViewById(R.id.totalAmout);
                EditText receiveProductQuantity = clickedView.findViewById(R.id.editQuantity);
                try {
                    receiveQuantity = Integer.parseInt(receiveProductQuantity.getText().toString());
                } catch (NumberFormatException e) {
                    return;
                }

                try {
                    receiveAmount = Long.parseLong(receiveProductTotalAmount.getText().toString());
                } catch (NumberFormatException e) {
                    return;
                }

                if (receiveQuantity > 0 && receiveAmount > 0) {
                    ReceiveProduct receiveProduct = new ReceiveProduct(receiveQuantity, receiveAmount);
                    myDbHelper.receiveProductDao().addReceiveProduct(
                            receiveProduct
                    );
                    Toast.makeText(getContext(), "Received Product Successfully", Toast.LENGTH_SHORT).show();
                }
                receivedProductAdapter.clearClickPosition();
            }
        });


//        save the value in the database
//        rcProductAmount = view.findViewById(R.id.totalAmout);
//        saveReceiveProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int clickedPosition = receivedProductAdapter.getClickedPosition();
//                if (clickedPosition == RecyclerView.NO_POSITION) {
//                    return;
//                }
//                ProductDetails product = productDetails.get(clickedPosition);
//                String receiveProductName = rcProductName.getText().toString();
//                String receiveProductMrp = rcProductMrp.getText().toString();
//                String receiveProductUnit = rcProductUnit.getText().toString();
//                Long receiveProductAmount = Long.parseLong(rcProductAmount.getText().toString());
//
//                if (!receiveProductMrp.isEmpty() && receiveProductAmount != 0) {
//                    if (!receiveProductName.equals(product.getProductDetailsName())
//                            || !receiveProductUnit.equals(product.getProductDetailsUnit())
//                            || !receiveProductMrp.equals(product.getProductDetailsMrp())) {
//                        ReceivedProduct receivedProduct = new ReceivedProduct(receiveProductName, receiveProductMrp, receiveProductUnit, receiveProductAmount);
//                        myDbHelper.receiveProductDao().addReceiveProduct(receivedProduct);
//                        Toast.makeText(getContext(), "Received Product Successfully", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });


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