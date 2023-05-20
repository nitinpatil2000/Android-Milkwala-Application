package com.technosoul.milkwala.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.technosoul.milkwala.aboutscreen.AboutAppActivity;
import com.technosoul.milkwala.AdminDashboardFragment;
import com.technosoul.milkwala.myprofile.MyProfileActivity;
import com.technosoul.milkwala.myprofile.ProfileFragment;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.customerorder.CustomerActivity;
import com.technosoul.milkwala.receiveProduct.ReceivedProductActivity;
import com.technosoul.milkwala.ui.auth.AuthActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private int supplieId;
    boolean showBackButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //opening and closing the toggle mode of the navigation.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toolbar.setTitleTextColor(Color.WHITE);
        toggle.syncState();

        //by default set the fragment
        loadNewFragment(new AdminDashboardFragment());

        //after click the menu item
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();
                if (id == R.id.menu_dashboard) {
                    loadFragment(new AdminDashboardFragment());
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                } else if (id == R.id.menu_master_info) {
                    Intent intent = new Intent(MainActivity.this, MasterInfoActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                } else if (id == R.id.menu_stock) {
                    Intent iReceiveIntent = new Intent(MainActivity.this, ReceivedProductActivity.class);
                    startActivity(iReceiveIntent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                } else if (id == R.id.menu_distribution) {
                    Intent iCustomerIntent = new Intent(MainActivity.this, CustomerActivity.class);
                    startActivity(iCustomerIntent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                } else if (id == R.id.menu_profile) {
                    Intent iMyProfile = new Intent(MainActivity.this, MyProfileActivity.class);
                    startActivity(iMyProfile);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                } else if (id == R.id.menu_log_out) {
                    SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("flag", false);
                    editor.apply();

                    Intent iAuth = new Intent(MainActivity.this, AuthActivity.class);
                    startActivity(iAuth);
                } else {
                    Intent iAboutIntent = new Intent(MainActivity.this, AboutAppActivity.class);
                    startActivity(iAboutIntent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }




    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadNewFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();

//        if (showBackButton) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        } else {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        }
    }

    
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}



