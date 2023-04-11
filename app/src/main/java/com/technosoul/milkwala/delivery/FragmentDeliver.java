package com.technosoul.milkwala.delivery;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;



import java.util.ArrayList;

public class FragmentDeliver extends Fragment {
    ArrayList<Deliver> delivers = new ArrayList<>();
    DeliverViewAdapter deliverViewAdapter;
    Button addDeliveryBoyBtn;


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

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        ArrayList<Deliver> deliverList = (ArrayList<Deliver>) myDbHelper.deliveryDetailDao().getAllDeliveryBoys();

        for (int i = 0; i < deliverList.size(); i++) {
            deliverViewAdapter = new DeliverViewAdapter(getContext(), deliverList);
            deliverViewAdapter.notifyDataSetChanged();
            deliverRecyclerView.setAdapter(deliverViewAdapter);
        }


        if(getActivity() != null){
            ((MainActivity) getActivity()).setActionBarTitle("Delivery Boys");
        }


        addDeliveryBoyBtn = view.findViewById(R.id.addDeliveryBoyBtn);
        addDeliveryBoyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDeliverFragment addDeliverFragment = new AddDeliverFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, addDeliverFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
}