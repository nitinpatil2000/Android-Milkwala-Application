package com.technosoul.milkwala.Supplier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.technosoul.milkwala.Helper.MyDbHelper;
import com.technosoul.milkwala.MainActivity;
import com.technosoul.milkwala.R;

import java.util.ArrayList;
import java.util.List;

public class SupplierFragment extends Fragment {
    RecyclerView supplierRecyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    int position;

    Button addSupplierTxt;

    public SupplierFragment() {
        // Required empty public constructor
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
        ArrayList<Supplier> supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();

        for (int i = 0; i < supplierList.size(); i++) {
            recyclerViewAdapter = new RecyclerViewAdapter(getContext(), supplierList);
            recyclerViewAdapter.notifyDataSetChanged();
            supplierRecyclerView.setAdapter(recyclerViewAdapter);
        }

        //set the title in the fragment;
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setActionBarTitle("Supplier");
        }
        return view;
    }

    public void showDetailsFragmentt(int position) {
        Supplier supplier = ((RecyclerViewAdapter) recyclerViewAdapter).getItem(position);
        // Create a new SupplierDetailsFragment and pass the supplier ID and position
        Bundle args = new Bundle();
//        args.putInt("id", supplier.getId());
        args.putInt("position", position);
        SupplierDetailsFragment supplierDetailsFragment = new SupplierDetailsFragment();
        supplierDetailsFragment.setArguments(args);
        // Pass a reference to the adapter to the SupplierDetailsFragment
        supplierRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyItemRemoved(position);
    }
}