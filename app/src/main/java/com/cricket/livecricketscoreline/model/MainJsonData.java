package com.cricket.livecricketscoreline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 7/15/2017.
 */

public class MainJsonData {

    @SerializedName("jsondata")
    @Expose
    private Jsondata jsondata;

    public Jsondata getJsondata() {
        return jsondata;
    }

    public void setJsondata(Jsondata jsondata) {
        this.jsondata = jsondata;
    }

}