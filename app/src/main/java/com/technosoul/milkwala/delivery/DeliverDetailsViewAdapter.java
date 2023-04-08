package com.technosoul.milkwala.delivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DeliverDetailsViewAdapter extends RecyclerView.Adapter<DeliverDetailsViewAdapter.ViewHolder> {
    Context context;
    ArrayList<DeliverDetails> deliverDetails;

    public DeliverDetailsViewAdapter(Context context, ArrayList<DeliverDetails> deliverDetails){
        this.context = context;
        this.deliverDetails = deliverDetails;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.deliver_details_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.deliverTxt.setText(deliver.get(position).deliverTxt);
//        holder.deliverSubText.setText(deliver.get(position).deliverSubText);
        holder.deliverName.setText(deliverDetails.get(position).getDeliveryName());

    }

    @Override
    public int getItemCount() {
        return deliverDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView deliverName;
      ImageView deliverImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deliverName = itemView.findViewById(R.id.deliverName);
            deliverImg = itemView.findViewById(R.id.deliverImg);

        }
    }
}
