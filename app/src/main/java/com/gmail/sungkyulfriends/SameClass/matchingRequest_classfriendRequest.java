package com.gmail.sungkyulfriends.SameClass;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class matchingRequest_classfriendRequest extends StringRequest {

    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://3.34.20.219/Classfriend/matchingRequest_classfriend.php";
    private Map<String, String> map;

    public matchingRequest_classfriendRequest(String userID,String department,int academic_year,String courseName,String wantyear, String wantsex,Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("department", department);
        map.put("academic_year", String.valueOf(academic_year));
        map.put("courseName", courseName);
        map.put("wantyear", wantyear);
        map.put("wantsex", wantsex);

    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
