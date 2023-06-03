package com.technosoul.milkwala.ui.masterinfo.deliveryPerson;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.DeliveryPerson;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class DeliveryPersonDetailsFragment extends Fragment {
    TextView txtDeliveryBoyName;
    TextView txtDeliveryBoyCity;
    TextView txtDeliveryBoyContactNo;
    TextView txtDeliveryBoyAlterNo;
    TextView tvTitleDeliveryPerson;
    Button deleteDeliveryBoyBtn;
    private final int deliveryId;
    private String deliveryBoyName;


    TextView tvDeliveryBoyLoginTitle;
    TextView txtDeliveryDetailsEmail, txtDeliveryDetailsPassword;


    private MasterInfoListener listener;

    public DeliveryPersonDetailsFragment(int deliveryId, String deliveryBoyName) {
        this.deliveryId = deliveryId;
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

        MyDbHelper myDbHelper = MyDbHelper.getDB(getContext());
        DeliveryPerson deliveryPersonDetails = myDbHelper.deliveryDetailDao().getDeliveryPersonById(deliveryId);

        txtDeliveryBoyName.setText(deliveryPersonDetails.getDeliveryBoyName());
        tvDeliveryBoyAddress.setText(deliveryPersonDetails.getDeliveryBoyAddress());
        txtDeliveryBoyCity.setText(deliveryPersonDetails.getDeliveryBoyCity());
        txtDeliveryBoyContactNo.setText(deliveryPersonDetails.getDeliveryBoyNumber());
        txtDeliveryBoyAlterNo.setText(deliveryPersonDetails.getDeliveryBoyAlterNo());
        tvTitleDeliveryPerson.setText(getString(R.string.title_delivery_person_details, deliveryPersonDetails.getDeliveryBoyName()));

        txtDeliveryDetailsEmail.setText(deliveryPersonDetails.getDeliveryBoyEmail());
        txtDeliveryDetailsPassword.setText(deliveryPersonDetails.getDeliveryBoyPassword());
        tvDeliveryBoyLoginTitle.setText(getString(R.string.title_delivery_person_login_title, deliveryPersonDetails.getDeliveryBoyName()));


        deleteDeliveryBoyBtn.setOnClickListener(v -> {
            Button btnCancel;
            Button btnDelete;
            TextView title;
            TextView message;
            TextView confirmationMsg;

            Dialog dialog = new Dialog(getContext());

            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_design);

            btnCancel = dialog.findViewById(R.id.btn_action_cancel);
            btnDelete = dialog.findViewById(R.id.btn_action_delete);
            title = dialog.findViewById(R.id.tv_delete_dialog_title);
            message = dialog.findViewById(R.id.tv_delete_dialog_desc);
            confirmationMsg = dialog.findViewById(R.id.tv_delete_dialog_confirmation_msg);

            title.setText(getString(R.string.title_delete_dialog, txtDeliveryBoyName.getText().toString()));
            message.setText(R.string.msg_delete_delivery_person_desc);
            confirmationMsg.setText(R.string.msg_delete_delivery_person_confirmation);

            btnCancel.setOnClickListener(view1 -> dialog.dismiss());

            btnDelete.setOnClickListener(v1 -> {
                myDbHelper.deliveryDetailDao().deleteDeliveryBoyId(deliveryId);
                Toast.makeText(getContext(), R.string.msg_delete_delivery_person_success, Toast.LENGTH_SHORT).show();
                listener.onBackToPreviousScreen();
                dialog.dismiss();
            });

            dialog.show();

        });

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle(deliveryBoyName);
        }

        return view;
    }
}