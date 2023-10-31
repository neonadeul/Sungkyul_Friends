package com.gmail.sungkyulfriends.SeniorAndJunior;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// 매칭완료상태에서 매칭취소하기
public class MatchedCancelRequest extends StringRequest {

    final static private String URL = "http://3.34.20.219/MatchedCancel/matchedCancel1.php";
    private Map<String,String> map;

    public MatchedCancelRequest(String userID, Response.Listener<String> listener, Response.ErrorListener errorListener){
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
