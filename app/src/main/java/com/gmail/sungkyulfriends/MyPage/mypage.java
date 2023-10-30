package com.gmail.sungkyulfriends.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.sungkyulfriends.Calender.class_calender;
import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;

public class mypage extends AppCompatActivity implements GetProfile.OnProfileInfoListener, GetMyCalScore.OnTotalScoreReceivedListener {

    private Button more_review_button;
    private Button retouch_profile_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        // "백(Back)" 버튼 클릭 시 메인 화면으로 이동
        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView calender = findViewById(R.id.calender);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypage.this, class_calender.class);
                startActivity(intent);
            }
        });

        more_review_button = findViewById(R.id.more_review_button);
        more_review_button.setEnabled(false); // 일단 비활성화 상태로 초기화
        more_review_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d9d9d9")));
        more_review_button.setText("받은 리뷰가 없습니다");
        more_review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypage.this, review_page.class);
                startActivity(intent);
            }
        });

        // GetProfile 클래스로 사용자 프로필 정보 가져오기
        GetProfile getProfile = new GetProfile(this);
        getProfile.setOnProfileInfoListener(this);

        // GetMyCalScore 클래스로 평균 점수 가져오기
        GetMyCalScore getMyCalScore = new GetMyCalScore(this, this);

        retouch_profile_button = findViewById(R.id.retouch_profile_button);
        retouch_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypage.this, profile_retouch.class);
                startActivity(intent);
            }
        });
    }

    // GetProfile 클래스에서 사용자 프로필 정보 받아오기
    @Override
    public void onProfileInfoReceived(String userID, String name, String sex, String year, String main_dept, String dept_t) {
        TextView myNameTextView = findViewById(R.id.et_name);
        myNameTextView.setText(name);

        retouch_profile_button = findViewById(R.id.retouch_profile_button);
        retouch_profile_button.setEnabled(true);
        retouch_profile_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FEE6E2")));
        retouch_profile_button.setText("수정하기");
    }

    @Override
    public void onProfileInfoError(String errorMessage) {
        // GetProfile 클래스로부터 사용자 프로필 정보 받아오기 실패 시 에러 처리
    }

    // GetMyCalScore 클래스에서 평균 점수 받아오기
    @Override
    public void onTotalScoreReceived(String totalScore) {
        TextView my_avg_score = findViewById(R.id.my_avg_score);
        my_avg_score.setText(totalScore);
    }
}
