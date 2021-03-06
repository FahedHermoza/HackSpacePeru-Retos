package com.fahed.developer.hackspaceperu_reto3.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 21/02/18.
 */
public class ResponseItemsCoins {

    private String id;
    private String name;
    private String symbol;
    private String rank;

    @SerializedName("price_usd")
    private String priceUsd;

    @SerializedName("price_btc")
    private String priceBtc;

    @SerializedName("24h_volume_usd")
    private String _24hVolumeUsd;

    @SerializedName("market_cap_usd")
    private String marketCapUsd;

    @SerializedName("available_supply")
    private String availableSupply;

    @SerializedName("total_supply")
    private String totalSupply;

    @SerializedName("max_supply")
    private String maxSupply;

    @SerializedName("percent_change_1h")
    private String percentChange1h;

    @SerializedName("percent_change_24h")
    private String percentChange24h;

    @SerializedName("percent_change_7d")
    private String percentChange7d;

    @SerializedName("last_updated")
    private String lastUpdated;

    public ResponseItemsCoins() {
    }

    public ResponseItemsCoins(String id, String name, String symbol, String rank, String priceUsd, String priceBtc, String _24hVolumeUsd, String marketCapUsd, String availableSupply, String totalSupply, String maxSupply, String percentChange1h, String percentChange24h, String percentChange7d, String lastUpdated) {
        super();
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.priceUsd = priceUsd;
        this.priceBtc = priceBtc;
        this._24hVolumeUsd = _24hVolumeUsd;
        this.marketCapUsd = marketCapUsd;
        this.availableSupply = availableSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.percentChange1h = percentChange1h;
        this.percentChange24h = percentChange24h;
        this.percentChange7d = percentChange7d;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(String priceBtc) {
        this.priceBtc = priceBtc;
    }

    public String get24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    public void set24hVolumeUsd(String _24hVolumeUsd) {
        this._24hVolumeUsd = _24hVolumeUsd;
    }

    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(String marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public String getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(String availableSupply) {
        this.availableSupply = availableSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}