package com.technosoul.milkwala.ui.masterinfo.route;

import android.os.Bundle;
import android.text.TextUtils;
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

public class AddNewRouteFragment extends Fragment {
    EditText edtRouteName, edtRouteNo, edtRouteDesc;
    Button addNewRouteBtn;
    private MasterInfoListener masterInfoListener;
    private final int deliveryPersonId;


    public AddNewRouteFragment(int deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_new_route, container, false);
        edtRouteName = view.findViewById(R.id.edtRouteName);
        edtRouteNo = view.findViewById(R.id.edtRouteNo);
        edtRouteDesc = view.findViewById(R.id.edtRouteDesc);
        addNewRouteBtn = view.findViewById(R.id.addNewRouteBtn);

        addNewRouteBtn.setOnClickListener(view1 -> {
            String routeName = edtRouteName.getText().toString();
            if (TextUtils.isEmpty(routeName)) {
                Toast.makeText(getContext(), R.string.err_empty_route_name, Toast.LENGTH_SHORT).show();
                return;
            }

            String routeNo = edtRouteNo.getText().toString();
            if (TextUtils.isEmpty(routeNo)) {
                Toast.makeText(getContext(), R.string.err_empty_route_no, Toast.LENGTH_SHORT).show();
                return;
            }

            String routeDesc = edtRouteDesc.getText().toString();
            if (TextUtils.isEmpty(routeDesc)) {
                Toast.makeText(getContext(), R.string.err_empty_route_desc, Toast.LENGTH_SHORT).show();
                return;
            }

            ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
            Retrofit retrofit = apiRetrofitService.getRetrofit();
            RouteService routeService = retrofit.create(RouteService.class);
            RouteFromServer routeFromServer = new RouteFromServer();

            routeFromServer.setRouteName(routeName);
            routeFromServer.setRouteNo(routeNo);
            routeFromServer.setRouteDesc(routeDesc);
            routeFromServer.setDeliveryPersonId(deliveryPersonId);

            Call<RouteFromServer> createRouteForDeliveryPerson = routeService.createRouteForDeliveryBoy(routeFromServer);
            createRouteForDeliveryPerson.enqueue(new Callback<RouteFromServer>() {
                @Override
                public void onResponse(@NonNull Call<RouteFromServer> call, @NonNull Response<RouteFromServer> response) {
                    RouteFromServer routeListFromServer = response.body();
                    if (routeListFromServer != null) {
                        Toast.makeText(getContext(), R.string.msg_route_added_success, Toast.LENGTH_SHORT).show();
                        if (masterInfoListener != null) {
                            masterInfoListener.onBackToPreviousScreen();
                        }
                    } else {
                        Toast.makeText(getContext(), R.string.failed_add_route_data, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RouteFromServer> call, @NonNull Throwable t) {
                    t.printStackTrace();
                }
            });


        });

        if(getActivity() !=  null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle("Add New Routes ");
        }
        return  view;
    }
}