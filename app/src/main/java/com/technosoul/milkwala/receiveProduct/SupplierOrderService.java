package com.technosoul.milkwala.receiveProduct;

import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SupplierOrderService {

    @POST("/addsupplierorder")
    Call<String> createSupplierOrder(@Body SupplierOrder supplierOrder);
}
