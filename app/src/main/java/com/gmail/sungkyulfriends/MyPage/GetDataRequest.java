package com.gmail.sungkyulfriends.MyPage;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;

public class GetDataRequest {

    private OnDataReceivedListener listener;
    private RequestQueue queue;
    private String userID;
    private String[] interestArray;

    public GetDataRequest(String userID, String[] interestArray, OnDataReceivedListener listener) {
        this.userID = userID;
        this.interestArray = interestArray;
        this.listener = listener;
    }

    public void execute(Context context) {
        queue = Volley.newRequestQueue(context);

        String url = "http://3.34.20.219/Interest.php";

        // 관심사 배열에 있는 각 항목을 URL에 추가
        for (String interest : interestArray) {
            url += "&interest=" + interest;
        }

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onDataReceived(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleError(error);
                    }
                });

        queue.add(stringRequest); // 요청을 큐에 추가
    }

    private void handleError(VolleyError error) {
        // 오류 처리 로직을 여기에 구현
    }

    public interface OnDataReceivedListener {
        void onDataReceived(String data);
    }
}
