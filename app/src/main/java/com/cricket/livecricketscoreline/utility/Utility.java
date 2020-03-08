package com.cricket.livecricketscoreline.utility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.cricket.livecricketscoreline.BuildConfig;

/**
 * Created by admin on 7/17/2017.
 */

public class Utility {
    private static Utility utility;

    Context context;

    public static Utility getInstance(Context context) {
        if (utility == null) {
            utility = new Utility(context);
        }
        return utility;
    }

    private Utility(Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void openPlayStore(){
        final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
    public int getVersionCode(){
        int versionCode=2000;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;
            versionCode=pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
            versionCode=getVCode();
        }
        return versionCode;

    }

    public int getVCode(){
        int versionCode = BuildConfig.VERSION_CODE;
        return versionCode;
    }
    public void showSnackBarToast(View view, String message) {
        /*Snackbar snackBar = Snackbar.make(view,message, Snackbar.LENGTH_LONG);
        // snackBar.set
        View snackBarView = snackBar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_grey)); // Navigation Bg Color
        TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(context, R.color.white));
        tv.setMaxLines(4);
        snackBar.show();*/
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
