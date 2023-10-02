// matching_list_same_class.java

package com.gmail.sungkyulfriends.MatchingList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.SameClass.chat_room;
import com.gmail.sungkyulfriends.SameClass.class_main_friend;
import com.gmail.sungkyulfriends.MyPage.mypage;
import com.gmail.sungkyulfriends.MyPage.review_write;

public class matching_list_same_class extends AppCompatActivity {
    Dialog matching_button;
    Dialog finish_button;
    private ImageButton alarm_page_button;
    private ImageButton matching_list_button;
    private ImageButton mypage_button;
    private ImageButton main_page_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_list_same_class);

        // ... (이전 코드)
        alarm_page_button = findViewById(R.id.alarm_page_button);
        alarm_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class.this, matching_alarm_list.class);
                startActivity(intent);
            }
        });


        main_page_button = findViewById(R.id.main_button);
        main_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class.this, MainActivity.class);
                startActivity(intent);
            }
        });

        matching_list_button = findViewById(R.id.matching_list_button);
        matching_list_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class.this, matching_list.class);
                startActivity(intent);
            }
        }));

        mypage_button = findViewById(R.id.mypage_button);
        mypage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class.this, mypage.class);
                startActivity(intent);
            }
        });
        ImageView back_arrow= findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView chat_button = findViewById(R.id.chat_button);
        chat_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class.this, chat_room.class);
                startActivity(intent);
            }
        });

        matching_button = new Dialog(matching_list_same_class.this);
        matching_button.requestWindowFeature(Window.FEATURE_NO_TITLE);
        matching_button.setContentView(R.layout.activity_class_main_matched_dialog_cancel);

        findViewById(R.id.matching_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog01();
            }
        });
    }

    public void showDialog01() {
        matching_button.show();
        ImageView closeImageView = matching_button.findViewById(R.id.close);
        Button noBtn = matching_button.findViewById(R.id.refuse);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matching_button.dismiss();
            }
        });
        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_button.dismiss();
            }
        });
        Button yesBtn = matching_button.findViewById(R.id.accept);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matching_button.dismiss();
                showDialog02();
            }
        });
    }

    public void showDialog02() {
        finish_button = new Dialog(matching_list_same_class.this);
        finish_button.requestWindowFeature(Window.FEATURE_NO_TITLE);
        finish_button.setContentView(R.layout.activity_class_main_matched_dialog_finish);

        // 다이얼로그 레이아웃 내부 뷰들을 찾기
        Button reviewButton = finish_button.findViewById(R.id.review_button);
        Button newMatchingButton = finish_button.findViewById(R.id.new_matching_button);
        ImageView closeButton = finish_button.findViewById(R.id.close);

        // 리뷰 버튼 클릭 리스너 설정
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_button.dismiss();
                Intent intent = new Intent(matching_list_same_class.this, review_write.class);
                startActivity(intent);
            }
        });

        // 새로운 매칭 버튼 클릭 리스너 설정
        newMatchingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_button.dismiss();
                Intent intent = new Intent(matching_list_same_class.this, class_main_friend.class);
                startActivity(intent);
            }
        });

        // 닫기 버튼 클릭 리스너 설정
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_button.dismiss();
            }
        });

        finish_button.show();
    }
}
