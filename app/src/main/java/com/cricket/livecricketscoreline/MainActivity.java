package com.cricket.livecricketscoreline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cricket.livecricketscoreline.Fragments.Fragment_TimeTable;
import com.cricket.livecricketscoreline.Fragments.Fragment_home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private LinearLayout layout_order,layout_offer,layout_user,layout_swap,layout_swapHistory;
    private ImageView img_order,img_offer,img_swap,img_edit,img_swapHistory;
    private TextView txt_order,txt_offer,toolbartitle,txt_swap,txt_swapHistory;
    String tab="one";
    boolean doubleBackToExitPressedOnce = false;

    // InterstitialAd mInterstitialAd;
    //InterstitialAd mInterstitialAd1;

    private static final String TAG = "AnonymousAuth";

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();



        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
// Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
// If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            try {
                                throw new Exception("Authentication Failed.");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

// ...
                    }
                });


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



        /*
         *
         * Loading Dialog
         *
         * */








        // AdView mAdView = (AdView) findViewById(R.id.adView);
        // AdRequest adRequest = new AdRequest.Builder().build();
        //  mAdView.loadAd(adRequest);
        //   mInterstitialAd = new InterstitialAd(MainActivity.this);

        //  mInterstitialAd.setAdUnitId("ca-app-pub-4299669739563213/1226103917");
        //  mInterstitialAd1 = new InterstitialAd(MainActivity.this);
        //  mInterstitialAd1.setAdUnitId("ca-app-pub-4299669739563213/1226103917");
        // test id mInterstitialAd1.setAdUnitId("ca-app-pub-1529332403265862/9801032633");
        // AdRequest adRequest1 = new AdRequest.Builder().build();
        //  AdRequest adRequest2 = new AdRequest.Builder().build();


        //  mInterstitialAd.loadAd(adRequest1);
        // mInterstitialAd1.loadAd(adRequest2);

        Fragment fragment =new Fragment_home();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(R.id.container, fragment);
        ft.addToBackStack("confirm_pending");
        ft.commit();
        tab="one";

        layout_order= (LinearLayout) findViewById(R.id.layout_order);
        layout_offer= (LinearLayout) findViewById(R.id.layout_offer);

        img_order= (ImageView) findViewById(R.id.img_company_details);
        img_offer= (ImageView) findViewById(R.id.img_price);

        txt_order= (TextView) findViewById(R.id.txt_company_Details);
        txt_offer= (TextView) findViewById(R.id.txt_price);




        layout_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstTab();
            }
        });

        layout_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secondTab();
            }
        });





    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }






    public void Tab()
    {
        tab = "";
    }


    public void firstTab()
    {
        if(tab.equals("one"))
        {

        }
        else
        {
            Fragment fragment =new Fragment_home();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // ft .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
            ft.replace(R.id.container, fragment);
           // ft.addToBackStack("confirm_pending");
            ft.commit();
            tab="one";
        }
        img_order.setColorFilter(getResources().getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
        txt_order.setTextColor(getResources().getColor(R.color.colorPrimary));

        img_offer.setColorFilter(getResources().getColor(R.color.newicontcolor), android.graphics.PorterDuff.Mode.SRC_IN);
        txt_offer.setTextColor(getResources().getColor(R.color.newtextcolor));


                /*img_profile.setColorFilter(getResources().getColor(R.color.bb_inActiveBottomBarItemColor), android.graphics.PorterDuff.Mode.SRC_IN);
                txt_profile.setTextColor(getResources().getColor(R.color.bb_inActiveBottomBarItemColor));*/
        //layout_order.setBackgroundResource(R.drawable.button_outline_bg);
        layout_offer.setBackgroundResource(android.R.color.transparent);

    }
    public void secondTab()
    {
        if(tab.equals("two"))
        {

        }
        else
        {
            //getCars();

            Fragment fragment =new Fragment_TimeTable();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // ft .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
            ft.replace(R.id.container, fragment);
            //ft.addToBackStack("confirm_pending");
            ft.commit();
            tab="one";

            tab="two";
        }


        img_offer.setColorFilter(getResources().getColor(R.color.tab_color), android.graphics.PorterDuff.Mode.SRC_IN);
        txt_offer.setTextColor(getResources().getColor(R.color.tab_color));
        img_order.setColorFilter(getResources().getColor(R.color.newicontcolor), android.graphics.PorterDuff.Mode.SRC_IN);
        txt_order.setTextColor(getResources().getColor(R.color.newtextcolor));

        // layout_offer.setBackgroundResource(R.drawable.button_outline_bg);
        layout_order.setBackgroundResource(android.R.color.transparent);

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }


        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit app", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}