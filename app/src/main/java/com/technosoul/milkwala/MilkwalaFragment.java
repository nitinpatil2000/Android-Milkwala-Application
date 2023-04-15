package com.technosoul.milkwala;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.Supplier.Supplier;
import com.technosoul.milkwala.customer.Customer;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class MilkwalaFragment extends Fragment {
    TextView customerId, productId, supplierId;

    public MilkwalaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_milkwala, container, false);

        supplierId = view.findViewById(R.id.supplierId);
        customerId = view.findViewById(R.id.customerId);
        productId = view.findViewById(R.id.productId);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        ArrayList<Supplier> supplierList = (ArrayList<Supplier>)myDbHelper.supplierDao().getAllSuppliers();
        int noSuppliers = supplierList.size();
        supplierId.setText(String.valueOf(noSuppliers));
        productId.setText(String.valueOf(noSuppliers));

        ArrayList<Customer> customersList = (ArrayList<Customer>) myDbHelper.customerDao().getAllCustomers();
        int noCustomers = customersList.size();
        customerId.setText(String.valueOf( noCustomers));

        //set the title
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Milkwala");


        PieChartView pieChartView = view.findViewById(R.id.pieChart);
        List<SliceValue> pieData = new ArrayList<>();
//
        pieData.add(new SliceValue(15, Color.BLUE));
        pieData.add(new SliceValue(25, Color.GRAY));
        pieData.add(new SliceValue(10, Color.RED));
        pieData.add(new SliceValue(60, Color.MAGENTA));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartView.setPieChartData(pieChartData);

        return view;
    }
}