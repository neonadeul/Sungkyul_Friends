package com.gmail.sungkyulfriends.LoginRegister;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class InterestRequest extends StringRequest {

    private static final String URL = "http://3.34.20.219/Interest.php";
    private Map<String, String> params;

    public InterestRequest(String userID, String[] interestArray, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        if (TextUtils.isEmpty(userID)) {
            throw new IllegalArgumentException("userID cannot be null or empty");
        }

        // Request Parameters 설정
        params = new HashMap<>();
        params.put("userID", userID);

        if (interestArray != null) {
            // interestArray를 직접 params에 추가
            for (int i = 0; i < interestArray.length; i++) {
                params.put("interestArray[" + i + "]", interestArray[i]);
            }
        }

        Map<Object, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("interestArray", interestArray);
        Log.v("태그", "매핑3 완료");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
