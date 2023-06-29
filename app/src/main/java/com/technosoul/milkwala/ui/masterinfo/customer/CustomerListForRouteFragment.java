package com.technosoul.milkwala.ui.masterinfo.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.CustomerListForRouteAdapter;
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

public class CustomerListForRouteFragment extends Fragment {
    private final int routeId;
    private final String routeNo;
    CustomerListForRouteAdapter customerListForRouteAdapter;

    private  MasterInfoListener listener;
    private OnItemSelected onItemSelected;

    public CustomerListForRouteFragment(int routeId, String routeNo) {
        this.routeId = routeId;
        this.routeNo = routeNo;
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
        View view = inflater.inflate(R.layout.fragment_customer_list_for_route, container, false);
        EditText edtCustomerSearch = view.findViewById(R.id.edt_customer_search);
        RecyclerView recyclerViewCustomerList = view.findViewById(R.id.recyclerview_customer_list);
        TextView tvEmptyCustomerList = view.findViewById(R.id.tv_empty_customer_list);

        recyclerViewCustomerList.setLayoutManager(new LinearLayoutManager(getContext()));
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        recyclerViewCustomerList.setLayoutAnimation(animationController);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        CustomerService customerService = retrofit.create(CustomerService.class);

        Call<List<CustomerFromServer>> getCustomerForRoute = customerService.getAllCustomerByRouteId(routeId);
        getCustomerForRoute.enqueue(new Callback<List<CustomerFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<CustomerFromServer>> call, @NonNull Response<List<CustomerFromServer>> response) {
                if(response.isSuccessful()){
                    List<CustomerFromServer> customerListFromServers = response.body();
                    if(customerListFromServers == null || customerListFromServers.isEmpty()){
                        tvEmptyCustomerList.setVisibility(View.VISIBLE);
                    }else{
                        tvEmptyCustomerList.setVisibility(View.GONE); // Show the empty text message
                        customerListForRouteAdapter = new CustomerListForRouteAdapter(getContext(), (ArrayList<CustomerFromServer>) customerListFromServers, onItemSelected);
                        recyclerViewCustomerList.setAdapter(customerListForRouteAdapter);
                        recyclerViewCustomerList.scheduleLayoutAnimation();
                    }
                }else{
                    tvEmptyCustomerList.setVisibility(View.VISIBLE); // Show the empty text message if data retrieval failed
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CustomerFromServer>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

        Button addNewCustomerBtn = view.findViewById(R.id.btn_add_new_customer);
        addNewCustomerBtn.setOnClickListener(view1 -> listener.addNewCustomer(routeId));

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle(routeNo + " Routes");
        }
        return view;
    }
}