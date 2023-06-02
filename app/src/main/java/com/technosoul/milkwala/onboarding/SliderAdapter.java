package com.technosoul.milkwala.onboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.technosoul.milkwala.R;


public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int []lottiAnimations ={
            R.raw.milkyanim,
            R.raw.lottie_delivery_boy_bumpy_ride,
            R.raw.customeranim,
            R.raw.productanim
    };

    int[] heading = {
            R.string.product_heading,
            R.string.delivery_hading,
            R.string.customer_heading,
            R.string.supplier_heading
    };
    int[] desc = {
            R.string.product_desc,
            R.string.delivery_desc,
            R.string.customer_desc,
            R.string.supplier_desc
    };



    @Override
    public int getCount() {
        return lottiAnimations.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);


        //Hooks
        LottieAnimationView lottieAnimationView = view.findViewById(R.id.slider_image);
        TextView imageDesc = view.findViewById(R.id.image_desc);
        TextView imageHeading = view.findViewById(R.id.image_heading);

        lottieAnimationView.setAnimation(lottiAnimations[position]);
        imageDesc.setText(desc[position]);
        imageHeading.setText(heading[position]);
        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
