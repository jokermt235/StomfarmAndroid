package devel.exesoft.com.accshop.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Debt;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.model.Partner;
import devel.exesoft.com.accshop.model.Sale;
import devel.exesoft.com.accshop.model.User;
import devel.exesoft.com.accshop.view.CustomStringRequest;
import io.realm.Realm;
import io.realm.RealmResults;

public class SynchController extends AppController {
    private String TAG = "SynchController";
    public void synchAll(){
        synchPartners();
        synchSales();
        synchDebts();
        synchItems();
    }

    public void synchItems(){
        String NAME = "Items";
        String url = getInstance().getString(R.string.server_url) + "/" + NAME ;
        final JSONObject params  = new JSONObject();
        final Realm realm = Realm.getDefaultInstance();
        final User user = realm.where(User.class).findFirst();
        try {
            if(user != null) {
                final RealmResults<Item> items = realm.where(Item.class).
                        equalTo("changed",true).findAll();
                JSONArray partnersArray = new JSONArray();
                for(Item item : items){
                    JSONObject param = new JSONObject();
                    param.put("mobile_id", item.getId());
                    param.put("name", item.getName());
                    param.put("price", item.getPrice());
                    param.put("user_id", user.getId());
                    param.put("amount", item.getCount());
                    param.put("barcode", item.getBarcode());
                    param.put("acc_code", item.getAcc_code());
                    param.put("unit_string", item.getUnit_string());
                    partnersArray.put(param);
                }
                params.put("debts", partnersArray);
                url += "?token=" + user.getToken();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomStringRequest jsonObjectRequest = new CustomStringRequest(Request.Method.POST, url, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, response);
                if(response != null){
                    try {
                        JSONObject result = new JSONObject(response);

                        if(result.getBoolean("success")){
                            JSONArray data = result.getJSONArray("data");
                            if(data.length() > 0){
                                for(int i=0;i<data.length();i++){
                                    final Item  item = new Item();
                                    if(data.getJSONObject(i).getString("mobile_id") != null) {
                                        item.setId(data.getJSONObject(i).getString("mobile_id"));
                                        item.setCount(data.getJSONObject(i).getInt("amount"));
                                    }else{
                                       item.setName(data.getJSONObject(i).getString("name"));
                                       item.setCount(data.getJSONObject(i).getInt("amount"));
                                       item.setUnit_string(data.getJSONObject(i).getString("unit_string"));
                                       item.setBarcode(data.getJSONObject(i).getString("barcode"));
                                       item.setAcc_code(data.getJSONObject(i).getString("acc_code"));
                                       item.setPrice(data.getJSONObject(i).getInt("price"));
                                       item.setUser_id(user.getId());
                                    }
                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            // This will create a new object in Realm or throw an exception if the
                                            // object already exists (same primary key)
                                            realm.copyToRealmOrUpdate(item);
                                        }
                                    });
                                }
                            }

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

    public void synchDebts(){
        String NAME = "Debts";
        String url = getInstance().getString(R.string.server_url) + "/" + NAME ;
        final JSONObject params  = new JSONObject();
        final Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        try {
            if(user != null) {
                final RealmResults<Debt> debts = realm.where(Debt.class).equalTo("status","new").findAll();
                JSONArray partnersArray = new JSONArray();
                for(Debt debt : debts){
                    JSONObject param = new JSONObject();
                    param.put("mobile_id", debt.getId());
                    param.put("item_id", debt.getItem_id());
                    param.put("item_name",debt.getItem_name());
                    param.put("item_unit",debt.getItem_unit());
                    param.put("item_price", debt.getItem_price());
                    param.put("partner_id", debt.getPartner_id());
                    param.put("amount", debt.getAmount());
                    param.put("clc_status", debt.getClc_status());
                    param.put("clc_timestamp", debt.getClc_timestamp());
                    partnersArray.put(param);
                }
                params.put("debts", partnersArray);
                url += "?token=" + user.getToken();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomStringRequest jsonObjectRequest = new CustomStringRequest(Request.Method.POST, url, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, response);
                if(response != null){
                    try {
                        JSONObject result = new JSONObject(response);

                        if(result.getBoolean("success")){
                            JSONArray data = result.getJSONArray("data");
                            if(data.length() > 0){
                                for(int i=0;i<data.length();i++){
                                    final Debt  debt = new Debt();
                                    debt.setId(data.getJSONObject(i).getString("mobile_id"));
                                    debt.setStatus("");
                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            // This will create a new object in Realm or throw an exception if the
                                            // object already exists (same primary key)
                                            realm.copyToRealmOrUpdate(debt);
                                        }
                                    });
                                }
                            }

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

    public void synchSales(){
        String NAME = "Sales";
        String url = getInstance().getString(R.string.server_url) + "/" + NAME ;
        final JSONObject params  = new JSONObject();
        final Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        try {
            if(user != null) {
                final RealmResults<Sale> sales = realm.where(Sale.class).equalTo("status","new").findAll();
                JSONArray salesArray = new JSONArray();
                for(Sale sale : sales){
                    JSONObject param = new JSONObject();
                    param.put("mobile_id", sale.getId());
                    param.put("item_id", sale.getItem_id());
                    param.put("item_name",sale.getItem_name());
                    param.put("item_unit",sale.getItem_unit());
                    param.put("item_price", sale.getItem_price());
                    param.put("partner_id", sale.getPartner_id());
                    param.put("amount", sale.getAmount());
                    salesArray.put(param);
                }
                params.put("sales", sales);
                url += "?token=" + user.getToken();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomStringRequest jsonObjectRequest = new CustomStringRequest(Request.Method.POST, url, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, response);
                if(response != null){
                    try {
                        JSONObject result = new JSONObject(response);

                        if(result.getBoolean("success")){
                            JSONArray data = result.getJSONArray("data");
                            if(data.length() > 0){
                                for(int i=0;i<data.length();i++){
                                    final Sale  sale = new Sale();
                                    sale.setId(data.getJSONObject(i).getString("mobile_id"));
                                    sale.setStatus("");
                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            // This will create a new object in Realm or throw an exception if the
                                            // object already exists (same primary key)
                                            realm.copyToRealmOrUpdate(sale);
                                        }
                                    });
                                }
                            }

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

    public void synchPartners(){
        String NAME = "Partners";
        String url = getInstance().getString(R.string.server_url) + "/" + NAME ;
        final JSONObject params  = new JSONObject();
        final Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        try {
            if(user != null) {
                final RealmResults<Partner> partners = realm.where(Partner.class).equalTo("status","new").findAll();
                JSONArray partnersArray = new JSONArray();
                for(Partner partner : partners){
                    JSONObject param = new JSONObject();
                    param.put("mobile_id", partner.getId());
                    param.put("name", partner.getName());
                    param.put("address",partner.getAddress());
                    param.put("phone",partner.getPhone());
                    partnersArray.put(param);
                }
                params.put("partners", partnersArray);
                url += "?token=" + user.getToken();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomStringRequest jsonObjectRequest = new CustomStringRequest(Request.Method.POST, url, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, response);
                if(response != null){
                    try {
                        JSONObject result = new JSONObject(response);

                        if(result.getBoolean("success")){
                            JSONArray data = result.getJSONArray("data");
                            if(data.length() > 0){
                                for(int i=0;i<data.length();i++){
                                    final Partner  partner = new Partner();
                                    partner.setId(data.getJSONObject(i).getString("mobile_id"));
                                    partner.setStatus("");
                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            // This will create a new object in Realm or throw an exception if the
                                            // object already exists (same primary key)
                                            realm.copyToRealmOrUpdate(partner);
                                        }
                                    });
                                }
                            }

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
