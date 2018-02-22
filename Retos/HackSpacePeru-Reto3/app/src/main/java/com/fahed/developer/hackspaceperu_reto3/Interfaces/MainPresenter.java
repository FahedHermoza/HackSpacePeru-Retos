package com.fahed.developer.hackspaceperu_reto3.Interfaces;

import com.fahed.developer.hackspaceperu_reto3.Models.ResponseItemsCoins;

import java.util.List;

/**
 * Created by mac on 21/02/18.
 */

public interface MainPresenter {

    void initRecycler(List<ResponseItemsCoins> listResponseItemCoins);
    void loadListImmovables(int pagination);
}
