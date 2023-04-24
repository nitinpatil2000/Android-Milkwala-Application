package com.technosoul.milkwala.delivery;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.helper.MyDbHelper;

public class DeliveryDetailsFragment extends Fragment {
    TextView txtDeliveryBoyName, txtDeliveryBoyAdd, txtDeliveryBoyCity, txtDeliveryBoyContactNo, txtDeliveryBoyAlterNo;
    Button deleteDeliveryBoyBtn;
    String deliveryBoyName;
    int deliveryId;


    public DeliveryDetailsFragment(int deliveryId) {
        this.deliveryId = deliveryId;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery_details, container, false);
        txtDeliveryBoyName = view.findViewById(R.id.txtDeliveryBoyName);
        txtDeliveryBoyAdd = view.findViewById(R.id.txtDeliveryBoyAdd);
        txtDeliveryBoyCity = view.findViewById(R.id.txtDeliveryBoyCity);
        txtDeliveryBoyContactNo = view.findViewById(R.id.txtDeliveryBoyContact);
        txtDeliveryBoyAlterNo = view.findViewById(R.id.txtDeliveryBoyAlterNo);

        deleteDeliveryBoyBtn = view.findViewById(R.id.deleteDeliveryBoyBtn);


        Bundle bundle = getArguments();
        if(bundle != null){
            deliveryBoyName = bundle.getString("Name");
            String deliveryBoyAddress = bundle.getString("Address");
            String deliveryBoyCity = bundle.getString("City");
            String deliveryBoyContact = bundle.getString("Contact");
            String deliveryBoyAlter = bundle.getString("Alter");

            txtDeliveryBoyName.setText(deliveryBoyName);
            txtDeliveryBoyAdd.setText(deliveryBoyAddress);
            txtDeliveryBoyCity.setText(deliveryBoyCity);
            txtDeliveryBoyContactNo.setText(deliveryBoyContact);
            txtDeliveryBoyAlterNo.setText(deliveryBoyAlter);
        }

        deleteDeliveryBoyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView cancelBtn, deleteBtn, deleteTxt, deleteInfo, dltMsg;

                Dialog dialog = new Dialog(getContext());

                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_design);

                cancelBtn = dialog.findViewById(R.id.cancelBtn);
                deleteBtn = dialog.findViewById(R.id.delteBtn);
                deleteTxt = dialog.findViewById(R.id.deleteTxt);
                deleteInfo = dialog.findViewById(R.id.deleteInfo);
                dltMsg = dialog.findViewById(R.id.dltMsg);

                deleteTxt.setText(" Delete Delivery Boy");
                deleteInfo.setText(deliveryBoyName);
                dltMsg.setText("are you sure to delete this \n product?");


                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyDbHelper myDbHelper = MyDbHelper.getDB(getContext());
                        myDbHelper.deliveryDetailDao().deleteDeliveryBoyId(deliveryId);
                        Toast.makeText(getContext(), "Delivery boy deleted Successfully", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().popBackStack();
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        return  view;
    }
}