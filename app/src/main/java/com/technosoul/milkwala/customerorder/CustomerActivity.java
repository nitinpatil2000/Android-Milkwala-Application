package com.technosoul.milkwala.customerorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.receiveProduct.ReceivedProductFragment;

public class CustomerActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

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

        loadFragment(new CustomerOrderFragment());

    }

    private void loadFragment(CustomerOrderFragment customerOrderFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.customer_order_container, customerOrderFragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Handle back button click
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setActionBarTitle(String actionBarTitle){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null && actionBarTitle.isEmpty()){
            actionBar.setTitle(actionBarTitle);
        }
        //TODO set the title color
        SpannableString spannableString = new SpannableString(actionBarTitle);
        spannableString.setSpan(new ForegroundColorSpan(Color.WHITE),0, actionBarTitle.length(),0);
        actionBar.setTitle(spannableString);
    }

}