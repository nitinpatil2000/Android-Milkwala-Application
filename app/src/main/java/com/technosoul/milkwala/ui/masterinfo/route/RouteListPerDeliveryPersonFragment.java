package com.technosoul.milkwala.ui.masterinfo.route;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.RouteListPerDeliveryPersonAdapter;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RouteListPerDeliveryPersonFragment extends Fragment {

    private final int deliveryPersonId;
    private final String deliveryPersonName;
    RouteListPerDeliveryPersonAdapter routeListPerDeliveryPersonAdapter;
    RecyclerView routeRecyclerView;
    TextView tvEmptyRouteList;
    Button addRouteBtn;
    EditText searchRoutes;
    private MasterInfoListener listener;
    private OnItemSelected onItemSelected;


    public RouteListPerDeliveryPersonFragment(int deliveryPersonId, String deliveryPersonName) {
        this.deliveryPersonId = deliveryPersonId;
        this.deliveryPersonName = deliveryPersonName;
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    public void setOnItemSelected(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_list_per_delivery_person, container, false);
        tvEmptyRouteList = view.findViewById(R.id.tv_empty_route_list);
        routeRecyclerView = view.findViewById(R.id.routeRecyclerView);
        routeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        routeRecyclerView.setLayoutAnimation(animationController);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        RouteService routeService = retrofit.create(RouteService.class);

        Call<List<RouteFromServer>> getRouteForDelivery = routeService.getRouteByDelivertId(deliveryPersonId);
        getRouteForDelivery.enqueue(new Callback<List<RouteFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<RouteFromServer>> call, @NonNull Response<List<RouteFromServer>> response) {
                if (response.isSuccessful()) {
                    List<RouteFromServer> getAllRouteFromServers = response.body();
                    if (getAllRouteFromServers == null || getAllRouteFromServers.isEmpty()) {
                        Toast.makeText(getContext(), R.string.empty_route_list, Toast.LENGTH_SHORT).show();
                        tvEmptyRouteList.setVisibility(View.VISIBLE);
                    } else {
                        tvEmptyRouteList.setVisibility(View.GONE); // Show the empty text message
                        routeListPerDeliveryPersonAdapter = new RouteListPerDeliveryPersonAdapter(getContext(), (ArrayList<RouteFromServer>) getAllRouteFromServers, onItemSelected);
                        routeRecyclerView.setAdapter(routeListPerDeliveryPersonAdapter);
                        routeRecyclerView.scheduleLayoutAnimation();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.failed_get_route_data, Toast.LENGTH_SHORT).show();
                    tvEmptyRouteList.setVisibility(View.VISIBLE); // Show the empty text message if data retrieval failed
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RouteFromServer>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });


        addRouteBtn = view.findViewById(R.id.addRouteBtn);
        addRouteBtn.setOnClickListener(view1 -> listener.addNewRoute(deliveryPersonId));

        searchRoutes = view.findViewById(R.id.searchRoutes);
        searchRoutes.clearFocus();
        searchRoutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                filter(editable.toString());
            }
        });

        if (getActivity() != null) {
            ((MasterInfoActivity) getActivity()).setActionBarTitle(deliveryPersonName + " Routes");
        }

        return view;
    }
}
