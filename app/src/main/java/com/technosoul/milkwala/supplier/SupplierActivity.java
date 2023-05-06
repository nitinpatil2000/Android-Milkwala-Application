package com.technosoul.milkwala.supplier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFragment;

public class SupplierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        String fragmentName = getIntent().getStringExtra("fragment");
        Fragment fragment = null;
//
        switch (fragmentName) {
            case "supplierFragment":
                fragment = new SupplierFragment();
                break;
        }

        if(fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FrameLayout detailsContainer = findViewById(R.id.supplierContainer);
            if (detailsContainer != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.supplierContainer, fragment)
                        .commit();
            } else {
                Log.e("MainActivity2", "Details Container is null");
            }
        }
    }
}