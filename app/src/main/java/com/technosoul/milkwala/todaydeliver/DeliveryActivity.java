package com.technosoul.milkwala.todaydeliver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
;


import com.technosoul.milkwala.R;
import com.technosoul.milkwala.accounts.auth.AuthTokenManager;
import com.technosoul.milkwala.managers.SharedPreferenceManager;
import com.technosoul.milkwala.myprofile.MyProfileActivity;
import com.technosoul.milkwala.ui.MainActivity;
import com.technosoul.milkwala.ui.auth.AuthActivity;
import com.technosoul.milkwala.utils.Constants;

public class DeliveryActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_profile:
                Intent iMyProfile = new Intent(DeliveryActivity.this, MyProfileActivity.class);
                startActivity(iMyProfile);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            case R.id.upload_item:
                // do your code
                return true;
            case R.id.copy_item:
                // do your code
                return true;
            case R.id.print_item:
                // do your code
                return true;
            case R.id.share_item:
                // do your code
                return true;
            case R.id.menu_logout:
                AuthTokenManager.instance().set("");
                SharedPreferenceManager.getInstance().putInt(Constants.KEY_LOGIN_TYPE, Constants.LOGIN_TYPE_NONE);
                startActivity(new Intent(DeliveryActivity.this, AuthActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deliver_menu, menu);
//        Drawable drawable = overFlowMen
//        drawable.setTint(ContextCompat.getColor(this, R.color.white));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        toolbar = findViewById(R.id.deliveryToolbar);
        setSupportActionBar(toolbar);
        loadFragment(new TodayDeliverFragment());
    }

    
    @Override
    public void onBackPressed() {
        finish();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.delivery_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}