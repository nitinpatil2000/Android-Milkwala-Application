package com.technosoul.milkwala.ui.masterinfo.customer;

import com.technosoul.milkwala.ui.masterinfo.customer.CustomerFromServer;
import com.technosoul.milkwala.ui.masterinfo.deliveryPerson.DeliveryFromServer;
import com.technosoul.milkwala.ui.masterinfo.products.ProductFromServer;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CustomerService {
    @POST("/addcustomer")
    Call<CustomerFromServer> createCustomer(@Body CustomerFromServer customerFromServer);

    @GET("/customer/{customerId}")
    Call<CustomerFromServer> getAllCustomersById(@Path("customerId") int customerId);

    @GET("/allcustomersforroute/{routeId}")
    Call<List<CustomerFromServer>> getAllCustomerByRouteId(@Path("routeId") int routeId);

    @GET("/getallcustomers")
    Call<List<CustomerFromServer>> getAllCustomers();

    @PUT("/updatecustomer/{customerId}")
    Call<CustomerFromServer> updateCustomer(@Path("customerId") int customerId, @Body CustomerFromServer customerFromServer);

    @DELETE("/customer/{customerId}")
    Call<ResponseBody> deleteCustomer(@Path("customerId") int customerId);
}
