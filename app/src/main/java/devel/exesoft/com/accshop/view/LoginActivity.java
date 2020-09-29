package devel.exesoft.com.accshop.view;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.controller.UserController;
import devel.exesoft.com.accshop.databinding.ActivityLoginBinding;
import devel.exesoft.com.accshop.model.User;
import devel.exesoft.com.accshop.view_model.LoginViewModel;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {


    private static final String  TAG = "LoginActivity";

    private Button loginButton;
    private EditText username;
    private EditText password;
    private TextView errorMessage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        activityLoginBinding.setViewModel(new LoginViewModel());
        activityLoginBinding.executePendingBindings();
        //setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.button_login);
        username = findViewById(R.id.input_username);
        password  = findViewById(R.id.input_password);
        progressBar = findViewById(R.id.login_progress_bar);
        errorMessage = findViewById(R.id.error_message);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //UserController.login(login.getUsername(), login.getPassword());
                Authorize();
            }
        });
    }

    private void Authorize(){
        progressBar.setVisibility(View.VISIBLE);
        UserController.login(username.getText().toString(), password.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, response);
                if(response != null){
                    try {
                        JSONObject result = new JSONObject(response);

                        if(result.getBoolean("success")){
                            final User user = new User();
                            user.setId(result.getJSONObject("data").getLong("id"));
                            user.setFio(result.getJSONObject("data").getString("fio"));
                            user.setUsername(result.getJSONObject("data").getString("username"));
                            user.setToken(result.getJSONObject("data").getString("token"));

                            Realm realm = Realm.getDefaultInstance();

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    // This will create a new object in Realm or throw an exception if the
                                    // object already exists (same primary key)
                                    realm.copyToRealm(user);
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    }catch (Exception excp){
                        Log.e(TAG, excp.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG , "Ошибка авторизации");
                progressBar.setVisibility(View.GONE);
                errorMessage.setText("Ошибка авторизации");
                errorMessage.setVisibility(View.VISIBLE);
            }
        });
    }


    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        Log.e(TAG, "Data Binding is working");
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
