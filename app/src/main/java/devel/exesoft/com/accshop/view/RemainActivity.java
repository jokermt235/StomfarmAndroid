package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.adapters.StoreItemAdapter;
import devel.exesoft.com.accshop.controller.RemainController;
import devel.exesoft.com.accshop.model.Item;

public class RemainActivity extends AppCompatActivity {

    private String TAG = "RemainActivity";
    private Toolbar toolbar;
    private ListView remainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remain);
        toolbar = findViewById(R.id.remainToolbar);
        setSupportActionBar(toolbar);
        remainList = findViewById(R.id.remainList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.remain_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        onItemSearch(item);
        return super.onOptionsItemSelected(item);
    }

    private void onItemSearch(MenuItem item){
        Log.d(TAG, "onItemSearchClicked action");
        SearchView mSearchView = (SearchView) item.getActionView(); mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange " + newText);
                if(newText != "") {
                    RemainController.getItems(newText, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            if(response != null) {
                                try {
                                    JSONObject result = new JSONObject(response);
                                    if (result.getBoolean("success")) {
                                        JSONArray data = result.getJSONArray("data");
                                        ArrayList<Item> items = new ArrayList<>();
                                        if (data.length() > 0) {
                                            for (int i = 0; i < data.length(); i++) {
                                                final Item item = new Item();
                                                item.setCount(data.getJSONObject(i).getInt("amount"));
                                                item.setName(data.getJSONObject(i).getString("name"));
                                                item.setUnit_string(data.getJSONObject(i).getString("unit_string"));
                                                item.setBarcode(data.getJSONObject(i).getString("barcode"));
                                                item.setPrice(data.getJSONObject(i).getLong("price"));
                                                items.add(item);
                                            }
                                        }
                                        remainList.setAdapter(new StoreItemAdapter(getApplicationContext(), items));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
                    });
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
}