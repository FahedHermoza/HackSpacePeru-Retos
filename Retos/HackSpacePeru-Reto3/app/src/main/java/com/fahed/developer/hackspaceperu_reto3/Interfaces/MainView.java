package com.fahed.developer.hackspaceperu_reto3.Interfaces;

import com.fahed.developer.hackspaceperu_reto3.Models.ResponseItemsCoins;

import java.util.List;

/**
 * Created by mac on 21/02/18.
 */

public interface MainView {

    void initRecycler(List<ResponseItemsCoins> listResponseItemCoins);
    void showProgress();
    void hideProgress();
    void hideListEmpty();
    void setError();
    void showEmptyList();
    void showLoading();
    void hideLoading();
    void removeList();
}
