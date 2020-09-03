package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.adapters.PartnerDebtAdapter;
import devel.exesoft.com.accshop.model.Debt;
import devel.exesoft.com.accshop.model.User;
import io.realm.Realm;
import io.realm.RealmResults;

public class DebtActivity extends AppCompatActivity {

    private String TAG = "DebtActivity";
    private Toolbar toolbar;
    private ListView list;
    private String mobileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt);
        toolbar = findViewById(R.id.debtToolbar);
        setSupportActionBar(toolbar);
        list = findViewById(R.id.debtList);
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        mobileId  = user.getMobile_id();
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

    public void loadLocal(){
        ArrayList<Debt> debtItems  = new ArrayList();
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Debt> items = realm.where(Debt.class).equalTo("mobile_id",mobileId).findAll();
        for(Debt item : items){
            if(!item.getClc_status()) {
                debtItems.add(item);
            }
        }
        PartnerDebtAdapter storeItemAdapter = new PartnerDebtAdapter(getApplicationContext(), debtItems);
        list.setAdapter(storeItemAdapter);
    }

}