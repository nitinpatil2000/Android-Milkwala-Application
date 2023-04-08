package com.technosoul.milkwala.delivery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.products.AddProductFragment;
import com.technosoul.milkwala.products.ProductDetails;
import com.technosoul.milkwala.products.ProductViewDetailsAdapter;

import java.util.ArrayList;

public class DeliveryDetailsFragment extends Fragment {

    DeliverDetailsViewAdapter deliverDetailsViewAdapter;
    RecyclerView deliverDetailRecyclerView;
    ArrayList<DeliverDetails> deliverDetails = new ArrayList<>();

    Button addDeliveryBtn;

    public DeliveryDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_delivery_details, container, false);

        deliverDetailRecyclerView = view.findViewById(R.id.deliverDetailRecyclerView);
        deliverDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        deliverDetails = (ArrayList<DeliverDetails>) myDbHelper.deliveryDetailDao().getAllDetails();

        for (int i = 0; i < deliverDetails.size(); i++) {
            deliverDetailsViewAdapter = new DeliverDetailsViewAdapter(getContext(),deliverDetails);
            deliverDetailsViewAdapter.notifyDataSetChanged();
            deliverDetailRecyclerView.setAdapter(deliverDetailsViewAdapter);
        }

        addDeliveryBtn = view.findViewById(R.id.addDeliverBtn);

        addDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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