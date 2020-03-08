package com.cricket.livecricketscoreline.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import android.widget.ImageView;

import android.widget.TextView;

import com.cricket.livecricketscoreline.LoadingDialog.LoadingDialog;
import com.cricket.livecricketscoreline.MainActivity;
import com.cricket.livecricketscoreline.R;
import com.cricket.livecricketscoreline.Support.GridSpacingItemDecoration;

import com.cricket.livecricketscoreline.model.Match_TimeTable;
import com.cricket.livecricketscoreline.model.time_table;


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
 * {@link Fragment_TimeTable.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_TimeTable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_TimeTable extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    InterstitialAd mInterstitialAd;
    InterstitialAd mInterstitialAd1;
    private Toolbar toolbar;
    private TextView toolbartitle;
    private ImageView img_logo,imglogo;

    private MHAdapter mAdapter;
    private RecyclerView recycler_View;

    private ArrayList<String> mPath =new ArrayList<String>() ;

    ArrayList<Match_TimeTable> universityList = new ArrayList<>();
    ArrayList<time_table> itemlist = new ArrayList<>();

    private DatabaseReference jsonRef;
    private FirebaseDatabase mFirebaseInstance;
    private OnFragmentInteractionListener mListener;
    private DatabaseReference criclineDataRef;
    private LoadingDialog mDialog;
    public Fragment_TimeTable() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_TimeTable.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_TimeTable newInstance(String param1, String param2) {
        Fragment_TimeTable fragment = new Fragment_TimeTable();
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
        return inflater.inflate(R.layout.fragment_fragment__time_table, container, false);
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



        img_logo= view.findViewById(R.id.img_logo);
        imglogo= view.findViewById(R.id.imglogo);
        toolbartitle= view.findViewById(R.id.toolbartitle);
       // toolbartitle.setText("Match Time Table");
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



        recycler_View=view.findViewById(R.id.recyclerView);

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




        /*mInterstitialAd = new InterstitialAd(getActivity());

        mInterstitialAd.setAdUnitId("ca-app-pub-3201092669740213/6706622737");
        mInterstitialAd1 = new InterstitialAd(getActivity());
        mInterstitialAd1.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        AdRequest adRequest1 = new AdRequest.Builder().build();
        AdRequest adRequest2 = new AdRequest.Builder().build();


        mInterstitialAd.loadAd(adRequest1);
        mInterstitialAd1.loadAd(adRequest2);
*/



        for(int i=0;i<2;i++)
        {
            mPath.add(String.valueOf(i));
        }



        mAdapter= new MHAdapter(universityList);

        //initMylistRecyclerView(recycler_View, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true), mAdapter);
        mAdapter.notifyDataSetChanged();
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycler_View.setLayoutManager(mLayoutManager);
        recycler_View.addItemDecoration(new GridSpacingItemDecoration(1, 0, true));
        recycler_View.setItemAnimator(new DefaultItemAnimator());
        //recycler_View.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recycler_View.setAdapter(mAdapter);
        recycler_View.setNestedScrollingEnabled(true);


        hideSoftKeyboard(getActivity());





        mDialog.show();
        DatabaseReference Match1 = mFirebaseInstance.getReference("timetable");

        Match1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                universityList.clear();

               Match_TimeTable data = new Match_TimeTable();

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {




                        if (postSnapshot.getKey().equalsIgnoreCase("data")) {
                            int count_ = Integer.parseInt(String.valueOf(postSnapshot.getChildrenCount()));


                                for (DataSnapshot ing : postSnapshot.getChildren()) {



                                        data = ing.getValue(Match_TimeTable.class);
                                        universityList.add(data);


                                }



                        }



                }

                String Dept_nm ="";

                mAdapter.notifyDataSetChanged();
                mDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


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


    class MHAdapter extends RecyclerView.Adapter<MHAdapter.MyViewHolder> {
        private Context context;

        private ArrayList<Match_TimeTable> movieList;
        private ArrayList<String> noti_List = new ArrayList<String>();
        String date ="";
        //  private SpringyAdapterAnimator mAnimator;



        public MHAdapter( ArrayList<Match_TimeTable> notifdates) {
            movieList = notifdates;
        }



        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txt_offer;
            private TextView upcomming_matche,title,match_desc,match_data,match_time,teama,
                    teamb,txt_wait_for_images,btn_accept,txt_delivery_status,txt_qty;
            private View rectangle_3;
            ImageView img_bating,img_bowling;

            public MyViewHolder(View view) {
                super(view);

                this.setIsRecyclable(true);
                rectangle_3= view.findViewById(R.id.rectangle_3);
                upcomming_matche = view.findViewById(R.id.upcomming_matche);
                title= view.findViewById(R.id.title);
                match_desc= view.findViewById(R.id.match_desc);
                match_data= view.findViewById(R.id.match_data);
                match_time= view.findViewById(R.id.match_time);
                teama= view.findViewById(R.id.teama);
                teamb= view.findViewById(R.id.teamb);

                img_bating=view.findViewById(R.id.img_bating);
                img_bowling=view.findViewById(R.id.img_bowling);


            }
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.listitem_time_table, parent, false);
            //  mAnimator.onSpringItemCreate(itemView);
            return new MyViewHolder(itemView);
        }



        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {



            if(!date.equalsIgnoreCase(movieList.get(position).getDate()))
            {
                date = movieList.get(position).getDate();

                holder. upcomming_matche.setText(movieList.get(position).getDate());
            }
            else
            {
                date = movieList.get(position).getDate();

                holder. upcomming_matche.setVisibility(View.GONE);
                holder. rectangle_3.setVisibility(View.GONE);
            }




            holder. title.setText(movieList.get(position).getMatch_title());
            holder.match_desc.setText(movieList.get(position).getMatch_desc());
            holder. match_data.setText(movieList.get(position).getMatch_date());
            holder.match_time.setText(movieList.get(position).getMatch_time());
            holder.teama.setText(movieList.get(position).getBating_team_shrt_nm());
            holder.teamb.setText(movieList.get(position).getBowling_team_shrt_nm());




            /*String flag1 = movieList.getBating_team_flag();
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

            String flag2 = movieList.getBowling_team_flag();
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
            }*/


        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }

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
