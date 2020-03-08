package com.cricket.livecricketscoreline.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cricket.livecricketscoreline.LoadingDialog.LoadingDialog;
import com.cricket.livecricketscoreline.MainActivity;
import com.cricket.livecricketscoreline.R;
import com.cricket.livecricketscoreline.Support.Constant;
import com.cricket.livecricketscoreline.Support.GridSpacingItemDecoration;
import com.cricket.livecricketscoreline.model.AppVersion;
import com.cricket.livecricketscoreline.model.JSON_DATA;
import com.cricket.livecricketscoreline.model.JSON_RUN;
import com.cricket.livecricketscoreline.model.Jsondata;
import com.cricket.livecricketscoreline.model.Jsonruns;
import com.cricket.livecricketscoreline.model.MainData;
import com.cricket.livecricketscoreline.model.MainJsonData;
import com.cricket.livecricketscoreline.model.MainJsonRuns;
import com.cricket.livecricketscoreline.model.Match;
import com.cricket.livecricketscoreline.model.MatchData;
import com.cricket.livecricketscoreline.utility.Utility;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RecyclerView recyclerView;

    private CategoryAdapter catAdapter;


    private ArrayList<String> mPath =new ArrayList<String>() ;



    private DatabaseReference criclineDataRef;
    private DatabaseReference jsondataRef;
    private DatabaseReference jsonrunsRef;
    private DatabaseReference jsonRef;
    private FirebaseDatabase mFirebaseInstance;
    private OnFragmentInteractionListener mListener;


    ProgressDialog progressBar;
    InterstitialAd mInterstitialAd;
    InterstitialAd mInterstitialAd1;
    static int ab = 0;
    static int abc = 0;

    Timer timer;

    FirebaseDatabase firebaseDatabase;

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

    TextView bowler;
    TextView sbatsmen;
    TextView batsman;
    TextView strikerruns;
    TextView strikerballs;
    TextView nonstrikerruns;
    TextView nonstrikerballs;
    TextView target;
    TextView score;
    Animation animBounce;
    int appCurrentVersion;
    NestedScrollView scrollView;
    ImageView imgInfo;
    TextView live ;

    ArrayList<Match> universityList = new ArrayList<>();

    RelativeLayout no_match_data;
    ScrollView scroll_top_layout;
    String LiveCount="";
    private LoadingDialog mDialog;

    public Fragment_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_home.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_home newInstance(String param1, String param2) {
        Fragment_home fragment = new Fragment_home();
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
        return inflater.inflate(R.layout.fragment_fragment_home, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Fabric.with(this, new Crashlytics());

        mFirebaseInstance = FirebaseDatabase.getInstance();

        criclineDataRef =  FirebaseDatabase.getInstance().getReference();


        LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(getActivity())
                .setCancelable(true)
                .setCancelOutside(false);
        mDialog = loadBuilder.create();


        mDialog.show();



        recyclerView=view.findViewById(R.id.recyclerview);
        no_match_data=view.findViewById(R.id.no_match_data);
        scroll_top_layout=view.findViewById(R.id.scroll_top_layout);

        for(int i=0;i<5;i++)
        {
            mPath.add(String.valueOf(i));
        }




        catAdapter= new CategoryAdapter(universityList);
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 0, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(catAdapter);
        recyclerView.setNestedScrollingEnabled(true);


        hideSoftKeyboard(getActivity());


        MobileAds.initialize(getActivity(),
                "ca-app-pub-3201092669740213~8019704401");


        AdView mAdView = (AdView)view. findViewById(R.id.adView);

         AdRequest adRequest = new AdRequest.Builder().build();
          mAdView.loadAd(adRequest);


        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-1529332403265862/8651379356");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd1 = new InterstitialAd(getActivity());
        mInterstitialAd1.setAdUnitId("ca-app-pub-1529332403265862/8651379356");
        mInterstitialAd1.loadAd(new AdRequest.Builder().build());


       /* mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });*/




        jsonRef = FirebaseDatabase.getInstance().getReference();
        jsonRef.keepSynced(true);

        jsonRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                int Count =1;
                universityList.clear();

                /*Match data1 = new Match();
                data1.setAppversion("0");
                universityList.add(data1);*/

                int id=0;


                for (DataSnapshot postSnapshot: snapshot.getChildren()) {


                    /**
                     *
                     * Version Control
                     *
                     * */


                    if (postSnapshot.getKey().equalsIgnoreCase("Updates")) {

                        for (DataSnapshot UpdatesSnapshot: postSnapshot.getChildren()) {

                         //String android = String.valueOf(UpdatesSnapshot.getKey());

                            if(UpdatesSnapshot.getKey().equalsIgnoreCase("Android"))
                            {

                                    AppVersion appversion = UpdatesSnapshot.getValue(AppVersion.class);

                                    if(appversion.getUpdateMode().equalsIgnoreCase("Hard"))
                                    {
                                        Force_Update_Dialog(appversion.getUpdateFeatures(),appversion.getURL());
                                        scroll_top_layout.setVisibility(View.GONE);
                                        break;
                                    }
                                    else
                                        if(appversion.getUpdateMode().equalsIgnoreCase("Mild"))
                                    {
                                        Update_Dialog(appversion.getUpdateFeatures(),appversion.getURL());
                                        scroll_top_layout.setVisibility(View.GONE);
                                        break;
                                    }


                            }


                        }

                    }





                    /**
                     *
                     * Live Match Count
                     *
                     * */

                    if (postSnapshot.getKey().equalsIgnoreCase("LiveCount")) {

                        LiveCount = String.valueOf(postSnapshot.getValue());

                    }


                    id = Integer.parseInt(LiveCount);

                    if (id == 0) {

                        no_match_data.setVisibility(View.VISIBLE);
                        scroll_top_layout.setVisibility(View.GONE);
                    }
                    else {

                        no_match_data.setVisibility(View.GONE);

                    String key = "";

                    key = "Match" + Count;





                        /**
                         *
                         * Live Match Data
                         *
                         * */

                    if (postSnapshot.getKey().equalsIgnoreCase(key)) {

                        Match data = new Match();


                        for (DataSnapshot inpost : postSnapshot.getChildren()) {


                            if (inpost.getKey().equalsIgnoreCase("jsondata")) {


                                JSON_DATA university = inpost.getValue(JSON_DATA.class);


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


                            }

                            if (inpost.getKey().equalsIgnoreCase("jsonruns")) {

                                JSON_RUN university = inpost.getValue(JSON_RUN.class);

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


                                data.setMatch_key(key);

                                universityList.add(data);
                                Count++;

                            }


                        }

                    }




                        scroll_top_layout.setVisibility(View.VISIBLE);
                }
                   /* if(postSnapshot.getKey().equalsIgnoreCase("jsondata")) {

                        JSON_DATA university = postSnapshot.getValue(JSON_DATA.class);
                        universityList.get(0).setTeamA(university.getTeamA());
                        universityList.get(0).setTeamB(university.getTeamB());
                        universityList.get(0).setOversA(university.getOversA());
                        universityList.get(0).setOversB(university.getOversB());
                        universityList.get(0).setRateA(university.getRateA());
                        universityList.get(0).setSessionA(university.getSessionA());
                        universityList.get(0).setWicketA(university.getWicketA());
                        universityList.get(0).setWicketB(university.getWicketB());
                        universityList.get(0).setAppversion(university.getAppversion());
                        universityList.get(0).setBatsman(university.getBatsman());
                        universityList.get(0).setScore(university.getScore());
                        universityList.get(0).setSessionOver(university.getSessionOver());
                        universityList.get(0).setTitle(university.getTitle());
                        universityList.get(0).setTotalballs(university.getTotalballs());
                        universityList.get(0).setBowler(university.getBowler());

                        universityList.get(0).setBatsman_score(university.getBatsman_score());
                        universityList.get(0).setMatch_nm(university.getMatch_nm());
                        universityList.get(0).setMatch_time(university.getMatch_time());
                        universityList.get(0).setMatch_date(university.getMatch_date());
                        universityList.get(0).setMatch_desc(university.getMatch_desc());

                        universityList.get(0).setBating_team_shrt_nm(university.getBating_team_shrt_nm());
                        universityList.get(0).setBowling_team_shrt_nm(university.getBowling_team_shrt_nm());
                        universityList.get(0).setBating_team_flag(university.getBating_team_flag());
                        universityList.get(0).setBowling_team_flag(university.getBowling_team_flag());
                        universityList.get(0).setRequired_run(university.getRequired_run());
                        universityList.get(0).setRequired_ball(university.getRequired_ball());

                        universityList.get(0).setBatsmanA_nm(university.getBatsmanA_nm());
                        universityList.get(0).setBatsmanA_run(university.getBatsmanA_run());
                        universityList.get(0).setBatsmanA_ball(university.getBatsmanA_ball());
                        universityList.get(0).setBatsmanB_nm(university.getBatsmanB_nm());
                        universityList.get(0).setBatsmanB_run(university.getBatsmanB_run());
                        universityList.get(0).setBatsmanB_ball(university.getBatsmanB_ball());

                        universityList.get(0).setBowler_ball(university.getBowler_ball());
                        universityList.get(0).setBowler_wicket(university.getBowler_wicket());


                    }

                    if(postSnapshot.getKey().equalsIgnoreCase("jsonruns")) {

                        JSON_RUN university = postSnapshot.getValue(JSON_RUN.class);

                        universityList.get(0).setRateA(university.getRateA());
                        universityList.get(0).setRateB(university.getRateB());
                        universityList.get(0).setRunxa(university.getRunxa());
                        universityList.get(0).setRunxb(university.getRunxb());
                        universityList.get(0).setSessionB(university.getSessionB());
                        universityList.get(0).setFav(university.getFav());
                        universityList.get(0).setSummary(university.getSummary());


                        universityList.get(0).setSessionA(university.getSessionA());

                        universityList.get(0).setSessionOver(university.getSessionOver());
                        universityList.get(0).setSession_run(university.getSession_run());
                        universityList.get(0).setSession_ball(university.getSession_ball());




                        universityList.get(0).setMatch_key("current");


                    }*/





                }

                /*int appAvailableVersion = Integer.parseInt(universityList.get(1).getAppversion());
                if (appAvailableVersion <= appCurrentVersion) {
                    catAdapter.notifyDataSetChanged();

                }
                else
                {
                    //showUpdateAlert();
                    Update_Dialog();
                }*/
                catAdapter.notifyDataSetChanged();

                mDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //System.out.println("The read failed: " + firebaseError.getMessage());
                mDialog.dismiss();
            }
        });




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


    class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
        private Context context;

        private ArrayList<Match> movieList;
        private ArrayList<Match> noti_List = new ArrayList<Match>();
        String date ="";




        public CategoryAdapter( ArrayList<Match> notifdates) {
            movieList=notifdates;

        }



        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txt_offer;
            private LinearLayout layout_add;
            public CheckBox chk_check;

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

            TextView bowler;
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
                    teama_rate,team_a_rate,fav_team,
                    bat_batsman,overview,match_overview;
            ImageView img_bating,img_bowling;
            AdView adView;

            public MyViewHolder(View view) {
                super(view);



                summary = (TextView)view. findViewById(R.id.summary);
                teama = (TextView)view. findViewById(R.id.teama);
                teamb = (TextView)view. findViewById(R.id.teamb);
                overs = (TextView)view. findViewById(R.id.overs);
                scorea = (TextView)view. findViewById(R.id.some_id);
                scoreb = (TextView) view.findViewById(R.id.scoreb);
                rate = (TextView) view.findViewById(R.id.rate);
                rateee = (TextView)view. findViewById(R.id.rateee);
                fav = (TextView) view.findViewById(R.id.fav);
                sessionOver = (TextView)view. findViewById(R.id.sessionover);
                session = (TextView)view. findViewById(R.id.session);
                sessionb = (TextView)view. findViewById(R.id.sessionb);
                sessionrunx = (TextView)view. findViewById(R.id.sessionrunx);
                title = (TextView)view. findViewById(R.id.title);
                bowler = (TextView) view.findViewById(R.id.bowlertitle);
                sbatsmen = (TextView)view. findViewById(R.id.strikerbatsman);
                batsman = (TextView)view. findViewById(R.id.batsman);
                strikerruns = (TextView)view. findViewById(R.id.strikerruns);
                strikerballs = (TextView)view. findViewById(R.id.strikerballs);
                nonstrikerruns = (TextView)view. findViewById(R.id.nonstrikerruns);
                nonstrikerballs = (TextView) view.findViewById(R.id.nonstrikerballs);
                target = (TextView) view.findViewById(R.id.target);
                //imageView1.setBackgroundResource(R.drawable.india);
                //imageView2.setBackgroundResource(R.drawable.england);
                score = (TextView) view.findViewById(R.id.textView99);
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
                img_bating=view.findViewById(R.id.img_bating);
                img_bowling=view.findViewById(R.id.img_bowling);

                adView = view.findViewById(R.id.adView);

                this.setIsRecyclable(true);


            }
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.listitem_offer_list, parent, false);
            //  mAnimator.onSpringItemCreate(itemView);
            return new MyViewHolder(itemView);
        }



        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            final Match matchData =movieList.get(position);

            // mAnimator.onSpringItemBind(holder.itemView, position);

            if(position==0)
            {

                holder.adView.setVisibility(View.GONE);

            }
            MobileAds.initialize(getActivity(),
                    "ca-app-pub-3201092669740213~8019704401");

            AdRequest adRequest = new AdRequest.Builder().build();
            holder.adView.loadAd(adRequest);


            String teama_=matchData.getBating_team_shrt_nm();
           // score.setText(matchData.getScore());

            holder.teama.setText(teama_);
           // holder.team_a.setText(teama_);
            holder.teamb.setText(matchData.getBowling_team_shrt_nm().toString());
           // holder.team_b.setText(matchData.getBowling_team_shrt_nm().toString());

            holder.rate_a.setText(matchData.getRateA().toString());
            holder.rate_b.setText(matchData.getRateB().toString());
            holder. fav_team.setText("Fav team: "+matchData.getFav());

            holder.scorea.setText(matchData.getWicketA().toString());
            holder.scoreb.setText(matchData.getWicketB().toString());

            String  Sscorea=matchData.getWicketA();
            String Sscoreb=matchData.getWicketB();



           int raterun = Integer.parseInt(matchData.getSessionB());
           String act_run = matchData.getWicketA().toString() ;
            int runs = (raterun - Integer.parseInt(act_run.toString().substring(0, act_run.toString().indexOf("/"))));


            holder.runrate.setText(String.valueOf(runs));
            String  Sovers=matchData.getOversA();
            int overrate= Integer.parseInt(matchData.getSessionOver()) * 6;
            int balls = ((Integer.parseInt(Sovers.toString().substring(0, Sovers.toString().indexOf("."))) * 6)) +
                    Integer.parseInt(Sovers.toString().substring(Sovers.toString().indexOf(".") + 1));
            int total_balls = overrate - balls;
            holder.ballrate.setText(String.valueOf(total_balls));/*matchData.getRunxb().toString());*/


            holder.overs.setText(matchData.getOversA().toString());


            holder.title.setText(matchData.getMatch_nm().toString());

            holder.match_date.setText(matchData.getMatch_date().toString());
            holder.match_time.setText(matchData.getMatch_time().toString());
            holder.match_desc.setText(matchData.getMatch_desc().toString());


          //  holder.ball_rate.setText(matchData.getMatch_desc().toString());
           // holder.run_rate.setText(matchData.getMatch_desc().toString());
            //holder.runrate.setText(matchData.getRunxa().toString());
            //holder.over_rate.setText(matchData.getMatch_desc().toString());
            holder.overrate.setText(matchData.getSessionOver().toString());
           // holder.teamb_rate.setText(matchData.getBowling_team_shrt_nm().toString());
            holder.team_b_rate.setText(matchData.getSessionB().toString());
           // holder.teama_rate.setText(matchData.getBating_team_shrt_nm().toString());
            holder.team_a_rate.setText(matchData.getSessionA().toString());
            holder.bat_batsman.setText("Batsman : "+matchData.getBatsman()+" "+matchData.getBatsman_score().toString());
            //holder.match_overview.setText(matchData.getBating_team_shrt_nm().toString()+" needs "+matchData.getRequired_run()+ " in "+matchData.getRequired_ball()+" overs");

            String flag1 = matchData.getBating_team_flag();
            if(!flag1.equalsIgnoreCase("")) {
                Picasso.get()
                        .load(flag1)
                        .placeholder(R.drawable.ic_landscape_image)
                        .error(R.drawable.blue_bg)
                        .into(holder.img_bating);
            }
            else
            {
                Picasso.get().load(R.drawable.blue_bg)
                        .placeholder(R.drawable.ic_landscape_image)
                        .error(R.drawable.blue_bg)
                        .into(holder.img_bating);
            }

            String flag2 = matchData.getBowling_team_flag();
            if(!flag2.equalsIgnoreCase("")) {
                Picasso.get()
                        .load(flag2)
                        .placeholder(R.drawable.ic_landscape_image)
                        .error(R.drawable.red_bg)
                        .into(holder.img_bowling);
            }
            else
            {
                Picasso.get()
                        .load(R.drawable.red_bg)
                        .placeholder(R.drawable.ic_landscape_image)
                        .error(R.drawable.red_bg)
                        .into(holder.img_bowling);
            }
            /*if(matchData.getTitle().contains("|")){
                holder.title.setText(matchData.getTitle().substring(0, matchData.getTitle().indexOf("|")));
            }else{
                holder.title.setText(matchData.getTitle().toString());
            }*/
            if(matchData.getBatsman().contains("|")){
              //  sbatsmen.setText(matchData.getBatsman().substring(0, matchData.getBatsman().indexOf("|")));
              //  batsman.setText(matchData.getBatsman().substring(matchData.getBatsman().indexOf("|") + 1));
            }else{
              //  sbatsmen.setText(matchData.getBatsman());
              //  batsman.setText(matchData.getBatsman());
            }
            String  overs=matchData.getOversA();
            String  scorea=matchData.getWicketA();
            String scoreb=matchData.getWicketB();
            if (matchData.getTotalballs() != null) {
                if (!matchData.getTotalballs().equalsIgnoreCase("")) {
                    if (!matchData.getTotalballs().equalsIgnoreCase("0")) {
                        holder.match_overview.setVisibility(View.VISIBLE);
                        //holder.overview.setVisibility(View.VISIBLE);
                        int remainingruns = (Integer.parseInt(scoreb.toString().substring(0, scoreb.toString().indexOf("/"))) - Integer.parseInt(scorea.toString().substring(0, scorea.toString().indexOf("/")))) + 1;
                        int remainingballs = Integer.parseInt(matchData.getTotalballs()) - ((Integer.parseInt(overs.toString().substring(0, overs.toString().indexOf("."))) * 6)) - Integer.parseInt(overs.toString().substring(overs.toString().indexOf(".") + 1));
                    if(remainingruns>0) {
                        holder.match_overview.setText(matchData.getTeamA() + " needs " + remainingruns + " runs in " + remainingballs + " balls to win");
                    }
                    else
                    {
                        holder.match_overview.setVisibility(View.GONE);
                       // holder.overview.setVisibility(View.GONE);
                    }

                    } else {
                       holder. match_overview.setVisibility(View.GONE);
                       // holder.overview.setVisibility(View.GONE);

                    }
                } else {
                    holder. match_overview.setVisibility(View.GONE);
                   // holder.overview.setVisibility(View.GONE);
                }
            } else {
                holder. match_overview.setVisibility(View.GONE);
               // holder.overview.setVisibility(View.GONE);
            }


            if(matchData.getOversB().contains("|")){
                String left = matchData.getOversB().substring(0, matchData.getOversB().indexOf("|"));
                String right = matchData.getOversB().substring(matchData.getOversB().indexOf("|") + 1);
                holder.sessionOver.setText(left);
             //   strikerballs.setText(right.split(",")[1]);
             //   nonstrikerballs.setText(right.split(",")[0]);
             //   strikerruns.setText(left.split(",")[1]);
             //   nonstrikerruns.setText(left.split(",")[0]);

            }else{
              //  strikerballs.setText(matchData.getOversB());
              //  nonstrikerballs.setText(matchData.getOversB());
              //  strikerruns.setText(matchData.getOversB());
              //  nonstrikerruns.setText(matchData.getOversB());

            }

           // holder.bowler.setText("Bowler : " + matchData.getBowler().toString());
            holder.bowler.setText(matchData.getScore().toString());
            if (matchData.getBowler().equalsIgnoreCase("0")) {





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


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Constant.match_id = matchData.getMatch_key();

                    Fragment fragment =new Fragment_Details();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    //ft .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);
                    ft.replace(R.id.container, fragment);
                   // ft.addToBackStack("details");
                    ft.commit();





                }
            });


        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }

    }








    public void showInfoAlert2() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Disclaimer");
        builder.setMessage(getResources().getString(R.string.info_text));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }








    @Override
    public void onResume() {
        super.onResume();
        try {
            appCurrentVersion = Utility.getInstance(getActivity()).getVersionCode();
            if (alertDialog != null) {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
            }
            if (Utility.getInstance(getActivity()).isNetworkAvailable()) {



            } else {

                Utility.getInstance(getActivity()).showToast("No internet! Please try again");
            }
        } catch (Exception ex) {

        }

    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // stopTimer();
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {

    }




    AlertDialog alertDialog;

    void showUpdateAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Update");
        builder.setCancelable(false);
        builder.setMessage("New version of app is available on play store. Please update!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Utility.getInstance(getActivity()).openPlayStore();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });
        alertDialog = builder.create();

        alertDialog.show();
    }

    public void Update_Dialog(String msg, final String url)
    {

        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(R.layout.dialog_update_app, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Dialog);
//         builder.setTitle("Confirm!");
//        builder.setMessage("Not any employee entry found in ("+PASSED_M_NAME+") machine. We have found 'Intial Entry' & 'Previous Day Entry' from Master Data. What type entry do you like to load?");

        builder.setView(deleteDialogView);
        builder.setCancelable(false);
        final AlertDialog ad = builder.show();
        TextView txt_msg =(TextView)deleteDialogView.findViewById(R.id.txt_msg);


        txt_msg.setText(msg);


        deleteDialogView.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {


            private Handler h;
            @Override
            public void onClick(View v) {
                h = new Handler();
                new Thread(new Runnable() {
                    public void run() {
                        // DO NOT ATTEMPT TO DIRECTLY UPDATE THE UI HERE, IT WON'T WORK!
                        // YOU MUST POST THE WORK TO THE UI THREAD'S HANDLER

                        h.postDelayed(new Runnable() {
                            public void run() {
                                // Open the Spinner...
                                final String appPackageName =getActivity(). getPackageName(); // getPackageName() from Context or Activity object
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url+ appPackageName)));
                                    //Utility.getInstance(getActivity()).openPlayStore();
                                }
//		  					  								String url = "https://play.google.com/store/apps/details?id=com.paywell.activity&hl=en";
//		  													Intent i = new Intent(Intent.ACTION_VIEW);
//		  													i.setData(Uri.parse(url));
//		  													startActivity(i);
                                getActivity().finish();

                            }
                        }, 500);
                    }
                }).start();


                ad.dismiss();
            }
        });
        deleteDialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad.dismiss();


            }
        });




    }
    public void Force_Update_Dialog(String msg, final String url)
    {

        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(R.layout.dialog_force_update, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Dialog);
//         builder.setTitle("Confirm!");
//        builder.setMessage("Not any employee entry found in ("+PASSED_M_NAME+") machine. We have found 'Intial Entry' & 'Previous Day Entry' from Master Data. What type entry do you like to load?");

        builder.setView(deleteDialogView);
        builder.setCancelable(false);
        final AlertDialog ad = builder.show();
        TextView txt_msg =(TextView)deleteDialogView.findViewById(R.id.txt_msg);



       txt_msg.setText(msg);

        deleteDialogView.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {


            private Handler h;
            @Override
            public void onClick(View v) {
                h = new Handler();
                new Thread(new Runnable() {
                    public void run() {
                        // DO NOT ATTEMPT TO DIRECTLY UPDATE THE UI HERE, IT WON'T WORK!
                        // YOU MUST POST THE WORK TO THE UI THREAD'S HANDLER

                        h.postDelayed(new Runnable() {
                            public void run() {
                                // Open the Spinner...
                                final String appPackageName =getActivity(). getPackageName(); // getPackageName() from Context or Activity object
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url+ appPackageName)));
                                    //Utility.getInstance(getActivity()).openPlayStore();
                                }
//		  					  								String url = "https://play.google.com/store/apps/details?id=com.paywell.activity&hl=en";
//		  													Intent i = new Intent(Intent.ACTION_VIEW);
//		  													i.setData(Uri.parse(url));
//		  													startActivity(i);
                                getActivity().finish();

                            }
                        }, 500);
                    }
                }).start();


                ad.dismiss();
            }
        });
        deleteDialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    ad.dismiss();
                    getActivity().finish();


            }
        });




    }
}
