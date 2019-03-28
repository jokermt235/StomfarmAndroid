package devel.exesoft.com.accshop.controller;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.model.Store;
import devel.exesoft.com.accshop.model.User;
import devel.exesoft.com.accshop.view.CustomStringRequest;
import io.realm.Realm;

public class ItemController extends AppController {

    private static String TAG = "ItemController";

    private static String NAME = "Items";

    public static void getItemByBarcode(String barcode)
    {
        String url = getInstance().getString(R.string.server_url) + "/" + NAME;

        JSONObject params  = new JSONObject();
        final Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        try {
            if(user != null) {
                params.put("barcode", barcode);
                url += "?token=" + user.getToken() + "&barcode=" + barcode;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomStringRequest jsonObjectRequest = new CustomStringRequest(url, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, response);
                if(response != null){
                    try {
                        JSONObject result = new JSONObject(response);

                        if(result.getBoolean("success")){
                            final Item item = new Item();
                            JSONObject data = result.getJSONObject("data");

                            Log.d(TAG, data.toString());
                            item.setName(data.getString("name"));
                            item.setStore_id(data.getLong("storage_id"));
                            item.setAcc_code(data.getString("acc_code"));

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    // This will create a new object in Realm or throw an exception if the
                                    // object already exists (same primary key)
                                    realm.copyToRealm(item);
                                    //activityStorageBinding.storageToolbar.setTitle(store.getName());
                                }
                            });
                            realm.close();
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
