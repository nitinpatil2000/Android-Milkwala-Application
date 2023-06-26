package com.technosoul.milkwala.ui.masterinfo.route;

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

public class RouteDetailsFragment extends Fragment {
    RouteFromServer routeFromServer;
    private MasterInfoListener masterInfoListener;
    private final int routeId;
    private final String routeNo;
    TextView txtRouteName, txtRouteNo, txtRouteDesc;
    Button deleteRouteBtn;
    FloatingActionButton btnUpdateRouteDetails;


    public RouteDetailsFragment(int routeId, String routeNo) {
        this.routeId = routeId;
        this.routeNo = routeNo;
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_details, container, false);

        txtRouteName = view.findViewById(R.id.tv_route_name);
        txtRouteNo = view.findViewById(R.id.tv_route_no);
        txtRouteDesc = view.findViewById(R.id.tv_route_desc);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        RouteService routeService = retrofit.create(RouteService.class);
        Call<RouteFromServer> getRouteByRouteId = routeService.getRouteByRouteId(routeId);
        getRouteByRouteId.enqueue(new Callback<RouteFromServer>() {
            @Override
            public void onResponse(@NonNull Call<RouteFromServer> call, @NonNull Response<RouteFromServer> response) {
                if (response.isSuccessful()) {
                    routeFromServer = response.body();
                    if (routeFromServer != null) {
                        txtRouteName.setText(routeFromServer.getRouteName());
                        txtRouteNo.setText(routeFromServer.getRouteNo());
                        txtRouteDesc.setText(routeFromServer.getRouteDesc());
                        } else {
                        Toast.makeText(getContext(), R.string.failed_fetch_route_data, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<RouteFromServer> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });


        btnUpdateRouteDetails = view.findViewById(R.id.btnUpdateRouteDetails);
        btnUpdateRouteDetails.setOnClickListener(view1 -> {
            Button btnCancelRoute;
            Button btnUpdateRoute;
            TextView updateRouteDialogTitle;
            EditText edtUpdateRouteName, edtUpdateRouteNo, edtUpdateRouteDesc;
            LinearLayout linearLayoutContactNo, linearLayoutAlterNo, linearLayoutRouteNo;
            TextView txtUpdateRouteName, txtUpdateRouteNo, txtUpdateRouteDesc;

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

            txtUpdateRouteName = dialog.findViewById(R.id.txtUpdateDialogName);
            txtUpdateRouteDesc = dialog.findViewById(R.id.txtUpdateDialogAddress);
            txtUpdateRouteNo = dialog.findViewById(R.id.txtUpdateDialogRouteNo);

            edtUpdateRouteName = dialog.findViewById(R.id.edtUpdateDialogName);
            edtUpdateRouteDesc = dialog.findViewById(R.id.edtUpdateDialogAddress);
            edtUpdateRouteNo = dialog.findViewById(R.id.edtUpdateDialogRouteNo);

            linearLayoutRouteNo = dialog.findViewById(R.id.linearLayoutRouteNo);
            linearLayoutContactNo = dialog.findViewById(R.id.linearLayoutContactNo);
            linearLayoutAlterNo = dialog.findViewById(R.id.linearLayoutAlterNo);

            btnCancelRoute = dialog.findViewById(R.id.btnActionCancel);
            btnUpdateRoute = dialog.findViewById(R.id.btnActionUpdate);
            updateRouteDialogTitle = dialog.findViewById(R.id.tv_update_dialog_title);

            linearLayoutRouteNo.setVisibility(View.VISIBLE);
            linearLayoutContactNo.setVisibility(View.GONE);
            linearLayoutAlterNo.setVisibility(View.GONE);

            //TODO set the values in the runtime
            txtUpdateRouteName.setText(getString(R.string.title_route_name));
            txtUpdateRouteDesc.setText(getString(R.string.title_route_desc));
            txtUpdateRouteNo.setText(getString(R.string.title_route_No));

            //todo to set the values in editText runtime
            edtUpdateRouteName.setHint(getString(R.string.hint_enter_route_name));
            edtUpdateRouteDesc.setHint(getString(R.string.hint_enter_route_desc));
            edtUpdateRouteNo.setHint(getString(R.string.hint_enter_route_no));

            edtUpdateRouteName.setText(routeFromServer.getRouteName());
            edtUpdateRouteDesc.setText(routeFromServer.getRouteDesc());
            edtUpdateRouteNo.setText(routeFromServer.getRouteNo());

            updateRouteDialogTitle.setText(String.format(getString(R.string.str_update_route_title), routeFromServer.getRouteNo()));

            btnCancelRoute.setOnClickListener(view2 -> dialog.dismiss());
            btnUpdateRoute.setOnClickListener(view2 -> {
                RouteFromServer updateRouteFromServer = new RouteFromServer(
                        edtUpdateRouteName.getText().toString(),
                        edtUpdateRouteNo.getText().toString(),
                        edtUpdateRouteDesc.getText().toString()
                        );
                Call<RouteFromServer> updateRouteList = routeService.updateRoute(routeId, updateRouteFromServer);
                updateRouteList.enqueue(new Callback<RouteFromServer>() {
                    @Override
                    public void onResponse(@NonNull Call<RouteFromServer> call, @NonNull Response<RouteFromServer> response) {
                        if (response.isSuccessful()) {
                            RouteFromServer updateRoute = response.body();
                            Toast.makeText(getContext(), R.string.success_update_route, Toast.LENGTH_SHORT).show();
                            routeFromServer = updateRoute;
                            dialog.dismiss();
                        }

                    }
                    @Override
                    public void onFailure(@NonNull Call<RouteFromServer> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            });
            dialog.show();
        });



            deleteRouteBtn = view.findViewById(R.id.btnDeleteRoute);
        deleteRouteBtn.setOnClickListener(view1 -> {
            Button btnCancel;
            Button deleteBtn;
            TextView dialogTitle;
            TextView dialogDesc;
            TextView deleteConfirmation;

            Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_design);

            btnCancel = dialog.findViewById(R.id.btn_action_cancel);
            deleteBtn = dialog.findViewById(R.id.btn_action_delete);
            dialogTitle = dialog.findViewById(R.id.tv_delete_dialog_title);
            dialogDesc = dialog.findViewById(R.id.tv_delete_dialog_desc);
            deleteConfirmation = dialog.findViewById(R.id.tv_delete_dialog_confirmation_msg);

            dialogTitle.setText(String.format(getString(R.string.title_delete_dialog), routeFromServer.getRouteNo()));
            dialogDesc.setText(R.string.msg_delete_route_desc);
            deleteConfirmation.setText(R.string.msg_delete_route_confirmation);

            btnCancel.setOnClickListener(view2 -> dialog.dismiss());
            deleteBtn.setOnClickListener(view2 -> {
                Call<ResponseBody> deleteRoute = routeService.deleteRoute(routeId);
                deleteRoute.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), R.string.msg_delete_route_success, Toast.LENGTH_SHORT).show();
                            if (masterInfoListener != null) {
                                masterInfoListener.onBackToPreviousScreen();
                            }
                        } else {
                            Toast.makeText(getContext(), R.string.no_route_found, Toast.LENGTH_SHORT).show();

                        }
                    }


                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();

                    }
                });
                dialog.dismiss();
            });
            dialog.show();
        });

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle(routeNo + " Route");
        }
        return view;
    }

}