package com.gmail.sungkyulfriends.MatchingAlarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.SameClass.chat_room;
import com.gmail.sungkyulfriends.SeniorAndJunior.ChatActivity;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_main_friendMatching;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_matched_finish;
import com.gmail.sungkyulfriends.MatchingList.matching_list_same_class;
import com.gmail.sungkyulfriends.MyPage.review_page;

public class alarm_list_new extends AppCompatActivity {

    Dialog matching_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list_new);


        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alarm_list_new.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 같은수업듣는사람 멘트 이동
        LinearLayout linearLayout = findViewById(R.id.ment_Panel);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alarm_list_new.this, review_page.class);
                startActivity(intent);
            }
        });

        // 짝선배 짝후배 채팅방 이동
        LinearLayout linearLayout2 = findViewById(R.id.match_chat_Panel);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alarm_list_new.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        // 같은수업 채팅방 이동
        LinearLayout linearLayout3 = findViewById(R.id.class_chat_Panel);
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alarm_list_new.this, chat_room.class);
                startActivity(intent);
            }
        });

        // 같은수업 듣는사람 매칭 완료 이동
        LinearLayout linearLayout4 = findViewById(R.id.class_match_finish);
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(alarm_list_new.this, matching_list_same_class.class);
                startActivity(intent);
            }
        });



        // 짝선배 짝후배 매칭제안 다이얼로그 띄우기
        matching_dialog = new Dialog(alarm_list_new.this);  // Dialog 초기화
        matching_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  // 타이틀 제거
        matching_dialog.setContentView(R.layout.activity_class_matching_dialog);

        findViewById(R.id.match_proposal_Panel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_matching_dialog();
            }
        });
    }

    public void show_matching_dialog() {

        matching_dialog.show();

        Button accept_btn = matching_dialog.findViewById(R.id.accept);
        Button refuse_btn = matching_dialog.findViewById(R.id.refuse);

        // 수락 버튼
        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_dialog.dismiss();

                Intent intent = new Intent(alarm_list_new.this, class_matched_finish.class);
                startActivity(intent);
            }
        });


        // 거절 버튼
        refuse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_dialog.dismiss();      // 다이어로그 닫기

                AlertDialog.Builder refuse_dialog_builder = new AlertDialog.Builder(alarm_list_new.this);
                View refuse_dialog = LayoutInflater.from(alarm_list_new.this).inflate(R.layout.activity_matching_refuse_dialog, null);
                refuse_dialog_builder.setView(refuse_dialog);

                Button reMatching_btn = refuse_dialog.findViewById(R.id.reMatching);
                ImageView close_imgView = refuse_dialog.findViewById(R.id.close_imgView);

                final AlertDialog refuse_dialog2 = refuse_dialog_builder.create();
                refuse_dialog2.show();

                // 다시매칭 버튼
                reMatching_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(alarm_list_new.this, class_main_friendMatching.class);
                        startActivity(intent);
                    }
                });

                // x 버튼
                close_imgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refuse_dialog2.dismiss();
                    }
                });
            }
        });

    }

}