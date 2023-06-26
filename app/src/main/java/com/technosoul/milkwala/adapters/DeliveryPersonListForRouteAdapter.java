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
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.DeliveryFromServer;
import com.technosoul.milkwala.ui.masterinfo.route.RouteFromServer;
import com.technosoul.milkwala.ui.masterinfo.route.RouteService;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DeliveryPersonListForRouteAdapter extends RecyclerView.Adapter<DeliveryPersonListForRouteAdapter.ViewHolder> {
    private final OnItemSelected onItemSelected;
    Context context;
    ArrayList<DeliveryFromServer> deliveryFromServers;

    public DeliveryPersonListForRouteAdapter(Context context, ArrayList<DeliveryFromServer> deliveryFromServers, OnItemSelected onItemSelected) {
        this.context = context;
        this.deliveryFromServers = deliveryFromServers;
        this.onItemSelected = onItemSelected;
    }


    @NonNull
    @Override
    public DeliveryPersonListForRouteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delivery_for_route_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryPersonListForRouteAdapter.ViewHolder holder, int position) {
        DeliveryFromServer selectedDeliveryPerson = deliveryFromServers.get(position);
        int deliveryPersonId = selectedDeliveryPerson.getDeliveryPersonId();
        holder.deliveryPersonForRouteName.setText(deliveryFromServers.get(position).getDeliveryPersonName());

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        RouteService routeService = retrofit.create(RouteService.class);

        Call<List<RouteFromServer>> getRouteForDeliveryBoy = routeService.getRouteByDelivertId(deliveryPersonId);
        getRouteForDeliveryBoy.enqueue(new Callback<List<RouteFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<RouteFromServer>> call, @NonNull Response<List<RouteFromServer>> response) {
                if (response.isSuccessful()) {
                    List<RouteFromServer> routeListFromServer = response.body();
                    if (routeListFromServer != null && !routeListFromServer.isEmpty()) {
                        int numCounterRoutes = routeListFromServer.size();
                        holder.deliveryPersonForRoutesCounter.setText(String.format(context.getString(R.string.routes_of_delivery_person), numCounterRoutes));
                    }
                }
            }


            @Override
            public void onFailure(@NonNull Call<List<RouteFromServer>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public int getItemCount() {
        return deliveryFromServers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView deliveryPersonForRouteName, deliveryPersonForRoutesCounter;
        ImageView deliveryImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deliveryPersonForRouteName = itemView.findViewById(R.id.deliveryPersonForRouteName);
            deliveryPersonForRoutesCounter = itemView.findViewById(R.id.deliveryPersonForRoutes);
            deliveryImg = itemView.findViewById(R.id.deliverImg);

            deliveryImg.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    DeliveryFromServer selectedDeliveryPerson = deliveryFromServers.get(position);
                    int deliveryPersonId = selectedDeliveryPerson.getDeliveryPersonId();
                    String deliveryPersonName = selectedDeliveryPerson.getDeliveryPersonName();
                    if (onItemSelected != null) {
                        onItemSelected.onItemClicked(Constants.SELECTED_DELIVERY_PERSON_FOR_ROUTE_LIST, deliveryPersonId, deliveryPersonName, null);
                    }
                }
            });

        }
    }
}
