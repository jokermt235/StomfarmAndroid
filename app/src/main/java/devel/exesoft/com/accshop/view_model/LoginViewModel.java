package devel.exesoft.com.accshop.view_model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import devel.exesoft.com.accshop.controller.AppController;
import devel.exesoft.com.accshop.controller.UserController;
import devel.exesoft.com.accshop.model.Login;

public class LoginViewModel extends BaseObservable {

    private Login login;

    @Bindable
    private String toastMessage = null;
    private static final String  TAG = "LoginViewModel";

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

    /*public void onLoginClicked() {
        toastMessage = "HELLO WORLD";
        Log.d(TAG, "Accessing by username :" + login.getUsername());
        UserController.login(login.getUsername(), login.getPassword());
        notifyPropertyChanged(BR.username);
    }*/


}
