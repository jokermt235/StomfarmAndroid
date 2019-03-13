package devel.exesoft.com.accshop.controller;

import android.app.Application;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    private static  final String TAG  = "AppController";

    private static AppController mInstance;

    private RequestQueue requestQueue;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
        Log.e(TAG, "Application created");
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        return requestQueue;
    }


    /*public void login(String username, String password) {

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
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });

        getRequestQueue().add(jsonObjectRequest);
    }*/
}

