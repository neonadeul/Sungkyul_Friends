package com.gmail.sungkyulfriends.MyPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.SeniorAndJunior.GetMatchingPartnerID;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class review_page extends AppCompatActivity implements GetMyCalScore.OnTotalScoreReceivedListener{

    // 내 리뷰 페이지

    RecyclerView recyclerView;
    ReviewAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(review_page.this, mypage.class);
                startActivity(intent);
            }
        });

        // 내 아이디값 가져와서 receiver 변수에 넣기
        String receiver = login_page.userID;

        // 상대방 평균후기학점
        GetMyCalScore getMyCalScore = new GetMyCalScore(review_page.this, this); // 리스너 등록
        String total_score = getMyCalScore.getTotal_score();
        onTotalScoreReceived(total_score);

        // 리사이클러뷰
        recyclerView = findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(review_page.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ReviewAdapter(new ArrayList<>());


        // 서버에서 ViewReviewRequest를 통해 리뷰 가져오기
        Response.Listener<String> response_listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 응답을 받았을 때 실행할 코드
                Log.d("리뷰응답: ", response);

                // JSON 응답을 파싱
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    // "success" 키가 true이면 응답이 성공적으로 도착한 것입니다.
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        // 리뷰 데이터에 접근
                        for (int i = 0; i < jsonObject.length() - 1; i++) {
                            JSONObject review = jsonObject.getJSONObject(String.valueOf(i));
                            String score = review.getString("score");
                            String content = review.getString("content");

                            // 이제 score와 content를 사용할 수 있습니다.
                            Log.d("내 리뷰학점: ", score);
                            Log.d("내 리뷰내용: ", content);

                            adapter.addItem(new ReviewData(score, content));

                        }
                        // 리사이클러뷰에 어댑터를 연결한다.
                        recyclerView.setAdapter(adapter);

                    } else {
                        // 서버에서 오류가 발생한 경우
                        // 예: success가 false이면 서버에서 처리 중 오류가 발생했을 가능성이 있습니다.
                        Log.d("리뷰가져올때 에러발생, ", " success가 false");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    // JSON 파싱 중 오류가 발생한 경우
                }
            }
        };

        // 서버로 Volley를 이용해서 요청을 함
        ViewReviewRequest viewReviewRequest = new ViewReviewRequest(receiver, response_listener, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });
        RequestQueue queue = Volley.newRequestQueue(review_page.this);
        queue.add(viewReviewRequest);

    }
    @Override
    public void onTotalScoreReceived(String totalScore) {
        // total_score가 설정된 후에 호출되는 메서드
        TextView my_avg_score = findViewById(R.id.my_avg_score);
        my_avg_score.setText(totalScore);
    }
}