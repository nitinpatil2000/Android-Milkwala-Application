package com.technosoul.milkwala.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.customer.CustomerFromServer;
import com.technosoul.milkwala.ui.masterinfo.customer.CustomerService;
import com.technosoul.milkwala.ui.masterinfo.route.RouteFromServer;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RouteListForCustomerAdapter extends RecyclerView.Adapter<RouteListForCustomerAdapter.ViewHolder> {
    private final OnItemSelected onItemSelected;
    Context context;
    ArrayList<RouteFromServer> routeFromServers;


    public RouteListForCustomerAdapter(Context context, ArrayList<RouteFromServer> routeFromServers, OnItemSelected onItemSelected) {
        this.context = context;
        this.routeFromServers = routeFromServers;
        this.onItemSelected = onItemSelected;
    }


    @NonNull
    @Override
    public RouteListForCustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.route_for_customer_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteListForCustomerAdapter.ViewHolder holder, int position) {
        RouteFromServer selectedRouteForCustomer = routeFromServers.get(position);
        int routeId = selectedRouteForCustomer.getRouteId();
        holder.txtRouteNoForCustomer.setText(routeFromServers.get(position).getRouteNo());

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        CustomerService customerService = retrofit.create(CustomerService.class);
//
        Call<List<CustomerFromServer>> getAllCustomerFromServer = customerService.getAllCustomerByRouteId(routeId);
        getAllCustomerFromServer.enqueue(new Callback<List<CustomerFromServer>>() {
            @Override
            public void onResponse(Call<List<CustomerFromServer>> call, Response<List<CustomerFromServer>> response) {
                if (response.isSuccessful()) {
                    List<CustomerFromServer> customerListFromServer = response.body();
                    if (customerListFromServer != null && !customerListFromServer.isEmpty()) {
                        int numCustomersList = customerListFromServer.size();
                        holder.txtRouteForCustomerCounter.setText(String.format(context.getString(R.string.route_of_customer), numCustomersList));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CustomerFromServer>> call, Throwable t) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return routeFromServers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtRouteNoForCustomer, txtRouteForCustomerCounter;
        ImageView routeImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRouteNoForCustomer = itemView.findViewById(R.id.txtRouteNoForCustomer);
            txtRouteForCustomerCounter = itemView.findViewById(R.id.txtRouteForCustomerCounter);
            routeImg = itemView.findViewById(R.id.routeForCustomerImg);

            routeImg.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    RouteFromServer selectedRouteFromServer = routeFromServers.get(position);
                    int routeId = selectedRouteFromServer.getRouteId();
                    String routeNo = selectedRouteFromServer.getRouteNo();
                    if(onItemSelected != null){
                            onItemSelected.onItemClicked(Constants.SELECTED_ROUTE_FOR_CUSTOMER_LIST, routeId, routeNo, null);
                    }
                }
            });
        }
    }
}
