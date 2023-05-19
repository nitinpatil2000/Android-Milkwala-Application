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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.ProductListViewPerSupplierAdapter;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFromServer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductListPerSupplierFragment extends Fragment {
    private final int supplierId;
    private String supplierName;
    ProductListViewPerSupplierAdapter productListViewPerSupplierAdapter;
    RecyclerView productListPerSupplierRecyclerView;
//    ArrayList<ProductDetails> productDetailsList;
    ArrayList<ProductFromServer>productFromServers;
    TextView addProductTxt;
    EditText searchProductDetails;
    private MasterInfoListener listener;
    private OnItemSelected onItemSelected;


    public ProductListPerSupplierFragment(int supplierId, String supplierName) {
        // Required empty public constructor
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public void setListener(MasterInfoListener listener) {
        this.listener = listener;
    }

    public void setOnItemSelected(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list_per_supplier, container, false);

        productListPerSupplierRecyclerView = view.findViewById(R.id.productDetailRecylerView);
        productListPerSupplierRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
        LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
        productListPerSupplierRecyclerView.setLayoutAnimation(animationController);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        ProductService productService = retrofit.create(ProductService.class);

        Call<List<ProductFromServer>> getProductsFromSupplier = productService.getProductsBySupplierId(supplierId);
        getProductsFromSupplier.enqueue(new Callback<List<ProductFromServer>>() {
            @Override
            public void onResponse(Call<List<ProductFromServer>> call, Response<List<ProductFromServer>> response) {
                if(response.isSuccessful()){
                    List<ProductFromServer> productFromServers = response.body();
                    if(productFromServers == null || productFromServers.isEmpty()){
                        Toast.makeText(getContext(), R.string.empty_product_list, Toast.LENGTH_SHORT).show();
                    }else{
                        productListViewPerSupplierAdapter = new ProductListViewPerSupplierAdapter(getContext(), (ArrayList<ProductFromServer>) productFromServers, onItemSelected);
                        productListPerSupplierRecyclerView.setAdapter(productListViewPerSupplierAdapter);
                        productListPerSupplierRecyclerView.scheduleLayoutAnimation();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductFromServer>> call, Throwable t) {
                t.printStackTrace();

            }
        });



//        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
//        productDetailsList = (ArrayList<ProductDetails>) myDbHelper.productDetailsDto().getProductBySupplierId(supplierId);
//
//        for (int i = 0; i < productDetailsList.size(); i++) {
//            productListViewPerSupplierAdapter = new ProductListViewPerSupplierAdapter(getContext(), productDetailsList, onItemSelected);
//            productListPerSupplierRecyclerView.setAdapter(productListViewPerSupplierAdapter);
//        }

        addProductTxt = view.findViewById(R.id.addProductTxt);
        addProductTxt.setOnClickListener(view1 -> listener.addNewProduct(supplierId));

        searchProductDetails = view.findViewById(R.id.searchProdutctDetails);
        searchProductDetails.clearFocus();
        searchProductDetails.addTextChangedListener(new TextWatcher() {
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

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle(supplierName + " Products");
        }

        return view;
    }

    private void filter(String text) {
//        ArrayList<ProductFromServer> filterProductDetails = new ArrayList<>();
//        for (ProductFromServer productDetails : productFromServers) {
//            if (productDetails.getProductName().toLowerCase().contains(text.toLowerCase())) {
//                filterProductDetails.add(productDetails);
//            }
//        }
//
//        productListViewPerSupplierAdapter.filteredList(filterProductDetails);
    }
}

