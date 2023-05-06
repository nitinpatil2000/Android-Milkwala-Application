package com.technosoul.milkwala.ui.masterinfo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.customer.CustomerFragment;
import com.technosoul.milkwala.delivery.DeliveryDetailsFragment;
import com.technosoul.milkwala.products.AddNewProductFragment;
import com.technosoul.milkwala.products.ProductListPerSupplierFragment;
import com.technosoul.milkwala.ui.AbstractBaseActivity;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFragment;
import com.technosoul.milkwala.ui.masterinfo.suppliers.AddNewSupplierFragment;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierDetailsFragment;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFragment;
import com.technosoul.milkwala.utils.Constants;

public class MasterInfoActivity extends AbstractBaseActivity implements MasterInfoListener, OnItemSelected {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_info);
        MasterInfoFragment masterInfoFragment = new MasterInfoFragment();
        masterInfoFragment.setListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }

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
        ProductFragment productFragment = new ProductFragment();
        productFragment.setListener(this);
        productFragment.setOnItemSelectedListener(this);
        loadFragment(productFragment);
    }

    @Override
    public void onDeliveryBoyClick() {
        loadFragment(new DeliveryDetailsFragment());
    }

    @Override
    public void onCustomerClick() {
        loadFragment(new CustomerFragment());
    }

    @Override
    public void addNewSupplier() {
        AddNewSupplierFragment addNewSupplierFragment = new AddNewSupplierFragment();
        addNewSupplierFragment.setListener(this);
        loadFragment(addNewSupplierFragment);
    }

    @Override
    public void addNewProduct() {
        AddNewProductFragment addNewProductFragment = new AddNewProductFragment(1);
    }

    @Override
    public void addNewDeliveryBoy() {

    }

    @Override
    public void addNewCustomer() {

    }

    @Override
    public void onBackToPreviousScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
    }

    @Override
    public void onItemClicked(int type, int id, Bundle bundle) {
        switch (type) {
            case Constants.SELECTED_TYPE_SUPPLIER:
                SupplierDetailsFragment supplierDetailsFragment = new SupplierDetailsFragment(id);
                supplierDetailsFragment.setArguments(bundle);
                supplierDetailsFragment.setListener(this);
                loadFragment(supplierDetailsFragment);
                break;

            case Constants.SELECTED_TYPE_PRODUCT:
                break;

            case Constants.SELECTED_TYPE_DELIVERY_BOY:
                break;

            case Constants.SELECTED_TYPE_CUSTOMER:
                break;

            case Constants.SELECTED_SUPPLIER_FOR_PRODUCT_LIST:
                ProductListPerSupplierFragment productListPerSupplierFragment = new ProductListPerSupplierFragment(id);
                loadFragment(productListPerSupplierFragment);
                break;

        }
    }
}
