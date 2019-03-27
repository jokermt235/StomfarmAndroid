package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.controller.StoreContoller;
import devel.exesoft.com.accshop.databinding.ActivityStorageBinding;
import devel.exesoft.com.accshop.model.Store;
import devel.exesoft.com.accshop.view_model.StoreViewModel;
import io.realm.Realm;

public class StorageActivity extends AppCompatActivity {

    private static String TAG = "StorageActivity";

    private ActivityStorageBinding activityStorageBinding;
    private StoreViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStorageBinding  activityStorageBinding = DataBindingUtil.setContentView(this, R.layout.activity_storage);
        viewModel = new StoreViewModel(this);
        activityStorageBinding.setViewModel( viewModel);
        activityStorageBinding.executePendingBindings();
        setSupportActionBar(activityStorageBinding.storageToolbar);
        setStore(activityStorageBinding);

    }

    private void setStore(ActivityStorageBinding dataBindingUtil){
        if(!StoreContoller.isExist()){
            StoreContoller.getRemoteStore(dataBindingUtil);
        }else{
            Realm realm = Realm.getDefaultInstance();
            Store store = realm.where(Store.class).findFirst();
            if(store != null){
                dataBindingUtil.storageToolbar.setTitle(store.getName());
                realm.close();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.storage_toolbar_menu, menu);
        return true;
    }
}