package devel.exesoft.com.accshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = (Button) findViewById(R.id.button_login);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.d(“Application”, “The host is “ + BuildConfig.hostAPI );
                //Toast.makeText(LoginActivity.this, BuildConfig.SERVER_URL, Toast.LENGTH_LONG).show();
                //Auth.authorize("hello","123",getApplicationContext());

                if(Auth.isAuthorized()) {
                    Intent hIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    LoginActivity.this.startActivity(hIntent);
                }
            }
        });
    }

}
