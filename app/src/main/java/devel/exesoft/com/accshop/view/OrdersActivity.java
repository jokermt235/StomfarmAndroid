package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Partner;
import io.realm.Realm;
import io.realm.RealmResults;

public class OrdersActivity extends AppCompatActivity {

    FloatingActionButton mFloatingButton;
    RealmResults<Partner> clients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        mFloatingButton = (FloatingActionButton)findViewById(R.id.add_new_order_button);

        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrdersActivity.this.startActivity(new Intent(OrdersActivity.this, NewOrderActivtiy.class));
            }
        });

        Realm realm = Realm.getDefaultInstance();


        clients = realm.where(Partner.class).findAll();
        int[] items = {R.id.partner_id, R.id.textview_partner_name, R.id.textview_partner_phone, R.id.textview_client_debt};
        String[] items2 = {"Id","Name","Phone","Debt"};
        List<HashMap<String, Object>> aList = new ArrayList<HashMap<String, Object>>();
        if(clients.size() > 0){
            for(Partner partner: clients) {
                HashMap<String, Object> hm = new HashMap<String, Object>();
                hm.put("Id", partner.getId());
                hm.put("Name", partner.getName());
                hm.put("Phone",partner.getPhone());
                aList.add(hm);
            }

        }

        ListView orders = findViewById(R.id.listview_orders);

        orders.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.partners_listview_item_view, items2, items);
        orders.setAdapter(simpleAdapter);
        listViewEvents(orders);
    }

    private void listViewEvents(ListView pListviewPartners){
        pListviewPartners.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent  = new Intent(OrdersActivity.this, NewOrderActivtiy.class);
                TextView id = (TextView)view.findViewById(R.id.partner_id);
                intent.putExtra("id", clients.get(i).getId());
                OrdersActivity.this.startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent lIntent = new Intent(OrdersActivity.this, HomeActivity.class);
        startActivity(lIntent);
        finish();
    }
}
