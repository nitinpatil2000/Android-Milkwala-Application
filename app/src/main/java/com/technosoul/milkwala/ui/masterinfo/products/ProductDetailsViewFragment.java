package com.technosoul.milkwala.ui.masterinfo.products;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.ProductDetails;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetailsViewFragment extends Fragment {
    TextView tvProductName;
    TextView tvProductUnit;
    TextView tvProductType;
    TextView tvProductMrp;
    Button deleteNewProductBtn;
    String viewProductName;
    String viewProductUnit;
    ProductFromServer productFromServer;
    TextView tvWholesaleRate;
    TextView tvSupplierRate;
    private final int productId;
    private String productDetailsName;

    private MasterInfoListener masterInfoListener;

    public ProductDetailsViewFragment(int productId, String productDetailsName) {
        this.productId = productId;
        this.productDetailsName = productDetailsName;
        // Required empty public constructor
    }

    public void setMasterInfoListener(MasterInfoListener masterInfoListener) {
        this.masterInfoListener = masterInfoListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_view_details, container, false);



//        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
//        productDetails = myDbHelper.productDetailsDto().getProductById(productDetailsId);

        tvProductName = view.findViewById(R.id.tv_product_name);
        tvProductUnit = view.findViewById(R.id.tv_product_unit);
        tvProductType = view.findViewById(R.id.tv_product_type);
        tvProductMrp = view.findViewById(R.id.tv_product_mrp);
        tvSupplierRate = view.findViewById(R.id.tv_supplier_rate);
        tvWholesaleRate = view.findViewById(R.id.tv_wholesale_rate);
        deleteNewProductBtn = view.findViewById(R.id.btnDeleteProduct);

        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        ProductService productService = retrofit.create(ProductService.class);
        Call<ProductFromServer> getProductsFromProductId = productService.getProductsByProductId(productId);
        getProductsFromProductId.enqueue(new Callback<ProductFromServer>() {
            @Override
            public void onResponse(Call<ProductFromServer> call, Response<ProductFromServer> response) {
                if(response.isSuccessful()){
                    ProductFromServer productFromServer = response.body();
                    if(productFromServer  != null) {
                        tvProductName.setText(productFromServer.getProductName());
                        tvProductUnit.setText(productFromServer.getProductUnit());
                        tvProductType.setText(productFromServer.getProductType());
                        tvProductMrp.setText(String.valueOf(productFromServer.getProductMrpRetailerRate()));
                        tvSupplierRate.setText(String.valueOf(productFromServer.getProductSupplierRate()));
                        tvWholesaleRate.setText(String.valueOf(productFromServer.getProductWholesaleRate()));
                    }else{
                        Toast.makeText(getContext(), R.string.failed_get_product_data, Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ProductFromServer> call, Throwable t) {
                t.printStackTrace();
            }
        });



        deleteNewProductBtn.setOnClickListener(view1 -> {
            Button btnCancel;
            Button deleteBtn;
            TextView dialogTitle;
            TextView dialogDesc;
            TextView deleteConfirmation;

            Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_design);

            btnCancel = dialog.findViewById(R.id.btn_action_cancel);
            deleteBtn = dialog.findViewById(R.id.btn_action_delete);
            dialogTitle = dialog.findViewById(R.id.tv_delete_dialog_title);
            dialogDesc = dialog.findViewById(R.id.tv_delete_dialog_desc);
            deleteConfirmation = dialog.findViewById(R.id.tv_delete_dialog_confirmation_msg);

            dialogTitle.setText(String.format(getString(R.string.title_delete_dialog), viewProductName));
            dialogDesc.setText(R.string.msg_delete_product_desc);
            deleteConfirmation.setText(R.string.msg_delete_product_confirmation);

            btnCancel.setOnClickListener(view11 -> dialog.dismiss());

            deleteBtn.setOnClickListener(view112 -> {
//                myDbHelper.productDetailsDto().deleteProductById(productDetailsId);
//                Toast.makeText(getContext(), R.string.msg_delete_product_success, Toast.LENGTH_SHORT).show();
//                if (masterInfoListener != null) {
//                    masterInfoListener.onBackToPreviousScreen();
//                }
                Call<ResponseBody> deleteProductById = productService.deleteProduct(productId);
                deleteProductById.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), R.string.msg_delete_product_success, Toast.LENGTH_SHORT).show();
                            if (masterInfoListener != null) {
                                masterInfoListener.onBackToPreviousScreen();
                            }
                        }else{
                            Toast.makeText(getContext(),R.string.no_product_found, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            });
            dialog.show();
        });

        if(getActivity() != null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle(productDetailsName);
        }

        return view;

    }

}