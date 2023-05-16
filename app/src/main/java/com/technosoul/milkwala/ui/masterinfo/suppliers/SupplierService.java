package com.technosoul.milkwala.ui.masterinfo.suppliers;

import com.technosoul.milkwala.db.Supplier;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface SupplierService {
    @POST("/addsupplier")
    Call<SupplierEntity> createSupplier(@Body SupplierEntity supplierEntity);

    @GET("/getsupplier")
    Call<List<SupplierEntity>> getAllSuppliers();

    @DELETE("/supplier/{supplierId}")
    Call<ResponseBody> deleteSupplier(@Path("supplierId") int supplierId);
}
