package com.technosoul.milkwala.ui.masterinfo;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.DeliveryPersonListForRouteAdapter;
import com.technosoul.milkwala.ui.masterinfo.customer.AddNewCustomerFragment;
import com.technosoul.milkwala.ui.masterinfo.customer.CustomerDetailsFragment;
import com.technosoul.milkwala.ui.masterinfo.customer.CustomerFragment;
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.AddNewDeliverPersonFragment;
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.DeliveryPersonDetailsFragment;
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.DeliveryPersonListFragment;
import com.technosoul.milkwala.ui.masterinfo.products.AddNewProductFragment;
import com.technosoul.milkwala.ui.masterinfo.products.ProductDetailsViewFragment;
import com.technosoul.milkwala.ui.masterinfo.products.ProductListPerSupplierFragment;
import com.technosoul.milkwala.ui.AbstractBaseActivity;
import com.technosoul.milkwala.ui.masterinfo.products.SupplierListForProductsFragment;
import com.technosoul.milkwala.ui.masterinfo.route.AddNewRouteFragment;
import com.technosoul.milkwala.ui.masterinfo.route.DeliveryPersonForRouteFragment;
import com.technosoul.milkwala.ui.masterinfo.route.RouteDetailsFragment;
import com.technosoul.milkwala.ui.masterinfo.route.RouteListPerDeliveryPersonFragment;
import com.technosoul.milkwala.ui.masterinfo.suppliers.AddNewSupplierFragment;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierDetailsFragment;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFragment;
import com.technosoul.milkwala.utils.Constants;

