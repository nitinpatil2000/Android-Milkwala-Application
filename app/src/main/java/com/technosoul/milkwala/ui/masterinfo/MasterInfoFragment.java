package com.technosoul.milkwala.ui.masterinfo;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.technosoul.milkwala.db.DeliveryPerson;
import com.technosoul.milkwala.home.ImageAdapter;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.db.Supplier;
import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFromServer;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MasterInfoFragment extends Fragment {
    LinearLayout llSuppliers;
    TextView totalSuppliersSubText;
    LinearLayout llProducts;
    TextView totalProductsSubText;
    LinearLayout llDeliveryBoys;
    TextView totalDeliveryBoysSubText;
    LinearLayout llCustomers;
    TextView totalCustomersSubText;
    LinearLayout llRouters;
    TextView totalRouterSubText;

    private CardView supplierCardView;
    private CardView productCardView;
    private CardView deliveryCardView;
    private CardView customerCardView;
    private CardView routerCardView;


    private MasterInfoListener masterInfoListener;
    private ViewPager viewPager;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 3500;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 4000; // time period in milliseconds between successive task executions.
    private final int SCROLL_DURATION = 800; // Duration in milliseconds for page scroll animation

//    ArrayList<Supplier> suppliers = new ArrayList<>();

    public MasterInfoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_master_info, container, false);

        totalSuppliersSubText = view.findViewById(R.id.masterinfo_supplier_subtext);
        llSuppliers = view.findViewById(R.id.ll_masterinfo_suppliers);

        totalProductsSubText = view.findViewById(R.id.masterinfo_product_subtext);
        llProducts = view.findViewById(R.id.ll_masterinfo_products);

        totalDeliveryBoysSubText = view.findViewById(R.id.masterinfo_deliveryboy_subtext);
        llDeliveryBoys = view.findViewById(R.id.ll_masterinfo_deliveryboys);

        totalCustomersSubText = view.findViewById(R.id.masterinfo_customer_subtext);
        llCustomers = view.findViewById(R.id.ll_masterinfo_customers);

        llRouters = view.findViewById(R.id.ll_master_info_router);
        totalRouterSubText = view.findViewById(R.id.masterinfo_router_subtext);

        ApiRetrofitService supplierRetrofitService = new ApiRetrofitService();
        Retrofit retrofit = supplierRetrofitService.getRetrofit();
        SupplierService supplierService = retrofit.create(SupplierService.class);
        Call<List<SupplierFromServer>> call = supplierService.getAllSuppliers();
        call.enqueue(new Callback<List<SupplierFromServer>>() {
            @Override
            public void onResponse(Call<List<SupplierFromServer>> call, Response<List<SupplierFromServer>> response) {
                if (response.isSuccessful()) {
                    List<SupplierFromServer> supplierList = response.body();
                    if (supplierList != null && !supplierList.isEmpty()) {
                        int numSuppliers = supplierList.size();
                        totalSuppliersSubText.setText(getString(R.string.total_supplier_sub_text, numSuppliers));
                        totalProductsSubText.setText(getString(R.string.total_product_sub_text, numSuppliers));
                    }
                } else {
                    Toast.makeText(getContext(), R.string.no_supplier_found, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SupplierFromServer>> call, Throwable t) {

            }
        });

        //TODO GET ALL SUPPLIER IN ROOM DB
        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        ArrayList<Supplier> supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();
        int numSuppliers = supplierList.size();
        totalSuppliersSubText.setText(getString(R.string.total_supplier_sub_text, numSuppliers));
        llSuppliers.setOnClickListener(view1 -> masterInfoListener.onSupplierClick());

        int numProducts = supplierList.size();
        totalProductsSubText.setText(getString(R.string.total_deliver_sub_text, numProducts));
        llProducts.setOnClickListener(view12 -> masterInfoListener.onProductClick());

        ArrayList<DeliveryPerson> deliveryPersonList = (ArrayList<DeliveryPerson>) myDbHelper.deliveryDetailDao().getAllDeliveryBoys();
        int numDelivers = deliveryPersonList.size();
        totalDeliveryBoysSubText.setText(getString(R.string.total_deliver_sub_text, numDelivers));
        llDeliveryBoys.setOnClickListener(view13 -> masterInfoListener.onDeliveryPersonClick());

        ArrayList<Customer> customerList = (ArrayList<Customer>) myDbHelper.customerDao().getAllCustomers();
        int numCustomers = customerList.size();
        totalCustomersSubText.setText(getString(R.string.total_customer_sub_text, numCustomers));

        llCustomers.setOnClickListener(view14 -> masterInfoListener.onCustomerClick());

        //viewpager
        viewPager = view.findViewById(R.id.viewPagerImage);
        ImageAdapter adapter = new ImageAdapter(getActivity());
        viewPager.setAdapter(adapter);

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == adapter.getCount() - 1) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                viewPager.setCurrentItem(currentPage, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);


        if (getActivity() != null) {
            ((MasterInfoActivity) getActivity()).setActionBarTitle("Master Info");
        }


        supplierCardView = view.findViewById(R.id.supplier_card_view);
        productCardView = view.findViewById(R.id.product_card_view);
        customerCardView = view.findViewById(R.id.customer_card_view);
        deliveryCardView = view.findViewById(R.id.delivery_card_view);
        routerCardView = view.findViewById(R.id.router_card_view);

        // Animate card views when activity is opened
        animateCardView(supplierCardView, 0);
        animateCardView(productCardView, 100);
        animateCardView(customerCardView, 200);
        animateCardView(deliveryCardView, 300);
        animateCardView(routerCardView, 400);


        supplierCardView.setOnClickListener(view1 -> {
            animateCardView(supplierCardView, 0);

        });

        productCardView.setOnClickListener(view1 -> {
            animateCardView(productCardView, 0);
        });

        customerCardView.setOnClickListener(view1 -> {
            animateCardView(customerCardView, 0);
        });

        deliveryCardView.setOnClickListener(view1 -> {
            animateCardView(deliveryCardView, 0);
        });

        routerCardView.setOnClickListener(view1 -> {
            animateCardView(routerCardView, 0);
        });
        return view;
    }

    private void animateCardView(CardView cardView, int delay) {
        cardView.setScaleX(0f);
        cardView.setScaleY(0f);
        cardView.setAlpha(0f);

        cardView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(cardView, View.SCALE_X, 1f);
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(cardView, View.SCALE_Y, 1f);
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(cardView, View.ALPHA, 1f);
                scaleXAnimator.setDuration(500);
                scaleYAnimator.setDuration(500);
                alphaAnimator.setDuration(500);
                scaleXAnimator.start();
                scaleYAnimator.start();
                alphaAnimator.start();
            }
        }, delay);
    }


    public void setListener(MasterInfoListener listener) {
        this.masterInfoListener = listener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }


}