package com.fahed.developer.hackspaceperu_reto3.Interfaces;

/**
 * Created by mac on 21/02/18.
 */

public interface MainInteractor {

    void initRecycler(final OnMainFinishListener listener, int pagination);
    void webServiceGetImmovables(final OnMainFinishListener listener,final int pagination);
}
