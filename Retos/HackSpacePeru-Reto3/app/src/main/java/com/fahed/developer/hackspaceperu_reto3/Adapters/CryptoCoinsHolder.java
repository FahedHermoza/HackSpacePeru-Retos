package com.fahed.developer.hackspaceperu_reto3.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fahed.developer.hackspaceperu_reto3.Models.ResponseItemsCoins;
import com.fahed.developer.hackspaceperu_reto3.R;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

/**
 * Created by mac on 31/01/18.
 */

public class CryptoCoinsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String beginUrl = "https://raw.githubusercontent.com/cjdowner/cryptocurrency-icons/master/128/white/";
    private ResponseItemsCoins itemCoins;
    private View viewItem;
    private ImageView imageViewCoverPage;
    private TextView textViewSymbol;
    private TextView textViewRank;
    private Button buttonPriceUsd;
    private TextView textViewPriceBtc;
    private TextView textViewChange1h;
    private TextView textViewChange24h;
    private TextView textViewChange7d;
    private TextView textViewAvailableSupply;
    private TextView textViewTotalSupply;
    private TextView textViewMaxSupply;
    private TextView textViewLastUpdate;


    public CryptoCoinsHolder(View viewItem) {
        super(viewItem);
        this.viewItem = viewItem;
        viewItem.setOnClickListener(this);

        imageViewCoverPage = (ImageView) viewItem.findViewById(R.id.imageViewCoverPageItemCryptoCoins);
        textViewSymbol = (TextView) viewItem.findViewById(R.id.textViewSymbolItemCryptoCoins);
        textViewRank = (TextView) viewItem.findViewById(R.id.textViewRankItemCryptoCoins);
        textViewPriceBtc = (TextView) viewItem.findViewById(R.id.textViewPriceBtcItemCryptoCoins);
        buttonPriceUsd = (Button) viewItem.findViewById(R.id.buttonPriceUsdItemCryptoCoins);
        textViewChange1h = (TextView) viewItem.findViewById(R.id.textViewChange1hItemCryptoCoins);
        textViewChange24h = (TextView) viewItem.findViewById(R.id.textViewChange24hItemCryptoCoins);
        textViewChange7d = (TextView) viewItem.findViewById(R.id.textViewChange7dItemCryptoCoins);
        textViewAvailableSupply = (TextView) viewItem.findViewById(R.id.textViewAvailableSuplyItemCryptoCoins);
        textViewTotalSupply = (TextView) viewItem.findViewById(R.id.textViewTotalSuplyItemCryptoCoins);
        textViewMaxSupply = (TextView) viewItem.findViewById(R.id.textViewMaxSupplyItemCryptoCoins);
        textViewLastUpdate = (TextView) viewItem.findViewById(R.id.textViewLastUpdateItemCryptoCoins);
    }

    public void bindImmovables(ResponseItemsCoins itemCoins) {
        this.itemCoins = itemCoins;
        Picasso.with(viewItem.getContext()).load(beginUrl + this.itemCoins.getSymbol().toLowerCase() + ".png")
                .into(imageViewCoverPage);
        textViewSymbol.setText(this.itemCoins.getSymbol());
        textViewRank.setText("Rank #"+this.itemCoins.getRank());
        textViewPriceBtc.setText(this.itemCoins.getPriceBtc() + " BTC");
        buttonPriceUsd.setText("S./ " + this.itemCoins.getPriceUsd());
        changeColorAndText(textViewChange1h, this.itemCoins.getPercentChange1h());
        changeColorAndText(textViewChange24h, this.itemCoins.getPercentChange24h());
        changeColorAndText(textViewChange7d, this.itemCoins.getPercentChange7d());
        textViewAvailableSupply.setText(this.itemCoins.getAvailableSupply());
        textViewTotalSupply.setText(this.itemCoins.getTotalSupply());
        textViewMaxSupply.setText(this.itemCoins.getMaxSupply());
        textViewLastUpdate.setText(getLastUpdate(this.itemCoins.getLastUpdated()));
    }

    private Boolean isChangePositive(String percentChange1h){
        if(Double.parseDouble(percentChange1h)>=0){
            return true;
        }
        return false;
    }

    private void changeColorAndText(TextView textView, String percentChange){
        if(isChangePositive(percentChange))
            textView.setTextColor(viewItem.getResources().getColor(R.color.colorAccent));
        else
            textView.setTextColor(viewItem.getResources().getColor(R.color.colorRed));
        textView.setText(percentChange);
    }

    private String getLastUpdate(String lastUpdate){
        Date date = new Date(Long.parseLong(lastUpdate));
        if(date.getHours() > 0){
            return "Hace "+date.getHours()+" horas";
        }
        if(date.getMinutes() > 0){
            return "Hace "+date.getHours()+" minutos";
        }
        return "Hace "+date.getSeconds() +" segundos";
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "¡Criptomoneda conocida como "+itemCoins.getName()+"!", Toast.LENGTH_SHORT).show();
    }

    public static class CryptoCoinsAdapter extends RecyclerView.Adapter<CryptoCoinsHolder> {

        private List<ResponseItemsCoins> itemCoinsList;

        public CryptoCoinsAdapter(List<ResponseItemsCoins> itemCoinsList) {
            this.itemCoinsList = itemCoinsList;
        }

        @Override
        public CryptoCoinsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item_crypto_coins, parent, false);
            return new CryptoCoinsHolder( view);
        }

        @Override
        public void onBindViewHolder(CryptoCoinsHolder holder, int position) {
            ResponseItemsCoins itemsCoins = itemCoinsList.get(position);
            //Connect widgets
            holder.bindImmovables(itemsCoins);
        }

        @Override
        public int getItemCount() {
            return itemCoinsList.size();
        }

        public void addItemCoins(List<ResponseItemsCoins> itemCoinsList) {
            int startPosition = this.getItemCount();
            int endPosition = startPosition + itemCoinsList.size() - 1;
            this.itemCoinsList.addAll(itemCoinsList);
            notifyItemRangeInserted(startPosition,endPosition);
        }
    }

}
