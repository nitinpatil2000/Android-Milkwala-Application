package com.technosoul.milkwala.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.technosoul.milkwala.ui.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.todaydeliver.DeliveryActivity;
import com.technosoul.milkwala.ui.AbstractBaseActivity;

public class AuthActivity extends AbstractBaseActivity implements LoginListener {
    private SharedPreferences sharedPreferences;
   private  LoginFragment loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (getSupportActionBar() != null) {
            // Don't show action bar in login screen.
            getSupportActionBar().hide();
        }

            loadFragment(new LoginFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.auth_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }



    @Override
    public void onDeliveryBoyLoginSuccess() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();

        Intent iDeliver = new Intent(this, DeliveryActivity.class);
        startActivity(iDeliver);
        finish();
    }

    @Override
    public void onAdminLoginSuccess() {
        Intent iMain = new Intent(this, MainActivity.class);
        startActivity(iMain);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            finish();
        } else {
            super.onBackPressed();
        }
    }
}