package com.cricket.livecricketscoreline.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cricket.livecricketscoreline.LoadingDialog.LoadingDialog;
import com.cricket.livecricketscoreline.MainActivity;
import com.cricket.livecricketscoreline.R;
import com.cricket.livecricketscoreline.Support.Constant;
import com.cricket.livecricketscoreline.model.JSON_DATA;
import com.cricket.livecricketscoreline.model.JSON_RUN;
import com.cricket.livecricketscoreline.model.Match;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Details.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Details extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static int abc = 0;
    CardView card1;

    private Toolbar toolbar;
    private TextView toolbartitle;
    private ImageView img_logo,imglogo;

    private DatabaseReference criclineDataRef;
    private DatabaseReference jsondataRef;
    private DatabaseReference jsonrunsRef;
    private DatabaseReference jsonRef;
    private FirebaseDatabase mFirebaseInstance;
    ArrayList<Match> universityList = new ArrayList<>();


    TextView summary;
    TextView teama;
    TextView teamb;
    TextView title;
    TextView overs;
    TextView scorea;
    TextView scoreb;
    TextView rate;
    TextView rateee;
    TextView fav;
    TextView sessionOver;
    TextView session;
    TextView sessionb;
    TextView sessionrunx;

    TextView bowler,Bowler;
    TextView sbatsmen;
    TextView batsman;
    TextView strikerruns;
    TextView strikerballs;
    TextView nonstrikerruns;
    TextView nonstrikerballs;
    TextView target;
    TextView score;

    TextView team_b,team_a,rate_a,rate_b,match_date,match_time,match_desc,ballrate,ball_rate
            ,run_rate,runrate,
            over_rate,overrate,
            teamb_rate,team_b_rate,
            teama_rate,team_a_rate,
            bat_batsman,overview,match_overview,
    BatsmanA_nm,fav_team,currentover,ball1,ball2,ball3,ball4,ball5,ball6,
                        BatsmanA_run,
                        BatsmanA_ball,
                        BatsmanB_nm,
                        BatsmanB_run,
                        BatsmanB_ball;

    InterstitialAd mInterstitialAd;
    InterstitialAd mInterstitialAd1;
    DatabaseReference Match1;

    private LoadingDialog mDialog;
    private OnFragmentInteractionListener mListener;

    ImageView img_bating,img_bowling;

    private LinearLayout layout_bowlertitle;


    public Fragment_Details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Details.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Details newInstance(String param1, String param2) {
        Fragment_Details fragment = new Fragment_Details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment__details, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mFirebaseInstance = FirebaseDatabase.getInstance();

        criclineDataRef =  FirebaseDatabase.getInstance().getReference();


        LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(getActivity())
                .setCancelable(true)
                .setCancelOutside(false);
        mDialog = loadBuilder.create();



        toolbar = (Toolbar)view. findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        MobileAds.initialize(getActivity(),
                "ca-app-pub-3201092669740213~8019704401");


        AdView mAdView = (AdView)view. findViewById(R.id.adView);
        AdView mAd_View = (AdView)view. findViewById(R.id.ad_View);
        mAd_View.setVisibility(View.VISIBLE);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdRequest ad_Request = new AdRequest.Builder().build();
        mAd_View.loadAd(ad_Request);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-1529332403265862/8651379356");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd1 = new InterstitialAd(getActivity());
        mInterstitialAd1.setAdUnitId("ca-app-pub-1529332403265862/8651379356");
        mInterstitialAd1.loadAd(new AdRequest.Builder().build());


        /*mInterstitialAd = new InterstitialAd(getActivity());

        mInterstitialAd.setAdUnitId("ca-app-pub-3201092669740213/6706622737");
        mInterstitialAd1 = new InterstitialAd(getActivity());
        mInterstitialAd1.setAdUnitId("ca-app-pub-1529332403265862/8651379356");
        AdRequest adRequest1 = new AdRequest.Builder().build();
        AdRequest adRequest2 = new AdRequest.Builder().build();


        mInterstitialAd.loadAd(adRequest1);
        mInterstitialAd1.loadAd(adRequest2);
*/








        img_logo= view.findViewById(R.id.img_logo);
        imglogo= view.findViewById(R.id.imglogo);
        toolbartitle= view.findViewById(R.id.toolbartitle);
        //toolbartitle.setText("Match Details");
        img_logo.setVisibility(View.GONE);
        imglogo.setVisibility(View.VISIBLE);
        imglogo.setImageDrawable(getResources().getDrawable(R.drawable.ic_left_arrow));


        /*
         *
         * Click Event
         * */

        imglogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // getActivity().onBackPressed();
                ((MainActivity)getActivity()).Tab();
                ((MainActivity)getActivity()).firstTab();
            }
        });



        summary = (TextView)view. findViewById(R.id.summary);
        teama = (TextView)view. findViewById(R.id.teama);
        teamb = (TextView)view. findViewById(R.id.teamb);
        overs = (TextView)view. findViewById(R.id.overs);
        scorea = (TextView)view. findViewById(R.id.some_id);
        scoreb = (TextView) view.findViewById(R.id.scoreb);
        rate = (TextView) view.findViewById(R.id.rate);
       // rateee = (TextView)view. findViewById(R.id.rateee);
       // fav = (TextView) view.findViewById(R.id.fav);
        sessionOver = (TextView)view. findViewById(R.id.sessionover);
        session = (TextView)view. findViewById(R.id.session);
        sessionb = (TextView)view. findViewById(R.id.sessionb);
       // sessionrunx = (TextView)view. findViewById(R.id.sessionrunx);
        title = (TextView)view. findViewById(R.id.title);
        bowler = (TextView) view.findViewById(R.id.bowlertitle);
        Bowler= (TextView) view.findViewById(R.id.Bowler);
        //sbatsmen = (TextView)view. findViewById(R.id.strikerbatsman);
        batsman = (TextView)view. findViewById(R.id.batsman);
       // strikerruns = (TextView)view. findViewById(R.id.strikerruns);
       // strikerballs = (TextView)view. findViewById(R.id.strikerballs);
       // nonstrikerruns = (TextView)view. findViewById(R.id.nonstrikerruns);
       // nonstrikerballs = (TextView) view.findViewById(R.id.nonstrikerballs);
       // target = (TextView) view.findViewById(R.id.target);
        //imageView1.setBackgroundResource(R.drawable.india);
        //imageView2.setBackgroundResource(R.drawable.england);
       // score = (TextView) view.findViewById(R.id.textView99);
        //score.setMovementMethod(new ScrollingMovementMethod());

        fav_team=view.findViewById(R.id.fav_team);
        team_b=view.findViewById(R.id.team_b);
        team_a=view.findViewById(R.id.team_a);
        rate_a=view.findViewById(R.id.rate_a);
        rate_b=view.findViewById(R.id.rate_b);
        match_date=view.findViewById(R.id.match_data);
        match_time=view.findViewById(R.id.match_time);
        match_desc=view.findViewById(R.id.match_desc);

        ballrate=view.findViewById(R.id.ballrate);
        ball_rate=view.findViewById(R.id.ball_rate);
        run_rate=view.findViewById(R.id.run_rate);
        runrate=view.findViewById(R.id.runrate);
        over_rate=view.findViewById(R.id.over_rate);
        overrate=view.findViewById(R.id.overrate);
        teamb_rate=view.findViewById(R.id.teamb_rate);
        team_b_rate=view.findViewById(R.id.team_b_rate);
        teama_rate=view.findViewById(R.id.teama_rate);
        team_a_rate=view.findViewById(R.id.team_a_rate);
        bat_batsman=view.findViewById(R.id.bat_batsman);
        match_overview=view.findViewById(R.id.match_overview);
        overview=view.findViewById(R.id.overview);
        BatsmanA_nm=view.findViewById(R.id.BatsmanA_nm);
                BatsmanA_run=view.findViewById(R.id.BatsmanA_run);
                BatsmanA_ball=view.findViewById(R.id.BatsmanA_ball);
                BatsmanB_nm=view.findViewById(R.id.BatsmanB_nm);
                BatsmanB_run=view.findViewById(R.id.BatsmanB_run);
                BatsmanB_ball=view.findViewById(R.id.BatsmanB_ball);

        layout_bowlertitle =view.findViewById(R.id.layout_bowlertitle);


        currentover=view.findViewById(R.id.currentover);
        ball1=view.findViewById(R.id.ball1);
        ball2=view.findViewById(R.id.ball2);
        ball3=view.findViewById(R.id.ball3);
        ball4=view.findViewById(R.id.ball4);
        ball5=view.findViewById(R.id.ball5);
        ball6=view.findViewById(R.id.ball6);

        card1 =view. findViewById(R.id.card1);

        img_bating=view.findViewById(R.id.img_bating);
        img_bowling=view.findViewById(R.id.img_bowling);


        hideSoftKeyboard(getActivity());
        mDialog.show();

        String key = Constant.match_id;

        if(!key.equalsIgnoreCase("current")) {



            DatabaseReference Match1 = mFirebaseInstance.getReference(key);

            Match1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    universityList.clear();
                    Match data = new Match();

                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {


                        if (postSnapshot.getKey().equalsIgnoreCase("jsondata")) {


                            JSON_DATA university = postSnapshot.getValue(JSON_DATA.class);


                            data.setTeamA(university.getTeamA());
                            data.setTeamB(university.getTeamB());
                            data.setOversA(university.getOversA());
                            data.setOversB(university.getOversB());
                            data.setRateA(university.getRateA());
                            data.setSessionA(university.getSessionA());
                            data.setWicketA(university.getWicketA());
                            data.setWicketB(university.getWicketB());
                            data.setAppversion(university.getAppversion());
                            data.setBatsman(university.getBatsman());
                            data.setScore(university.getScore());
                            data.setSessionOver(university.getSessionOver());
                            data.setTitle(university.getTitle());
                            data.setTotalballs(university.getTotalballs());
                            data.setBowler(university.getBowler());


                            data.setBatsman_score(university.getBatsman_score());
                            data.setMatch_nm(university.getMatch_nm());
                            data.setMatch_time(university.getMatch_time());
                            data.setMatch_date(university.getMatch_date());
                            data.setMatch_desc(university.getMatch_desc());

                            data.setBating_team_shrt_nm(university.getBating_team_shrt_nm());
                            data.setBowling_team_shrt_nm(university.getBowling_team_shrt_nm());
                            data.setBating_team_flag(university.getBating_team_flag());
                            data.setBowling_team_flag(university.getBowling_team_flag());
                            data.setRequired_run(university.getRequired_run());
                            data.setRequired_ball(university.getRequired_ball());

                            data.setBatsmanA_nm(university.getBatsmanA_nm());
                            data.setBatsmanA_run(university.getBatsmanA_run());
                            data.setBatsmanA_ball(university.getBatsmanA_ball());
                            data.setBatsmanB_nm(university.getBatsmanB_nm());
                            data.setBatsmanB_run(university.getBatsmanB_run());
                            data.setBatsmanB_ball(university.getBatsmanB_ball());

                            data.setBowler_ball(university.getBowler_ball());
                            data.setBowler_wicket(university.getBowler_wicket());

                            String teama_ = data.getTeamA();
                            teama.setText(teama_);
                            // team_a.setText(teama_);
                            teamb.setText(data.getTeamB().toString());
                           // team_b.setText(data.getBowling_team_shrt_nm().toString());

                            scorea.setText(data.getWicketA().toString());
                            scoreb.setText(data.getWicketB().toString());
                            overs.setText(data.getOversA().toString());
                            title.setText(data.getMatch_nm().toString());
                            match_date.setText(data.getMatch_date().toString());
                            match_time.setText(data.getMatch_time().toString());
                            match_desc.setText(data.getMatch_desc().toString());
                            bat_batsman.setText("Batsman : " + data.getBatsman() + " " + data.getBatsman_score().toString());
                            //match_overview.setText(data.getBating_team_shrt_nm().toString()+" needs "+data.getRequired_run()+ " in "+data.getRequired_ball()+" overs");
                            Bowler.setText(data.getBowler().toString());
                            String  overs=data.getOversA();
                            String  scorea=data.getWicketA();
                            String scoreb=data.getWicketB();
                            if (data.getTotalballs() != null) {
                                if (!data.getTotalballs().equalsIgnoreCase("")) {
                                    if (!data.getTotalballs().equalsIgnoreCase("0")) {
                                        match_overview.setVisibility(View.VISIBLE);
                                       // overview.setVisibility(View.VISIBLE);
                                        int remainingruns = (Integer.parseInt(scoreb.toString().substring(0, scoreb.toString().indexOf("/"))) - Integer.parseInt(scorea.toString().substring(0, scorea.toString().indexOf("/")))) + 1;
                                        int remainingballs = Integer.parseInt(data.getTotalballs()) - ((Integer.parseInt(overs.toString().substring(0, overs.toString().indexOf("."))) * 6)) - Integer.parseInt(overs.toString().substring(overs.toString().indexOf(".") + 1));
                                       // match_overview.setText(data.getTeamA() + " needs " + remainingruns + " runs in " + remainingballs + " balls to win");


                                        if(remainingruns>0) {
                                           match_overview.setText(data.getTeamA() + " needs " + remainingruns + " runs in " + remainingballs + " balls to win");
                                        }
                                        else
                                        {
                                           match_overview.setVisibility(View.GONE);
                                           // overview.setVisibility(View.GONE);
                                        }


                                    } else {
                                         match_overview.setVisibility(View.GONE);
                                       // overview.setVisibility(View.GONE);

                                    }
                                } else {
                                     match_overview.setVisibility(View.GONE);
                                  //  overview.setVisibility(View.GONE);
                                }
                            } else {
                                 match_overview.setVisibility(View.GONE);
                               // overview.setVisibility(View.GONE);
                            }
                            if(data.getOversB().contains("|")) {
                                String left = data.getOversB().substring(0, data.getOversB().indexOf("|"));
                                sessionOver.setText(left);
                            }

                            BatsmanA_nm.setText(data.getBatsman().substring(0, data.getBatsman().indexOf("|")));
                            BatsmanB_nm.setText(data.getBatsman().substring(data.getBatsman().indexOf("|") + 1));
                            String left = data.getOversB().substring(0, data.getOversB().indexOf("|"));
                            String right = data.getOversB().substring(data.getOversB().indexOf("|") + 1);
                            BatsmanB_ball.setText(right.split(",")[0]);
                            BatsmanA_ball.setText(right.split(",")[1]);
                            BatsmanB_run.setText(left.split(",")[0]);
                            BatsmanA_run.setText(left.split(",")[1]);


                           /* BatsmanA_nm.setText(data.getBatsmanA_nm().toString());
                            BatsmanA_run.setText(data.getBatsmanA_run().toString());
                            BatsmanA_ball.setText(data.getBatsmanA_ball().toString());
                            BatsmanB_nm.setText(data.getBatsmanB_nm().toString());
                            BatsmanB_run.setText(data.getBatsmanB_run().toString());
                            BatsmanB_ball.setText(data.getBatsmanB_ball().toString());*/
                            bowler.setText(data.getScore().toString());
                            String flag1 = data.getBating_team_flag();

                            String flag2 = data.getBowling_team_flag();




                            if(!flag1.equalsIgnoreCase("")) {
                                Picasso.get()
                                        .load(flag1)
                                        .placeholder(R.drawable.ic_landscape_image)
                                        .error(R.drawable.blue_bg)
                                        .into(img_bating);
                            }
                            else
                            {
                                Picasso.get().load(R.drawable.blue_bg)
                                        .placeholder(R.drawable.ic_landscape_image)
                                        .error(R.drawable.blue_bg)
                                        .into(img_bating);
                            }


                            if(!flag2.equalsIgnoreCase("")) {
                                Picasso.get()
                                        .load(flag2)
                                        .placeholder(R.drawable.ic_landscape_image)
                                        .error(R.drawable.red_bg)
                                        .into(img_bowling);
                            }
                            else
                            {
                                Picasso.get()
                                        .load(R.drawable.red_bg)
                                        .placeholder(R.drawable.ic_landscape_image)
                                        .error(R.drawable.red_bg)
                                        .into(img_bowling);
                            }

                            if (data.getBowler().equalsIgnoreCase("0")) {


                                try {
                                    mInterstitialAd.setAdListener(new AdListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            if (abc == 0) {
                                                //abc = abc + 1;
                                                if (mInterstitialAd != null) {
                                                    mInterstitialAd.show();

                                                }

                                            }
                                        }

                                        @Override
                                        public void onAdFailedToLoad(int errorCode) {
                                            // no interstitial available
                                            mInterstitialAd = null;
                                        }

                                    });
                                } catch (Exception ex) {
                                    Log.e("Adds Error : ",ex.getMessage());
                                }

                                try {
                                    mInterstitialAd1.setAdListener(new AdListener() {
                                        @Override
                                        public void onAdLoaded() {


                                            if (mInterstitialAd1 != null) {
                                                mInterstitialAd1.show();

                                            }


                                        }

                                        @Override
                                        public void onAdFailedToLoad(int errorCode) {
                                            // no interstitial available
                                            mInterstitialAd1 = null;
                                        }

                                    });
                                } catch (Exception ex) {
                                    Log.e("Adds2 Error : ",ex.getMessage());
                                }

                            }

                        }

                        if (postSnapshot.getKey().equalsIgnoreCase("jsonruns")) {

                            JSON_RUN university = postSnapshot.getValue(JSON_RUN.class);
                            // universityList.add(university);
                            data.setRateA(university.getRateA());
                            data.setRateB(university.getRateB());
                            data.setRunxa(university.getRunxa());
                            data.setRunxb(university.getRunxb());
                            data.setSessionA(university.getSessionA());
                            data.setSessionB(university.getSessionB());
                            data.setSessionOver(university.getSessionOver());
                            data.setSession_run(university.getSession_run());
                            data.setSession_ball(university.getSession_ball());
                            data.setFav(university.getFav());
                            data.setSummary(university.getSummary());
                            data.setCurrentOver(university.getCurrentOver());
                            data.setLastBall1(university.getLastBall1());
                            data.setLastBall2(university.getLastBall2());
                            data.setLastBall3(university.getLastBall3());
                            data.setLastBall4(university.getLastBall4());
                            data.setLastBall5(university.getLastBall5());
                            data.setLastBall6(university.getLastBall6());



                            int raterun = Integer.parseInt(data.getSessionB());
                            String act_run = scorea.getText().toString() ;
                            int runs = (raterun - Integer.parseInt(act_run.toString().substring(0, act_run.toString().indexOf("/"))));


                            runrate.setText(String.valueOf(runs));



                            String  Sovers=data.getOversA();
                            int over_rate= Integer.parseInt(data.getSessionOver()) * 6;
                            int balls = ((Integer.parseInt(Sovers.toString().substring(0, Sovers.toString().indexOf("."))) * 6)) +
                                    Integer.parseInt(Sovers.toString().substring(Sovers.toString().indexOf(".") + 1));
                            int total_balls = over_rate - balls;
                            ballrate.setText(String.valueOf(total_balls));/*matchData.getRunxb().toString());*/



                            //  rate_a.setText(data.getRateA().toString()+"%");
                            //   rate_b.setText(data.getRateB().toString()+"%");
                            //ballrate.setText(data.getRunxb().toString());
                            //  holder.ball_rate.setText(matchData.getMatch_desc().toString());
                            // holder.run_rate.setText(matchData.getMatch_desc().toString());
                            //runrate.setText(data.getRunxa().toString());
                            //holder.over_rate.setText(matchData.getMatch_desc().toString());
                            overrate.setText(data.getSessionOver().toString());
                           // teamb_rate.setText(data.getBowling_team_shrt_nm().toString());
                            team_b_rate.setText(data.getSessionB().toString());
                           // teama_rate.setText(data.getBating_team_shrt_nm().toString());
                            team_a_rate.setText(data.getSessionA().toString());

                           rate_a.setText(data.getRateA().toString());
                            rate_b.setText(data.getRateB().toString());
                             fav_team.setText(data.getFav());


                            currentover.setText("OVER : "+data.getOversA());
                            ball1.setText(data.getLastBall1());
                            ball2.setText(data.getLastBall2());
                            ball3.setText(data.getLastBall3());
                            ball4.setText(data.getLastBall4());
                            ball5.setText(data.getLastBall5());
                            ball6.setText(data.getLastBall6());



                            summary.setText(data.getSummary());


                            data.setMatch_key("match1");

                            universityList.add(data);
                            mDialog.dismiss();
                            // }
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    mDialog.dismiss();
                }
            });





        }
        else {


            Match1 = FirebaseDatabase.getInstance().getReference();
            Match1.keepSynced(true);
            Match1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    universityList.clear();
                    Match data = new Match();

                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {


                        if (postSnapshot.getKey().equalsIgnoreCase("jsondata")) {


                            JSON_DATA university = postSnapshot.getValue(JSON_DATA.class);


                            data.setTeamA(university.getTeamA());
                            data.setTeamB(university.getTeamB());
                            data.setOversA(university.getOversA());
                            data.setOversB(university.getOversB());
                            data.setRateA(university.getRateA());
                            data.setSessionA(university.getSessionA());
                            data.setWicketA(university.getWicketA());
                            data.setWicketB(university.getWicketB());
                            data.setAppversion(university.getAppversion());
                            data.setBatsman(university.getBatsman());
                            data.setScore(university.getScore());
                            data.setSessionOver(university.getSessionOver());
                            data.setTitle(university.getTitle());
                            data.setTotalballs(university.getTotalballs());
                            data.setBowler(university.getBowler());


                            data.setBatsman_score(university.getBatsman_score());
                            data.setMatch_nm(university.getMatch_nm());
                            data.setMatch_time(university.getMatch_time());
                            data.setMatch_date(university.getMatch_date());
                            data.setMatch_desc(university.getMatch_desc());

                            data.setBating_team_shrt_nm(university.getBating_team_shrt_nm());
                            data.setBowling_team_shrt_nm(university.getBowling_team_shrt_nm());
                            data.setBating_team_flag(university.getTeamA_flag());
                            data.setBowling_team_flag(university.getTeamB_flag());
                            data.setRequired_run(university.getRequired_run());
                            data.setRequired_ball(university.getRequired_ball());

                            data.setBatsmanA_nm(university.getBatsmanA_nm());
                            data.setBatsmanA_run(university.getBatsmanA_run());
                            data.setBatsmanA_ball(university.getBatsmanA_ball());
                            data.setBatsmanB_nm(university.getBatsmanB_nm());
                            data.setBatsmanB_run(university.getBatsmanB_run());
                            data.setBatsmanB_ball(university.getBatsmanB_ball());

                            data.setBowler_ball(university.getBowler_ball());
                            data.setBowler_wicket(university.getBowler_wicket());

                            String teama_=data.getBating_team_shrt_nm();
                            teama.setText(teama_);
                            // team_a.setText(teama_);
                            teamb.setText(data.getBowling_team_shrt_nm().toString());
                            // team_b.setText(data.getBowling_team_shrt_nm().toString());
                          //  team_a.setText(teama_);
                           //team_b.setText(data.getBowling_team_shrt_nm().toString());
                            scorea.setText(data.getWicketA().toString());
                            scoreb.setText(data.getWicketB().toString());
                            overs.setText(data.getOversA().toString());
                            title.setText(data.getMatch_nm().toString());
                            match_date.setText(data.getMatch_date().toString());
                            match_time.setText(data.getMatch_time().toString());
                            match_desc.setText(data.getMatch_desc().toString());
                            bat_batsman.setText("Batsman : "+data.getBatsman()+" "+data.getBatsman_score().toString());
                            //match_overview.setText(data.getBating_team_shrt_nm().toString()+" needs "+data.getRequired_run()+ " in "+data.getRequired_ball()+" overs");

                            String  overs=data.getOversA();
                            String  scorea=data.getWicketA();
                            String scoreb=data.getWicketB();
                            if (data.getTotalballs() != null) {
                                if (!data.getTotalballs().equalsIgnoreCase("")) {
                                    if (!data.getTotalballs().equalsIgnoreCase("0")) {
                                        match_overview.setVisibility(View.VISIBLE);
                                        overview.setVisibility(View.VISIBLE);
                                        int remainingruns = (Integer.parseInt(scoreb.toString().substring(0, scoreb.toString().indexOf("/"))) - Integer.parseInt(scorea.toString().substring(0, scorea.toString().indexOf("/")))) + 1;
                                        int remainingballs = Integer.parseInt(data.getTotalballs()) - ((Integer.parseInt(overs.toString().substring(0, overs.toString().indexOf("."))) * 6)) - Integer.parseInt(overs.toString().substring(overs.toString().indexOf(".") + 1));
                                        // match_overview.setText(data.getTeamA() + " needs " + remainingruns + " runs in " + remainingballs + " balls to win");


                                        if(remainingruns>0) {
                                            match_overview.setText(data.getTeamA() + " needs " + remainingruns + " runs in " + remainingballs + " balls to win");
                                        }
                                        else
                                        {
                                            match_overview.setVisibility(View.GONE);
                                            overview.setVisibility(View.GONE);
                                        }


                                    } else {
                                        match_overview.setVisibility(View.GONE);
                                        overview.setVisibility(View.GONE);

                                    }
                                } else {
                                    match_overview.setVisibility(View.GONE);
                                    overview.setVisibility(View.GONE);
                                }
                            } else {
                                match_overview.setVisibility(View.GONE);
                                overview.setVisibility(View.GONE);
                            }
                            if(data.getOversB().contains("|")) {
                                String left = data.getOversB().substring(0, data.getOversB().indexOf("|"));
                                sessionOver.setText(left);
                            }

                            BatsmanA_nm.setText(data.getBatsman().substring(0, data.getBatsman().indexOf("|")));
                            BatsmanB_nm.setText(data.getBatsman().substring(data.getBatsman().indexOf("|") + 1));
                            String left = data.getOversB().substring(0, data.getOversB().indexOf("|"));
                            String right = data.getOversB().substring(data.getOversB().indexOf("|") + 1);
                            BatsmanB_ball.setText(right.split(",")[0]);
                            BatsmanA_ball.setText(right.split(",")[1]);
                            BatsmanB_run.setText(left.split(",")[0]);
                            BatsmanA_run.setText(left.split(",")[1]);


                           /* BatsmanA_nm.setText(data.getBatsmanA_nm().toString());
                            BatsmanA_run.setText(data.getBatsmanA_run().toString());
                            BatsmanA_ball.setText(data.getBatsmanA_ball().toString());
                            BatsmanB_nm.setText(data.getBatsmanB_nm().toString());
                            BatsmanB_run.setText(data.getBatsmanB_run().toString());
                            BatsmanB_ball.setText(data.getBatsmanB_ball().toString());*/
                            bowler.setText(data.getScore().toString());
                            String flag1 = data.getBating_team_flag();

                            String flag2 = data.getBowling_team_flag();

                            if(!flag1.equalsIgnoreCase("")) {
                                Picasso.get()
                                        .load(flag1)
                                        .placeholder(R.drawable.ic_landscape_image)
                                        .error(R.drawable.blue_bg)
                                        .into(img_bating);
                            }
                            else
                            {
                                Picasso.get().load(R.drawable.blue_bg)
                                        .placeholder(R.drawable.ic_landscape_image)
                                        .error(R.drawable.blue_bg)
                                        .into(img_bating);
                            }


                            if(!flag2.equalsIgnoreCase("")) {
                                Picasso.get()
                                        .load(flag2)
                                        .placeholder(R.drawable.ic_landscape_image)
                                        .error(R.drawable.red_bg)
                                        .into(img_bowling);
                            }
                            else
                            {
                                Picasso.get()
                                        .load(R.drawable.red_bg)
                                        .placeholder(R.drawable.ic_landscape_image)
                                        .error(R.drawable.red_bg)
                                        .into(img_bowling);
                            }







                            if (data.getBowler().equalsIgnoreCase("0")) {


                            try {
                                mInterstitialAd.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        if (abc == 0) {
                                            //abc = abc + 1;
                                            if (mInterstitialAd != null) {
                                                mInterstitialAd.show();

                                            }

                                        }
                                    }

                                    @Override
                                    public void onAdFailedToLoad(int errorCode) {
                                        // no interstitial available
                                        mInterstitialAd = null;
                                    }

                                });
                            } catch (Exception ex) {
                                Log.e("Adds Error : ",ex.getMessage());
                            }

                            try {
                                mInterstitialAd1.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdLoaded() {


                                        if (mInterstitialAd1 != null) {
                                            mInterstitialAd1.show();

                                        }


                                    }

                                    @Override
                                    public void onAdFailedToLoad(int errorCode) {
                                        // no interstitial available
                                        mInterstitialAd1 = null;
                                    }

                                });
                            } catch (Exception ex) {
                                Log.e("Adds2 Error : ",ex.getMessage());
                            }

                        }

                    }

                        if(postSnapshot.getKey().equalsIgnoreCase("jsonruns")) {

                            JSON_RUN university = postSnapshot.getValue(JSON_RUN.class);
                            // universityList.add(university);
                            data.setRateA(university.getRateA());
                            data.setRateB(university.getRateB());
                            data.setRunxa(university.getRunxa());
                            data.setRunxb(university.getRunxb());
                            data.setSessionA(university.getSessionA());
                            data.setSessionB(university.getSessionB());
                            data.setSessionOver(university.getSessionOver());
                            data.setSession_run(university.getSession_run());
                            data.setSession_ball(university.getSession_ball());
                            data.setFav(university.getFav());
                            data.setSummary(university.getSummary());
                            data.setCurrentOver(university.getCurrentOver());
                            data.setLastBall1(university.getLastBall1());
                            data.setLastBall2(university.getLastBall2());
                            data.setLastBall3(university.getLastBall3());
                            data.setLastBall4(university.getLastBall4());
                            data.setLastBall5(university.getLastBall5());
                            data.setLastBall6(university.getLastBall6());

                            //  rate_a.setText(data.getRateA().toString()+"%");
                            //   rate_b.setText(data.getRateB().toString()+"%");


                            int raterun = Integer.parseInt(data.getSessionB());
                            String act_run =  scorea.getText().toString() ;
                            int runs = (raterun - Integer.parseInt(act_run.toString().substring(0, act_run.toString().indexOf("/"))));


                            runrate.setText(String.valueOf(runs));


                            String  Sovers=data.getOversA();
                            int over_rate= Integer.parseInt(data.getSessionOver()) * 6;
                            int balls = ((Integer.parseInt(Sovers.toString().substring(0, Sovers.toString().indexOf("."))) * 6)) +
                                    Integer.parseInt(Sovers.toString().substring(Sovers.toString().indexOf(".") + 1));
                            int total_balls = over_rate - balls;
                           ballrate.setText(String.valueOf(total_balls));/*matchData.getRunxb().toString());*/





                            //ballrate.setText(data.getSession_ball().toString());
                            //  holder.ball_rate.setText(matchData.getMatch_desc().toString());
                            // holder.run_rate.setText(matchData.getMatch_desc().toString());
                           // runrate.setText(data.getSession_run().toString());
                            //holder.over_rate.setText(matchData.getMatch_desc().toString());
                            overrate.setText(data.getSessionOver().toString());
                           // teamb_rate.setText(data.getBowling_team_shrt_nm().toString());
                            team_b_rate.setText(data.getSessionB().toString());
                           // teama_rate.setText(data.getBating_team_shrt_nm().toString());
                            team_a_rate.setText(data.getSessionA().toString());

                            rate_a.setText(data.getRateA().toString());
                            rate_b.setText(data.getRateB().toString());
                            fav_team.setText(data.getFav());

                            summary.setText(data.getSummary());

                           // currentover.setText("OVER : "+data.getCurrentOver());
                            currentover.setText("OVER : "+data.getOversA());
                            ball1.setText(data.getLastBall1());
                            ball2.setText(data.getLastBall2());
                            ball3.setText(data.getLastBall3());
                            ball4.setText(data.getLastBall4());
                            ball5.setText(data.getLastBall5());
                            ball6.setText(data.getLastBall6());

                            data.setMatch_key("match1");

                            universityList.add(data);
                            mDialog.dismiss();
                            // }
                        }


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    mDialog.dismiss();
                }
            });
        }



    }


    public void init()
    {








    }


    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    ((MainActivity)getActivity()).Tab();
                    ((MainActivity)getActivity()).firstTab();

                    return true;
                }
                return false;
            }
        });
    }


}
