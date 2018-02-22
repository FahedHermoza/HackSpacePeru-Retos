package com.fahed.developer.hackspaceperu_reto3.Views;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fahed.developer.hackspaceperu_reto3.Adapters.CryptoCoinsHolder;
import com.fahed.developer.hackspaceperu_reto3.Interfaces.MainPresenter;
import com.fahed.developer.hackspaceperu_reto3.Interfaces.MainView;
import com.fahed.developer.hackspaceperu_reto3.Models.ResponseItemsCoins;
import com.fahed.developer.hackspaceperu_reto3.Presenters.MainPresenterImpl;
import com.fahed.developer.hackspaceperu_reto3.R;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MainView {
    private View viewRoot;
    private ProgressBar progressBarItems;

    private RecyclerView recyclerViewItems;
    private CryptoCoinsHolder.CryptoCoinsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private TextView textViewListEmptyItems;

    private MainPresenter presenter;
    private int PAGINATION_IMMOVABLES_CATEGORIES = 0;

    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private TextView textViewLoading;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_main, container, false);
        progressBarItems = (ProgressBar) viewRoot.findViewById(R.id.progressBarItems);
        textViewListEmptyItems = (TextView) viewRoot.findViewById(R.id.textViewListEmptyItems);
        textViewLoading = (TextView) viewRoot.findViewById(R.id.textViewLoadingItems);

        recyclerViewItems = (RecyclerView) viewRoot.findViewById(R.id.recyclerViewItems);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewItems.setLayoutManager(linearLayoutManager);

        presenter = new MainPresenterImpl(this);
        presenter.loadListImmovables(PAGINATION_IMMOVABLES_CATEGORIES);

        updateList();
        return viewRoot;
    }

    @Override
    public void initRecycler(final List<ResponseItemsCoins>listResponseItemCoins) {
        if (adapter == null){
            adapter = new CryptoCoinsHolder.CryptoCoinsAdapter(listResponseItemCoins);
            recyclerViewItems.setAdapter(adapter);
        }else{
            recyclerViewItems.post(new Runnable() {
                public void run() {
                    adapter.addItemCoins(listResponseItemCoins);
                    hideLoading();
                    loading =true;
                }
            });
        }
    }

    @Override
    public void showProgress() {
        progressBarItems.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarItems.setVisibility(View.GONE);
    }

    @Override
    public void setError() {
        Snackbar.make(viewRoot, R.string.snackbar_error_items, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyList() {
        textViewListEmptyItems.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListEmpty() {
        textViewListEmptyItems.setVisibility(View.GONE);
    }

    private void updateList(){
        recyclerViewItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            showLoading();
                            loading = false;
                            PAGINATION_IMMOVABLES_CATEGORIES = adapter.getItemCount();
                            presenter.loadListImmovables(PAGINATION_IMMOVABLES_CATEGORIES);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void showLoading() {
        textViewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        textViewLoading.setVisibility(View.GONE);
    }

    @Override
    public void removeList() {
        loading = false;
    }
}
