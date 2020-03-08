package com.cricket.livecricketscoreline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 7/15/2017.
 */

public class Jsondata {

    @SerializedName("batsman")
    @Expose
    private String batsman;
    @SerializedName("bowler")
    @Expose
    private String bowler;
    @SerializedName("oversA")
    @Expose
    private String oversA;
    @SerializedName("oversB")
    @Expose
    private String oversB;
    @SerializedName("rateA")
    @Expose
    private String rateA;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("sessionA")
    @Expose
    private String sessionA;
    @SerializedName("sessionB")
    @Expose
    private String sessionB;
    @SerializedName("sessionOver")
    @Expose
    private String sessionOver;
    @SerializedName("teamA")
    @Expose
    private String teamA;
    @SerializedName("teamB")
    @Expose
    private String teamB;
    @SerializedName("totalballs")
    @Expose
    private String totalballs;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("wicketA")
    @Expose
    private String wicketA;
    @SerializedName("wicketB")
    @Expose
    private String wicketB;

    @SerializedName("appversion")
    @Expose
    private String appversion;

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getBatsman() {
        return batsman;
    }

    public void setBatsman(String batsman) {
        this.batsman = batsman;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public String getOversA() {
        return oversA;
    }

    public void setOversA(String oversA) {
        this.oversA = oversA;
    }

    public String getOversB() {
        return oversB;
    }

    public void setOversB(String oversB) {
        this.oversB = oversB;
    }

    public String getRateA() {
        return rateA;
    }

    public void setRateA(String rateA) {
        this.rateA = rateA;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getTotalballs() {
        return totalballs;
    }

    public void setTotalballs(String totalballs) {
        this.totalballs = totalballs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWicketA() {
        return wicketA;
    }

    public void setWicketA(String wicketA) {
        this.wicketA = wicketA;
    }

    public String getWicketB() {
        return wicketB;
    }

    public void setWicketB(String wicketB) {
        this.wicketB = wicketB;
    }

}
