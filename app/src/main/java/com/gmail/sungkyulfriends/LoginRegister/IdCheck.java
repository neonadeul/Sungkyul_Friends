package com.gmail.sungkyulfriends.LoginRegister;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class IdCheck extends StringRequest {

    final static private String URL = "http://3.34.20.219/IdCheck.php";
    private Map<String, String> map;

    public IdCheck(String userID, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("userID", userID);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
