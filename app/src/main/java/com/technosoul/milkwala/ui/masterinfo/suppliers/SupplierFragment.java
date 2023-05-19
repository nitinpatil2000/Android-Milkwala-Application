package com.technosoul.milkwala.ui.masterinfo.suppliers;

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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.SupplierRecyclerViewAdapter;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SupplierFragment extends Fragment {
    private SupplierRecyclerViewAdapter recyclerViewAdapter;
    private EditText searchSupplier;
//    private ArrayList<Supplier> supplierList = new ArrayList<>();
    private ArrayList<SupplierFromServer> supplierListFromServer = new ArrayList<>();
    private MasterInfoListener masterInfoListener;
    private OnItemSelected onItemSelected;

    public SupplierFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supplier, container, false);

        Button btnAddNewSupplier = view.findViewById(R.id.btn_add_new_supplier);

        btnAddNewSupplier.setOnClickListener(view1 -> {
            if (masterInfoListener != null) {
                masterInfoListener.addNewSupplier();
            }
        });

        RecyclerView supplierRecyclerView = view.findViewById(R.id.recyclerView_supplier_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        supplierRecyclerView.setLayoutManager(gridLayoutManager);
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        supplierRecyclerView.setLayoutAnimation(animationController);
//        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.item_animation_fall_down);
//        supplierRecyclerView.setLayoutAnimation(animationController);

//        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());

//        supplierListFromServer = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();
//        if (supplierListFromServer == null || supplierListFromServer.size() == 0) {
//            Toast.makeText(getContext(), R.string.empty_supplier_list, Toast.LENGTH_SHORT).show();
//        } else {
//            recyclerViewAdapter = new SupplierRecyclerViewAdapter(getContext(), supplierListFromServer, onItemSelected);
//            supplierRecyclerView.setAdapter(recyclerViewAdapter);
//        }



        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        SupplierService supplierService = retrofit.create(SupplierService.class);

        Call<List<SupplierFromServer>> call = supplierService.getAllSuppliers();
        call.enqueue(new Callback<List<SupplierFromServer>>() {
            @Override
            public void onResponse(Call<List<SupplierFromServer>> call, Response<List<SupplierFromServer>> response) {
                if(response.isSuccessful()){
                    List<SupplierFromServer> supplierEntityList = response.body();
//                    supplierListFromServer = (ArrayList<SupplierFromServer>) response.body();
                    if(supplierEntityList == null || supplierEntityList.isEmpty()){
                        Toast.makeText(getContext(), R.string.empty_supplier_list, Toast.LENGTH_SHORT).show();
                    }else{
//                        MyDbHelper myDbHelper = MyDbHelper.getDB(getContext());
//                        ArrayList<Supplier> supplierArrayList = new ArrayList<>();
//                        for(SupplierFromServer supplier : supplierListFromServer){
//                            supplierArrayList.add(new Supplier(supplier.getSupplierId(),
//                                    supplier.getSupplierName(),
//                                    supplier.getSupplierAddress(),
//                                    supplier.getSupplierNumber(),
//                                    supplier.getSupplierAltNumber()));
//                        }
//                        myDbHelper.supplierDao().addSupplier(supplierArrayList);
                        SupplierRecyclerViewAdapter supplierRecyclerViewAdapter = new SupplierRecyclerViewAdapter(getContext(), (ArrayList<SupplierFromServer>) supplierEntityList,onItemSelected);
                        supplierRecyclerView.setAdapter(supplierRecyclerViewAdapter);
                        supplierRecyclerView.scheduleLayoutAnimation();
                    }
                }else{
                    Toast.makeText(getContext(), R.string.failed_get_supplier_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SupplierFromServer>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        //search the supplier
        searchSupplier = view.findViewById(R.id.searchSupplier);
        searchSupplier.clearFocus();
        searchSupplier.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        if(getActivity()!= null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle("Supplier");
        }
        return view;
    }

    private void filter(String text) {
        ArrayList<SupplierFromServer> filterList = new ArrayList<>();
        for (SupplierFromServer supplier : supplierListFromServer) {
            if (supplier.getSupplierName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(supplier);
            }
        }
        //check if the adapter is not null before calling filteredList method
        if (recyclerViewAdapter != null) {
            recyclerViewAdapter.filteredList(filterList);
        }
    }



    public void setListener(MasterInfoListener listener) {
        this.masterInfoListener = listener;
    }

    public void setOnItemSelectedListener(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }


}