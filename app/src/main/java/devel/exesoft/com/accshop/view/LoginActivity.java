package devel.exesoft.com.accshop.view;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.databinding.ActivityLoginBinding;
import devel.exesoft.com.accshop.view_model.LoginViewModel;

public class LoginActivity extends AppCompatActivity {


    private static final String  TAG = "LoginActivity";

    private ProgressBar progressBar;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        LoginViewModel loginViewModel = new LoginViewModel();
        progressBar = activityLoginBinding.loginProgressBar;
        loginViewModel.setProgressBar(progressBar);
        loginViewModel.setContext(getApplicationContext());
        loginViewModel.setActivity(this);
        loginViewModel.setErrorMessage(activityLoginBinding.errorMessage);
        activityLoginBinding.setViewModel(loginViewModel);
        activityLoginBinding.executePendingBindings();

        //setContentView(R.layout.activity_login);

        //Button button = (Button) findViewById(R.id.button_login);

        /*button.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        Log.e(TAG, "Data Binding is working");
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
