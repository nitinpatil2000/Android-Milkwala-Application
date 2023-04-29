package com.technosoul.milkwala.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.technosoul.milkwala.delivery.Deliver;
import com.technosoul.milkwala.todaydeliver.DeliveryActivity;
import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;

public class AuthActivity extends AppCompatActivity implements LoginListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
//        signIn = findViewById(R.id.btn_signin);
//        signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AuthActivity.this, MainActivity.class));
//            }
//        });


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
    public void onNormalLoginSuccess() {

        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("deliver", true);
        editor.apply();
        Intent iDeliver = new Intent(this, DeliveryActivity.class);
        startActivity(iDeliver);
        finish();
    }

    @Override
    public void onAdminLognSuccess() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("flag", true);
        editor.apply();
        Intent iMain = new Intent(this, MainActivity.class);
        startActivity(iMain);
        finish();
    }
}