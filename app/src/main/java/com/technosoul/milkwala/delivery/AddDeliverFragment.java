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
EditText editDeliveryName, editAddress, editContactNo, editAlternateNo;
Button addNewDeliveryBtn;

    public AddDeliverFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_deliver, container, false);

        editDeliveryName = view.findViewById(R.id.editDeliveryName);
        editAddress = view.findViewById(R.id.editAddress);
        editContactNo = view.findViewById(R.id.editContactNo);
        editAlternateNo = view.findViewById(R.id.editAlternateNo);

        addNewDeliveryBtn = view.findViewById(R.id.addNewDeliverBtn);
        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        addNewDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String  deliverName = editDeliveryName.getText().toString();
               String deliveryAddress = editAddress.getText().toString();
               String deliveryContactNo = editContactNo.getText().toString();
               String deliveryAlterNo = editAlternateNo.getText().toString();

               if(!deliverName.isEmpty() && !deliveryAddress.isEmpty() && !deliveryContactNo.isEmpty() && !deliveryAlterNo.isEmpty()){
                   myDbHelper.deliveryDetailDao().addDeliver(
                           new DeliverDetails(deliverName, deliveryAddress, deliveryContactNo, deliveryAlterNo)
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