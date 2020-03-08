package com.cricket.livecricketscoreline.model;

/**
 * Created by ankitmalhotra on 07/02/17.
 */

public class Match {

    public Match() {
    }

    private String match_key="";
    private String totalballs="";
    private String title="";
    private String teamA="";
    private String teamB="";
    private String teamALogo="";
    private String teamBLogo="";
    private String oversA="";
    private String oversB="";
    private String wicketA="";
    private String wicketB="";
    private String rateA="";
    private String rateB="";
    private String sessionOver="";
    private String sessionA="";
    private String sessionB="";
    private String batsman="";
    private String bowler="";
    private String score="";

    private String teamA_flag="";
    private String teamB_flag="";

    private String batsman_score="",
            match_nm="",
            match_time="",
            match_date="",
            match_desc="",
            bating_team_shrt_nm="",
            bowling_team_shrt_nm="",
            bating_team_flag="",
            bowling_team_flag="",
            required_run="",
            required_ball="",
            batsmanA_nm="",
            batsmanA_run="",
            batsmanA_ball="",
            batsmanB_nm="",
            batsmanB_run="",
            batsmanB_ball="",
            bowler_wicket="",
            bowler_ball="";



    private String fav="",

            runxa="",
            runxb="",
                    currentOver,lastBall1,lastBall2,lastBall3,lastBall4,lastBall5,lastBall6,
            session_run="",
            session_ball="",
            summary="";


    public String getTeamA_flag() {
        return teamA_flag;
    }

    public void setTeamA_flag(String teamA_flag) {
        this.teamA_flag = teamA_flag;
    }

    public String getTeamB_flag() {
        return teamB_flag;
    }

    public void setTeamB_flag(String teamB_flag) {
        this.teamB_flag = teamB_flag;
    }

    public String getCurrentOver() {
        return currentOver;
    }

    public void setCurrentOver(String currentOver) {
        this.currentOver = currentOver;
    }

    public String getLastBall1() {
        return lastBall1;
    }

    public void setLastBall1(String lastBall1) {
        this.lastBall1 = lastBall1;
    }

    public String getLastBall2() {
        return lastBall2;
    }

    public void setLastBall2(String lastBall2) {
        this.lastBall2 = lastBall2;
    }

    public String getLastBall3() {
        return lastBall3;
    }

    public void setLastBall3(String lastBall3) {
        this.lastBall3 = lastBall3;
    }

    public String getLastBall4() {
        return lastBall4;
    }

    public void setLastBall4(String lastBall4) {
        this.lastBall4 = lastBall4;
    }

    public String getLastBall5() {
        return lastBall5;
    }

    public void setLastBall5(String lastBall5) {
        this.lastBall5 = lastBall5;
    }

    public String getLastBall6() {
        return lastBall6;
    }

    public void setLastBall6(String lastBall6) {
        this.lastBall6 = lastBall6;
    }
    private String appversion="";

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }


    public String getMatch_key() {
        return match_key;
    }

    public void setMatch_key(String match_key) {
        this.match_key = match_key;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

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

    public String getSession_run() {
        return session_run;
    }

    public void setSession_run(String session_run) {
        this.session_run = session_run;
    }

    public String getSession_ball() {
        return session_ball;
    }

    public void setSession_ball(String session_ball) {
        this.session_ball = session_ball;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBatsman_score() {
        return batsman_score;
    }

    public void setBatsman_score(String batsman_score) {
        this.batsman_score = batsman_score;
    }

    public String getMatch_nm() {
        return match_nm;
    }

    public void setMatch_nm(String match_nm) {
        this.match_nm = match_nm;
    }

    public String getMatch_time() {
        return match_time;
    }

    public void setMatch_time(String match_time) {
        this.match_time = match_time;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getMatch_desc() {
        return match_desc;
    }

    public void setMatch_desc(String match_desc) {
        this.match_desc = match_desc;
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

    public String getRequired_run() {
        return required_run;
    }

    public void setRequired_run(String required_run) {
        this.required_run = required_run;
    }

    public String getRequired_ball() {
        return required_ball;
    }

    public void setRequired_ball(String required_ball) {
        this.required_ball = required_ball;
    }

    public String getBatsmanA_nm() {
        return batsmanA_nm;
    }

    public void setBatsmanA_nm(String batsmanA_nm) {
        this.batsmanA_nm = batsmanA_nm;
    }

    public String getBatsmanA_run() {
        return batsmanA_run;
    }

    public void setBatsmanA_run(String batsmanA_run) {
        this.batsmanA_run = batsmanA_run;
    }

    public String getBatsmanA_ball() {
        return batsmanA_ball;
    }

    public void setBatsmanA_ball(String batsmanA_ball) {
        this.batsmanA_ball = batsmanA_ball;
    }

    public String getBatsmanB_nm() {
        return batsmanB_nm;
    }

    public void setBatsmanB_nm(String batsmanB_nm) {
        this.batsmanB_nm = batsmanB_nm;
    }

    public String getBatsmanB_run() {
        return batsmanB_run;
    }

    public void setBatsmanB_run(String batsmanB_run) {
        this.batsmanB_run = batsmanB_run;
    }

    public String getBatsmanB_ball() {
        return batsmanB_ball;
    }

    public void setBatsmanB_ball(String batsmanB_ball) {
        this.batsmanB_ball = batsmanB_ball;
    }

    public String getBowler_wicket() {
        return bowler_wicket;
    }

    public void setBowler_wicket(String bowler_wicket) {
        this.bowler_wicket = bowler_wicket;
    }

    public String getBowler_ball() {
        return bowler_ball;
    }

    public void setBowler_ball(String bowler_ball) {
        this.bowler_ball = bowler_ball;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTeamALogo() {
        return teamALogo;
    }

    public void setTeamALogo(String teamALogo) {
        this.teamALogo = teamALogo;
    }

    public String getTeamBLogo() {
        return teamBLogo;
    }

    public void setTeamBLogo(String teamBLogo) {
        this.teamBLogo = teamBLogo;
    }

    public String getRateA() {
        return rateA;
    }

    public void setRateA(String rateA) {
        this.rateA = rateA;
    }

    public String getSessionOver() {
        return sessionOver;
    }

    public void setSessionOver(String sessionOver) {
        this.sessionOver = sessionOver;
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


    public String getRateB() {
        return rateB;
    }

    public void setRateB(String rateB) {
        this.rateB = rateB;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTotalballs() {
        return totalballs;
    }

    public void setTotalballs(String totalballs) {
        this.totalballs = totalballs;
    }
}
