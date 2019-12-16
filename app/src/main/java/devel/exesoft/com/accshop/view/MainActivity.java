package devel.exesoft.com.accshop.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.controller.SynchController;
import devel.exesoft.com.accshop.controller.UserController;
import devel.exesoft.com.accshop.databinding.ActivityMainBinding;
import devel.exesoft.com.accshop.view_model.MainViewModel;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainActivity extends AppCompatActivity {

    private final static int INTERVAL = 1000 * 60 * 1;

    private static String TAG = "MainActivity";

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getApplicationContext());
        RealmConfiguration config =
                new RealmConfiguration.Builder().name("stomdb.realm").deleteRealmIfMigrationNeeded().build();
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

        startRepeatingTask();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    Runnable mHandlerTask = new Runnable()
    {
        @Override
        public void run() {
            if(isNetworkAvailable()) {
                Log.d(TAG, "The repeating task has been running!!!");
                SynchController.synchAll();
            }
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };

    private void startRepeatingTask()
    {
        mHandlerTask.run();
    }

    private void stopRepeatingTask()
    {
        mHandler.removeCallbacks(mHandlerTask);
    }


}
