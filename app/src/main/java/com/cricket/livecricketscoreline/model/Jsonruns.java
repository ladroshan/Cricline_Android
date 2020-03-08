package com.cricket.livecricketscoreline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 7/15/2017.
 */

public class Jsonruns {

    @SerializedName("runxa")
    @Expose
    private String runxa;
    @SerializedName("runxb")
    @Expose
    private String runxb;
    @SerializedName("fav")
    @Expose
    private String fav;
    @SerializedName("rateA")
    @Expose
    private String rateA;
    @SerializedName("rateB")
    @Expose
    private String rateB;
    @SerializedName("sessionA")
    @Expose
    private String sessionA;
    @SerializedName("sessionB")
    @Expose
    private String sessionB;
    @SerializedName("sessionOver")
    @Expose
    private String sessionOver;
    @SerializedName("summary")
    @Expose
    private String summary;

    public String getRunxa() {
        return runxa;
    }

    public void setRunxa(String runxa) {
        this.runxa = runxa;
    }

    public String getRunxb() {
        return runxb;
    }

    public void setRunxb(String runxb) {
        this.runxb = runxb;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getRateA() {
        return rateA;
    }

    public void setRateA(String rateA) {
        this.rateA = rateA;
    }

    public String getRateB() {
        return rateB;
    }

    public void setRateB(String rateB) {
        this.rateB = rateB;
    }

    public String getSessionA() {
        return sessionA;
    }

    public void setSessionA(String sessionA) {
        this.sessionA = sessionA;
    }

    public String getSessionB() {
        return sessionB;
    }

    public void setSessionB(String sessionB) {
        this.sessionB = sessionB;
    }

    public String getSessionOver() {
        return sessionOver;
    }

    public void setSessionOver(String sessionOver) {
        this.sessionOver = sessionOver;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
