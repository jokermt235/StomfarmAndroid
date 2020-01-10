package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.adapters.MannualItemAdapter;
import devel.exesoft.com.accshop.model.Item;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class MannualActivity extends AppCompatActivity {

    private static String TAG = "MannualActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mannual);
        Toolbar toolbar = findViewById(R.id.add_mannual_toolbar);
        ListView listView = findViewById(R.id.add_mannual_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClickListner");
                TextView itemId = view.findViewById(R.id.storeItemId);
                confirmSelection(String.valueOf(itemId.getText()));
            }
        });
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.partners_search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "OnOptionsItemSelected action");
        SearchView mSearchView = (SearchView) item.getActionView(); mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange " + newText);
                if(newText != "")
                fillItemList(newText);
                return false;
            }
        });
        return super.onOptionsItemSelected(item);
    }

    public void fillItemList(String itemName){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Item> items = realm.where(Item.class).contains("name", itemName, Case.INSENSITIVE).findAll();
        ListView listView = (ListView)findViewById(R.id.add_mannual_listview);
        ArrayList<Item> list = new ArrayList();
        for(Item item : items){
            list.add(item);
        }
        MannualItemAdapter storeItemAdapter = new MannualItemAdapter(this,list);
        listView.setAdapter(storeItemAdapter);
    }

    public void confirmSelection(String id){
        Intent mIntent = new Intent(this, PartnerActivity.class );
        mIntent.putExtra("id",id);
        Log.d(TAG, "Confirming selection with value : " + id);
        setResult(RESULT_OK, mIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
