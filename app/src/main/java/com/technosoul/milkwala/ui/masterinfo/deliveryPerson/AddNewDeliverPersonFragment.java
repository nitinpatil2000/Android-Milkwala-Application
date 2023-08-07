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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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



        //for name
        btnAddNewDeliveryPerson.setOnClickListener(view1 -> {
            String deliveryPersonName = etDeliveryPersonName.getText().toString();
            if (TextUtils.isEmpty(deliveryPersonName)) {
                Toast.makeText(getContext(), R.string.err_empty_delivery_name, Toast.LENGTH_SHORT).show();
                return;
            }
            if (deliveryPersonName.length() < 3) {
                Toast.makeText(getContext(), R.string.err_min_length_delivery_name, Toast.LENGTH_SHORT).show();
                return;
            }

            //for emailId and password
            String deliveryPersonEmail = deliveryLoginEmail.getText().toString();
            // Validate the input values
            if (TextUtils.isEmpty(deliveryPersonEmail)) {
                deliveryLoginEmail.setError(getString(R.string.err_empty_email));
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(deliveryPersonEmail).matches()) {
                deliveryLoginEmail.setError(getString(R.string.err_invalid_email));
                return;
            }

            String deliveryPersonPassword = deliveryLoginPassword.getText().toString();
            if (TextUtils.isEmpty(deliveryPersonPassword)) {
                deliveryLoginPassword.setError(getString(R.string.err_empty_pwd));
                return;
            }

            if (deliveryPersonPassword.length() < 6) {
                deliveryLoginPassword.setError(getString(R.string.err_pwd_min_length));
                return;
            }


            String deliveryPersonAddress = etDeliveryPersonAddress.getText().toString();
            if (TextUtils.isEmpty(deliveryPersonAddress)) {
                Toast.makeText(getContext(), R.string.err_empty_delivery_addr, Toast.LENGTH_SHORT).show();
                return;
            }
            if (deliveryPersonAddress.length() < 8) {
                Toast.makeText(getContext(), R.string.err_min_length_delivery_addr, Toast.LENGTH_SHORT).show();
                return;
            }


            String deliveryPersonCity = etDeliveryPersonCity.getText().toString();
            if (TextUtils.isEmpty(deliveryPersonCity)) {
                Toast.makeText(getContext(), R.string.err_empty_city_name, Toast.LENGTH_SHORT).show();
                return;
            }
            if (deliveryPersonCity.length() < 3) {
                Toast.makeText(getContext(), R.string.err_min_length_city, Toast.LENGTH_SHORT).show();
                return;
            }

            String deliveryPersonNo = etDeliveryPersonMobileNo.getText().toString();
            if (TextUtils.isEmpty(deliveryPersonNo)) {
                Toast.makeText(getContext(), R.string.err_empty_mobile_number, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Patterns.PHONE.matcher(deliveryPersonNo).matches()) {
                Toast.makeText(getContext(), R.string.err_invalid_mobile_number, Toast.LENGTH_SHORT).show();
                return;
            }else if (deliveryPersonNo.length() > 10) {
                Toast.makeText(getContext(), R.string.err_max_digits_delivery_mobile_no, Toast.LENGTH_LONG).show();
                etDeliveryPersonMobileNo.requestFocus();
                return;
            }
            long deliveryPersonContactNo = Long.parseLong(deliveryPersonNo);

            String deliveryAlterNo = etDeliveryPersonAlternateNo.getText().toString();
            if (!deliveryAlterNo.isEmpty() && !Patterns.PHONE.matcher(deliveryAlterNo).matches()) {
                Toast.makeText(getContext(), R.string.err_invalid_alternate_number, Toast.LENGTH_SHORT).show();
                return;
            }else if (deliveryAlterNo.length() > 10) {
                Toast.makeText(getContext(), R.string.err_max_digits_delivery_alter_no, Toast.LENGTH_LONG).show();
                etDeliveryPersonAlternateNo.requestFocus();
                return;
            }

            long deliveryPersonAlterNo = Long.parseLong(deliveryAlterNo);



//        TODO FOR LOCAL DATABASE
//            MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
//            myDbHelper.deliveryDetailDao().addDeliver(new DeliveryPerson(deliveryPersonName,deliveryPersonEmail, deliveryPersonPassword,deliveryPersonAddress, deliveryPersonCity, deliveryPersonContactNo, deliveryPersonAlterNo));
//            Toast.makeText(getContext(), getString(R.string.msg_delivery_person_add_success, deliveryPersonName), Toast.LENGTH_SHORT).show();


            ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
            Retrofit retrofit = apiRetrofitService.getRetrofit();
            DeliveryPersonService deliveryPersonService = retrofit.create(DeliveryPersonService.class);
            DeliveryFromServer deliveryFromServer = new DeliveryFromServer();

            deliveryFromServer.setDeliveryPersonName(deliveryPersonName);
            deliveryFromServer.setDeliveryPersonEmail(deliveryPersonEmail);
            deliveryFromServer.setDeliveryPersonPassword(deliveryPersonPassword);
            deliveryFromServer.setDeliveryPersonAddress(deliveryPersonAddress);
            deliveryFromServer.setDeliveryPersonCity(deliveryPersonCity);
            deliveryFromServer.setDeliveryPersonContactNo(deliveryPersonContactNo);
            deliveryFromServer.setDeliveryPersonAlterNo(deliveryPersonAlterNo);

            Call<DeliveryFromServer> deliveryFromServerCall = deliveryPersonService.addDeliveryPerson(deliveryFromServer);
            deliveryFromServerCall.enqueue(new Callback<DeliveryFromServer>() {
                @Override
                public void onResponse(@NonNull Call<DeliveryFromServer> call, @NonNull Response<DeliveryFromServer> response) {
                    if(response.isSuccessful()){
                        DeliveryFromServer createdDeliveryBoy = response.body();
                        if(createdDeliveryBoy != null){
                            Toast.makeText(getContext(), R.string.msg_delivery_person_add_success, Toast.LENGTH_SHORT).show();
                            if(listener != null){
                                listener.onBackToPreviousScreen();
                            }
                        }
                    }else{
                        Toast.makeText(getContext(), R.string.msg_delivery_person_add_error, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DeliveryFromServer> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });



        });

        if (getActivity() != null) {
//            ((MasterInfoActivity) getActivity()).setActionBarTitle(getString(R.string.add_delivery_person_title));
        }
        
        return view;
    }
}