package com.technosoul.milkwala.receiveProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class ReceivedProductActivity extends AppCompatActivity{
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_product);

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

        loadFragment(new ReceivedProductFragment());

    }

    private void loadFragment(ReceivedProductFragment receivedProductFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.received_info_container, receivedProductFragment);
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



