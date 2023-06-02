package com.technosoul.milkwala.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.auth.AuthActivity;

import java.util.Timer;
import java.util.TimerTask;

public class OnBoardingActivity extends AppCompatActivity {
//    private Button nextBtn;
    private Button getStartedBtn;
    private ViewPager viewPager;
    private LinearLayout dotsLayout;

    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    private int currentPage = 0;
    private Timer timer;
    final long DELAY_MS = 1500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 1500; // time in milliseconds between successive task executions.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);

        //call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        //Start auto image sliding
        startAutoSlider();

        //click to next activity
//        nextBtn = findViewById(R.id.next_btn);
        getStartedBtn = findViewById(R.id.get_started_btn);
//        nextBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(OnBoardingActivity.this, AuthActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                i.putExtra("title", "Login");
//
//            }
//        });

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, AuthActivity.class));

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }

    private void addDots(int position) {
        dots = new TextView[sliderAdapter.getCount()];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactiveDotColor));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.activeDotColor));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPage = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //Start auto image sliding
    private void startAutoSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage == sliderAdapter.getCount()) {
                            currentPage = 0;
                        }
                        viewPager.setCurrentItem(currentPage++, true);
                    }
                });
            }
        }, DELAY_MS, PERIOD_MS);
    }

}