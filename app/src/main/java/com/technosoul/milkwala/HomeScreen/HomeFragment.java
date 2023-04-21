package com.technosoul.milkwala.HomeScreen;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.Supplier.Supplier;
import com.technosoul.milkwala.Supplier.SupplierFragment;
import com.technosoul.milkwala.customer.Customer;
import com.technosoul.milkwala.customer.CustomerFragment;
import com.technosoul.milkwala.delivery.Deliver;
import com.technosoul.milkwala.delivery.FragmentDeliver;

import com.technosoul.milkwala.products.ProductFragment;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {
    TextView supplierTxt, productTxt, deliveryTxt, customerTxt;
    TextView supplierSubText, productSubText, deliverySubText, customerSubText;
    ImageView supplierImg, productImg, deliveryImg, customerImg;
    private ViewPager viewPager;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 3000; // time period in milliseconds between successive task executions.

    public HomeFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        supplierTxt = view.findViewById(R.id.suppliersTxt);
        productTxt = view.findViewById(R.id.productTxt);
        deliveryTxt = view.findViewById(R.id.deliveryTxt);
        customerTxt = view.findViewById(R.id.customerTxt);

        supplierImg = view.findViewById(R.id.supplierImg);
        productImg = view.findViewById(R.id.productImg);
        deliveryImg = view.findViewById(R.id.deliverImg);
        customerImg = view.findViewById(R.id.customerImg);

        supplierSubText = view.findViewById(R.id.supplierSubText);
        productSubText = view.findViewById(R.id.productSubText);
        deliverySubText = view.findViewById(R.id.deliverySubText);
        customerSubText = view.findViewById(R.id.customerSubText);


        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        ArrayList<Supplier> supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();

        int numSuppliers = supplierList.size();
        supplierSubText.setText(String.format("%d Suppliers ", numSuppliers));
        supplierImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SupplierFragment supplierFragment = new SupplierFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.container, supplierFragment);
                ft.addToBackStack(null);
                ft.commit();
//                Intent iSupplier = new Intent(getActivity(), SupplierActivity.class);
//                iSupplier.putExtra("fragment", "supplierFragment");
//                startActivity(iSupplier);
            }
        });


        int numProducts = supplierList.size();
        productSubText.setText(String.format("%d Products ", numProducts));
        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProductFragment productFragment = new ProductFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, productFragment);
                ft.commit();
            }
        });

        ArrayList<Deliver> deliverList = (ArrayList<Deliver>) myDbHelper.deliveryDetailDao().getAllDeliveryBoys();
        int numDelivers = deliverList.size();
        deliverySubText.setText(String.format("%d Delivery Boys", numDelivers));
        deliveryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentDeliver fragmentDeliver = new FragmentDeliver();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, fragmentDeliver);
                ft.commit();
            }
        });

        ArrayList<Customer> customerList = (ArrayList<Customer>) myDbHelper.customerDao().getAllCustomers();
        int numCustomers = customerList.size();
        customerSubText.setText(String.format("%d Customers", numCustomers));
        customerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerFragment customerFragment = new CustomerFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, customerFragment);
                ft.commit();
            }
        });

        //viewpager
        viewPager = view.findViewById(R.id.viewPagerImage);
        ImageAdapter adapter = new ImageAdapter(getActivity());
        viewPager.setAdapter(adapter);

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == adapter.getCount() - 1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);


        //set the title in fragment .
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Master Info :");

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    //    @Override
//    public void onResume() {
//        super.onResume();
//        getActivity().setTitle("Master Info :");
//    }
}