package devel.exesoft.com.accshop.controller;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.databinding.ActivityStorageBinding;
import devel.exesoft.com.accshop.model.Store;
import devel.exesoft.com.accshop.model.User;
import devel.exesoft.com.accshop.view.CustomStringRequest;
import io.realm.Realm;

public class StoreContoller extends AppController {

    private static String TAG = "StoreController";

    private static String NAME = "Storages";

    public static boolean isExist(){
        Realm realm = Realm.getDefaultInstance();
        Store store = realm.where(Store.class).findFirst();
        if(store != null){
            realm.close();
            return true;
        }
        realm.close();
        return false;
    }

    public static void getRemoteStore(final ActivityStorageBinding activityStorageBinding){

        String url = getInstance().getString(R.string.server_url) + "/" + NAME + "/view";

        JSONObject params  = new JSONObject();
        final Realm realm = Realm.getDefaultInstance();
        User  user = realm.where(User.class).findFirst();
        try {
            if(user != null) {
                params.put("user_id", user.getId());
                url += "?token=" + user.getToken() + "&user_id=" + user.getId();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, params.toString());

        Log.d(TAG, "getRemoteStore method");

        try {

            CustomStringRequest jsonObjectRequest = new CustomStringRequest(url, params, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e(TAG, response);
                    if (response != null) {
                        try {
                            JSONObject result = new JSONObject(response);

                            if (result.getBoolean("success")) {
                                final Store store = new Store();
                                JSONObject data = result.getJSONObject("data");

                                Log.d(TAG, data.toString());
                                store.setName(data.getString("name"));
                                store.setUser_id(data.getLong("user_id"));

                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        // This will create a new object in Realm or throw an exception if the
                                        // object already exists (same primary key)
                                        realm.copyToRealm(store);
                                        activityStorageBinding.storageToolbar.setTitle(store.getName());
                                    }
                                });
                                realm.close();
                            }
                        } catch (Exception excp) {
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
        }catch (UnsupportedEncodingException e){
            Log.d(TAG, e.toString());
        }
    }

}
