package devel.exesoft.com.accshop;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class NewOrderActivtiy extends AppCompatActivity {

    FloatingActionButton mFloatingAddItemMannual;
    FloatingActionButton mScanItemFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
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
                NewOrderActivtiy.this.startActivityForResult(new Intent(NewOrderActivtiy.this, SimpleScannerActivity.class), 2);
            }
        });

        ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.new_order_items_header, mItemListView,false);
        mItemListView.addHeaderView(headerView);

        //mItemListView.setAdapter();
        //ViewGroup headerView = (ViewGroup)
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

    private void addItem(JSONObject jsonObject) throws  JSONException{

        int[] items = {R.id.new_order_items_item_name, R.id.new_order_items_item_price, R.id.new_order_items_item_count,  R.id.new_order_items_item_unit};

        String[] items2 = {"Name","Price","Count","Unit"};

        List<HashMap<String, Object>> aList = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("Name", jsonObject.getString("name"));
        hm.put("Price",jsonObject.getString("barcode"));
        hm.put("Count", jsonObject.getString("count"));
        hm.put("Unit", "шт");
        aList.add(hm);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.new_order_items_item, items2, items);

        ListView mItemListView = (ListView)findViewById(R.id.new_order_listview_items);

        mItemListView.setAdapter(simpleAdapter);
    }
}
