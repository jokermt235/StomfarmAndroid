package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.databinding.ActivityClientsBinding;
import devel.exesoft.com.accshop.model.Partner;
import devel.exesoft.com.accshop.view_model.ClientsViewModel;
import io.realm.Realm;
import io.realm.RealmResults;

public class ClientsActivity extends AppCompatActivity {

    ListView mListviewPartners;
    ImageButton mImageButton;

    FloatingActionButton mFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_clients);

        ActivityClientsBinding activityClientsBinding = DataBindingUtil.setContentView(this, R.layout.activity_clients);
        ClientsViewModel clientsViewModel = new ClientsViewModel();
        activityClientsBinding.setViewModel(clientsViewModel);
        activityClientsBinding.executePendingBindings();

        initPartnersList(activityClientsBinding);

        activityClientsBinding.floatingButtonPartners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientsActivity.this.startActivity(new Intent(ClientsActivity.this, NewPartnerActivity.class));
            }
        });
    }

    private void initPartnersList(ActivityClientsBinding activityClientsBinding)
    {
        Realm realm = Realm.getDefaultInstance();

        final RealmResults<Partner> clients = realm.where(Partner.class).findAll();
        int[] items = {R.id.partner_id, R.id.textview_partner_name, R.id.textview_partner_phone, R.id.textview_client_debt};
        String[] items2 = {"id","Name","Phone","Debt"};
        List<HashMap<String, Object>> aList = new ArrayList<HashMap<String, Object>>();
        if(clients.size() > 0){
            for(Partner partner: clients) {
                HashMap<String, Object> hm = new HashMap<String, Object>();
                hm.put("Id", partner.getId());
                hm.put("Name", partner.getName());
                hm.put("Phone",partner.getPhone());
                hm.put("Debt", "0 СОМ");
                aList.add(hm);
            }

        }
        activityClientsBinding.listviewPartners.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.partners_listview_item_view, items2, items);
        activityClientsBinding.listviewPartners.setAdapter(simpleAdapter);
        listViewEvents(activityClientsBinding.listviewPartners);

        /*Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.tool_bar_clients);
        setSupportActionBar(mActionBarToolbar);

        LinearLayout linearLayout = findViewById(R.id.linear_layout_partners);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Linear layout click", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void listViewEvents(ListView pListviewPartners){
        pListviewPartners.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent  = new Intent(ClientsActivity.this, PartnerActivity.class);
                TextView id = (TextView)view.findViewById(R.id.partner_id);
                intent.putExtra("id", id.getText());
                ClientsActivity.this.startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.partners_search_menu, menu);
        return true;
    }
}
