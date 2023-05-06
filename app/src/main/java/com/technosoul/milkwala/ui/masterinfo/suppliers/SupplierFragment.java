package com.technosoul.milkwala.ui.masterinfo.suppliers;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.technosoul.milkwala.adapters.SupplierRecyclerViewAdapter;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.R;
import com.technosoul.milkwala.supplier.Supplier;
import com.technosoul.milkwala.supplier.SupplierDetailsFragment;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;

import java.util.ArrayList;

public class SupplierFragment extends Fragment {
    RecyclerView supplierRecyclerView;
    SupplierRecyclerViewAdapter recyclerViewAdapter;
    int position;
    EditText searchSupplier;
    ArrayList<Supplier> supplierList;

    private MasterInfoListener masterInfoListener;

    Button btnAddNewSupplier;
    int supplierId;

    public SupplierFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supplier, container, false);

        btnAddNewSupplier = view.findViewById(R.id.btn_add_new_supplier);

        btnAddNewSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (masterInfoListener != null) {
                    masterInfoListener.addNewSupplier();
                }
            }
        });

//        supplierRecyclerView = view.findViewById(R.id.recylerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
//        supplierRecyclerView.setLayoutManager(gridLayoutManager);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();

//        if (supplierList != null && supplierList.size() > 0) {
//            recyclerViewAdapter = new SupplierRecyclerViewAdapter(getContext(), supplierList);
//            supplierRecyclerView.setAdapter(recyclerViewAdapter);
//            recyclerViewAdapter.notifyDataSetChanged();
//        }


        ActionBar actionBar = ((MasterInfoActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Suppliers");
        }

        //search the supplier
//        searchSupplier = view.findViewById(R.id.searchSupplier);
//        searchSupplier.clearFocus();
//        searchSupplier.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                filter(editable.toString());
//            }
//        });
        return view;
    }

    private void filter(String text) {
        ArrayList<Supplier> filterList = new ArrayList<>();
        for(Supplier supplier : supplierList){
            if(supplier.getSupplierName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(supplier);
            }
        }
        //check if the adapter is not null before calling filteredList method
        if(recyclerViewAdapter != null){
        recyclerViewAdapter.filteredList(filterList);
        }
    }

//    private void filter(String text) {
//        ArrayList<Supplier> searchSupplierList = new ArrayList<>();
//        for(Supplier supplier : supplierArrayList){
//            if(supplier.getSupplierName().contains(text)){
//                searchSupplierList.add(supplier);
//            }
//        }
//        recyclerViewAdapter.filtered(searchSupplierList);
//    }



    public void showDetailsFragment(int position) {
        Supplier supplier = ((SupplierRecyclerViewAdapter) recyclerViewAdapter).getItem(position);
        // Create a new SupplierDetailsFragment and pass the supplier ID and position
        Bundle args = new Bundle();
//        args.putInt("id", supplier.getId());
        args.putInt("position", position);
        SupplierDetailsFragment supplierDetailsFragment = new SupplierDetailsFragment(supplierId);
        supplierDetailsFragment.setArguments(args);
        // Pass a reference to the adapter to the SupplierDetailsFragment
//        supplierRecyclerView.setAdapter(recyclerViewAdapter);
//        recyclerViewAdapter.notifyItemRemoved(position);
    }


    public void setListener(MasterInfoListener listener) {
        this.masterInfoListener = listener;
    }


}