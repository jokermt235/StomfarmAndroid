package devel.exesoft.com.accshop.view;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.controller.UserController;
import devel.exesoft.com.accshop.model.Debt;
import devel.exesoft.com.accshop.model.User;
import io.realm.Realm;

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
                case "Остатки" : hm.put("Image", R.mipmap.ic_home_left_menu_credit);hm.put("Title",row);break;
                case "Заказы" : hm.put("Image", R.mipmap.ic_home_left_menu_orders);hm.put("Title",row);break;
                case "Склад" : hm.put("Image", R.mipmap.ic_home_left_menu_stores);hm.put("Title",row);break;
                case "Продажи" : hm.put("Image", R.mipmap.ic_sales_icon_foreground);hm.put("Title",row);break;
                case "Долги" : hm.put("Image", R.mipmap.ic_debt_icon_foreground);hm.put("Title",row);break;
             }
            aList.add(hm);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.home_left_menu_item, items2, items);

        mHomeLeftMenu.setAdapter(simpleAdapter);

        listViewEvents(mHomeLeftMenu);

        askPermission();

        setToolbarTitle(getToolbarTitle());
    }

    private String getToolbarTitle(){
        String title = "";
        Realm realm = Realm.getDefaultInstance();
        try {
            User user = realm.where(User.class).findFirst();
            title = user.getFio();
        } finally {
            realm.close();
        }
        return title;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home_toolbar_menu_item1: UserController.logout(getApplicationContext(), this); return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void listViewEvents(ListView pHomeLeftMenu)
    {
        pHomeLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, ClientsActivity.class));finish();break;
                    case 1 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, RemainActivity.class));finish();break;
                    case 2 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, OrdersActivity.class));finish();break;
                    case 3 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, StorageActivity.class));finish();break;
                    case 4 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, SaleActivity.class));finish();break;
                    case 5 : HomeActivity.this.startActivity(new Intent(HomeActivity.this, DebtActivity.class));finish();break;
                }
            }
        });
    }

    private void askPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA
        },1);

    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
