package devel.exesoft.com.accshop.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import devel.exesoft.com.accshop.CustomStringRequest;
import devel.exesoft.com.accshop.HomeActivity;

public class UserController extends AppController {

    private static  final String TAG  = "UserController";

    public static void login(String username, String password) {

        String url = "http://test.exesoft.org/RestService/api/users/token/";

        JSONObject params  = new JSONObject();

        try {
            params.put("username", username);
            params.put("password", password
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, params.toString());

        CustomStringRequest jsonObjectRequest = new CustomStringRequest(Request.Method.POST, url, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, response);
                if(response != null){
                    try {
                        JSONObject result = new JSONObject(response);

                        if(result.getBoolean("success")){
                            Intent intent = new Intent(getInstance(), HomeActivity.class);
                            getInstance().startActivity(intent);
                        }
                    }catch (Exception excp){
                        Log.e(TAG, excp.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });

        getInstance().getRequestQueue().add(jsonObjectRequest);
    }


}
