package com.technosoul.milkwala.ui.masterinfo.products;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.utils.Constants;

import java.util.ArrayList;

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
    ProductFromServer productFromServer;
    TextView tvWholesaleRate;
    TextView tvSupplierRate;
    private final int productId;
    private final String productDetailsName;

    private MasterInfoListener masterInfoListener;

    FloatingActionButton btnUpdateProductDetails;

    public ProductDetailsViewFragment(int productId, String productDetailsName) {
        this.productId = productId;
        this.productDetailsName = productDetailsName;
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
            public void onResponse(@NonNull Call<ProductFromServer> call, @NonNull Response<ProductFromServer> response) {
                if (response.isSuccessful()) {
                    productFromServer = response.body();
                    if (productFromServer != null) {
                        tvProductName.setText(productFromServer.getProductName());
                        tvProductUnit.setText(productFromServer.getProductUnit());
                        tvProductType.setText(productFromServer.getProductType());
                        tvProductMrp.setText(String.valueOf(productFromServer.getProductMrpRetailerRate()));
                        tvSupplierRate.setText(String.valueOf(productFromServer.getProductSupplierRate()));
                        tvWholesaleRate.setText(String.valueOf(productFromServer.getProductWholesaleRate()));
                    } else {
                        Toast.makeText(getContext(), R.string.failed_get_product_data, Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductFromServer> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });


        btnUpdateProductDetails = view.findViewById(R.id.btnUpdateProductDetails);
        btnUpdateProductDetails.setOnClickListener(view1 -> {
            Button btnCancelUpdateProduct;
            Button btnUpdateProduct;
            TextView updateProductDialogTitle;
            EditText edtUpdateProductName, edtUpdateProductSupplierRate, edtUpdateProductWholesaleRate, edtUpdateProductMrpRate;
            Spinner updateProductUnit, updateProductType;
            LinearLayout linearUpdateDialogAddress, linearUpdateDialogType, linearUpdateDialogUnit, linearUpdateDialogMrp;
            TextView txtUpdateProductName, txtUpdateProductSupplierRate, txtUpdateProductWholesaleRate;


            Dialog dialog = new Dialog(getContext());
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.update_dialog_design);
            Window window = dialog.getWindow();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());

            // Set the width to match the parent
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);


            txtUpdateProductName = dialog.findViewById(R.id.txtUpdateDialogName);
            txtUpdateProductSupplierRate = dialog.findViewById(R.id.txtUpdateDialogNumber);
            txtUpdateProductWholesaleRate = dialog.findViewById(R.id.txtUpdateDialogAlterNo);

            edtUpdateProductName = dialog.findViewById(R.id.edtUpdateDialogName);
            edtUpdateProductSupplierRate = dialog.findViewById(R.id.edtUpdateDialogNumber);
            edtUpdateProductWholesaleRate = dialog.findViewById(R.id.edtUpdateDialogAltNo);
            edtUpdateProductMrpRate = dialog.findViewById(R.id.edtUpdateDialogMrp);
            updateProductType = dialog.findViewById(R.id.updateDialogTypeSpinner);
            updateProductUnit = dialog.findViewById(R.id.updateDialogUnitSpinner);

            linearUpdateDialogAddress = dialog.findViewById(R.id.linearUpdateDialogAddress);
            linearUpdateDialogType = dialog.findViewById(R.id.linearUpdateDialogType);
            linearUpdateDialogUnit = dialog.findViewById(R.id.linearUpdateDialogUnit);
            linearUpdateDialogMrp = dialog.findViewById(R.id.linearUpdateDialogMrpRate);

            updateProductDialogTitle = dialog.findViewById(R.id.tv_update_dialog_title);
            btnCancelUpdateProduct = dialog.findViewById(R.id.btnActionCancel);
            btnUpdateProduct = dialog.findViewById(R.id.btnActionUpdate);

            linearUpdateDialogAddress.setVisibility(View.GONE);
            linearUpdateDialogType.setVisibility(View.VISIBLE);
            linearUpdateDialogUnit.setVisibility(View.VISIBLE);
            linearUpdateDialogMrp.setVisibility(View.VISIBLE);

            //TODO set the values in the runtime
            txtUpdateProductName.setText(getString(R.string.str_name));
            txtUpdateProductSupplierRate.setText(getString(R.string.str_supplier_rate));
            txtUpdateProductWholesaleRate.setText(getString(R.string.str_wholesale_rate));

            //todo to set the values in editText runtime
            edtUpdateProductName.setHint(getString(R.string.hint_enter_product_name));
            edtUpdateProductSupplierRate.setHint(getString(R.string.hint_enter_product_supp_rate));
            edtUpdateProductWholesaleRate.setHint(getString(R.string.hint_enter_product_wholesale_rate));
            edtUpdateProductMrpRate.setHint(getString(R.string.hint_enter_product_mrp_rate));

            edtUpdateProductName.setText(productFromServer.getProductName());
            ArrayList<String> typeProductNamesArrayList = new ArrayList<>();
            typeProductNamesArrayList.add(Constants.MILK);
            typeProductNamesArrayList.add(Constants.BUTTERMILK);
            typeProductNamesArrayList.add(Constants.PANEER);
            typeProductNamesArrayList.add(Constants.GHEE);
            typeProductNamesArrayList.add(Constants.BUTTER);
            typeProductNamesArrayList.add(Constants.CURD);
            ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, typeProductNamesArrayList);
            updateProductType.setAdapter(typeAdapter);
            int defaultProductName = typeProductNamesArrayList.indexOf(productFromServer.getProductType());
            updateProductType.setSelection(defaultProductName);

            ArrayList<String> typesProductUnitArrayList = new ArrayList<>();
            typesProductUnitArrayList.add(Constants.UNIT_LITRE_1);
            typesProductUnitArrayList.add(Constants.UNIT_LITRE_2);
            typesProductUnitArrayList.add(Constants.UNIT_ML_500);
            typesProductUnitArrayList.add(Constants.UNIT_ML_250);
            typesProductUnitArrayList.add(Constants.UNIT_ML_100);
            typesProductUnitArrayList.add(Constants.UNIT_KG_1);
            typesProductUnitArrayList.add(Constants.UNIT_KG_2);
            typesProductUnitArrayList.add(Constants.UNIT_GRAM_500);
            typesProductUnitArrayList.add(Constants.UNIT_GRAM_250);
            typesProductUnitArrayList.add(Constants.UNIT_GRAM_200);
            typesProductUnitArrayList.add(Constants.UNIT_GRAM_100);
            typesProductUnitArrayList.add(Constants.UNIT_GRAM_50);
            typesProductUnitArrayList.add(Constants.UNIT_PACKETS);
            typesProductUnitArrayList.add(Constants.CARET);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, typesProductUnitArrayList);
            updateProductUnit.setAdapter(adapter);
            int defaultProductUnit = typesProductUnitArrayList.indexOf(productFromServer.getProductUnit());
            updateProductUnit.setSelection(defaultProductUnit);

            edtUpdateProductSupplierRate.setText(String.valueOf(productFromServer.getProductSupplierRate()));
            edtUpdateProductWholesaleRate.setText(String.valueOf(productFromServer.getProductWholesaleRate()));
            edtUpdateProductMrpRate.setText(String.valueOf(productFromServer.getProductMrpRetailerRate()));
            updateProductDialogTitle.setText(String.format(getString(R.string.str_update_title), productFromServer.getProductName()));

            btnCancelUpdateProduct.setOnClickListener(view2 -> dialog.dismiss());

            btnUpdateProduct.setOnClickListener(view2 -> {
                ProductFromServer updateProductFromServer = new ProductFromServer(
                        edtUpdateProductName.getText().toString(),
                        updateProductType.getSelectedItem().toString(),
                        updateProductUnit.getSelectedItem().toString(),
                        Float.parseFloat(edtUpdateProductSupplierRate.getText().toString()),
                        Float.parseFloat(edtUpdateProductWholesaleRate.getText().toString()),
                        Float.parseFloat(edtUpdateProductMrpRate.getText().toString())
                );
                Call<ProductFromServer> updateProductList = productService.updateProduct(productId, updateProductFromServer);
                updateProductList.enqueue(new Callback<ProductFromServer>() {
                    @Override
                    public void onResponse(@NonNull Call<ProductFromServer> call, @NonNull Response<ProductFromServer> response) {
                        if (response.isSuccessful()) {
                            ProductFromServer updateProduct = response.body();
                            Toast.makeText(getContext(), R.string.success_update_product, Toast.LENGTH_SHORT).show();
                            productFromServer = updateProduct;
                            dialog.dismiss();
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ProductFromServer> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            });
            dialog.show();
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

            dialogTitle.setText(String.format(getString(R.string.title_delete_dialog), productFromServer.getProductName()));
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
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), R.string.msg_delete_product_success, Toast.LENGTH_SHORT).show();
                            if (masterInfoListener != null) {
                                masterInfoListener.onBackToPreviousScreen();
                            }
                        } else {
                            Toast.makeText(getContext(), R.string.no_product_found, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            });
            dialog.show();
        });

        if (getActivity() != null) {
            ((MasterInfoActivity) getActivity()).setActionBarTitle(productDetailsName);
        }

        return view;

    }

}