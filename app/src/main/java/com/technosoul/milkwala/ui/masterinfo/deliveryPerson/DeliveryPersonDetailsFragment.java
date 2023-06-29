package com.technosoul.milkwala.ui.masterinfo.deliveryPerson;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private final String deliveryBoyName;
    private DeliveryFromServer deliveryFromServer;

    FloatingActionButton updateDeliveryBoy;

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
//         MyDbHelper myDbHelper = MyDbHelper.getDB(getContext());
//         DeliveryPerson deliveryPersonDetails = myDbHelper.deliveryDetailDao().getDeliveryPersonById(deliveryId);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        DeliveryPersonService deliveryPersonService = retrofit.create(DeliveryPersonService.class);
        Call<DeliveryFromServer> deliveryFromServerCall = deliveryPersonService.getDeliveryPersonDetails(deliveryPersonId);
        deliveryFromServerCall.enqueue(new Callback<DeliveryFromServer>() {
            @Override
            public void onResponse(@NonNull Call<DeliveryFromServer> call, @NonNull Response<DeliveryFromServer> response) {
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
            public void onFailure(@NonNull Call<DeliveryFromServer> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });


        updateDeliveryBoy = view.findViewById(R.id.btnUpdateDeliveryBoy);
        updateDeliveryBoy.setOnClickListener(view1 ->{
            Button btnCancelDeliveryBoy;
            Button btnUpdateDeliveryBoy;
            TextView updateDialogTitle, updateDialogLoginDetailsTitle;
            EditText editDeliveryBoyName, editDeliveryBoyEmail, editDeliveryBoyPass, editDeliveryBoyAddress, editDeliveryBoyCity, editDeliveryBoyNumber, editDeliveryBoyAltNo;
            TextView txtUpdateName, txtUpdateEmail, txtUpdatePass,  txtUpdateAddress, txtUpdateCity, txtUpdateNumber, txtUpdateAltNo;
            CardView titleLoginCardView, deliveryEmailPasswordCardView;
            LinearLayout linearLayoutCity;

            Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.update_dialog_design);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());

            // Set the width to match the parent
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);

            txtUpdateName = dialog.findViewById(R.id.txtUpdateDialogName);
            txtUpdateAddress = dialog.findViewById(R.id.txtUpdateDialogAddress);
            txtUpdateEmail = dialog.findViewById(R.id.txtUpdateDialogEmail);
            txtUpdatePass = dialog.findViewById(R.id.txtUpdateDialogPassword);
            txtUpdateCity = dialog.findViewById(R.id.txtUpdateDialogCity);
            txtUpdateNumber = dialog.findViewById(R.id.txtUpdateDialogNumber);
            txtUpdateAltNo = dialog.findViewById(R.id.txtUpdateDialogAlterNo);

            editDeliveryBoyName = dialog.findViewById(R.id.edtUpdateDialogName);
            editDeliveryBoyEmail = dialog.findViewById(R.id.edtUpdateDialogEmail);
            editDeliveryBoyPass = dialog.findViewById(R.id.edtUpdateDialogPassword);
            editDeliveryBoyCity = dialog.findViewById(R.id.edtUpdateDialogCity);
            editDeliveryBoyAddress = dialog.findViewById(R.id.edtUpdateDialogAddress);
            editDeliveryBoyNumber = dialog.findViewById(R.id.edtUpdateDialogNumber);
            editDeliveryBoyAltNo = dialog.findViewById(R.id.edtUpdateDialogAltNo);

           linearLayoutCity = dialog.findViewById(R.id.linearLayoutCity);
           titleLoginCardView = dialog.findViewById(R.id.cv_title_update_login_details);
           deliveryEmailPasswordCardView = dialog.findViewById(R.id.cv_email_pass_card_view);

            updateDialogTitle = dialog.findViewById(R.id.tv_update_dialog_title);
            updateDialogLoginDetailsTitle = dialog.findViewById(R.id.tv_title_update_login_details);
            btnCancelDeliveryBoy = dialog.findViewById(R.id.btnActionCancel);
            btnUpdateDeliveryBoy = dialog.findViewById(R.id.btnActionUpdate);

            linearLayoutCity.setVisibility(View.VISIBLE);
            titleLoginCardView.setVisibility(View.VISIBLE);
            deliveryEmailPasswordCardView.setVisibility(View.VISIBLE);

            //TODO set the values in the runtime
            txtUpdateName.setText(getString(R.string.title_delivery_boy_name));
            txtUpdateAddress.setText(getString(R.string.title_delivery_boy_address));
            txtUpdateEmail.setText(getString(R.string.title_delivery_boy_email));
            txtUpdatePass.setText(getString(R.string.title_delivery_boy_password));
            txtUpdateCity.setText(getString(R.string.title_delivery_boy_city));
            txtUpdateNumber.setText(getString(R.string.title_delivery_boy_contact1));
            txtUpdateAltNo.setText(getString(R.string.title_delivery_boy_contact2));

            //todo to set the values in editText runtime
            editDeliveryBoyName.setHint(getString(R.string.hint_delivery_name));
            editDeliveryBoyEmail.setHint(getString(R.string.hint_delivery_email));
            editDeliveryBoyPass.setHint(getString(R.string.hint_delivery_password));
            editDeliveryBoyAddress.setHint(getString(R.string.hint_delivery_address));
            editDeliveryBoyNumber.setHint(getString(R.string.hint_delivery_mobile1));
            editDeliveryBoyAltNo.setHint(getString(R.string.hint_delivery_mobile2));
            editDeliveryBoyCity.setHint(getString(R.string.hint_delivery_city));

            editDeliveryBoyName.setText(deliveryFromServer.getDeliveryPersonName());
            editDeliveryBoyEmail.setText(deliveryFromServer.getDeliveryPersonEmail());
            editDeliveryBoyPass.setText(deliveryFromServer.getDeliveryPersonPassword());
            editDeliveryBoyAddress.setText(deliveryFromServer.getDeliveryPersonAddress());
            editDeliveryBoyCity.setText(deliveryFromServer.getDeliveryPersonCity());
            editDeliveryBoyNumber.setText(String.valueOf(deliveryFromServer.getDeliveryPersonContactNo()));
            editDeliveryBoyAltNo.setText(String.valueOf(deliveryFromServer.getDeliveryPersonAlterNo()));

            updateDialogTitle.setText(String.format(getString(R.string.str_update_title) ,deliveryFromServer.getDeliveryPersonName()));
            updateDialogLoginDetailsTitle.setText(String.format(getString(R.string.str_update_login_title), deliveryFromServer.getDeliveryPersonName()));

            btnCancelDeliveryBoy.setOnClickListener(view2 -> dialog.dismiss());

            btnUpdateDeliveryBoy.setOnClickListener(view2 -> {
                DeliveryFromServer updateDeliveryBoy = new DeliveryFromServer(
                        editDeliveryBoyName.getText().toString(),
                        editDeliveryBoyEmail.getText().toString(),
                        editDeliveryBoyPass.getText().toString(),
                        editDeliveryBoyAddress.getText().toString(),
                        editDeliveryBoyCity.getText().toString(),
                        Long.parseLong(editDeliveryBoyNumber.getText().toString()),
                        Long.parseLong(editDeliveryBoyAltNo.getText().toString())
                );

                Call<DeliveryFromServer> updateDeliveryBoyList = deliveryPersonService.updateDeliveryBoy(deliveryPersonId,updateDeliveryBoy);
                updateDeliveryBoyList.enqueue(new Callback<DeliveryFromServer>() {
                    @Override
                    public void onResponse(@NonNull Call<DeliveryFromServer> call, @NonNull Response<DeliveryFromServer> response) {
                        if(response.isSuccessful()) {
                            if(listener != null){
                                listener.onBackToPreviousScreen();
                            }
                            DeliveryFromServer updateDeliveryBoy = response.body();
                            Toast.makeText(getContext(), R.string.success_update_delivery_boy, Toast.LENGTH_SHORT).show();
                            deliveryFromServer = updateDeliveryBoy;
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<DeliveryFromServer> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            });
            dialog.show();

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
//                Toast.makeText(getContext(), R.string.msg_delete_delivery_person_success, Toast.LENGTH_SHORT).show();
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
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
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
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}