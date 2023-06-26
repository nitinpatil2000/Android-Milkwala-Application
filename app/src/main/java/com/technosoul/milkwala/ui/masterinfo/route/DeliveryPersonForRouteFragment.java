package com.technosoul.milkwala.ui.masterinfo.route;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.DeliveryPersonListForRouteAdapter;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.DeliveryFromServer;
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.DeliveryPersonService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeliveryPersonForRouteFragment extends Fragment {

    DeliveryPersonListForRouteAdapter deliveryPersonListForRouteAdapter;
    EditText searchDeliveryPerson;
    private MasterInfoListener masterInfoListener;
    private OnItemSelected onItemSelected;

    public DeliveryPersonForRouteFragment() {
        // Required empty public constructor
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener){
        this.masterInfoListener = masterInfoListener;
    }

    public void setOnItemSelectedListener(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delivery_person_for_route, container, false);
        RecyclerView deliveryForRouteRecyclerView = view.findViewById(R.id.recyclerView_delivery_for_route_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        deliveryForRouteRecyclerView.setLayoutManager(gridLayoutManager);
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        deliveryForRouteRecyclerView.setLayoutAnimation(animationController);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        DeliveryPersonService deliveryPersonService = retrofit.create(DeliveryPersonService.class);
        Call<List<DeliveryFromServer>> deliveryForRouteList = deliveryPersonService.getAllDeliveryPersons();
        deliveryForRouteList.enqueue(new Callback<List<DeliveryFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<DeliveryFromServer>> call, @NonNull Response<List<DeliveryFromServer>> response) {
                if (response.isSuccessful()) {
                    List<DeliveryFromServer> deliveryForRouteEntityList = response.body();
                    if (deliveryForRouteEntityList == null || deliveryForRouteEntityList.isEmpty()) {
                        Toast.makeText(getContext(), R.string.empty_delivery_boy_list_message, Toast.LENGTH_SHORT).show();
                    } else {
                        deliveryPersonListForRouteAdapter = new DeliveryPersonListForRouteAdapter(getContext(), (ArrayList<DeliveryFromServer>) deliveryForRouteEntityList, onItemSelected);
                        deliveryForRouteRecyclerView.setAdapter(deliveryPersonListForRouteAdapter);
                        deliveryForRouteRecyclerView.scheduleLayoutAnimation();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.failed_get_supplier_data, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(@NonNull Call<List<DeliveryFromServer>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle("Delivery Person For Routes");
        }
        return view;
    }
}