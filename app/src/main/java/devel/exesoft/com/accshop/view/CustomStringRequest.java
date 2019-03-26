package devel.exesoft.com.accshop.view;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import devel.exesoft.com.accshop.model.User;
import io.realm.Realm;

public class CustomStringRequest extends StringRequest {

    private JSONObject body;
    public void setBody(JSONObject pBody){
        body = pBody;
    }
    public CustomStringRequest(int method, String url,JSONObject params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);

        body = params;
    }

    public CustomStringRequest(String url,JSONObject params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, listener, errorListener);

        body = params;
    }

    public CustomStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError{

        Map<String, String> headers = new  HashMap<>();

        headers.put("Content-Type", "application/json; charset=UTF-8");

        headers.put("Accept", "application/json");

        return headers;
    }

    @Override

    public byte[] getBody() throws AuthFailureError{

        return body.toString().getBytes();
    }
}
