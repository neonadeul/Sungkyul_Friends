package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MatchingList_json extends StringRequest {

    final static private String URL = "http://3.34.20.219/Matching/matchinglist.php";
    private Map<String, String> map;

    public MatchingList_json(String fuserID, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("fuserID", fuserID);

        Log.d("fuserID 생성자호출할때 값" ,fuserID);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
