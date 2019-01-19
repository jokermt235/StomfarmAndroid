package devel.exesoft.com.accshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent lIntent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(lIntent);
        this.finish();
    }

}
