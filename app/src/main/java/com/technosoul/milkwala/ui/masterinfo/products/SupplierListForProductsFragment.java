package com.technosoul.milkwala.ui.masterinfo.products;

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
import com.technosoul.milkwala.adapters.SupplierListViewForProductAdapter;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFromServer;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SupplierListForProductsFragment extends Fragment {
    //    ArrayList<Product> products = new ArrayList<>();
    SupplierListViewForProductAdapter supplierListViewForProductAdapter;
    EditText searchProduct;
    //    ArrayList<Supplier> supplierList;
    ArrayList<SupplierFromServer> supplierFromServers;
    Button btnAddNewProduct;
    private MasterInfoListener masterInfoListener;
    private OnItemSelected onItemSelected;

    public SupplierListForProductsFragment() {
    }

    public void setListener(MasterInfoListener listener) {
        this.masterInfoListener = listener;
    }

    public void setOnItemSelectedListener(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplier_list_for_products, container, false);

        RecyclerView productRecyclerView = view.findViewById(R.id.recyclerView_product_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        productRecyclerView.setLayoutAnimation(animationController);

//        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
//        supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();
//        for (int i = 0; i < supplierList.size(); i++) {
//            supplierListViewForProductAdapter = new SupplierListViewForProductAdapter(getContext(), supplierList, onItemSelected);
//            productRecyclerView.setAdapter(supplierListViewForProductAdapter);
//        }

        ApiRetrofitService supplierRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = supplierRetrofitService.getRetrofit();
        SupplierService supplierService = retrofit.create(SupplierService.class);
        Call<List<SupplierFromServer>> call = supplierService.getAllSuppliers();
        call.enqueue(new Callback<List<SupplierFromServer>>() {
            @Override
            public void onResponse(Call<List<SupplierFromServer>> call, Response<List<SupplierFromServer>> response) {
                if (response.isSuccessful()) {
                    List<SupplierFromServer> supplierEntityList = response.body();
                    if (supplierEntityList == null || supplierEntityList.isEmpty()) {
                        Toast.makeText(getContext(), R.string.supplier_list_is_empty_in_product_list, Toast.LENGTH_SHORT).show();
                    } else {
                        supplierListViewForProductAdapter = new SupplierListViewForProductAdapter(getContext(), (ArrayList<SupplierFromServer>) supplierEntityList, onItemSelected);
                        productRecyclerView.setAdapter(supplierListViewForProductAdapter);
                        productRecyclerView.scheduleLayoutAnimation();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.failed_get_supplier_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SupplierFromServer>> call, Throwable t) {
                t.printStackTrace();
            }
        });


        searchProduct = view.findViewById(R.id.searchProduct);
        searchProduct.clearFocus();
        searchProduct.addTextChangedListener(new TextWatcher() {
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
            ((MasterInfoActivity) getActivity()).setActionBarTitle("Products");
        }
        return view;
    }

    private void filter(String text) {
//        ArrayList<SupplierFromServer> filterList = new ArrayList<>();
//        for (SupplierFromServer supplier : supplierFromServers) {
//            if (supplier.getSupplierName().toLowerCase().contains(text.toLowerCase())) {
//                filterList.add(supplier);
//            }
//        }
        //check if the adapter is not null before calling filteredList method
//        if (supplierListViewForProductAdapter != null) {
//            supplierListViewForProductAdapter.filteredList(filterList);
//        }
    }
}