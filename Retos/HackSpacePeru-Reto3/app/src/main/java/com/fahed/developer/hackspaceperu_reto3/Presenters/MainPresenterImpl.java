package com.fahed.developer.hackspaceperu_reto3.Presenters;

import com.fahed.developer.hackspaceperu_reto3.Interactors.MainInteractorImpl;
import com.fahed.developer.hackspaceperu_reto3.Interfaces.MainInteractor;
import com.fahed.developer.hackspaceperu_reto3.Interfaces.MainPresenter;
import com.fahed.developer.hackspaceperu_reto3.Interfaces.MainView;
import com.fahed.developer.hackspaceperu_reto3.Interfaces.OnMainFinishListener;
import com.fahed.developer.hackspaceperu_reto3.Models.ResponseItemsCoins;

import java.util.List;

/**
 * Created by mac on 21/02/18.
 */

public class MainPresenterImpl implements MainPresenter,OnMainFinishListener {

    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        view.showProgress();
        view.hideListEmpty();
        view.hideLoading();
        interactor =  new MainInteractorImpl(this);
    }

    @Override
    public void initRecycler(List<ResponseItemsCoins> listResponseItemCoins) {
        if(view != null){
            view.initRecycler(listResponseItemCoins);
            view.hideProgress();
        }
    }

    @Override
    public void loadListImmovables(int pagination) {
        if (view != null) {
            interactor.initRecycler(this, pagination);
        }
    }

    @Override
    public void operationError() {
        if (view != null){
            view.hideProgress();
            view.setError();
        }
    }

    @Override
    public void emptyItemList() {
        if (view != null) {
            view.hideProgress();
            view.showEmptyList();
        }
    }
}
