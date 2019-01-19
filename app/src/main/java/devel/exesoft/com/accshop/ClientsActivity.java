package devel.exesoft.com.accshop;

import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientsActivity extends AppCompatActivity {

    ListView mListviewPartners;
    ImageButton mImageButton;

    FloatingActionButton mFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
        initPartnersList();
        mFloatingButton = (FloatingActionButton)findViewById(R.id.floating_button_partners);

        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"BUTTON CLICKED", Toast.LENGTH_LONG).show();
                ClientsActivity.this.startActivity(new Intent(ClientsActivity.this, NewPartnerActivity.class));
            }
        });
    }

    private void initPartnersList()
    {
        mListviewPartners = (ListView)findViewById(R.id.listview_partners);
        mListviewPartners.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        int[] items = {R.id.partner_id, R.id.textview_partner_name, R.id.textview_partner_phone, R.id.textview_client_debt};

        String[] items2 = {"id","Name","Phone","Debt"};

        List<HashMap<String, Object>> aList = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("Id", "1");
        hm.put("Name", "Test1");
        hm.put("Phone","0701 710 780");
        hm.put("Debt", "234444 СОМ");
        HashMap<String, Object> hm2 = new HashMap<String, Object>();
        hm2.put("Id", "2");
        hm2.put("Name", "Test2");
        hm2.put("Phone","0701 710 780");
        hm2.put("Debt", "234444 СОМ");
        aList.add(hm);
        aList.add(hm2);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, aList, R.layout.partners_listview_item_view, items2, items);

        mListviewPartners.setAdapter(simpleAdapter);
        listViewEvents(mListviewPartners);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.tool_bar_clients);
        setSupportActionBar(mActionBarToolbar);

        LinearLayout linearLayout = findViewById(R.id.linear_layout_partners);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Linear layout click", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void listViewEvents(ListView pListviewPartners){
        //Toast.makeText(ClientsActivity.this, "HELLO WORLD", Toast.LENGTH_LONG).show();


        pListviewPartners.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(ClientsActivity.this, "HELLO WORLD", Toast.LENGTH_LONG).show();
                ClientsActivity.this.startActivity(new Intent(ClientsActivity.this, PartnerActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.partners_search_menu, menu);
        return true;
    }
}
