package devel.exesoft.com.accshop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ListView mHomeLeftMenu;
    String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mHomeLeftMenu = (ListView)findViewById(R.id.home_left_menu);
        mHomeLeftMenu.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        int[] items = {R.id.home_left_menu_image, R.id.home_left_menu_text};
        String[] items2 = {"Image","Title"};

        List<HashMap<String, Object>> aList = new ArrayList<HashMap<String, Object>>();

        String[] stringArray = this.getResources().getStringArray(R.array.home_left_menu_items);

        for(String row : stringArray)
        {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            switch(row){
                case "Контрагенты" : hm.put("Image", R.mipmap.ic_home_left_menu_partners);hm.put("Title",row);break;
                case "Долги" : hm.put("Image", R.mipmap.ic_home_left_menu_credit);hm.put("Title",row);break;
                case "Заказы" : hm.put("Image", R.mipmap.ic_home_left_menu_orders);hm.put("Title",row);break;
                case "Склад" : hm.put("Image", R.mipmap.ic_home_left_menu_stores);hm.put("Title",row);break;
                case "Платежи" : hm.put("Image", R.mipmap.ic_home_left_menu_payments);hm.put("Title",row);break;
             }
            aList.add(hm);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.home_left_menu_item, items2, items);

        mHomeLeftMenu.setAdapter(simpleAdapter);

        listViewEvents(mHomeLeftMenu);

        setToolbarTitle("Test User");
    }

    private void setToolbarTitle(String title){
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        mActionBarToolbar.setTitle(title);
        setSupportActionBar(mActionBarToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);
        return true;
    }


    public void listViewEvents(ListView pHomeLeftMenu)
    {
        pHomeLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),Integer.toString(i), Toast.LENGTH_LONG).show();
                switch (i){
                    case 0 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, ClientsActivity.class));break;
                    case 1 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, DebtsActivity.class));break;
                    case 2 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, OrdersActivity.class));break;
                    case 3 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, StorageActivity.class));break;
                    case 4 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, ClientsActivity.class));break;
                }
            }
        });
    }
}
