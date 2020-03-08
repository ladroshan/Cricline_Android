package com.cricket.livecricketscoreline.network;

import android.app.ProgressDialog;
import android.content.Context;


import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lakhan.sharma on 10/9/2015.
 */
public class Volley {

    RequestQueue requestQueue;
    static Volley volley = null;
    ProgressDialog dialog;
    Context context;
    Fragment fragment;
    RequestFuture<String> synchronousStringRequest;
    RequestFuture<JSONObject> synchronousJsonObjectRequest;


    public Volley(Context context) {
        this.context = context;
        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context.getApplicationContext());

    }

    public Volley(Fragment fragment) {
        this.fragment = fragment;
        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(fragment.getActivity().getApplicationContext());
    }

    /**
     * create a singleton instance for newwork handling
     **/
    public static synchronized Volley getInstance(Context context) {
        if (null == volley) {
            volley = new Volley(context);
        }
        return volley;
    }

    /**
     * remove instance
     **/
    public static void removeVolleyInstance() {
        volley = null;
    }

    public void setLoadingDialog(ProgressDialog dialog) {
        this.dialog = dialog;
    }

    public ProgressDialog getLoadingDialog() {
        return this.dialog;
    }


    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    /**
     * synchronous request (request processed in current thread) to get data
     **/
    public RequestFuture<String> executeSynchronousStringReguest(String URL) {
        synchronousStringRequest = RequestFuture.newFuture();
        StringRequest request = new StringRequest(Request.Method.GET, URL, synchronousStringRequest, synchronousStringRequest);
        request.setRetryPolicy(new DefaultRetryPolicy(VolleyConstants.DEFAULT_TIMEOUT_MS, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        requestQueue.add(request);
        return synchronousStringRequest;
    }

    /**
     * synchronous request (request processed in current thread) to get json data
     **/
    public RequestFuture<JSONObject> executeSynchronousJsonObjectReguest(String URL) {
        synchronousJsonObjectRequest = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(URL, null, synchronousJsonObjectRequest, synchronousJsonObjectRequest);
        request.setRetryPolicy(new DefaultRetryPolicy(VolleyConstants.DEFAULT_TIMEOUT_MS, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
        return synchronousJsonObjectRequest;
    }


    /**
     * Asynchronous request (request processed in seperate thread) to get string data
     **/
    public void executeStringRequest(String URL, final String TAG, final VollyResponceListener<String> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onVolleyResponce(response, TAG);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onVolleyResponce(VolleyConstants.VOLLEY_ERROR, TAG);
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VolleyConstants.DEFAULT_TIMEOUT_MS, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setTag(TAG);
        stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);
    }
/*    public void executeJsonArrayRequest1(String URL, final String TAG, final VollyResponceListener1<String> listener, final int position) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onVolleyResponce(response, TAG,position);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(VolleyConstants.DEFAULT_TIMEOUT_MS, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setTag(TAG);
        requestQueue.add(stringRequest);
    }*/

    /**
     * Asynchronous request (request processed in seperate thread) to get json data
     **/
    public void executeJsonArrayRequest(String URL, final String TAG, final VollyResponceListener<String> listener) {
        JSONObject jsonObject = null;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL, jsonObject,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /**getting first name of user**/
                        if (TAG.equals(VolleyConstants.TAG_GET_USER_FIRSTNAME)) {
                            JSONObject jsonObj = null;

                            try {
                                jsonObj = response.getJSONObject(0);
                                String firstname = jsonObj.getString("firstname");
                                listener.onVolleyResponce(firstname, TAG);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        /**getting users list from unified**/
                        else if (TAG.equals(VolleyConstants.TAG_GET_USERS_FROM_UNIFIED)) {
                            listener.onVolleyResponce(response.toString(), TAG);
                        }
                        /**setBackground user on unified**/
                        else if (TAG.equals(VolleyConstants.TAG_CHECK_USER_EXISTS_ON_UNIFED)) {
                            final JSONArray[] jsonArr = new JSONArray[1];
                            jsonArr[0] = response;
                            if (jsonArr[0].length() == 0) {
                                // listener.onVolleyResponce(Constants.NOT_REGISTERED,TAG);
                            } else {
                                // listener.onVolleyResponce(Constants.ALREADY_REGISTERED,TAG);

                            }
                        }

                        System.out.println("json data" + response);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onVolleyResponce(VolleyConstants.VOLLEY_ERROR, TAG);
                String message = error.getMessage();
                VolleyLog.d("TAG", "Error: " + message);

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(VolleyConstants.DEFAULT_TIMEOUT_MS, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setTag(TAG);
        jsonArrayRequest.setShouldCache(false);
        requestQueue.add(jsonArrayRequest);
    }


    public void cancelRequest(String tag) {
        requestQueue.cancelAll(tag);
    }

    public synchronized void executeJsonObjectRequest(String URL, final String TAG, final VollyResponceListener listener, final String jason, int requestCheck) {
        JsonObjectRequest jsonobjectrequest = new JsonObjectRequest(requestCheck, URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onVolleyResponce(response.toString(), TAG);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                listener.onVolleyResponce(arg0.toString(), "Error");
                // TODO Auto-generated method stub
            }
        }) {
            @Override
            public byte[] getBody() {
                return jason.getBytes();
            }
        };
        jsonobjectrequest.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonobjectrequest);
    }

}
