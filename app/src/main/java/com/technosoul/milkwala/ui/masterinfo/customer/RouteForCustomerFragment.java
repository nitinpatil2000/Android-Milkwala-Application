package com.technosoul.milkwala.ui.masterinfo.customer;

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
import com.technosoul.milkwala.adapters.RouteListForCustomerAdapter;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.route.RouteFromServer;
import com.technosoul.milkwala.ui.masterinfo.route.RouteService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RouteForCustomerFragment extends Fragment {
    private  MasterInfoListener masterInfoListener;
    private OnItemSelected onItemSelected;
    RouteListForCustomerAdapter routeListForCustomerAdapter;
    EditText searchRouteForCustomer;

    public RouteForCustomerFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_route_for_customer, container, false);
        RecyclerView routeForCustomerRecyclerView = view.findViewById(R.id.recycler_route_list_for_customer);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        routeForCustomerRecyclerView.setLayoutManager(gridLayoutManager);
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        routeForCustomerRecyclerView.setLayoutAnimation(animationController);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        RouteService routeService = retrofit.create(RouteService.class);
        Call<List<RouteFromServer>> routeListForCustomer = routeService.getAllRoutes();
        routeListForCustomer.enqueue(new Callback<List<RouteFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<RouteFromServer>> call, @NonNull Response<List<RouteFromServer>> response) {
                if (response.isSuccessful()) {
                    List<RouteFromServer> routeListForCustomerServer = response.body();
                    if (routeListForCustomerServer == null || routeListForCustomerServer.isEmpty()) {
                        Toast.makeText(getContext(), R.string.empty_route_list, Toast.LENGTH_SHORT).show();
                    } else {
                        routeListForCustomerAdapter = new RouteListForCustomerAdapter(getContext(), (ArrayList<RouteFromServer>) routeListForCustomerServer, onItemSelected);
                        routeForCustomerRecyclerView.setAdapter(routeListForCustomerAdapter);
                        routeForCustomerRecyclerView.scheduleLayoutAnimation();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.failed_get_route_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RouteFromServer>> call, @NonNull Throwable t) {

            }
        });

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle("Routes For Customer");
        }

        return view;
    }
}