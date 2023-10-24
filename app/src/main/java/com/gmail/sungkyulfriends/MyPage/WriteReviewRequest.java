package com.gmail.sungkyulfriends.MyPage;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class WriteReviewRequest extends StringRequest {

    final static private String URL = "http://3.34.20.219/Review/WriteReview.php";
    private Map<String, String> map;

    public WriteReviewRequest(String sender, String receiver, String score, String content, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("sender" , sender);
        map.put("receiver" , receiver);
        map.put("score" , score);
        map.put("content" , content);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
