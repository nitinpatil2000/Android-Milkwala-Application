package com.technosoul.milkwala.ui.masterinfo.products;

import android.hardware.lights.LightState;

import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFromServer;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {
    @POST("/addproductforsupplier")
    Call<String> createProduct(@Body ProductFromServer productFromServer);

    @GET("/productforsupplier/{supplierId}")
    Call<List<ProductFromServer>> getProductsBySupplierId(@Path("supplierId") int supplierId);

    @GET("/products/{productId}")
    Call<ProductFromServer> getProductsByProductId(@Path("productId") int productId);

    @GET("/getproducts")
    Call<List<ProductFromServer>> getAllProducts();

    @DELETE("/product/{productId}")
    Call<ResponseBody> deleteProduct(@Path("productId") int productId);
}
