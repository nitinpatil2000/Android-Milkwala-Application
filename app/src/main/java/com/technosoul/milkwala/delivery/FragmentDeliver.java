package com.technosoul.milkwala.delivery;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.products.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FragmentDeliver extends Fragment {
    ArrayList<Deliver> delivers = new ArrayList<>();
    DeliverViewAdapter deliverViewAdapter;

    public FragmentDeliver() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_deliver, container, false);

        RecyclerView deliverRecyclerView = view.findViewById(R.id.deliverRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        deliverRecyclerView.setLayoutManager(gridLayoutManager);

        delivers = new ArrayList<>();
        delivers.add(new Deliver("Chitale", "16 Delivers"));
        delivers.add(new Deliver("Gokul", "14 Delivers"));
        delivers.add(new Deliver("Amul", "20 Delivers"));
        delivers.add(new Deliver("Katraj", "24 Delivers"));
//        delivers.add(new Deliver("Deliver", "16 Delivers"));
//        delivers.add(new Deliver("Deliver", "14 Delivers"));
//        delivers.add(new Deliver("Deliver" ,"20 Delivers"));
//        delivers.add(new Deliver("Deliver", "24 Delivers"));
//        delivers.add(new Deliver("Deliver", "16 Delivers"));
//        delivers.add(new Deliver("Deliver", "14 Delivers"));
//        delivers.add(new Deliver("Deliver", "20 Delivers"));
//        delivers.add(new Deliver("Deliver", "24 Delivers"));

        DeliverViewAdapter deliverViewAdapter = new DeliverViewAdapter(getContext(), delivers);
        deliverRecyclerView.setAdapter(deliverViewAdapter);

        if(getActivity() != null){
            ((MainActivity) getActivity()).setActionBarTitle("Delivery");
        }



        return view;
    }
}