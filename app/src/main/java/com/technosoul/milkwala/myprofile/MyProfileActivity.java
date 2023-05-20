package com.technosoul.milkwala.myprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.aboutscreen.AboutFragment;

public class MyProfileActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadFragment(new ProfileFragment());

    }

    private void loadFragment(ProfileFragment profileFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.my_profile_container, profileFragment);
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

