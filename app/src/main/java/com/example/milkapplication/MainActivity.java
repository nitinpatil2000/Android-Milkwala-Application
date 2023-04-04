package com.example.milkapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.milkapplication.HomeScreen.HomeFragment;
import com.example.milkapplication.Supplier.Supplier;
import com.example.milkapplication.Supplier.SupplierFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private String currentFragmentTag;

    //set the title in the activity
    public void setActionBarTitle(String actionBarTitle) {
        if (!TextUtils.isEmpty(actionBarTitle) && getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(actionBarTitle);
        }
    }

    public void setCurrentFragmentTag(String currentFragmentTag){
        this.currentFragmentTag = currentFragmentTag;
    }


    //option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_new) {
            Toast.makeText(this, "new File", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menu_save) {
            Toast.makeText(this, "Save menu", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menu_open) {
            Toast.makeText(this, "Open menu", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        //step1
        setSupportActionBar(toolbar);


        //opening and closing the toggle mode of the navigation.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //by default set the fragment
        loadFragment(new HomeFragment());

        //after click the menu item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id == R.id.menu_new){
//                    loadFragment(new HomeFragment());
                    Toast.makeText(MainActivity.this, "new", Toast.LENGTH_SHORT).show();
                } else if(id == R.id.menu_open){
                    Toast.makeText(MainActivity.this, "open", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "save", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

        //press the back button then set the title
//        if(currentFragmentTag!= null && currentFragmentTag.equals(SupplierFragment.class.getSimpleName())){
            setActionBarTitle("MilkWala");
//        }else{
//            super.onBackPressed();
//        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, fragment);
        ft.commit();
    }
}