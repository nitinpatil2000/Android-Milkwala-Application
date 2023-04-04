package com.example.milkapplication.delivery;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.milkapplication.R;

import java.util.ArrayList;

public class DeliverViewAdapter extends RecyclerView.Adapter<DeliverViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Deliver> deliver;

    public DeliverViewAdapter(Context context, ArrayList<Deliver> deliver){
        this.context = context;
        this.deliver = deliver;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delivery_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.deliverTxt.setText(deliver.get(position).deliverTxt);
        holder.deliverSubText.setText(deliver.get(position).deliverSubText);

    }

    @Override
    public int getItemCount() {
        return deliver.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView deliverTxt, deliverSubText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deliverTxt = itemView.findViewById(R.id.deliverTxt);
            deliverSubText = itemView.findViewById(R.id.deliverSubText);
        }
    }
}
