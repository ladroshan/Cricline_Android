package com.cricket.livecricketscoreline.network;

import android.content.Context;

import com.cricket.livecricketscoreline.network.Volley;
import com.cricket.livecricketscoreline.network.VollyResponceListener;

/**
 * Created by admin on 7/15/2017.
 */

public class NetworkTask {

    public static String SERVER_URL=null;
    private static NetworkTask networkTask;
    Volley volley;
    public static NetworkTask getInstance(Context context) {
        if (networkTask == null) {
            networkTask = new NetworkTask(context);
        }
        return networkTask;
    }
   // private static final String API_KEY = BuildCon;

    private NetworkTask(Context context) {
        volley = Volley.getInstance(context);
    }

    public void getCricData(String TAG, VollyResponceListener vollyResponceListener) {
        volley.executeStringRequest(SERVER_URL, TAG, vollyResponceListener);
    }
}
