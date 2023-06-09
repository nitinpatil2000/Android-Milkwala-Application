package com.technosoul.milkwala.ui.masterinfo.suppliers;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SupplierService {
    @POST("/addsupplier")
    Call<SupplierFromServer> addSupplier(@Body SupplierFromServer supplierEntity);

    @GET("/getsupplier")
    Call<List<SupplierFromServer>> getAllSuppliers();

    @GET("/suppliers/{supplierId}")
    Call<SupplierFromServer> getSupplierDetails(@Path("supplierId") int supplierId);

    @PUT("/updatesupplier/{supplierId}")
    Call<SupplierFromServer> updateSupplier(@Path("supplierId") int supplierId, @Body SupplierFromServer updatedSupplier);

    @DELETE("/supplier/{supplierId}")
    Call<ResponseBody> deleteSupplier(@Path("supplierId") int supplierId);
}
