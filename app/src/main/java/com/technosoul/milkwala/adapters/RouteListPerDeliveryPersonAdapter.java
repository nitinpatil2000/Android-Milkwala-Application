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
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.route.RouteFromServer;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;


public class RouteListPerDeliveryPersonAdapter extends RecyclerView.Adapter<RouteListPerDeliveryPersonAdapter.ViewHolder> {
    private final OnItemSelected onItemSelected;
    Context context;
    ArrayList<RouteFromServer> routeFromServers;

    public RouteListPerDeliveryPersonAdapter(Context context, ArrayList<RouteFromServer> routeFromServers, OnItemSelected onItemSelected) {
        this.context = context;
        this.routeFromServers = routeFromServers;
        this.onItemSelected = onItemSelected;
    }

    @NonNull
    @Override
    public RouteListPerDeliveryPersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.route_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteListPerDeliveryPersonAdapter.ViewHolder holder, int position) {
        holder.routeName.setText(routeFromServers.get(position).getRouteName());
        holder.routeNo.setText(routeFromServers.get(position).getRouteNo());
    }

    @Override
    public int getItemCount() {
        return routeFromServers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView routeName, routeNo;
        ImageView routeImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            routeName = itemView.findViewById(R.id.txtRouteNameForDelivery);
            routeNo = itemView.findViewById(R.id.txtRouteNoForDelivery);
            routeImg = itemView.findViewById(R.id.routeImg);

            routeImg.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    RouteFromServer routeClickedItem = routeFromServers.get(position);
                    int routeId = routeClickedItem.getRouteId();
                    String routeNo = routeClickedItem.getRouteNo();
                    if(onItemSelected != null){
                        onItemSelected.onItemClicked(Constants.SELECTED_TYPE_ROUTE, routeId, routeNo, null);
                    }
                }
            });
        }
    }
}
