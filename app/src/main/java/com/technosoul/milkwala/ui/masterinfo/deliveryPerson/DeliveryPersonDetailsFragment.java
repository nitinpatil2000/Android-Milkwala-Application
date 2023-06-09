package com.technosoul.milkwala.ui.masterinfo.deliveryPerson;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeliveryPersonDetailsFragment extends Fragment {
    TextView txtDeliveryBoyName;
    TextView txtDeliveryBoyCity;
    TextView txtDeliveryBoyContactNo;
    TextView txtDeliveryBoyAlterNo;
    TextView tvTitleDeliveryPerson;
    Button deleteDeliveryBoyBtn;
    private final int deliveryPersonId;
    private String deliveryBoyName;
    private DeliveryFromServer deliveryFromServer;

    TextView tvDeliveryBoyLoginTitle;
    TextView txtDeliveryDetailsEmail, txtDeliveryDetailsPassword;
    private MasterInfoListener listener;

    public DeliveryPersonDetailsFragment(int deliveryPersonId, String deliveryBoyName) {
        this.deliveryPersonId = deliveryPersonId;
        this.deliveryBoyName = deliveryBoyName;
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery_details, container, false);
        txtDeliveryBoyName = view.findViewById(R.id.txtDeliveryBoyName);
        TextView tvDeliveryBoyAddress = view.findViewById(R.id.txtDeliveryBoyAdd);
        txtDeliveryBoyCity = view.findViewById(R.id.txtDeliveryBoyCity);
        txtDeliveryBoyContactNo = view.findViewById(R.id.txtDeliveryBoyContact);
        txtDeliveryBoyAlterNo = view.findViewById(R.id.txtDeliveryBoyAlterNo);
        tvTitleDeliveryPerson = view.findViewById(R.id.tv_title_delivery_person);

        tvDeliveryBoyLoginTitle = view.findViewById(R.id.delivery_boy_login_title);
        txtDeliveryDetailsEmail = view.findViewById(R.id.delivery_details_email);
        txtDeliveryDetailsPassword = view.findViewById(R.id.deliver_details_password);
        deleteDeliveryBoyBtn = view.findViewById(R.id.deleteDeliveryBoyBtn);


//        TODO FOR LOCAL DATABASE
//        MyDbHelper myDbHelper = MyDbHelper.getDB(getContext());
//        DeliveryPerson deliveryPersonDetails = myDbHelper.deliveryDetailDao().getDeliveryPersonById(deliveryId);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        DeliveryPersonService deliveryPersonService = retrofit.create(DeliveryPersonService.class);
        Call<DeliveryFromServer> deliveryFromServerCall = deliveryPersonService.getDeliveryPersonDetails(deliveryPersonId);
        deliveryFromServerCall.enqueue(new Callback<DeliveryFromServer>() {
            @Override
            public void onResponse(Call<DeliveryFromServer> call, Response<DeliveryFromServer> response) {
                if (response.isSuccessful()) {
                    deliveryFromServer = response.body();
                    if (deliveryFromServer != null) {
                        txtDeliveryBoyName.setText(deliveryFromServer.getDeliveryPersonName());
                        txtDeliveryDetailsEmail.setText(deliveryFromServer.getDeliveryPersonEmail());
                        txtDeliveryDetailsPassword.setText(deliveryFromServer.getDeliveryPersonPassword());
                        tvDeliveryBoyAddress.setText(deliveryFromServer.getDeliveryPersonAddress());
                        txtDeliveryBoyCity.setText(deliveryFromServer.getDeliveryPersonCity());
                        txtDeliveryBoyContactNo.setText(String.valueOf(deliveryFromServer.getDeliveryPersonContactNo()));
                        txtDeliveryBoyAlterNo.setText(String.valueOf(deliveryFromServer.getDeliveryPersonAlterNo()));
                        tvTitleDeliveryPerson.setText(getString(R.string.title_delivery_person_details, deliveryFromServer.getDeliveryPersonName()));
                        tvDeliveryBoyLoginTitle.setText(getString(R.string.title_delivery_person_login_title, deliveryFromServer.getDeliveryPersonName()));
                    }else{
                        Toast.makeText(getContext(), R.string.error_text, Toast.LENGTH_SHORT).show();
                    }
                }
            }


            @Override
            public void onFailure(Call<DeliveryFromServer> call, Throwable t) {
                t.printStackTrace();
            }
        });




        deleteDeliveryBoyBtn.setOnClickListener(v -> {
            Button btnCancelableDeliveryPerson;
            Button btnDeleteDeliveryPerson;
            TextView titleDeliveryPerson;
            TextView messageDeliveryPerson;
            TextView confirmationMsgDeliveryPerson;

            Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_design);

            btnCancelableDeliveryPerson = dialog.findViewById(R.id.btn_action_cancel);
            btnDeleteDeliveryPerson = dialog.findViewById(R.id.btn_action_delete);
            titleDeliveryPerson = dialog.findViewById(R.id.tv_delete_dialog_title);
            messageDeliveryPerson = dialog.findViewById(R.id.tv_delete_dialog_desc);
            confirmationMsgDeliveryPerson = dialog.findViewById(R.id.tv_delete_dialog_confirmation_msg);

            titleDeliveryPerson.setText(getString(R.string.title_delete_dialog, txtDeliveryBoyName.getText().toString()));
            messageDeliveryPerson.setText(R.string.msg_delete_delivery_person_desc);
            confirmationMsgDeliveryPerson.setText(R.string.msg_delete_delivery_person_confirmation);

            btnCancelableDeliveryPerson.setOnClickListener(view1 -> dialog.dismiss());
            btnDeleteDeliveryPerson.setOnClickListener(v1 -> {
                //TODO FOR LOCAL DATABASE
//                myDbHelper.deliveryDetailDao().deleteDeliveryBoyId(deliveryPersonId);
                Toast.makeText(getContext(), R.string.msg_delete_delivery_person_success, Toast.LENGTH_SHORT).show();
                deleteDeliveryPersons();

                dialog.dismiss();
            });

            dialog.show();

        });

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle(deliveryBoyName);
        }

        return view;
    }

    private void deleteDeliveryPersons() {
        ApiRetrofitService retrofitService = new ApiRetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        DeliveryPersonService deliveryPersonService = retrofit.create(DeliveryPersonService.class);
        Call<ResponseBody> deliveryPersonDetails = deliveryPersonService.deleteDeliveryPerson(deliveryPersonId);
        deliveryPersonDetails.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), R.string.msg_delete_delivery_person_success, Toast.LENGTH_SHORT).show();
                    if (listener != null) {
                        listener.onBackToPreviousScreen();
                    }

                }else{
                    Toast.makeText(getContext(),  R.string.no_delivery_person_found, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}