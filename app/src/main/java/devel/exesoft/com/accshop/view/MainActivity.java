package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.databinding.ActivityMainBinding;
import devel.exesoft.com.accshop.view_model.MainViewModel;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().name("stomdb.realm").build();
        Realm.setDefaultConfiguration(config);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new MainViewModel();
        activityMainBinding.setViewModel(mainViewModel);
        activityMainBinding.executePendingBindings();
        if(!mainViewModel.isAuthorized()) {
            Intent lIntent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(lIntent);
            this.finish();
        }else{
            Intent lIntent = new Intent(MainActivity.this, HomeActivity.class);
            MainActivity.this.startActivity(lIntent);
            this.finish();
        }
    }


}
