package com.technosoul.milkwala;

import android.app.slice.Slice;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.ui.MainActivity;
import com.technosoul.milkwala.ui.masterinfo.ApiRetrofitService;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFromServer;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierService;

import java.util.ArrayList;
import java.util.List;

import ir.mahozad.android.PieChart;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminDashboardFragment extends Fragment {
    TextView totalCustomers;
    TextView totalProducts;
    TextView totalSuppliers;
    TextView todayTotalWaste;
    TextView todayTotalSale;

    private PieChart pieChart;

    public AdminDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        ArrayList<Customer> customerList = (ArrayList<Customer>) myDbHelper.customerDao().getAllCustomers();
        int numCustomers = customerList.size();
        totalCustomers = getView().findViewById(R.id.totalCustomers);
        totalCustomers.setText(getString(R.string.total_customers, numCustomers));
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_admin_dashboard, container, false);

        totalSuppliers = view.findViewById(R.id.totalSuppliers);
        totalProducts = view.findViewById(R.id.totalProducts);
        todayTotalSale = view.findViewById(R.id.today_total_sale);
        todayTotalWaste = view.findViewById(R.id.today_total_waste);





        // Get total Suppliers
//        ArrayList<Supplier> supplierList = (ArrayList<Supplier>)myDbHelper.supplierDao().getAllSuppliers();
//        int noSuppliers = supplierList.size();
//        totalSuppliers.setText(getString(R.string.total_suppliers, noSuppliers));

        // Get total Products
        // TODO: need to add the products & fetch it.
//        totalProducts.setText(getString(R.string.total_products, noSuppliers));

        ApiRetrofitService supplierRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = supplierRetrofitService.getRetrofit();
        SupplierService supplierService = retrofit.create(SupplierService.class);
        Call<List<SupplierFromServer>> call = supplierService.getAllSuppliers();
        call.enqueue(new Callback<List<SupplierFromServer>>() {
            @Override
            public void onResponse(Call<List<SupplierFromServer>> call, Response<List<SupplierFromServer>> response) {
                if(response.isSuccessful()){
                    List<SupplierFromServer> supplierList = response.body();
                    if(supplierList != null && !supplierList.isEmpty()){
                        int numSuppliers = supplierList.size();
                        totalSuppliers.setText(getString(R.string.total_suppliers, numSuppliers));
                        totalProducts.setText(getString(R.string.total_products, numSuppliers));
                    }else{

                    }
                }else{
                    Toast.makeText(getContext(), R.string.no_supplier_found, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SupplierFromServer>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        // Get total Customers


//        List<SliceValue> pieData = new ArrayList<>();

//        pieData.add(new SliceValue(15, Color.BLUE));
//        pieData.add(new SliceValue(25, Color.GRAY));
//        pieData.add(new SliceValue(10, Color.RED));
//        pieData.add(new SliceValue(60, Color.MAGENTA));
//
//        PieChartData pieChartData = new PieChartData(pieData);
//        pieChartView.setPieChartData(pieChartData);

//        List<PieChart.Slice> slices = new ArrayList<>();
//        slices.add(new PieChart.Slice(0.2f, Color.BLUE));
//        slices.add(new PieChart.Slice(0.4f, Color.MAGENTA));
//        slices.add(new PieChart.Slice(0.3f, Color.YELLOW));
//        slices.add(new PieChart.Slice(0.1f, Color.CYAN));
//        pieChart.setSlices(slices);

// Assuming PieChartView is called from another method or class
//        public void PieChartView() {
//            ViewModifier modifier = Modifier.fillMaxSize();
//            AndroidViewFactory factory = new AndroidViewFactory() {
//                @Override
//                public View create(Context context) {
//                    PieChart pieChart = new PieChart(context);
//                    List<PieChart.Slice> slices = new ArrayList<>();
//                    slices.add(new PieChart.Slice(0.2f, Color.BLUE));
//                    slices.add(new PieChart.Slice(0.4f, Color.MAGENTA));
//                    slices.add(new PieChart.Slice(0.3f, Color.YELLOW));
//                    slices.add(new PieChart.Slice(0.1f, Color.CYAN));
//                    pieChart.setSlices(slices);
//                    return pieChart;
//                }
//            };
//            AndroidViewUpdate<LinearLayout> update = new AndroidViewUpdate<LinearLayout>() {
//                @Override
//                public void update(PieChart view) {
//                    // View's been inflated or state read in this block has been updated
//                    // Add logic here if necessary
//                }
//            };
//            AndroidView<LinearLayout, PieChart> androidView = new AndroidView<>(modifier, factory, update);
//        }

        pieChart = view.findViewById(R.id.pie_chart);
        Typeface typefaceObject = Typeface.create("ubuntu", Typeface.NORMAL);
        List<PieChart.Slice> slices = new ArrayList<>();
        slices.add(new PieChart.Slice(
                0.3f, Color.BLUE, Color.RED, "C - 24%", Color.WHITE, 50f,
                typefaceObject, null, 35f, 25f, null, 0.6f,
                null, null, null, "",    Color.GREEN, null,
                R.drawable.suppliericon, null, 8f, Color.BLUE, Color.GRAY,
                16f, 0.8f, 1.0f, 0.2f
        ));

        slices.add(new PieChart.Slice(
                0.2f, Color.RED, Color.CYAN, "K - 15%", Color.BLACK, 50f,
                null, null, null, null, null, 0.7f,
                null, null, null, "", null, null,
                null, null, null, Color.BLUE, null,
                null, null, 1.0f, 0.2f
        ));
//
        slices.add(new PieChart.Slice(
                0.2f, Color.GREEN, Color.DKGRAY, "G - 40%", Color.WHITE, 50f,
                null, null, null, null, null, 0.6f,
                null, null, null, "", null, null,
                null, null, null, Color.BLUE, null,
                null, null, 1.0f, 0.2f
        ));
//
        slices.add(new PieChart.Slice(
                0.3f, Color.TRANSPARENT, Color.LTGRAY, "A - 21%", Color.BLACK, 50f,
                null, null, 30f, 5f, null, 0.6f,
                null, null, null, "", null, null,
                null, null, null, Color.BLUE, null,
                null, null, 1.0f, 0.2f
        ));





        pieChart.setSlices(slices);



//        TODO SET THE TITLE
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("DashBoard");
        }


        
        return view;
    }

//    private void applyAnimation(TextView textView, int animationId) {
//        Animation animation = AnimationUtils.loadAnimation(getActivity(), animationId);
//        textView.setAnimation(animation);
//    }
}