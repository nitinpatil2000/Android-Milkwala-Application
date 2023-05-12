package com.technosoul.milkwala.ui.masterinfo.deliveryPerson;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.delivery.Deliver;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

public class DeliveryPersonDetailsFragment extends Fragment {
    TextView txtDeliveryBoyName;
    TextView txtDeliveryBoyCity;
    TextView txtDeliveryBoyContactNo;
    TextView txtDeliveryBoyAlterNo;
    TextView tvTitleDeliveryPerson;
    Button deleteDeliveryBoyBtn;
    private final int deliveryId;

    private MasterInfoListener listener;

    public DeliveryPersonDetailsFragment(int deliveryId) {
        this.deliveryId = deliveryId;
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

        deleteDeliveryBoyBtn = view.findViewById(R.id.deleteDeliveryBoyBtn);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getContext());
        Deliver deliveryPersonDetails = myDbHelper.deliveryDetailDao().getDeliveryPersonById(deliveryId);

        txtDeliveryBoyName.setText(deliveryPersonDetails.getDeliveryBoyName());
        tvDeliveryBoyAddress.setText(deliveryPersonDetails.getDeliveryBoyAddress());
        txtDeliveryBoyCity.setText(deliveryPersonDetails.getDeliveryBoyCity());
        txtDeliveryBoyContactNo.setText(deliveryPersonDetails.getDeliveryBoyNumber());
        txtDeliveryBoyAlterNo.setText(deliveryPersonDetails.getDeliveryBoyAlterNo());
        tvTitleDeliveryPerson.setText(getString(R.string.title_delivery_person_details, deliveryPersonDetails.getDeliveryBoyName()));

        deleteDeliveryBoyBtn.setOnClickListener(v -> {
            TextView btnCancel;
            TextView btnDelete;
            TextView title;
            TextView message;
            TextView confirmationMsg;

            Dialog dialog = new Dialog(getContext());

            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_design);

            btnCancel = dialog.findViewById(R.id.btn_action_cancel);
            btnDelete = dialog.findViewById(R.id.btn_action_delete);
            title = dialog.findViewById(R.id.tv_title_delete_supplier);
            message = dialog.findViewById(R.id.tv_msg_delete_desc);
            confirmationMsg = dialog.findViewById(R.id.tv_msg_delete_confirmation);

            title.setText(getString(R.string.title_delete_delivery_person, txtDeliveryBoyName.getText().toString()));
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

        return view;
    }
}