package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MatchingPartnerID extends StringRequest {

    private static MatchingPartnerID instance;
    // private static Context context;
    final static private String URL = "http://3.34.20.219/Matching/matchingPartnerID.php";
    private Map<String,String> map;

    public MatchingPartnerID(Context context, String userID, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, URL, listener, errorListener);
        // this.context = context;
        map = new HashMap<>();
        map.put("userID", userID);
    }

    public static synchronized MatchingPartnerID getInstance(Context context, String userID, Response.Listener<String> listener, Response.ErrorListener errorListener ){
        if(instance == null){
            instance = new MatchingPartnerID(context, userID, listener, errorListener);
        }
        return instance;
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
