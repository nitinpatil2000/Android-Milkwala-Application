package com.technosoul.milkwala.HomeScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.technosoul.milkwala.R;

public class ImageAdapter extends PagerAdapter {
    private Context context;

    private int [] image = {R.drawable.milkk, R.drawable.healthy, R.drawable.glass, R.drawable.milk1, R.drawable.milk2, R.drawable.strawberry};
    public ImageAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_image, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(image[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
