package com.gmail.sungkyulfriends.SeniorAndJunior;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MatchingRequest extends StringRequest {

    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://3.34.20.219/Matching/matchingRequest.php";
    private Map<String, String> map;

    public MatchingRequest(String userID, String wantdept, String wantyear, String wantsex, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("wantdept", wantdept);
        map.put("wantyear", wantyear);
        map.put("wantsex", wantsex);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
