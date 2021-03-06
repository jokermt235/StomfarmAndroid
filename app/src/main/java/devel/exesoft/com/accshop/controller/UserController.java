package devel.exesoft.com.accshop.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.User;
import devel.exesoft.com.accshop.view.CustomStringRequest;
import devel.exesoft.com.accshop.view.HomeActivity;
import devel.exesoft.com.accshop.view.LoginActivity;
import devel.exesoft.com.accshop.view.MainActivity;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UserController extends AppController {

    private static  final String TAG  = "UserController";

    private static  final String NAME = "Users";


    public static void logout(Context context,HomeActivity activity){
        try {
            final Realm realm = Realm.getDefaultInstance();
            final User user = realm.where(User.class).findFirst();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm pRealm) {
                    pRealm.deleteAll();
                    realm.close();
                }
            });
            Intent lIntent = new Intent(activity, LoginActivity.class);
            activity.startActivity(lIntent);
            activity.finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void login(String username, String password) {

        String url = getInstance().getString(R.string.server_url) + "/" + NAME +"/token";

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
                            final User  user = new User();
                            user.setId(result.getJSONObject("data").getLong("id"));
                            user.setFio(result.getJSONObject("data").getString("fio"));
                            user.setUsername(result.getJSONObject("data").getString("username"));
                            user.setToken(result.getJSONObject("data").getString("token"));

                            Realm realm = Realm.getDefaultInstance();

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    // This will create a new object in Realm or throw an exception if the
                                    // object already exists (same primary key)
                                    realm.copyToRealm(user);
                                }
                            });
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

    public static boolean isAuthorized(){
        Realm realm = Realm.getDefaultInstance();
        User  user = realm.where(User.class).findFirst();
        if(user != null){
            realm.close();
            return true;
        }
        realm.close();
        return false;
    }

}
