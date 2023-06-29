package com.technosoul.milkwala.ui.masterinfo.suppliers;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SupplierDetailsFragment extends Fragment {
    TextView tvSupplierName;
    TextView tvSupplierAddress;
    TextView tvSupplierNumber;
    TextView tvSupplierAltNumber;
    TextView tvSupplierEmail;
    Button deleteSupplier;
    FloatingActionButton btnUpdateSupplierDetails;
    int supplierId;
    String supplierName;
    private SupplierFromServer supplierListFromServer;

    MasterInfoListener listener;

    public SupplierDetailsFragment(int supplierId, String supplierName) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplier_details, container, false);
        tvSupplierName = view.findViewById(R.id.supplierName);
        tvSupplierEmail = view.findViewById(R.id.supplierEmail);
        tvSupplierAddress = view.findViewById(R.id.supplierAddress);
        tvSupplierNumber = view.findViewById(R.id.supplierNumber);
        tvSupplierAltNumber = view.findViewById(R.id.supplier_alt_number);
        deleteSupplier = view.findViewById(R.id.deleteSupplier);


        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        SupplierService supplierService = retrofit.create(SupplierService.class);

        Call<SupplierFromServer> supplierFromServerCall = supplierService.getSupplierDetails(supplierId);
        supplierFromServerCall.enqueue(new Callback<SupplierFromServer>() {
            @Override
            public void onResponse(@NonNull Call<SupplierFromServer> call, @NonNull Response<SupplierFromServer> response) {
                if(response.isSuccessful()){
                    supplierListFromServer = response.body();
                    if(supplierListFromServer != null){
                        tvSupplierName.setText(supplierListFromServer.getSupplierName());
                        tvSupplierEmail.setText(supplierListFromServer.getSupplierEmail());
                        tvSupplierAddress.setText(supplierListFromServer.getSupplierAddress());
                        tvSupplierNumber.setText(String.valueOf(supplierListFromServer.getSupplierNumber()));
                        tvSupplierAltNumber.setText(String.valueOf(supplierListFromServer.getSupplierAltNumber()));
                    }else{
                        Toast.makeText(getContext(), R.string.error_text, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SupplierFromServer> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });



        btnUpdateSupplierDetails = view.findViewById(R.id.btnUpdateSupplierDetails);
        btnUpdateSupplierDetails.setOnClickListener(view1 -> {
            Button btnCancelUpdateSupplier;
            Button btnUpdateSupplier;
            TextView updateDialogTitle;
            EditText updateSupplierName, updateSupplierEmail, updateSupplierAddress, updateSupplierContactNo, updateSupplierAlterNo;
            TextView txtUpdateName, txtUpdateAddress, txtUpdateEmail, txtUpdateNumber, txtUpdateAltNo;

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
            txtUpdateNumber = dialog.findViewById(R.id.txtUpdateDialogNumber);
            txtUpdateAltNo = dialog.findViewById(R.id.txtUpdateDialogAlterNo);

            //TODO set the values in the runtime
            txtUpdateName.setText(getString(R.string.title_supplier_name));
            txtUpdateAddress.setText(getString(R.string.title_supplier_address));
            txtUpdateEmail.setText(getString(R.string.title_supplier_email));
            txtUpdateNumber.setText(getString(R.string.title_supplier_number));
            txtUpdateAltNo.setText(getString(R.string.title_supplier_alternate_number));


            updateSupplierName = dialog.findViewById(R.id.edtUpdateDialogName);
            updateSupplierEmail = dialog.findViewById(R.id.edtUpdateDialogEmail);
            updateSupplierAddress = dialog.findViewById(R.id.edtUpdateDialogAddress);
            updateSupplierContactNo = dialog.findViewById(R.id.edtUpdateDialogNumber);
            updateSupplierAlterNo = dialog.findViewById(R.id.edtUpdateDialogAltNo);
            updateDialogTitle = dialog.findViewById(R.id.tv_update_dialog_title);

            updateSupplierName.setHint(getString(R.string.hint_enter_supplier_name));
            updateSupplierEmail.setHint(getString(R.string.hint_enter_supplier_email));
            updateSupplierAddress.setHint(getString(R.string.hint_enter_supplier_address));
            updateSupplierContactNo.setHint(getString(R.string.hint_enter_supplier_mobile1));
            updateSupplierAlterNo.setHint(getString(R.string.hint_enter_supplier_mobile2));

            btnCancelUpdateSupplier = dialog.findViewById(R.id.btnActionCancel);
            btnUpdateSupplier = dialog.findViewById(R.id.btnActionUpdate);

            updateSupplierName.setText(supplierListFromServer.getSupplierName());
            updateSupplierEmail.setText(supplierListFromServer.getSupplierEmail());
            updateSupplierAddress.setText(supplierListFromServer.getSupplierAddress());
            updateSupplierContactNo.setText(String.valueOf(supplierListFromServer.getSupplierNumber()));
            updateSupplierAlterNo.setText(String.valueOf(supplierListFromServer.getSupplierAltNumber()));
            updateDialogTitle.setText(String.format(getString(R.string.str_update_title) ,supplierListFromServer.getSupplierName() ));

            btnCancelUpdateSupplier.setOnClickListener(view2 -> dialog.dismiss());

            btnUpdateSupplier.setOnClickListener(view2 -> {
                //rest of the code
                SupplierFromServer updateSupplier = new SupplierFromServer(
                        updateSupplierName.getText().toString(),
                        updateSupplierEmail.getText().toString(),
                        updateSupplierAddress.getText().toString(),
                        Long.parseLong(updateSupplierContactNo.getText().toString()),
                        Long.parseLong(updateSupplierAlterNo.getText().toString())
                );
                Call<SupplierFromServer> updateSupplierList = supplierService.updateSupplier(supplierId, updateSupplier);
                updateSupplierList.enqueue(new Callback<SupplierFromServer>() {
                    @Override
                    public void onResponse(@NonNull Call<SupplierFromServer> call, @NonNull Response<SupplierFromServer> response) {
                        if(response.isSuccessful()){
                            SupplierFromServer updatedSupplier = response.body();
                            if(listener != null){
                                listener.onBackToPreviousScreen();
                            }
                            Toast.makeText(getContext(), "Supplier updated successfully", Toast.LENGTH_SHORT).show();
                            supplierListFromServer = updatedSupplier;
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "Failed to update supplier!!", Toast.LENGTH_SHORT).show();
                            Request request = call.request();
                            try {
                                Buffer buffer = new Buffer();
                                Objects.requireNonNull(request.body()).writeTo(buffer);
                                String requestBody = buffer.readUtf8();
                                Log.d("API Request Body", requestBody);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SupplierFromServer> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            });
            dialog.show();



        });

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());

        deleteSupplier.setOnClickListener(view12 -> {
            //dialog box buttons
            Button btnCancelDeleteSupplier;
            Button btnDeleteSupplier;
            TextView titleDeleteSupplier;
            TextView msgDeleteSupplier;
            TextView confirmationDeleteSupplier;

            Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_design);

            btnCancelDeleteSupplier = dialog.findViewById(R.id.btn_action_cancel);
            btnDeleteSupplier = dialog.findViewById(R.id.btn_action_delete);
            titleDeleteSupplier = dialog.findViewById(R.id.tv_delete_dialog_title);
            msgDeleteSupplier = dialog.findViewById(R.id.tv_delete_dialog_desc);
            confirmationDeleteSupplier = dialog.findViewById(R.id.tv_delete_dialog_confirmation_msg);

            titleDeleteSupplier.setText(String.format(getString(R.string.title_delete_dialog), supplierListFromServer.getSupplierName() ));
            confirmationDeleteSupplier.setText(R.string.msg_delete_supplier_confirmation);
            msgDeleteSupplier.setText(R.string.msg_delete_supplier_desc);

            btnCancelDeleteSupplier.setOnClickListener(view1 -> dialog.dismiss());
            btnDeleteSupplier.setOnClickListener(v -> {
                myDbHelper.supplierDao().deleteById(supplierId);
                Toast.makeText(getContext(), R.string.msg_delete_supplier_success, Toast.LENGTH_SHORT).show();
                deleteSuppliers();

                dialog.dismiss();
            });

            dialog.show();
        });

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle(supplierName);
        }
        return view;
    }




    private void deleteSuppliers() {
        ApiRetrofitService retrofitService = new ApiRetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        SupplierService supplierService = retrofit.create(SupplierService.class);

        Call<ResponseBody> call = supplierService.deleteSupplier(supplierId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), R.string.msg_delete_supplier_success, Toast.LENGTH_SHORT).show();
                    if (listener != null) {
                        listener.onBackToPreviousScreen();
                    }

                }else{
                    Toast.makeText(getContext(),  R.string.no_supplier_found, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}





