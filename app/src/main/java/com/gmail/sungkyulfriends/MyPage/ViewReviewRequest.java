package com.gmail.sungkyulfriends.MyPage;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ViewReviewRequest extends StringRequest {
    final static private String URL = "http://3.34.20.219/Review/ViewReview.php";
    private Map<String, String> map;
    private Response.Listener<String> listener; // 추가

    public ViewReviewRequest(String receiver, Response.Listener<String> listener, Response.ErrorListener errorListener ){

        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("receiver" , receiver);
        this.listener = listener; // 추가
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    // 추가
    @Override
    protected void deliverResponse(String response) {
        // 요청이 성공했을 때 호출될 콜백 함수
        listener.onResponse(response);
    }
}
