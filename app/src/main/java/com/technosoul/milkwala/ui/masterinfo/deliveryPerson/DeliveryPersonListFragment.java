package com.technosoul.milkwala.ui.masterinfo.deliveryPerson;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.CustomerViewAdapter;
import com.technosoul.milkwala.adapters.DeliverPersonListViewAdapter;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.DeliveryPerson;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;

import java.util.ArrayList;

public class DeliveryPersonListFragment extends Fragment {
    DeliverPersonListViewAdapter deliverViewAdapter;
    Button btnAddDeliveryPerson;
    EditText searchDeliveryPerson;
    ArrayList<DeliveryPerson> deliveryPersonList;
    private MasterInfoListener masterInfoListener;
    private OnItemSelected onItemSelected;
    TextView tv_empty_delivery_list;

    public DeliveryPersonListFragment() {
        // Required empty public constructor
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    public void setOnItemSelected(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deliverperson_list, container, false);

        RecyclerView deliverRecyclerView = view.findViewById(R.id.recyclerView_delivery_person_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        deliverRecyclerView.setLayoutManager(gridLayoutManager);
        tv_empty_delivery_list = view.findViewById(R.id.tv_empty_delivery_list);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        deliveryPersonList = (ArrayList<DeliveryPerson>) myDbHelper.deliveryDetailDao().getAllDeliveryBoys();

//        for (int i = 0; i < deliveryPersonList.size(); i++) {
//            deliverViewAdapter = new DeliverPersonListViewAdapter(getContext(), deliveryPersonList, onItemSelected);
//            deliverRecyclerView.setAdapter(deliverViewAdapter);
//        }

        if (deliveryPersonList == null || deliveryPersonList.size() == 0) {
            Toast.makeText(getContext(), R.string.empty_delivery_boy_list_message, Toast.LENGTH_SHORT).show();
            tv_empty_delivery_list.setVisibility(View.VISIBLE); // Show the empty text message

        } else {
            tv_empty_delivery_list.setVisibility(View.GONE); // Show the empty text message
            deliverViewAdapter = new DeliverPersonListViewAdapter(getContext(), deliveryPersonList, onItemSelected);
            deliverRecyclerView.setAdapter(deliverViewAdapter);
        }

        btnAddDeliveryPerson = view.findViewById(R.id.btn_add_new_supplier);
        btnAddDeliveryPerson.setOnClickListener(view1 -> {
            if (masterInfoListener != null) {
                masterInfoListener.addNewDeliveryPerson();
            }
        });

        searchDeliveryPerson = view.findViewById(R.id.searchDeliveryPerson);
        searchDeliveryPerson.clearFocus();
        searchDeliveryPerson.addTextChangedListener(new TextWatcher() {
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

        if(getActivity()!= null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle("Delivery Boy");
        }

        return view;
    }

    private void filter(String text) {
        ArrayList<DeliveryPerson> filterDeliveryPerson = new ArrayList<>();
        for (DeliveryPerson deliveryPerson : deliveryPersonList) {
            if (deliveryPerson.getDeliveryBoyName().toLowerCase().contains(text.toLowerCase())) {
                filterDeliveryPerson.add(deliveryPerson);
            }
        }
        deliverViewAdapter.filteredList(filterDeliveryPerson);
    }
}