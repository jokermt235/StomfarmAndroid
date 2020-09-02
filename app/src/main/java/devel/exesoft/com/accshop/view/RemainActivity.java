package devel.exesoft.com.accshop.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.controller.RemainController;

public class RemainActivity extends AppCompatActivity {

    private String TAG = "RemainActivity";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remain);
        toolbar = findViewById(R.id.remainToolbar);
        setSupportActionBar(toolbar);
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
}