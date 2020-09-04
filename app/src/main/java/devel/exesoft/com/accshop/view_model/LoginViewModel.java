package devel.exesoft.com.accshop.view_model;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.databinding.library.baseAdapters.BR;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import devel.exesoft.com.accshop.controller.AppController;
import devel.exesoft.com.accshop.controller.UserController;
import devel.exesoft.com.accshop.model.Login;
import devel.exesoft.com.accshop.model.User;
import devel.exesoft.com.accshop.view.HomeActivity;
import devel.exesoft.com.accshop.view.LoginActivity;
import io.realm.Realm;

public class LoginViewModel extends BaseObservable {

    private Login login;

    @Bindable
    private String toastMessage = null;
    private static final String  TAG = "LoginViewModel";
    private ProgressBar progressBar;
    private Context context;
    private LoginActivity activity;
    private TextView errorMessage;

    @Bindable
    public String getUsername() {
        return login.getUsername();
    }

    @Bindable
    public String getPassword() {
        return login.getPassword();
    }

    public void setUsername(String username) {
        login.setUsername(username);
    }

    public void setPassword(String password){
        login.setPassword(password);
    }

    public LoginViewModel(){
        login = new Login("","");
    }

    public void setProgressBar(ProgressBar progressBar){
        this.progressBar = progressBar;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setActivity(LoginActivity activity) {
        this.activity = activity;
    }

    public void setErrorMessage(TextView errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void onLoginClicked() {
        toastMessage = "HELLO WORLD";
        Log.d(TAG, "Accessing by username :" + login.getUsername());
        progressBar.setVisibility(View.VISIBLE);
        UserController.login(login.getUsername(), login.getPassword(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null){
                    progressBar.setVisibility(View.VISIBLE);
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
                                }
                            });
                            Intent intent = new Intent(context, HomeActivity.class);
                            errorMessage.setVisibility(View.VISIBLE);
                            context.startActivity(intent);
                            activity.finish();
                        }
                    }catch (Exception excp){
                        Log.e(TAG, excp.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorMessage.setVisibility(View.VISIBLE);
                errorMessage.setText("Ошибка входа неправильное имя пользователя или пароль");
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        notifyPropertyChanged(BR.username);
    }

}
