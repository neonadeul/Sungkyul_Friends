package com.gmail.sungkyulfriends.MyPage;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class profileRequest extends StringRequest {
    final static private String URL = "http://3.34.20.219/UpdateInfo/updateUserInfo.php";
    private Map<String, String> map;
    private Response.Listener<String> listener;
    private Response.ErrorListener errorListener;

    public profileRequest(String userID, String Name, String sex, String year, String dept_t, String main_dept,
                          Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);
        map = new HashMap<>();
        map.put("userID", userID);
        map.put("Name", Name);
        map.put("sex", sex);
        map.put("year", year);
        map.put("dept_t", dept_t);
        map.put("main_dept", main_dept);
        Log.v("태그", "매핑5 완료");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    // 서버 응답 처리
    @Override
    protected Response<String> parseNetworkResponse(com.android.volley.NetworkResponse response) {
        try {
            String json = new String(response.data, "UTF-8");
            return Response.success(json, getCacheEntry());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return super.parseNetworkResponse(response);
    }

    // 서버 응답을 전달
    @Override
    protected void deliverResponse(String response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }

    // 서버 에러를 전달
    @Override
    public void deliverError(com.android.volley.VolleyError error) {
        if (errorListener != null) {
            errorListener.onErrorResponse(error);
        }
    }
}
