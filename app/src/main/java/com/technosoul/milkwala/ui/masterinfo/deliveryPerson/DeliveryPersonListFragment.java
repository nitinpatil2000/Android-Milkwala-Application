package com.technosoul.milkwala.ui.masterinfo.deliveryPerson;

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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.DeliverPersonListViewAdapter;
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

public class DeliveryPersonListFragment extends Fragment {
    DeliverPersonListViewAdapter deliverViewAdapter;
    Button btnAddDeliveryPerson;
    EditText searchDeliveryPerson;
    ArrayList<DeliveryFromServer> deliveryListFromServers;
    private MasterInfoListener masterInfoListener;
    private OnItemSelected onItemSelected;
    TextView tv_empty_delivery_list;

    public DeliveryPersonListFragment() {
        // Required empty public constructor
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    public void setOnItemSelected(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deliverperson_list, container, false);

        tv_empty_delivery_list = view.findViewById(R.id.tv_empty_delivery_list);
        RecyclerView deliverRecyclerView = view.findViewById(R.id.recyclerView_delivery_person_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        deliverRecyclerView.setLayoutManager(gridLayoutManager);
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        deliverRecyclerView.setLayoutAnimation(animationController);


        //TODO FOR LOCAL DATABASE
        // MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        // deliveryPersonList = (ArrayList<DeliveryPerson>) myDbHelper.deliveryDetailDao().getAllDeliveryBoys();
        // for (int i = 0; i < deliveryPersonList.size(); i++) {
        // deliverViewAdapter = new DeliverPersonListViewAdapter(getContext(), deliveryPersonList, onItemSelected);
        // deliverRecyclerView.setAdapter(deliverViewAdapter);
        // }

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        DeliveryPersonService deliveryPersonService = retrofit.create(DeliveryPersonService.class);

        Call<List<DeliveryFromServer>> deliveryListFromServerCall = deliveryPersonService.getAllDeliveryPersons();
        deliveryListFromServerCall.enqueue(new Callback<List<DeliveryFromServer>>() {
            @Override
            public void onResponse(Call<List<DeliveryFromServer>> call, Response<List<DeliveryFromServer>> response) {
                if(response.isSuccessful()){
                    List<DeliveryFromServer> deliveryPersonList = response.body();
                    if (deliveryPersonList == null || deliveryPersonList.size() == 0) {
                        Toast.makeText(getContext(), R.string.empty_delivery_boy_list_message, Toast.LENGTH_SHORT).show();
                        tv_empty_delivery_list.setVisibility(View.VISIBLE); // Show the empty text message

                    } else {
                        tv_empty_delivery_list.setVisibility(View.GONE); // Show the empty text message
                        deliverViewAdapter = new DeliverPersonListViewAdapter(getContext(), deliveryPersonList, onItemSelected);
                        deliverRecyclerView.setAdapter(deliverViewAdapter);
                        deliverRecyclerView.scheduleLayoutAnimation();
                    }
                }else{
                        Toast.makeText(getContext(), R.string.failed_get_delivery_data, Toast.LENGTH_SHORT).show();
                        tv_empty_delivery_list.setVisibility(View.VISIBLE); // Show the empty text message if data retrieval failed

                    }
            }

            @Override
            public void onFailure(Call<List<DeliveryFromServer>> call, Throwable t) {
                t.printStackTrace();
            }
        });



        btnAddDeliveryPerson = view.findViewById(R.id.btn_add_new_supplier);
        btnAddDeliveryPerson.setOnClickListener(view1 -> {
            if (masterInfoListener != null) {
                masterInfoListener.addNewDeliveryPerson();
            }
        });



        //TODO SET THE TITLE
        if(getActivity()!= null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle(getString(R.string.title_delivery_boys));
        }

        //TODO FOR SEARCH THE DELIVERY BOY
//        searchDeliveryPerson = view.findViewById(R.id.searchDeliveryPerson);
//        searchDeliveryPerson.clearFocus();
//        searchDeliveryPerson.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                filter(editable.toString());
//            }
//        });

        return view;
    }



//    private void filter(String text) {
//        ArrayList<DeliveryFromServer> filterDeliveryPerson = new ArrayList<>();
//        for (DeliveryFromServer deliveryPerson : deliveryListFromServers) {
//            if (deliveryPerson.getDeliveryPersonName().toLowerCase().contains(text.toLowerCase())) {
//                filterDeliveryPerson.add(deliveryPerson);
//            }
//        }
//        deliverViewAdapter.filteredList(filterDeliveryPerson);
//    }
}