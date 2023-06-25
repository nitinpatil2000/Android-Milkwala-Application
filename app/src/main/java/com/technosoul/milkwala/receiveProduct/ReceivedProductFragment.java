package com.technosoul.milkwala.receiveProduct;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.ReceivedProductAdapter;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.Supplier;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;
import com.technosoul.milkwala.ui.masterinfo.products.ProductService;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFromServer;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReceivedProductFragment extends Fragment {
    Spinner receivedSpinner;
    View line;
    ReceivedProductAdapter receivedProductAdapter;
    RecyclerView receivedRecyclerView;
    private List<SupplierFromServer> supplierFromServers;
    int productId;
    Button saveReceiveProduct;
    private int supplierId;

    private MasterInfoListener masterInfoListener;

    TextView dateTextView;
    Button dateButton;
    DatePickerDialog datePickerDialog;

    public ReceivedProductFragment() {
//        this.supplierId = supplierId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_received_product, container, false);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Today's Received Products");

        receivedSpinner = view.findViewById(R.id.receivedSpinner);
//        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
//        List<Supplier> suppliersList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();

        // TODO: GET THE ALL SUPPLIERS LIST
        ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = apiRetrofitService.getRetrofit();
        SupplierService supplierService = retrofit.create(SupplierService.class);
        Call<List<SupplierFromServer>> getAllSuppliers = supplierService.getAllSuppliers();
        getAllSuppliers.enqueue(new Callback<List<SupplierFromServer>>() {
            @Override
            public void onResponse(@NonNull Call<List<SupplierFromServer>> call, @NonNull Response<List<SupplierFromServer>> response) {
                if (response.isSuccessful()) {
                    supplierFromServers = response.body();
                    if (supplierFromServers != null) {
                        List<String> supplierNames = new ArrayList<>();
                        supplierNames.add("No Selected");

                        for (SupplierFromServer supplier : supplierFromServers) {
                            supplierNames.add(supplier.getSupplierName());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, supplierNames);
                        receivedSpinner.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SupplierFromServer>> call, @NonNull Throwable t) {
//                Toast.makeText(getContext(), R.string.network_error + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });


        receivedRecyclerView = view.findViewById(R.id.receivedRecyclerView);
        saveReceiveProduct = view.findViewById(R.id.saveReceivedProduct);
        line = view.findViewById(R.id.line);


        receivedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem.equals("No Selected")) {
                    receivedRecyclerView.setVisibility(View.GONE);
                    saveReceiveProduct.setVisibility(View.GONE);
                    line.setVisibility(View.GONE);
                } else {
                    receivedRecyclerView.setVisibility(View.VISIBLE);
                    saveReceiveProduct.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);

                    for (SupplierFromServer supplier : supplierFromServers) {
                        if (supplier != null && TextUtils.equals(supplier.getSupplierName(), selectedItem)) {
                            supplierId = supplier.getSupplierId();

                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                            receivedRecyclerView.setLayoutManager(gridLayoutManager);
                            Animation slideInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.item_animation_fall_down);
                            LayoutAnimationController animationController = new LayoutAnimationController(slideInAnimation);
                            receivedRecyclerView.setLayoutAnimation(animationController);

                            ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
                            Retrofit retrofit = apiRetrofitService.getRetrofit();

                            ProductService productService = retrofit.create(ProductService.class);
                            Call<List<ProductFromServer>> getProductBySupplierId = productService.getProductsBySupplierId(supplierId);
                            getProductBySupplierId.enqueue(new Callback<List<ProductFromServer>>() {
                                @Override
                                public void onResponse(@NonNull Call<List<ProductFromServer>> call, @NonNull Response<List<ProductFromServer>> response) {
                                    if (response.isSuccessful()) {
                                        List<ProductFromServer> productFromServers = response.body();
                                        if (productFromServers == null || productFromServers.isEmpty()) {
                                            Toast.makeText(getContext(), R.string.empty_product_list, Toast.LENGTH_SHORT).show();
                                        } else {
                                            receivedProductAdapter = new ReceivedProductAdapter(getContext(), (ArrayList<ProductFromServer>) productFromServers);
//                                            receivedProductAdapter.notifyDataSetChanged();
                                            receivedRecyclerView.setAdapter(receivedProductAdapter);
                                        }
                                    } else {
                                        Toast.makeText(getContext(), R.string.failed_get_product_data, Toast.LENGTH_SHORT).show();
                                    }
                                }


                                @Override
                                public void onFailure(@NonNull Call<List<ProductFromServer>> call, @NonNull Throwable t) {
                                    Toast.makeText(getContext(), "Failed to get products: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    t.printStackTrace();
                                }
                            });
                            break;
                        }
                    }

                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        dateTextView = view.findViewById(R.id.dateTextView);
        dateButton =  view.findViewById(R.id.dateButton);
        dateButton.setOnClickListener(view1 -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    dateTextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });


        saveReceiveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String selectedDate = dateTextView.getText().toString();
//                if (TextUtils.isEmpty(selectedDate)) {
//                    Toast.makeText(getContext(), R.string.err_empty_supplier_order_date, Toast.LENGTH_SHORT).show();
//                    dateTextView.requestFocus();
//                    return;
//                }
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    Date date = dateFormat.parse(selectedDate);
//                    System.out.println(date);
                    // Date format is valid, proceed with saving the data
                    // Rest of your code for saving the data goes here
                    HashMap<EditText, ProductFromServer> editTextMap = new HashMap<>();
                    ArrayList<ProductFromServer> productFromServers = receivedProductAdapter.getProductFromServers();
                    for (ProductFromServer getProductFromServer : productFromServers) {
                        int position = productFromServers.indexOf(getProductFromServer);
                        RecyclerView.ViewHolder viewHolder = receivedRecyclerView.findViewHolderForAdapterPosition(position);
                        if (viewHolder != null) {
                            View itemView = viewHolder.itemView;
                            EditText editQuantity = itemView.findViewById(R.id.editQuantity);
                            String quantity = editQuantity.getText().toString();
                            if (TextUtils.isEmpty(quantity)) {
                                Toast.makeText(getContext(), "Please enter the quantity for item: " + getProductFromServer.getProductName(), Toast.LENGTH_SHORT).show();
                                editQuantity.requestFocus();
                                return;
                            }

                            int receivedQuantity = Integer.parseInt(quantity);
                            editTextMap.put(editQuantity, getProductFromServer);
                            productId = getProductFromServer.getProductId();

                            SupplierOrder supplierOrder = new SupplierOrder();
                            supplierOrder.setSupplierId(supplierId);
                            supplierOrder.setProductId(productId);
                            supplierOrder.setOrderedQuantity(receivedQuantity);
//                            supplierOrder.setSupplierOrderDate(date);

                            ApiRetrofitService apiRetrofitService = new ApiRetrofitService();
                            Retrofit retrofit = apiRetrofitService.getRetrofit();
                            SupplierOrderService supplierOrderService = retrofit.create(SupplierOrderService.class);
                            Call<SupplierOrder> createSupplierOrder = supplierOrderService.createSupplierOrder(supplierOrder);
                            createSupplierOrder.enqueue(new Callback<SupplierOrder>() {
                                @Override
                                public void onResponse(@NonNull Call<SupplierOrder> call, @NonNull Response<SupplierOrder> response) {
                                    SupplierOrder supplierOrderList = response.body();
                                    if (supplierOrderList != null) {
                                        Toast.makeText(getContext(), R.string.msg_supplier_order_success, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), R.string.error_text, Toast.LENGTH_SHORT).show();
                                        Request request = call.request();
                                        try {
                                            Buffer buffer = new Buffer();
                                            Objects.requireNonNull(request.body()).writeTo(buffer);
                                            String requestBody = buffer.readUtf8();
                                            Log.d("API Request Body", requestBody);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<SupplierOrder> call, @NonNull Throwable t) {
                                    Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
                                    t.printStackTrace();
                                    // Log the request body
                                }
                            });
                        }
                    }

//                } catch (Exception e) {
//
//                }
            }
        });


//        if(getActivity() != null){
//            ((ReceivedProductActivity)getActivity()).setActionBarTitle("Today's Received Product");
//        }
        return view;
    }


    public void setListener(MasterInfoListener listener) {
        this.masterInfoListener = listener;
    }
}