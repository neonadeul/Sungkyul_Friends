package com.gmail.sungkyulfriends.MatchingList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.SameClass.class_main_friend;
import com.gmail.sungkyulfriends.SeniorAndJunior.ChatActivity;
import com.gmail.sungkyulfriends.MyPage.mypage;
import com.gmail.sungkyulfriends.MyPage.review_write;

public class matching_list_same_class_finish extends AppCompatActivity {
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

        alarm_page_button = findViewById(R.id.alarm_page_button);
        alarm_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class_finish.this, matching_alarm_list.class);
                startActivity(intent);
            }
        });


        main_page_button = findViewById(R.id.main_button);
        main_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class_finish.this, MainActivity.class);
                startActivity(intent);
            }
        });

        matching_list_button = findViewById(R.id.matching_list_button);
        matching_list_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class_finish.this, matching_list.class);
                startActivity(intent);
            }
        }));

        mypage_button = findViewById(R.id.mypage_button);
        mypage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class_finish.this, mypage.class);
                startActivity(intent);
            }
        });
        ImageView back_arrow= findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class_finish.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView chat_button = findViewById(R.id.chat_button);
        chat_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list_same_class_finish.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        matching_button = new Dialog(matching_list_same_class_finish.this);       // Dialog 초기화
        matching_button.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        matching_button.setContentView(R.layout.activity_class_main_matched_dialog_finish);


        findViewById(R.id.matching_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog01(); // 아래 showDialog01() 함수 호출
            }
        });
    }


    public void showDialog01(){
        matching_button.show();ImageView closeImageView = matching_button.findViewById(R.id.close);
        Button noBtn = matching_button.findViewById(R.id.refuse);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                matching_button.dismiss();}});
                // 다이얼로그 닫기
        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_button.dismiss(); // 두 번째 다이얼로그 닫기
            }
        });
        // 네 버튼
                Button yesBtn = matching_button.findViewById(R.id.accept);
                yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matching_button.dismiss();
                //
                AlertDialog.Builder dialogBuilder2 = new AlertDialog.Builder(matching_list_same_class_finish.this);
                View dialogView2 = LayoutInflater.from(matching_list_same_class_finish.this).inflate(R.layout.activity_class_main_matched_dialog_finish, null);
                dialogBuilder2.setView(dialogView2);

                Button review_Button = dialogView2.findViewById(R.id.review_button);
                Button new_matching_Button = dialogView2.findViewById(R.id.new_matching_button);

                // 닫기를 위한 ImageView 가져오기
                ImageView closeImageView = dialogView2.findViewById(R.id.close);

                final Dialog Dialog2 = dialogBuilder2.create();

                Dialog2.setOnShowListener(new DialogInterface.OnShowListener(){
                    @Override
                    public void onShow(DialogInterface dialog) {
                        // Dialog 객체의 LayoutParams를 가져와서 크기를 조정
                        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                        layoutParams.copyFrom(Dialog2.getWindow().getAttributes());
                        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        Dialog2.getWindow().setAttributes(layoutParams);
                    }
                });
                Dialog2.show();

                // 닫기 버튼에 클릭 리스너 설정
                review_Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog2.dismiss();
                        Intent intent = new Intent(matching_list_same_class_finish.this, review_write.class);
                        startActivity(intent);// 두 번째 다이얼로그 닫기
                    }
                });
                new_matching_Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog2.dismiss();
                        Intent intent = new Intent(matching_list_same_class_finish.this, class_main_friend.class);
                        startActivity(intent);// 두 번째 다이얼로그 닫기
                    }
                });

                // ImageView에 클릭 리스너 설정
                closeImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog2.dismiss(); // 두 번째 다이얼로그 닫기
                    }
                });
               }
                }); }}