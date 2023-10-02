package com.gmail.sungkyulfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.gmail.sungkyulfriends.MatchingAlarm.alarm_list_new;
import com.gmail.sungkyulfriends.MatchingList.matching_list;
import com.gmail.sungkyulfriends.MyPage.mypage;
import com.gmail.sungkyulfriends.SameClass.class_main_friend;
import com.gmail.sungkyulfriends.SeniorAndJunior.GetMatchingState;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_main_friendMatching;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_matched_firendFirst;

public class MainActivity extends AppCompatActivity {

    private ImageButton class_matching_button;
    private ImageButton alarm_page_button;
    private ImageButton matching_list_button;
    private ImageButton mypage_button;
    private ImageButton main_page_button;
    private ImageButton senior_matching_button;

    private void handleMatchingState(int matchingState) {
        if(matchingState == 2){
            // 매칭 완료된 상태
            Intent intent = new Intent(MainActivity.this, class_matched_firendFirst.class);
            startActivity(intent);
        } else if(matchingState == 1){
            Intent intent = new Intent(MainActivity.this, matching_list.class);
            startActivity(intent);
        } else if(matchingState == 0){
            Intent intent = new Intent(MainActivity.this, class_main_friendMatching.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        class_matching_button = findViewById(R.id.class_matching_button);
        class_matching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, class_main_friend.class);
                startActivity(intent); //엑티비티 이동
            }
        });
        senior_matching_button = findViewById(R.id.senior_matching_button);
//        senior_matching_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, class_main_friendMatching.class);
//                startActivity(intent); //엑티비티 이동
//            }
//        });
        alarm_page_button = findViewById(R.id.alarm_page_button);
        alarm_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, alarm_list_new.class);
                startActivity(intent);
            }
        });

        main_page_button = findViewById(R.id.main_page_button);
        main_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        matching_list_button = findViewById(R.id.matching_list_button);
        matching_list_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, matching_list.class);
                startActivity(intent);
            }
        }));

        mypage_button = findViewById(R.id.mypage_button);
        mypage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, mypage.class);
                startActivity(intent);
            }
        });

        // 짝선배 짝후배 버튼 눌렀을 때
        senior_matching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetMatchingState getMatchingState = new GetMatchingState(MainActivity.this);
                getMatchingState.requestMatchingState(new GetMatchingState.MatchingStateCallback() {
                    @Override
                    public void onMatchingStateReceived(int matchingState) {
                        handleMatchingState(matchingState);
                    }
                });
            }
        });

    }
}