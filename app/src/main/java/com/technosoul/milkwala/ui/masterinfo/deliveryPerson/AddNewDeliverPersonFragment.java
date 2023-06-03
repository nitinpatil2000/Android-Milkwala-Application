package com.technosoul.milkwala.ui.masterinfo.deliveryPerson;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.DeliveryPerson;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class AddNewDeliverPersonFragment extends Fragment {
    EditText etDeliveryPersonName;
    EditText etDeliveryPersonAddress;
    EditText etDeliveryPersonMobileNo;
    EditText etDeliveryPersonAlternateNo;
    EditText etDeliveryPersonCity;
    EditText deliveryLoginEmail;
    EditText deliveryLoginPassword;
    Button btnAddNewDeliveryPerson;

    MasterInfoListener listener;

    public AddNewDeliverPersonFragment() {
        // Required empty public constructor
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_deliver, container, false);

        etDeliveryPersonName = view.findViewById(R.id.etDeliveryPersonName);
        etDeliveryPersonAddress = view.findViewById(R.id.etDeliveryPersonAddress);
        etDeliveryPersonCity = view.findViewById(R.id.etDeliveryPersonCity);
        etDeliveryPersonMobileNo = view.findViewById(R.id.etDeliveryPersonContact);
        etDeliveryPersonAlternateNo = view.findViewById(R.id.etDeliveryPersonAlterNo);
        deliveryLoginEmail = view.findViewById(R.id.delivery_login_email);
        deliveryLoginPassword = view.findViewById(R.id.deliver_login_password);

        btnAddNewDeliveryPerson = view.findViewById(R.id.btnAddNewDeliveryPerson);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());

        btnAddNewDeliveryPerson.setOnClickListener(view1 -> {
            String name = etDeliveryPersonName.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), R.string.err_empty_delivery_name, Toast.LENGTH_SHORT).show();
                return;
            }
            if (name.length() < 3) {
                Toast.makeText(getContext(), R.string.err_min_length_delivery_name, Toast.LENGTH_SHORT).show();
                return;
            }

            String deliveryAddress = etDeliveryPersonAddress.getText().toString();
            if (TextUtils.isEmpty(deliveryAddress)) {
                Toast.makeText(getContext(), R.string.err_empty_delivery_addr, Toast.LENGTH_SHORT).show();
                return;
            }
            if (deliveryAddress.length() < 8) {
                Toast.makeText(getContext(), R.string.err_min_length_delivery_addr, Toast.LENGTH_SHORT).show();
                return;
            }

            String deliveryContactNo = etDeliveryPersonMobileNo.getText().toString();
            if (TextUtils.isEmpty(deliveryContactNo)) {
                Toast.makeText(getContext(), R.string.err_empty_mobile_number, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Patterns.PHONE.matcher(deliveryContactNo).matches()) {
                Toast.makeText(getContext(), R.string.err_invalid_mobile_number, Toast.LENGTH_SHORT).show();
                return;
            }else if (deliveryContactNo.length() > 10) {
                Toast.makeText(getContext(), R.string.err_max_digits_delivery_mobile_no, Toast.LENGTH_LONG).show();
                etDeliveryPersonMobileNo.requestFocus();
                return;
            }

            String deliveryAlterNo = etDeliveryPersonAlternateNo.getText().toString();
            if (!deliveryAlterNo.isEmpty() && !Patterns.PHONE.matcher(deliveryContactNo).matches()) {
                Toast.makeText(getContext(), R.string.err_invalid_alternate_number, Toast.LENGTH_SHORT).show();
                return;
            }else if (deliveryAlterNo.length() > 10) {
                Toast.makeText(getContext(), R.string.err_max_digits_delivery_alter_no, Toast.LENGTH_LONG).show();
                etDeliveryPersonAlternateNo.requestFocus();
                return;
            }

            String deliveryCity = etDeliveryPersonCity.getText().toString();
            if (TextUtils.isEmpty(deliveryCity)) {
                Toast.makeText(getContext(), R.string.err_empty_city_name, Toast.LENGTH_SHORT).show();
                return;
            }
            if (deliveryCity.length() < 3) {
                Toast.makeText(getContext(), R.string.err_min_length_city, Toast.LENGTH_SHORT).show();
                return;
            }

            String deliveryEmail = deliveryLoginEmail.getText().toString();
            // Validate the input values
            if (TextUtils.isEmpty(deliveryEmail)) {
                deliveryLoginEmail.setError(getString(R.string.err_empty_email));
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(deliveryEmail).matches()) {
                deliveryLoginEmail.setError(getString(R.string.err_invalid_email));
                return;
            }

            String deliveryPassword = deliveryLoginPassword.getText().toString();
            if (TextUtils.isEmpty(deliveryPassword)) {
                deliveryLoginPassword.setError(getString(R.string.err_empty_pwd));
                return;
            }

            if (deliveryPassword.length() < 6) {
                deliveryLoginPassword.setError(getString(R.string.err_pwd_min_length));
                return;
            }

            myDbHelper.deliveryDetailDao().addDeliver(new DeliveryPerson(name, deliveryAddress, deliveryCity, deliveryContactNo, deliveryAlterNo, deliveryEmail, deliveryPassword));

            Toast.makeText(getContext(), getString(R.string.msg_delivery_person_add_success, name), Toast.LENGTH_SHORT).show();

            if (listener != null) {
                listener.onBackToPreviousScreen();
            }
        });

        if (getActivity() != null) {
            ((MasterInfoActivity)getActivity()).setActionBarTitle("Add New Delivery Boy");
        }
        
        return view;
    }
}