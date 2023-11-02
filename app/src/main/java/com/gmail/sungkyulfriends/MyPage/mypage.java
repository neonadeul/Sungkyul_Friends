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
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.SchoolCheck.school_accreditation_choice;

public class mypage extends AppCompatActivity implements GetMyCalScore.OnTotalScoreReceivedListener{

    private Button more_review_button;
    private Button retouch_profile_button;
    private TextView log_out_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        // 로그아웃 버튼
        log_out_btn= findViewById(R.id.log_out_btn);
        log_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypage.this, login_page.class);
                startActivity(intent);
            }
        });

        // 내 평균후기학점
//        GetMyCalScore getMyCalScore = new GetMyCalScore(mypage.this, this); // 리스너 등록
//        String total_score = getMyCalScore.getTotal_score();
//        onTotalScoreReceived(total_score);

        // 내 평균후기학점
        GetMyCalScore getmyCalScore = new GetMyCalScore(mypage.this, new GetMyCalScore.OnTotalScoreReceivedListener() {
            @Override
            public void onTotalScoreReceived(String totalScore) {
                // total_score가 설정된 후에 호출되는 메서드
                TextView my_avg_score = findViewById(R.id.my_avg_score);
                my_avg_score.setText(totalScore);

                // 받은 후기멘트 보러가기 버튼 활성화
                more_review_button.setEnabled(true);
                more_review_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FEE6E2")));
                more_review_button.setText("받은 후기멘트 보러가기");
            }
        });



        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView calender= findViewById(R.id.calender);
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

        retouch_profile_button = findViewById(R.id.retouch_profile_button);
        retouch_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mypage.this, profile_retouch.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onTotalScoreReceived(String totalScore) {
        // total_score가 설정된 후에 호출되는 메서드
        TextView my_avg_score = findViewById(R.id.my_avg_score);
        my_avg_score.setText(totalScore);
    }
}