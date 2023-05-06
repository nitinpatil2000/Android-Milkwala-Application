package com.technosoul.milkwala.ui.masterinfo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.customer.CustomerFragment;
import com.technosoul.milkwala.delivery.DeliveryDetailsFragment;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFragment;
import com.technosoul.milkwala.ui.masterinfo.suppliers.AddNewSupplierFragment;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFragment;
import com.technosoul.milkwala.ui.AbstractBaseActivity;

public class MasterInfoActivity extends AbstractBaseActivity implements MasterInfoListener {

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
        loadFragment(supplierFragment);
    }

    @Override
    public void onProductClick() {
        ProductFragment productFragment = new ProductFragment();
        productFragment.setListener(this);
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
}
