package com.cricket.livecricketscoreline.model;


public class AppVersion {


    private String UpdateMode="",UpdateFeatures="",URL="";

    private double LatestVersion;


    public String getUpdateFeatures() {
        return UpdateFeatures;
    }

    public void setUpdateFeatures(String updateFeatures) {
        UpdateFeatures = updateFeatures;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public double getLatestVersion() {
        return LatestVersion;
    }

    public void setLatestVersion(double latestVersion) {
        LatestVersion = latestVersion;
    }

    public String getUpdateMode() {
        return UpdateMode;
    }

    public void setUpdateMode(String updateMode) {
        UpdateMode = updateMode;
    }
}
