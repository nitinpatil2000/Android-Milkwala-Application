package com.technosoul.milkwala.ui.masterinfo.deliveryPerson;

import com.technosoul.milkwala.db.DeliveryPerson;
import com.technosoul.milkwala.ui.masterinfo.suppliers.SupplierFromServer;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DeliveryPersonService {
    @POST("/adddeliveryboy")
    Call<String> addDeliveryPerson(@Body DeliveryFromServer deliveryFromServer);

    @GET("/getdeliveryperson")
    Call<List<DeliveryFromServer>> getAllDeliveryPersons();

    @GET("/deliveryperson/{deliveryPersonId}")
    Call<DeliveryFromServer> getDeliveryPersonDetails(@Path("deliveryPersonId") int deliveryPersonId);

    @DELETE("/deliveryperson/{deliveryPersonId}")
    Call<ResponseBody> deleteDeliveryPerson(@Path("deliveryPersonId") int deliveryPersonId);

}
