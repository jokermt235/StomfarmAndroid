package devel.exesoft.com.accshop.view_model;

import java.util.Observable;

import devel.exesoft.com.accshop.controller.UserController;

public class MainViewModel extends Observable {
    private static String TAG = "MainViewModel";
    private boolean authorized = false;
    public MainViewModel(){
        authorized = UserController.isAuthorized();
    }

    public boolean isAuthorized(){
        return  authorized;
    }
}
