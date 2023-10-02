package com.gmail.sungkyulfriends.SeniorAndJunior;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// 안쓰는 코드같지만 혹시몰라 안지우고 놔둘게요

public class MatchingStateRequest extends StringRequest {
    final static private String URL = "http://3.34.20.219/Matching/matchingState.php";
    private Map<String,String> map;

    public MatchingStateRequest(String userID, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("userID" , userID);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
