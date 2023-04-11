package com.technosoul.milkwala.delivery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.R;

public class AddDeliverFragment extends Fragment {
EditText editDeliveryBoyName, editDeliveryBoyAdd, editDeliveryBoyContact, editDeliveryBoyAlterNo, editDeliveryBoyCity;
Button addNewDeliveryBoyBtn;

    public AddDeliverFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_deliver, container, false);

        editDeliveryBoyName = view.findViewById(R.id.editDeliveryBoyName);
        editDeliveryBoyAdd = view.findViewById(R.id.editDeliveryBoyAdd);
        editDeliveryBoyContact = view.findViewById(R.id.editDeliveryBoyContact);
        editDeliveryBoyAlterNo = view.findViewById(R.id.editDeliveryBoyAlterNo);
        editDeliveryBoyCity = view.findViewById(R.id.editDeliveryBoyCity);

        addNewDeliveryBoyBtn = view.findViewById(R.id.addNewDeliveryBoyBtn);


        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());

        addNewDeliveryBoyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String  deliverName = editDeliveryBoyName.getText().toString();
               String deliveryAddress = editDeliveryBoyAdd.getText().toString();
               String deliveryContactNo = editDeliveryBoyContact.getText().toString();
               String deliveryAlterNo = editDeliveryBoyAlterNo.getText().toString();
               String deliveryCity = editDeliveryBoyCity.getText().toString();

               if(!deliverName.isEmpty() && !deliveryAddress.isEmpty() && !deliveryContactNo.isEmpty() && !deliveryAlterNo.isEmpty()){
                   myDbHelper.deliveryDetailDao().addDeliver(
                           new Deliver(deliverName, deliveryAddress, deliveryCity, deliveryContactNo, deliveryAlterNo)
                   );

                   Toast.makeText(getContext(), "Delivery Boy added successfully", Toast.LENGTH_SHORT).show();
                   // return to the supplierFragment
                   FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
                   fragmentManager.popBackStack();
               }
            }
        });
        return view;
    }
}