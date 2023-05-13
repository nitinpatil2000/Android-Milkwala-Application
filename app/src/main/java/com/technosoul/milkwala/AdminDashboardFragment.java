package com.technosoul.milkwala;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.Supplier;
import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.ui.MainActivity;

import java.util.ArrayList;

public class AdminDashboardFragment extends Fragment {
    TextView totalCustomers;
    TextView totalProducts;
    TextView totalSuppliers;

    public AdminDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_admin_dashboard, container, false);

        totalSuppliers = view.findViewById(R.id.totalSuppliers);
        totalCustomers = view.findViewById(R.id.totalCustomers);
        totalProducts = view.findViewById(R.id.totalProducts);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());

        // Get total Suppliers
        ArrayList<Supplier> supplierList = (ArrayList<Supplier>)myDbHelper.supplierDao().getAllSuppliers();
        int noSuppliers = supplierList.size();
        totalSuppliers.setText(getString(R.string.total_suppliers, noSuppliers));

        // Get total Customers
        ArrayList<Customer> customersList = (ArrayList<Customer>) myDbHelper.customerDao().getAllCustomers();
        int noCustomers = customersList.size();
        totalCustomers.setText(getString(R.string.total_customers, noCustomers));

        // Get total Products
        // TODO: need to add the products & fetch it.
        totalProducts.setText(getString(R.string.total_products, noSuppliers));

//        if (getActivity() != null) {
//            ActionBar actionBar =((MainActivity) getActivity()).getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setTitle(R.string.title_dashboard);
//            }
//        }

//        PieChartView pieChartView = view.findViewById(R.id.pieChart);
//        List<SliceValue> pieData = new ArrayList<>();

//        pieData.add(new SliceValue(15, Color.BLUE));
//        pieData.add(new SliceValue(25, Color.GRAY));
//        pieData.add(new SliceValue(10, Color.RED));
//        pieData.add(new SliceValue(60, Color.MAGENTA));
//
//        PieChartData pieChartData = new PieChartData(pieData);
//        pieChartView.setPieChartData(pieChartData);

//        TODO SET THE TITLE
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("DashBoard");
        }


        return view;
    }
}