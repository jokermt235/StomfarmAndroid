package devel.exesoft.com.accshop;

import android.content.Context;

import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Auth {

    public static void authorize(String usename, String password, Context mContext)
    {
        //Volley.newRequestQueue(mContext);
    }

    public static boolean isAuthorized()
    {
        return true;
    }

    public static  JSONObject user() throws JSONException {
        return  new JSONObject("{}");
    }

    public static void login(){

    }
}
