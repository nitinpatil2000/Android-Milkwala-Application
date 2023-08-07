package com.technosoul.milkwala.ui.masterinfo.customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;
import com.technosoul.milkwala.ui.masterinfo.products.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AssignProductForCustomerFragment extends Fragment {
    private ProductListForCustomerAdapter productListForCustomerAdapter;
    MasterInfoListener masterInfoListener;
    private RecyclerView productListForCustomerRecyclerView;
    Button productSubmitForCustomerBtn;
    private final int customerId;

    List<ProductFromServer> productFromServers = new ArrayList<>();

    public AssignProductForCustomerFragment(int customerId) {
        this.customerId = customerId;
    }


    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assign_product_for_customer, container, false);

        productListForCustomerRecyclerView = view.findViewById(R.id.recycler_product_list_for_customer);

        productListForCustomerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        productListForCustomerRecyclerView.setLayoutAnimation(animationController);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        ProductService productService = retrofit.create(ProductService.class);

        Call<List<ProductFromServer>> productListForCustomer = productService.getAllProducts();

        productListForCustomer.enqueue(new Callback<List<ProductFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductFromServer>> call, @NonNull Response<List<ProductFromServer>> response) {
                System.out.println(response.body());

                if(response.isSuccessful()){
                    List<ProductFromServer> productListFromServer = response.body();
                    if(productListFromServer == null || productListFromServer.isEmpty()){
                        Toast.makeText(getContext(), R.string.empty_product_list, Toast.LENGTH_SHORT).show();
                    }else{
                        productListForCustomerAdapter = new ProductListForCustomerAdapter(getContext(), (ArrayList<ProductFromServer>) productListFromServer);
                        productListForCustomerRecyclerView.setAdapter(productListForCustomerAdapter);
                        productListForCustomerRecyclerView.scheduleLayoutAnimation();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductFromServer>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });


        productSubmitForCustomerBtn = view.findViewById(R.id.submitButton);

        productSubmitForCustomerBtn.setOnClickListener(view1 -> {
            List<Integer> checkedProductIds = productListForCustomerAdapter.getSelectedProductIds();

            CustomerService customerService = retrofit.create(CustomerService.class);
            ProductForCustomerServer productForCustomerServer = new ProductForCustomerServer();
            productForCustomerServer.setCustomerId(customerId);
            productForCustomerServer.setProductIds(checkedProductIds);

            Call<ProductForCustomerServer> productForCustomerServerCall = customerService.createProductForCustomer(productForCustomerServer);
            productForCustomerServerCall.enqueue(new Callback<ProductForCustomerServer>() {
                @Override
                public void onResponse(@NonNull Call<ProductForCustomerServer> call, @NonNull Response<ProductForCustomerServer> response) {
                    ProductForCustomerServer productListForCustomer = response.body();
                    if(productListForCustomer != null){
                        Toast.makeText(getContext(), R.string.msg_product_added_success, Toast.LENGTH_SHORT).show();
                        if (masterInfoListener != null) {
                            masterInfoListener.onBackToPreviousScreen();
                        }
                    } else {
                        Toast.makeText(getContext(), R.string.failed_get_product_data, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ProductForCustomerServer> call, @NonNull Throwable t) {
                 t.printStackTrace();
                }
            });
        });



        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle("Total Products");
        }

        return view;

    }
}