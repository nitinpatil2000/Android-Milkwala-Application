package com.technosoul.milkwala.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.accounts.auth.AuthTokenManager;
import com.technosoul.milkwala.auth.AuthActivity;
import com.technosoul.milkwala.todaydeliver.DeliveryActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String token = AuthTokenManager.instance().get();
                SharedPreferences preferences= getSharedPreferences("login", MODE_PRIVATE);
                Boolean check= preferences.getBoolean("flag", false);
                Boolean deliver = preferences.getBoolean("deliver", false);
                Intent iDashBoard;
                if (check) {
                    iDashBoard =new Intent(SplashActivity.this, MainActivity.class);

                }else if(deliver){
                    iDashBoard = new Intent(SplashActivity.this, DeliveryActivity.class );
                }
                else{
                    iDashBoard = new Intent(SplashActivity.this, AuthActivity.class);
                }
                startActivity(iDashBoard);
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 4000);


    }
}