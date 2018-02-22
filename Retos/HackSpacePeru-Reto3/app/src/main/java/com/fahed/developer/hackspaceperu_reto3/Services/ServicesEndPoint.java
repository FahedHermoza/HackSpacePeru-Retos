package com.fahed.developer.hackspaceperu_reto3.Services;

import com.fahed.developer.hackspaceperu_reto3.Models.ResponseItemsCoins;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mac on 3/01/18.
 */

public interface ServicesEndPoint {

    // ?start=0&limit=10
    @GET("v1/ticker/")
    Call<List<ResponseItemsCoins>> findGetCoins(@Query("start") int start,
                                                @Query("limit") int limit);

}
