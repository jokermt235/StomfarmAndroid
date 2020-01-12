package devel.exesoft.com.accshop.view_model;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.adapters.StoreItemAdapter;
import devel.exesoft.com.accshop.controller.AppController;
import devel.exesoft.com.accshop.controller.DebtsController;
import devel.exesoft.com.accshop.controller.ItemController;
import devel.exesoft.com.accshop.controller.PartnerController;
import devel.exesoft.com.accshop.controller.StoreContoller;
import devel.exesoft.com.accshop.controller.SynchController;
import devel.exesoft.com.accshop.model.Debt;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.model.Partner;
import devel.exesoft.com.accshop.model.Store;
import devel.exesoft.com.accshop.model.User;
import devel.exesoft.com.accshop.view.CustomStringRequest;
import devel.exesoft.com.accshop.view.SimpleScannerActivity;
import devel.exesoft.com.accshop.view.StorageActivity;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.Request.Method.POST;

public class StoreViewModel extends BaseObservable {

    private StorageActivity mContext;

    private static int REQUEST_CODE_SCANER = 1;

    private static int REQUST_CODE_MANNUAL = 2;

    private static String TAG = "StoreViewModel";

    private static String NAME  = "Items";

    public StoreViewModel(StorageActivity pContext){
        mContext = pContext;
        mContext.activityStorageBinding.storageToolbar.setTitle("Необходима синхронизация...");
        fillItemList();
    }

    private StorageActivity getContext(){
        return mContext;
    }

    public void onScanClicked(){
        mContext.startActivityForResult(new Intent(mContext.getApplicationContext(), SimpleScannerActivity.class), REQUEST_CODE_SCANER);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE_SCANER){
                String itemBarcode = data.getStringExtra("item_barcode");
                Log.d(TAG, itemBarcode);
            }

            if(requestCode == REQUST_CODE_MANNUAL){
            }
        }
    }

    public void onSyncClicked(){

        mContext.activityStorageBinding.storageToolbar.setTitle("Идет загрузка ...");
        SynchController.synchItems();
        mContext.activityStorageBinding.storageToolbar.setTitle("Склад");
        fillItemList();
    }

    public void onExportClicked(){

    }

    public void setStore(){
        Realm realm = Realm.getDefaultInstance();
        Store store = realm.where(Store.class).findFirst();
        if(store != null){
            mContext.activityStorageBinding.storageToolbar.setTitle(store.getName());
            realm.close();
        }

    }

    public void fillItemList(){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Item> items = realm.where(Item.class).findAll();
        ListView listView = (ListView)mContext.findViewById(R.id.storage_items);
        ArrayList<Item> list = new ArrayList();
        for(Item item : items){
            list.add(item);
        }
        StoreItemAdapter storeItemAdapter = new StoreItemAdapter(mContext,list);
        listView.setAdapter(storeItemAdapter);
    }

    public void fillItemList(String name){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Item> items = realm.where(Item.class).contains("name",name).findAll();
        ListView listView = (ListView)mContext.findViewById(R.id.storage_items);
        ArrayList<Item> list = new ArrayList();
        for(Item item : items){
            list.add(item);
        }
        StoreItemAdapter storeItemAdapter = new StoreItemAdapter(mContext,list);
        listView.setAdapter(storeItemAdapter);
    }

    private void synchServerData(){
        final Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        String url = AppController.getInstance().getString(R.string.server_url) + "/synch?token=" + user.getToken() ;
        JSONObject params = new JSONObject();
        try {
            params.put("partners", this.partners());
            params.put("debts", this.debts());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            final CustomStringRequest jsonObjectRequest = new CustomStringRequest(POST,url, params, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e(TAG, response);
                    if (response != null) {
                        try {
                            JSONObject result = new JSONObject(response);
                            if (result.getBoolean("success")) {
                                Log.d(TAG, "Datasynchronized successiful!");
                            }
                        } catch (Exception excp) {
                            Log.e(TAG, excp.toString());
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            AppController.getInstance().getRequestQueue().add(jsonObjectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JSONArray partners(){
        JSONArray jsonArray = new JSONArray();
        RealmResults<Partner> realmResults = PartnerController.partners();
        for(Partner partner : realmResults){
            JSONObject jsonPartner = new  JSONObject();
            try {
                jsonPartner.put("name", partner.getName());
                jsonPartner.put("phone", partner.getPhone());
                jsonPartner.put("address", partner.getAddress());
                jsonPartner.put("user_id", partner.getUser_id());
                jsonArray.put(jsonPartner);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return  jsonArray;
    }

    public JSONArray debts(){
        JSONArray jsonArray = new JSONArray();
        RealmResults<Debt> realmResults = DebtsController.debts();
        for(Debt debt: realmResults){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("partner_id",debt.getPartner_id());
                jsonObject.put("item_id", debt.getItem_id());
                jsonObject.put("amount", debt.getAmount());
                jsonObject.put("clc_timestamp", debt.getClc_timestamp());
                jsonArray.put(jsonObject);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  jsonArray;
    }
}
