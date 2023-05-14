package com.technosoul.milkwala.ui.masterinfo.suppliers;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technosoul.milkwala.R;
import com.technosoul.milkwala.adapters.SupplierRecyclerViewAdapter;
import com.technosoul.milkwala.db.MyDbHelper;
import com.technosoul.milkwala.db.Supplier;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoActivity;
import com.technosoul.milkwala.ui.masterinfo.MasterInfoListener;
import com.technosoul.milkwala.ui.masterinfo.OnItemSelected;

import java.util.ArrayList;

public class SupplierFragment extends Fragment {
    private SupplierRecyclerViewAdapter recyclerViewAdapter;
    private EditText searchSupplier;
    private ArrayList<Supplier> supplierList = new ArrayList<>();
    private MasterInfoListener masterInfoListener;
    private OnItemSelected onItemSelected;

    public SupplierFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supplier, container, false);

        Button btnAddNewSupplier = view.findViewById(R.id.btn_add_new_supplier);

        btnAddNewSupplier.setOnClickListener(view1 -> {
            if (masterInfoListener != null) {
                masterInfoListener.addNewSupplier();
            }
        });

        RecyclerView supplierRecyclerView = view.findViewById(R.id.recyclerView_supplier_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        supplierRecyclerView.setLayoutManager(gridLayoutManager);

        MyDbHelper myDbHelper = MyDbHelper.getDB(getActivity());

        supplierList = (ArrayList<Supplier>) myDbHelper.supplierDao().getAllSuppliers();
        if (supplierList == null || supplierList.size() == 0) {
            Toast.makeText(getContext(), R.string.empty_supplier_list, Toast.LENGTH_SHORT).show();
        } else {
            recyclerViewAdapter = new SupplierRecyclerViewAdapter(getContext(), supplierList, onItemSelected);
            supplierRecyclerView.setAdapter(recyclerViewAdapter);
        }

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

        if(getActivity()!= null){
            ((MasterInfoActivity)getActivity()).setActionBarTitle("Supplier");
        }
        return view;
    }

    private void filter(String text) {
        ArrayList<Supplier> filterList = new ArrayList<>();
        for (Supplier supplier : supplierList) {
            if (supplier.getSupplierName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(supplier);
            }
        }
        //check if the adapter is not null before calling filteredList method
        if (recyclerViewAdapter != null) {
            recyclerViewAdapter.filteredList(filterList);
        }
    }


    public void setListener(MasterInfoListener listener) {
        this.masterInfoListener = listener;
    }

    public void setOnItemSelectedListener(OnItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }


}