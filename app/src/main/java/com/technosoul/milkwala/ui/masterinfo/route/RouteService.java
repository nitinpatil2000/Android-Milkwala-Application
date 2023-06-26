package com.technosoul.milkwala.ui.masterinfo.route;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RouteService {

    @POST("/addroutefordeliverboy")
    Call<RouteFromServer> createRouteForDeliveryBoy(@Body RouteFromServer routeFromServer);

    @GET("/routefordeliveryboy/{deliveryPersonId}")
    Call<List<RouteFromServer>> getRouteByDelivertId(@Path("deliveryPersonId") int deliveryPersonId);

    @GET("/routes/{routeId}")
    Call<RouteFromServer> getRouteByRouteId(@Path("routeId") int routeId);

    @PUT("/updateroute/{routeId}")
    Call<RouteFromServer> updateRoute(@Path("routeId") int routeId, @Body RouteFromServer routeFromServer);

    @DELETE("/route/{routeId}")
    Call<ResponseBody> deleteRoute(@Path("routeId") int routeId);
}

