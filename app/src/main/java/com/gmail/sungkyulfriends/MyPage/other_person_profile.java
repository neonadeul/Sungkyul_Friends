package com.gmail.sungkyulfriends.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.sungkyulfriends.MatchingList.matching_list_same_class;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.SeniorAndJunior.GetMatchingPartnerID;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_matched_firendFirst;

public class other_person_profile extends AppCompatActivity implements GetCalScore.OnTotalScoreReceivedListener{

    private Button more_review_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person_profile);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(other_person_profile.this, class_matched_firendFirst.class);
                startActivity(intent);
            }
        });

        // 상대방 평균후기학점
//        GetCalScore getCalScore = new GetCalScore(other_person_profile.this, this); // 리스너 등록
//        String total_score = getCalScore.getTotal_score();
//        onTotalScoreReceived(total_score);

        // 상대방 평균후기학점
        GetCalScore getCalScore = new GetCalScore(other_person_profile.this, new GetCalScore.OnTotalScoreReceivedListener() {
            @Override
            public void onTotalScoreReceived(String totalScore) {
                // total_score가 설정된 후에 호출되는 메서드
                TextView avg_score = findViewById(R.id.avg_score);
                avg_score.setText(totalScore);

                // 받은 후기멘트 보러가기 버튼 활성화
                more_review_button.setEnabled(true);
                more_review_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FEE6E2")));
                more_review_button.setText("받은 후기멘트 보러가기");
            }
        });

        // 받은 후기멘트 보러가기 버튼
        more_review_button = findViewById(R.id.more_review_button);
        more_review_button.setEnabled(false); // 일단 비활성화 상태로 초기화
        more_review_button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d9d9d9")));
        more_review_button.setText("받은 리뷰가 없습니다");
        more_review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(other_person_profile.this, other_person_review_page.class);
                intent.putExtra("total_score", getCalScore.getTotal_score());
                startActivity(intent);
            }
        });

        // 상대방 이름
        TextView partner_name_text = findViewById(R.id.partner_name_text);
        Intent intent = getIntent();
        String partner_name = intent.getStringExtra("partner_name");
        partner_name_text.setText(partner_name);


        // 서버에서 상대방정보 가져오기 ////////////////////////////////////////
        GetMatchingPartnerID getMatchingPartnerID = new GetMatchingPartnerID(this.getApplicationContext());
        TextView partner_dept_text = findViewById(R.id.partner_dept_text);
        TextView partner_studentID_text = findViewById(R.id.partner_studentID_text);
        TextView partner_sex_text = findViewById(R.id.partner_sex_text);

        getMatchingPartnerID.setOnPartnerInfoReceivedListener(new GetMatchingPartnerID.OnPartnerInfoReceivedListener() {
            @Override
            public void onPartnerInfoReceived(String partnerID, String name, String sex, String mainDept, String studentID, String deptT) {
                // 응답을 받아서 처리하는 코드를 여기에 작성합니다.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        partner_dept_text.setText(mainDept);
                        Log.d("상대방학과: ", mainDept);

                        partner_studentID_text.setText(studentID);
                        Log.d("상대방학번: ", studentID);

                        partner_sex_text.setText(sex);
                        Log.d("상대방성별: ", sex);

                    }
                });
            }
        });

    }

    @Override
    public void onTotalScoreReceived(String totalScore) {
        // total_score가 설정된 후에 호출되는 메서드
        TextView avg_score = findViewById(R.id.avg_score);
        avg_score.setText(totalScore);
    }
}