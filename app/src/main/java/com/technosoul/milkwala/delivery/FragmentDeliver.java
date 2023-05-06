package com.technosoul.milkwala.delivery;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.ui.MainActivity;
import com.technosoul.milkwala.R;



import java.util.ArrayList;

public class FragmentDeliver extends Fragment {
    DeliverViewAdapter deliverViewAdapter;
    Button addDeliveryBoyBtn;
    EditText searchDeliveryBoy;
    ArrayList<Deliver> deliverList;


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
       deliverList = (ArrayList<Deliver>) myDbHelper.deliveryDetailDao().getAllDeliveryBoys();

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

        searchDeliveryBoy = view.findViewById(R.id.searchDeliveryBoy);
        searchDeliveryBoy.clearFocus();
        searchDeliveryBoy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        return view;
    }

    private void filter(String text) {
        ArrayList<Deliver> filterDeliver = new ArrayList<>();
        for(Deliver deliver : deliverList){
            if(deliver.getDeliveryBoyName().toLowerCase().contains(text.toLowerCase())){
                filterDeliver.add(deliver);
            }
        }
        deliverViewAdapter.filteredList(filterDeliver);
    }
}