package com.technosoul.milkwala.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.technosoul.milkwala.AboutFragment;
import com.technosoul.milkwala.AdminDashboardFragment;
import com.technosoul.milkwala.ProfileFragment;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.customerorder.CustomerOrderFragment;
import com.technosoul.milkwala.receiveProduct.ReceivedProductFragment;
import com.technosoul.milkwala.ui.auth.AuthActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private int supplieId;
    boolean showBackButton = false;



    //set the title in the activity
//    public void setActionBarTitle(String actionBarTitle) {
//        if (!TextUtils.isEmpty(actionBarTitle) && getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(actionBarTitle);
//
//
////            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            if (true) {
////                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
////                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                navigationView.setVisibility(View.GONE);
////                getSupportFragmentManager().popBackStack();
//            } else {
//                onBackPressed();
////                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
////                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
//            }
//
//        }
//    }

    //option menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        new MenuInflater(this).inflate(R.menu.option_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int itemId = item.getItemId();
//
//        if (itemId == R.id.menu_master) {
//            MasterInfoFragment homeFragment = new MasterInfoFragment();
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            ft.add(R.id.container, homeFragment);
//            ft.commit();
//
//        } else if (itemId == R.id.menu_stock) {
//            Toast.makeText(this, "stock menu", Toast.LENGTH_SHORT).show();
//        } else if (itemId == android.R.id.home) {
////            getSupportFragmentManager().popBackStack();
//            super.onBackPressed();
//            return true;
//        } else if (itemId == R.id.menu_distribution){
//            Toast.makeText(this, "distribution menu", Toast.LENGTH_SHORT).show();
//        } else if(itemId == R.id.menu_profile){
//            Toast.makeText(this, "profile menu", Toast.LENGTH_SHORT).show();
//        } else if(itemId == R.id.menu_master){
//            Toast.makeText(this, "master menu", Toast.LENGTH_SHORT).show();
//        } else{
//            Toast.makeText(this, "About menu", Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }


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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.menu_dashboard) {
                    loadFragment(new AdminDashboardFragment());
                } else if (id == R.id.menu_master_info) {
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                    drawerLayout.setVisibility(navigationView.GONE);
                    Intent intent = new Intent(MainActivity.this, MasterInfoActivity.class);
                    startActivity(intent);

                } else if (id == R.id.menu_stock) {
                    loadFragment(new ReceivedProductFragment());
                } else if (id == R.id.menu_distribution) {
                    loadFragment(new CustomerOrderFragment());
                } else if (id == R.id.menu_profile) {
                    loadFragment(new ProfileFragment());
                } else if (id == R.id.menu_log_out) {
                    SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("flag", false);
                    editor.apply();

                    Intent iAuth = new Intent(MainActivity.this, AuthActivity.class);
                    startActivity(iAuth);
                } else {
                    loadFragment(new AboutFragment());
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



