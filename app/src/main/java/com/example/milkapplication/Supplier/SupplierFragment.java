package com.example.milkapplication.Supplier;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.milkapplication.Helper.MyDbHelper;
import com.example.milkapplication.MainActivity;
import com.example.milkapplication.R;

import java.util.ArrayList;

public class SupplierFragment extends Fragment {
    RecyclerView supplierRecyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
//    ArrayList<Supplier> supplierList = new ArrayList<>();
    int position;


    Button addSupplierTxt;

    public SupplierFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_supplier, container, false);
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
        MyDbHelper myDbHelper = new MyDbHelper(getActivity());
        ArrayList<Supplier> suppliers = myDbHelper.getData();
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), suppliers);
        recyclerViewAdapter.notifyDataSetChanged();
        supplierRecyclerView.setAdapter(recyclerViewAdapter);


//        //delete the item in recyclerView;
//        getParentFragmentManager().setFragmentResultListener("delete", getViewLifecycleOwner(), new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                int position = result.getInt("position");
//                suppliers.remove(position);
//                recyclerViewAdapter.notifyItemRemoved(position);
////                recyclerViewAdapter.notifyDataSetChanged(); // Add this line to refresh RecyclerView
//            }
//        });


        //set the title in the fragment;
        if(getActivity()!=null){
            ((MainActivity) getActivity()).setActionBarTitle("Supplier");
        }
        return view;
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        myDbHelper.close();
//    }


    //method to get adapter instance
    public RecyclerViewAdapter getAdapter(){
        return  recyclerViewAdapter;
    }
}