package com.gmail.sungkyulfriends.MyPage;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import java.util.HashMap;
import java.util.Map;

public class MatchingUserID {
    private String userID = login_page.userID;
    private RequestQueue queue;
    private Context context;

    private MatchingUserID(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public void profileRequest(String userID, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String URL = "http://3.34.20.219/UpdateInfo/updateUserInfo.php"; // Replace with the actual URL
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userID", userID);

                return params;
            }
        };
        queue.add(stringRequest);
    }
}
