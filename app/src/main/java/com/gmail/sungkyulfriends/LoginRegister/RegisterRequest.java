package com.gmail.sungkyulfriends.LoginRegister;


import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://3.34.20.219/Register.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPassword, String userPassword2, String userName , String sex, String year, String studentID, String dept_type, String dept ,Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("pw2", userPassword2);
        map.put("userName", userName);
        map.put("sex", sex);
        map.put("year", year);
        map.put("studentID", studentID);
        map.put("dept_type", dept_type);
        map.put("dept", dept);

    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
