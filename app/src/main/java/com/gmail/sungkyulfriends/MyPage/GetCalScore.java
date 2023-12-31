package com.gmail.sungkyulfriends.MyPage;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.SeniorAndJunior.GetMatchingPartnerID;
import com.gmail.sungkyulfriends.SeniorAndJunior.MatchingPartnerID;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_main_friendMatching;

import org.json.JSONException;
import org.json.JSONObject;

// 상대방 평균학점 가져오기
public class GetCalScore {

    private String total_score;
    private Context context;
    private OnTotalScoreReceivedListener listener; // 콜백 리스너 추가
    RequestQueue queue; // RequestQueue 변수 추가

    String receiver = GetMatchingPartnerID.partnerID;

    public GetCalScore(Context context) {
        this.context = context;
    }

    // 생성자를 통해 Context 전달받기
    public GetCalScore(Context context ,OnTotalScoreReceivedListener listener) {
        this.context = context;
        this.listener = listener; // 콜백 리스너 설정
        queue = Volley.newRequestQueue(context); // RequestQueue 초기화

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 응답을 처리하는 코드 작성
                // response 변수에 API 응답이 포함됩니다.
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    total_score = jsonObject.getString("total_score");
                    Log.d("서버에서 상대방정보 받아온값 total_score", total_score);

                    if (listener != null) {
                        listener.onTotalScoreReceived(total_score); // 콜백 호출
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };

        CalScoreRequest calScoreRequest = new CalScoreRequest(receiver, responseListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("에러", error.getMessage());
            }
        });
        queue.add(calScoreRequest);
    }

    public interface OnTotalScoreReceivedListener {
        void onTotalScoreReceived(String totalScore);
    }

    public String getTotal_score() {
        // 데이터베이스에서 데이터를 가져오는 코드

        if (total_score == null) {
            return "0"; // 데이터베이스에서 데이터를 가져오지 못한 경우, 평균 점수를 0으로 초기화
        } else {
            return total_score;
        }
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

}

