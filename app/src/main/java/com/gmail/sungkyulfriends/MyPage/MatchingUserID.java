// 파일명: MatchingUserID.java
package com.gmail.sungkyulfriends.MyPage;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MatchingUserID {
    private static MatchingUserID instance;
    private RequestQueue queue;
    private Context context;

    private MatchingUserID(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public static synchronized MatchingUserID getInstance(Context context) {
        if (instance == null) {
            instance = new MatchingUserID(context);
        }
        return instance;
    }

    public void profileRequest(String userID, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String URL = "http://3.34.20.219/Register.php"; // 실제 URL로 대체
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, listener, errorListener) {
            // 필요한 메서드 오버라이드
        };
        queue.add(stringRequest);
    }
}
