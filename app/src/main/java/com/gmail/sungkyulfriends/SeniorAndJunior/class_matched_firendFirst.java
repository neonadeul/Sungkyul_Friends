
package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.MyPage.other_person_profile;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.MatchingList.matching_alarm_list;
import com.gmail.sungkyulfriends.MatchingList.matching_list;
import com.gmail.sungkyulfriends.MyPage.mypage;
import com.gmail.sungkyulfriends.MyPage.review_write;

public class class_matched_firendFirst extends AppCompatActivity {
    Dialog matching_button;
    Dialog finish_button;
    private ImageButton alarm_page_button;
    private ImageButton matching_list_button;
    private ImageButton mypage_button;
    private ImageButton main_page_button;
    public String partner_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_matched_first);

        // 상대방 이름으로 보여주기
        GetMatchingPartnerID getMatchingPartnerID = new GetMatchingPartnerID(this.getApplicationContext());
        TextView partnerNameTextView = findViewById(R.id.partner_name);

        getMatchingPartnerID.setOnPartnerInfoReceivedListener(new GetMatchingPartnerID.OnPartnerInfoReceivedListener() {
            @Override
            public void onPartnerInfoReceived(String partnerID, String name, String sex, String mainDept, String studentID, String deptT) {
                // 응답을 받아서 처리하는 코드를 여기에 작성합니다.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        partnerNameTextView.setText(name);
                        partner_name = name;
                        Log.d("상대방이름: ", name);

                    }
                });
            }
        });

        // 상대방 프로필 눌렀을때 상대방 프로필 화면으로 전환
        // other_person_profile로 partner_name값 전달
        ImageView partner_profile = findViewById(R.id.partner_profile);
        partner_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_firendFirst.this, other_person_profile.class);
                intent.putExtra("partner_name", partner_name);
                startActivity(intent);
            }
        });


        alarm_page_button = findViewById(R.id.alarm_page_button);
        alarm_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_firendFirst.this, matching_alarm_list.class);
                startActivity(intent);
            }
        });


        main_page_button = findViewById(R.id.main_page_button);
        main_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_firendFirst.this, MainActivity.class);
                startActivity(intent);
            }
        });

        matching_list_button = findViewById(R.id.matching_list_button);
        matching_list_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_firendFirst.this, matching_list.class);
                startActivity(intent);
            }
        }));

        mypage_button = findViewById(R.id.mypage_button);
        mypage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_firendFirst.this, mypage.class);
                startActivity(intent);
            }
        });
        ImageView back_arrow= findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_firendFirst.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView chat_button = findViewById(R.id.chat_button);
        chat_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // partnerNameTextView에서 이름 가져오기
                String name = partnerNameTextView.getText().toString();
                Intent intent = new Intent(class_matched_firendFirst.this, ChatActivity.class);
                // ChatActivity로 name값 넘겨주기
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        matching_button = new Dialog(class_matched_firendFirst.this);
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
        finish_button = new Dialog(class_matched_firendFirst.this);
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
                Intent intent = new Intent(class_matched_firendFirst.this, review_write.class);
                startActivity(intent);
            }
        });

        // 새로운 매칭 버튼 클릭 리스너 설정
        newMatchingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_button.dismiss();
                Intent intent = new Intent(class_matched_firendFirst.this, class_main_friendMatching.class);
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
