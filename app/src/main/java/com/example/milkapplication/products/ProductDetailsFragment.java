package com.example.milkapplication.products;

import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milkapplication.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ProductDetailsFragment extends Fragment {
    ArrayList<ProductDetails> productDetailsList = new ArrayList<>();
    ProductViewDetailsAdapter productViewDetailsAdapter;
    RecyclerView productDetailRecyclerView;
    ImageView productImg;

    TextView addProductTxt;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);


        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("name");
            String mrp = bundle.getString("mrp");
            String unit = bundle.getString("unit");
            //add the item in the recyclerView.
            ProductDetails productDetails = new ProductDetails(name, mrp, unit);
            productDetailsList.add(productDetails);
            productViewDetailsAdapter.notifyDataSetChanged();
        }


        productDetailRecyclerView = view.findViewById(R.id.productDetailRecylerView);

        productDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productDetailsList = new ArrayList<>();
        productDetailsList.add(new ProductDetails("Cow Milk", "2 Litre", "Rs 112"));
        productDetailsList.add(new ProductDetails("Cow Milk", "1 Litre", "Rs 112"));
        productDetailsList.add(new ProductDetails("Cow Milk", "500 ml", "Rs 112"));
        productDetailsList.add(new ProductDetails("Full Cream Milk", "1 Litre", "Rs 112"));
        productDetailsList.add(new ProductDetails("Butter Milk", "500 ml", "Rs 112"));
        productDetailsList.add(new ProductDetails("Butter", "1 kg", "Rs 112"));
        productDetailsList.add(new ProductDetails("Curd", "500 ml", "Rs 112"));

        productViewDetailsAdapter = new ProductViewDetailsAdapter(getContext(), productDetailsList);
        productDetailRecyclerView.setAdapter(productViewDetailsAdapter);

        //swipe to delete this item.
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(productDetailRecyclerView);


        addProductTxt = view.findViewById(R.id.addProductTxt);
        addProductTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProductFragment addProductFragment = new AddProductFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.container, addProductFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

//        public void removeItem(int position) {
//            productDetailsList.remove(position);
//            productViewDetailsAdapter.notifyItemRemoved(position);
//        }

        return view;
    }

    public void removeItem(int position) {
        productDetailsList.remove(position);
        productViewDetailsAdapter.notifyItemRemoved(position);

    }



//    String deletedList = null;
//    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            int position = viewHolder.getAdapterPosition();
//            switch (direction) {
//                case ItemTouchHelper.LEFT:
//                    deletedList = productDetailsList.get(position);
//                    productDetailsList.remove(position);
//                    productViewDetailsAdapter.notifyItemRemoved(position);
//                    Snackbar.make(productDetailRecyclerView, deleteList, Snackbar.LENGTH_LONG)
//                            .setAction("Undo", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    productDetailsList.add(position, deleteList);
//                                    productViewDetailsAdapter.notifyItemInserted(position);
//                                }
//                            })
//
//
//                    break;
//                case ItemTouchHelper.RIGHT:
//                    break;
//            }
//
//        }
//
//    };
}

