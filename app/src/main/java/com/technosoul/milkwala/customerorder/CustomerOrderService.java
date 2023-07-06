package com.technosoul.milkwala.customerorder;

import com.technosoul.milkwala.db.Customer;
import com.technosoul.milkwala.receiveProduct.SupplierOrder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CustomerOrderService {

    @POST("/addcustomerorder")
    Call<CustomerOrder> createCustomerOrder(@Body CustomerOrder customerOrder);
}
