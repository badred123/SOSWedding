package com.example.soswedding.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.model.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RequestsService {


    static String getAllRequestsResult;

    public static void postRequest(Context context, com.example.soswedding.model.Request rq) {
        String url = "https://soswedding.herokuapp.com/request";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("title", rq.getTitle());
            jsonBody.put("description", rq.getDescription());
            jsonBody.put("serviceType", rq.getType());
            jsonBody.put("userId", Singleton.getInstance().getId());
            jsonBody.put("budget", rq.getBudget());
            jsonBody.put("address", rq.getAddress());

            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("VOLLEY", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> headers = new HashMap<String,String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            requestQueue.add(stringRequest);
        }
        catch( JSONException e)
        {
            e.printStackTrace();
        }

    }
    public static String getAllRequests(Context context,final VolleyCallback callback){
        String url = "https://soswedding.herokuapp.com/request";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                        getAllRequestsResult = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
        return getAllRequestsResult;
    }

    public static void getRequestsOfUser(Context context, double userId, final VolleyCallback callback){
        int temp = (int)userId;
        String url = "https://soswedding.herokuapp.com/request/user/"+temp;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

}
