package com.technosoul.milkwala.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.accounts.auth.AuthTokenManager;
import com.technosoul.milkwala.managers.SharedPreferenceManager;
import com.technosoul.milkwala.onboarding.OnBoardingActivity;
import com.technosoul.milkwala.todaydeliver.DeliveryActivity;
import com.technosoul.milkwala.ui.auth.AuthActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String token = AuthTokenManager.instance().get();
                Intent intent;
                if (TextUtils.isEmpty(token)) {
                    // Don't have a token. Let's show Login Screen.
//                    intent = new Intent(SplashActivity.this, AuthActivity.class);
                    intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                } else {
                    // User is already logged in. Let's check the login type
                    int loginType = SharedPreferenceManager.getInstance().getInt(Constants.KEY_LOGIN_TYPE, Constants.LOGIN_TYPE_ADMIN);
                    if (loginType == Constants.LOGIN_TYPE_DELIVERY_PERSON) {
                        intent = new Intent(SplashActivity.this, DeliveryActivity.class);
                    } else {
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                    }
                }

                startActivity(intent);
                finish();
            }
        }, 4000);




//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //mode private means this object cannot used in other application;
//                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
//                //false value set because user cannot login first Time;
//                Boolean check =  pref.getBoolean("flag", false);
//
//                Intent iNext;
//                if(check){ //for true (User is logged in)
//                    iNext = new Intent(SplashActivity.this, MainActivity.class);
//                }else{
//                    iNext = new Intent(SplashActivity.this, AuthActivity.class);
//                }
//
//                startActivity(iNext);
//            }
//        }, 4000);
    }
}