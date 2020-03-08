package com.cricket.livecricketscoreline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 7/15/2017.
 */

public class MainData {
    @SerializedName("ID")
    @Expose
    private Object iD;
    @SerializedName("jsonruns")
    @Expose
    private Object jsonruns;
    @SerializedName("jsondata")
    @Expose
    private Object jsondata;

    @SerializedName("Appversion")
    @Expose
    private String appversion;

    public Object getID() {
        return iD;
    }

    public void setID(Object iD) {
        this.iD = iD;
    }

    public Object getJsonruns() {
        return jsonruns;
    }

    public void setJsonruns(Object jsonruns) {
        this.jsonruns = jsonruns;
    }

    public Object getJsondata() {
        return jsondata;
    }

    public void setJsondata(Object jsondata) {
        this.jsondata = jsondata;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }
}
