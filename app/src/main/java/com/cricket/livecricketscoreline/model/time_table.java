package com.cricket.livecricketscoreline.model;

import java.util.ArrayList;

public class time_table {

    String date_title;
    Integer int_type;

    private String date="",
            match_title="",
            match_desc="",
            match_date="",
            match_time="",
            bating_team_flag="",
            bowling_team_flag="",
            bating_team_shrt_nm="",
            bowling_team_shrt_nm="";

    private ArrayList<Match_TimeTable> Match_TimeTable = new ArrayList<Match_TimeTable>();
    public time_table(String date, Integer type) {
        this.date_title = date;
        this.int_type = type;
    }


    public String getDate_title() {
        return date_title;
    }

    public void setDate_title(String date_title) {
        this.date_title = date_title;
    }

    public Integer getInt_type() {
        return int_type;
    }

    public void setInt_type(Integer int_type) {
        this.int_type = int_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatch_title() {
        return match_title;
    }

    public void setMatch_title(String match_title) {
        this.match_title = match_title;
    }

    public String getMatch_desc() {
        return match_desc;
    }

    public void setMatch_desc(String match_desc) {
        this.match_desc = match_desc;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getMatch_time() {
        return match_time;
    }

    public void setMatch_time(String match_time) {
        this.match_time = match_time;
    }

    public String getBating_team_flag() {
        return bating_team_flag;
    }

    public void setBating_team_flag(String bating_team_flag) {
        this.bating_team_flag = bating_team_flag;
    }

    public String getBowling_team_flag() {
        return bowling_team_flag;
    }

    public void setBowling_team_flag(String bowling_team_flag) {
        this.bowling_team_flag = bowling_team_flag;
    }

    public String getBating_team_shrt_nm() {
        return bating_team_shrt_nm;
    }

    public void setBating_team_shrt_nm(String bating_team_shrt_nm) {
        this.bating_team_shrt_nm = bating_team_shrt_nm;
    }

    public String getBowling_team_shrt_nm() {
        return bowling_team_shrt_nm;
    }

    public void setBowling_team_shrt_nm(String bowling_team_shrt_nm) {
        this.bowling_team_shrt_nm = bowling_team_shrt_nm;
    }

    public ArrayList<com.cricket.livecricketscoreline.model.Match_TimeTable> getMatch_TimeTable() {
        return Match_TimeTable;
    }

    public void setMatch_TimeTable(ArrayList<com.cricket.livecricketscoreline.model.Match_TimeTable> match_TimeTable) {
        Match_TimeTable = match_TimeTable;
    }
}
