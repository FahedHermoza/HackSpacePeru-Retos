package com.fahed.developer.hackspaceperu_reto3.Interactors;

import android.util.Log;

import com.fahed.developer.hackspaceperu_reto3.Interfaces.MainInteractor;
import com.fahed.developer.hackspaceperu_reto3.Interfaces.MainPresenter;
import com.fahed.developer.hackspaceperu_reto3.Interfaces.OnMainFinishListener;
import com.fahed.developer.hackspaceperu_reto3.Models.ResponseItemsCoins;
import com.fahed.developer.hackspaceperu_reto3.Services.ServicesEndPoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac on 21/02/18.
 */

public class MainInteractorImpl implements MainInteractor {
    private MainPresenter presenter;
    private final String URL = "https://api.coinmarketcap.com/";
    private final int LIMIT_ITEMS = 10;

    public MainInteractorImpl(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initRecycler(OnMainFinishListener listener, int pagination) {
        this.webServiceGetImmovables(listener,pagination);
    }

    @Override
    public void webServiceGetImmovables(final OnMainFinishListener listener, int pagination) {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ServicesEndPoint service = retrofit.create(ServicesEndPoint.class);

        // ?start=0&limit=10
        Call<List<ResponseItemsCoins>> call =service.findGetCoins(pagination,LIMIT_ITEMS);

        call.enqueue(new Callback<List<ResponseItemsCoins>>() {
            @Override
            public void onResponse(Call<List<ResponseItemsCoins>> call, Response<List<ResponseItemsCoins>> response) {
                if(response.isSuccessful()) {
                    List<ResponseItemsCoins> itemItemsCoins = new ArrayList<>();
                    for (ResponseItemsCoins responseItemCoin : response.body()) {
                        itemItemsCoins.add(responseItemCoin);
                    }
                    presenter.initRecycler(itemItemsCoins);
                }else{
                    listener.operationError();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseItemsCoins>> call, Throwable t) {
                listener.operationError();
            }
        });

    }
}
