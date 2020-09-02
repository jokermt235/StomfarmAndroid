package devel.exesoft.com.accshop.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.model.User;
import devel.exesoft.com.accshop.view.CustomStringRequest;
import io.realm.Realm;
import io.realm.RealmResults;

public class RemainController extends  AppController {
    private static  String TAG = "RemainController";
    public static  void getItems(String name, Response.Listener<String> response , Response.ErrorListener error){
        String NAME = "Remains";
        String url = getInstance().getString(R.string.server_url) + "/" + NAME ;
        final JSONObject params  = new JSONObject();
        Log.d(TAG, url);
        final Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        try {
            if(user != null) {
                params.put("token", user.getToken());
                params.put("name", name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            CustomStringRequest jsonObjectRequest = new CustomStringRequest(url, params, response, error);
            getInstance().getRequestQueue().add(jsonObjectRequest);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
