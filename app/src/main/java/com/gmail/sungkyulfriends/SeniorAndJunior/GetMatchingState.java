package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetMatchingState {

    private Context context;
    public static int matchingState = -1; // 초기값을 -1로 설정

    // 생성자에서 Context 받아오기
    public GetMatchingState(Context context) {
        this.context = context;
    }

    // login_page에서 userID값 받아오기
    String userID = login_page.userID;

    public interface MatchingStateCallback {
        void onMatchingStateReceived(int matchingState);
    }

    public void requestMatchingState(final MatchingStateCallback callback) {
        String url = "http://3.34.20.219/Matching/matchingState.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            matchingState = jsonObject.getInt("matchingState");
                            Log.d("이번에 만든 api에서 가져온값", String.valueOf(matchingState));
                            // 응답을 받았을 때 콜백을 호출하여 UI를 업데이트합니다.
                            callback.onMatchingStateReceived(matchingState);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userID", userID);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);


    }
}
