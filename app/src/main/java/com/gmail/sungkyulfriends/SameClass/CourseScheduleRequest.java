package com.gmail.sungkyulfriends.SameClass;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CourseScheduleRequest extends StringRequest {

    // 서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://3.34.20.219/Classfriend/course_schedule.php";
    private Map<String, String> map;

    public CourseScheduleRequest(String department,int academic_year, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("department", department);
        map.put("academic_year", String.valueOf(academic_year));

    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }


}
