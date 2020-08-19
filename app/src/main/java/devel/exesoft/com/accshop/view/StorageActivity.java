package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import devel.exesoft.com.accshop.R;

import devel.exesoft.com.accshop.databinding.ActivityStorageBinding;
import devel.exesoft.com.accshop.view_model.StoreViewModel;

public class StorageActivity extends AppCompatActivity {

    private static String TAG = "StorageActivity";

    public ActivityStorageBinding activityStorageBinding;
    private StoreViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStorageBinding = DataBindingUtil.setContentView(this, R.layout.activity_storage);
        viewModel = new StoreViewModel(this);
        activityStorageBinding.setViewModel( viewModel);
        activityStorageBinding.executePendingBindings();
        setSupportActionBar(activityStorageBinding.storageToolbar);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.storage_toolbar_menu, menu);
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.d(TAG, "Syncronized clicked");
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.storage_toolbar_item_sync: viewModel.onSyncClicked(); return true;
            case R.id.storage_toolbar_item_export: viewModel.onExportClicked(); return true;
        }

            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(StorageActivity.this, HomeActivity.class));
    }
}
