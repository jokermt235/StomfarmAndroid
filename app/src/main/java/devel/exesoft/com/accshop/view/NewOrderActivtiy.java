package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.databinding.ActivityNewOrderBinding;
import devel.exesoft.com.accshop.model.Orderitem;
import devel.exesoft.com.accshop.model.Partner;
import devel.exesoft.com.accshop.view_model.NewOrderModelView;
import io.realm.Realm;
import io.realm.RealmResults;

public class NewOrderActivtiy extends AppCompatActivity {

    FloatingActionButton mFloatingAddItemMannual;
    FloatingActionButton mScanItemFb;

    private String partner_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNewOrderBinding activityNewOrderBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_order);
        activityNewOrderBinding.setViewModel(new NewOrderModelView());

        ListView mItemListView = (ListView)findViewById(R.id.new_order_listview_items);

        mFloatingAddItemMannual = (FloatingActionButton)findViewById(R.id.new_order_mannual_fb);

        mScanItemFb = findViewById(R.id.new_order_scan_fb);

        mFloatingAddItemMannual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewOrderActivtiy.this.startActivityForResult(new Intent(NewOrderActivtiy.this, NewOrderAddItemManuallyActivity.class), 1);
            }
        });

        mScanItemFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewOrderActivtiy.this.startActivityForResult(
                        new Intent(NewOrderActivtiy.this, SimpleScannerActivity.class), 2);
            }
        });

        ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.new_order_items_header, mItemListView,false);
        mItemListView.addHeaderView(headerView);

        partner_id = getIntent().getStringExtra("partner_id");
        initItems();
    }

    private void initItems() {
        int[] items = {R.id.new_order_items_item_name, R.id.new_order_items_item_price, R.id.new_order_items_item_count,  R.id.new_order_items_item_unit};

        String[] items2 = {"Name","Price","Count","Unit"};

        final List<HashMap<String, Object>> aList = new ArrayList<HashMap<String, Object>>();

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Orderitem> orderItems;

        orderItems = realm.where(Orderitem.class).findAll();
        for(Orderitem item: orderItems) {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            hm.put("Name", item.getName());
            hm.put("Price",item.getPrice());
            hm.put("Count", item.getAmount() );
            hm.put("Unit", item.getUnit());
            aList.add(hm);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.new_order_items_item, items2, items);

        ListView mItemListView = (ListView)findViewById(R.id.new_order_listview_items);

        mItemListView.setAdapter(simpleAdapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String newItemData = data.getStringExtra("new_item_data");
                try {
                    JSONObject mObjectNewOrder = new JSONObject(newItemData);

                    addItem(mObjectNewOrder);

                }catch (JSONException e){

                }
            }
        }

        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                String itemBarcode = data.getStringExtra("item_barcode");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("count", "1");
                    jsonObject.put("name", "Scanned");
                    jsonObject.put("barcode", itemBarcode);
                    addItem(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void addItem(final JSONObject jsonObject) throws  JSONException{
        Realm realm = Realm.getDefaultInstance();
        final Orderitem orderItem = new Orderitem();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm pRealm) {
                // This will create a new object in Realm or throw an exception if the
                // object already exists (same primary key)
                HashMap<String, Object> hm = new HashMap<String, Object>();
                try {
                    orderItem.setName(jsonObject.getString("name"));
                    orderItem.setAmount(Long.parseLong(jsonObject.getString("count")));
                    orderItem.setPartner_id(partner_id);
                    orderItem.setUnit("шт.");
                    orderItem.setBarcode(jsonObject.getString("barcode"));
                    orderItem.setPrice(Long.parseLong(jsonObject.getString("price")));
                }catch (Exception e){
                    e.printStackTrace();
                }
                pRealm.copyToRealmOrUpdate(orderItem);
            }
        });
        initItems();
    }
}
