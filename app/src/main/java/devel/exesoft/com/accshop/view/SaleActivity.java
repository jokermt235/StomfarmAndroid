package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.adapters.PartnerSaleAapter;
import devel.exesoft.com.accshop.model.Sale;
import io.realm.Realm;
import io.realm.RealmResults;

public class SaleActivity extends AppCompatActivity {

    private String TAG = "SaleActivity";
    private Toolbar toolbar;
    private ListView list;
    private String mobileId;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        toolbar = findViewById(R.id.saleToolbar);
        setSupportActionBar(toolbar);
        list = findViewById(R.id.saleList);
        total = findViewById(R.id.saleTotal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.remain_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocal();
    }

    private void  loadLocal(){
        ArrayList<Sale> saleItems  = new ArrayList();
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Sale> items = realm.where(Sale.class).findAll();
        int sum = 0;
        for(Sale item : items){
            saleItems.add(item);
            sum += (item.getItem_price() * item.getAmount());
        }
        total.setText(Integer.toString(sum));
        PartnerSaleAapter storeItemAdapter = new PartnerSaleAapter(getApplicationContext(), saleItems);
        list.setAdapter(storeItemAdapter);
    }
}