public class MasterInfoActivity extends AbstractBaseActivity implements MasterInfoListener, OnItemSelected {
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_info);
        MasterInfoFragment masterInfoFragment = new MasterInfoFragment();
        masterInfoFragment.setListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int desiredColor = Color.parseColor("#FFFFFF");
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_ios_24);
        upArrow.setColorFilter(desiredColor, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.master_info_container, masterInfoFragment);
        fragmentTransaction.commit();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.master_info_container, fragment)
                .setReorderingAllowed(true)
                .addToBackStack(fragment.getTag())
                .commit();
    }

    @Override
    public void onSupplierClick() {
        SupplierFragment supplierFragment = new SupplierFragment();
        supplierFragment.setListener(this);
        supplierFragment.setOnItemSelectedListener(this);
        loadFragment(supplierFragment);
    }

    @Override
    public void onProductClick() {
        SupplierListForProductsFragment productFragment = new SupplierListForProductsFragment();
        productFragment.setListener(this);
        productFragment.setOnItemSelectedListener(this);
        loadFragment(productFragment);
    }

    @Override
    public void onDeliveryPersonClick() {
        DeliveryPersonListFragment deliveryPersonListFragment = new DeliveryPersonListFragment();
        deliveryPersonListFragment.setMasterInfoListener(this);
        deliveryPersonListFragment.setOnItemSelected(this);
        loadFragment(deliveryPersonListFragment);
    }

    @Override
    public void onCustomerClick() {
        CustomerFragment customerFragment = new CustomerFragment();
        customerFragment.setMasterInfoListener(this);
        customerFragment.setOnItemSelected(this);
        loadFragment(customerFragment);
    }

    @Override
    public void onRouterClick() {
        DeliveryPersonForRouteFragment deliveryPersonForRouteFragment = new DeliveryPersonForRouteFragment();
        deliveryPersonForRouteFragment.setMasterInfoListener(this);
        deliveryPersonForRouteFragment.setOnItemSelectedListener(this);
        loadFragment(deliveryPersonForRouteFragment);
    }


    @Override
    public void addNewSupplier() {
        AddNewSupplierFragment addNewSupplierFragment = new AddNewSupplierFragment();
        addNewSupplierFragment.setListener(this);
        loadFragment(addNewSupplierFragment);
    }

    @Override
    public void addNewProduct(int id) {
        AddNewProductFragment addNewProductFragment = new AddNewProductFragment(id);
        addNewProductFragment.setListener(this);
        loadFragment(addNewProductFragment);
    }

    @Override
    public void addNewDeliveryPerson() {
        AddNewDeliverPersonFragment addNewDeliverPersonFragment = new AddNewDeliverPersonFragment();
        addNewDeliverPersonFragment.setListener(this);
        loadFragment(addNewDeliverPersonFragment);
    }

    @Override
    public void addNewCustomer() {
        AddNewCustomerFragment addNewCustomerFragment = new AddNewCustomerFragment();
        addNewCustomerFragment.setMasterInfoListener(this);
        loadFragment(addNewCustomerFragment);
    }

    @Override
    public void addNewRoute(int id) {
        AddNewRouteFragment addNewRouteFragment = new AddNewRouteFragment(id);
        addNewRouteFragment.setMasterInfoListener(this);
        loadFragment(addNewRouteFragment);
    }

    @Override
    public void onBackToPreviousScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
    }


    @Override
    public void onItemClicked(int type, int id, String actionBarTitle, Bundle bundle) {
        switch (type) {
            case Constants.SELECTED_TYPE_SUPPLIER:
                SupplierDetailsFragment supplierDetailsFragment = new SupplierDetailsFragment(id, actionBarTitle);
//                supplierDetailsFragment.setArguments(bundle);
                supplierDetailsFragment.setListener(this);
                loadFragment(supplierDetailsFragment);
                break;

            case Constants.SELECTED_TYPE_PRODUCT:
                ProductDetailsViewFragment productDetailsViewFragment = new ProductDetailsViewFragment(id, actionBarTitle);
                productDetailsViewFragment.setMasterInfoListener(this);
                loadFragment(productDetailsViewFragment);
                break;

            case Constants.SELECTED_TYPE_DELIVERY_PERSON:
                DeliveryPersonDetailsFragment deliveryPersonDetailsFragment = new DeliveryPersonDetailsFragment(id, actionBarTitle);
                deliveryPersonDetailsFragment.setListener(this);
                loadFragment(deliveryPersonDetailsFragment);
                break;

            case Constants.SELECTED_TYPE_CUSTOMER:
                CustomerDetailsFragment customerDetailsFragment = new CustomerDetailsFragment(id, actionBarTitle);
                customerDetailsFragment.setMasterInfoListener(this);
                loadFragment(customerDetailsFragment);
                break;

            case Constants.SELECTED_TYPE_ROUTE:
                RouteDetailsFragment routeDetailsFragment = new RouteDetailsFragment(id, actionBarTitle);
                routeDetailsFragment.setMasterInfoListener(this);
                loadFragment(routeDetailsFragment);
                break;

            case Constants.SELECTED_SUPPLIER_FOR_PRODUCT_LIST:
                ProductListPerSupplierFragment productListPerSupplierFragment = new ProductListPerSupplierFragment(id, actionBarTitle);
                productListPerSupplierFragment.setListener(this);
                productListPerSupplierFragment.setOnItemSelected(this);
                loadFragment(productListPerSupplierFragment);
                break;

            case Constants.SELECTED_DELIVERY_PERSON_FOR_ROUTE_LIST:
                RouteListPerDeliveryPersonFragment routeListPerDeliveryPersonFragment = new RouteListPerDeliveryPersonFragment(id, actionBarTitle);
                routeListPerDeliveryPersonFragment.setListener(this);
                routeListPerDeliveryPersonFragment.setOnItemSelected(this);
                loadFragment(routeListPerDeliveryPersonFragment);
        }
    }


    //TODO SET THE TITLE
    @Override
    public void setActionBarTitle(String actionBarTitle) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && actionBarTitle != null) {
            actionBar.setTitle(actionBarTitle);

            //TODO set the title color
            SpannableString spannableString = new SpannableString(actionBarTitle);
            spannableString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, actionBarTitle.length(), 0);
            actionBar.setTitle(spannableString);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Handle back button click
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
