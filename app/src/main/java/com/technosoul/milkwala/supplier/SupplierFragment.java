package com.technosoul.milkwala.supplier;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.technosoul.milkwala.helper.MyDbHelper;
import com.technosoul.milkwala.R;

import java.util.ArrayList;
import java.util.List;

public class SupplierFragment extends Fragment {
    RecyclerView supplierRecyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    int position;
    EditText searchSupplier;
    ArrayList<Supplier> supplierList;

    Button addSupplierTxt;
    int supplierId;

    public SupplierFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supplier, container, false);
        addSupplierTxt = view.findViewById(R.id.addSupplierTxt);


//        changing the fragment i.e supplierFragment to AddFragment
        addSupplierTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSupplier addSupplier = new AddSupplier();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.container, addSupplier);
                ft.commit();
            }
        });

        supplierRecyclerView = view.findViewById(R.id.recylerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        supplierRecyclerView.setLayoutManager(gridLayoutManager);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());
        supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();

        for (int i = 0; i < supplierList.size(); i++) {
            recyclerViewAdapter = new RecyclerViewAdapter(getContext(), supplierList);
            recyclerViewAdapter.notifyDataSetChanged();
            supplierRecyclerView.setAdapter(recyclerViewAdapter);
        }


        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Suppliers");



        //search the supplier
        searchSupplier = view.findViewById(R.id.searchSupplier);
        searchSupplier.clearFocus();
        searchSupplier.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
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
        Supplier supplier = ((RecyclerViewAdapter) recyclerViewAdapter).getItem(position);
        // Create a new SupplierDetailsFragment and pass the supplier ID and position
        Bundle args = new Bundle();
//        args.putInt("id", supplier.getId());
        args.putInt("position", position);
        SupplierDetailsFragment supplierDetailsFragment = new SupplierDetailsFragment(supplierId);
        supplierDetailsFragment.setArguments(args);
        // Pass a reference to the adapter to the SupplierDetailsFragment
        supplierRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyItemRemoved(position);
    }


}