package com.cricket.livecricketscoreline.constants;

/**
 * Created by admin on 7/18/2017.
 */

public interface Constant {

    int ONE_SECOND = 1000;   //1000 millis = one second
    long API_DELAY_3_SECONDS = ONE_SECOND * 3;
    long API_DELAY_5_SECONDS = ONE_SECOND * 5;
    long API_DELAY_7_SECONDS = ONE_SECOND * 7;
    long API_DELAY_10_SECONDS = ONE_SECOND * 10;
    long API_DELAY_2_SECONDS = 2000;

    static int ONE_MINUTE = ONE_SECOND * 60;
    long API_DELAY_10_MINUTES = ONE_MINUTE * 10;   // 1000*10*60 every 10 minut
}
