package com.cricket.livecricketscoreline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 7/15/2017.
 */

public class MainJsonRuns {
    @SerializedName("jsonruns")
    @Expose
    private Jsonruns jsonruns;

    public Jsonruns getJsonruns() {
        return jsonruns;
    }

    public void setJsonruns(Jsonruns jsonruns) {
        this.jsonruns = jsonruns;
    }
}
