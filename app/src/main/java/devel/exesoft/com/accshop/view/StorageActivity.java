package devel.exesoft.com.accshop.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import devel.exesoft.com.accshop.R;

public class StorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.storage_toolbar);
        setSupportActionBar(mActionBarToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.storage_toolbar_menu, menu);
        return true;
    }
}
