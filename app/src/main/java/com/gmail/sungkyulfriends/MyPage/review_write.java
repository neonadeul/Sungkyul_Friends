package com.gmail.sungkyulfriends.MyPage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.SeniorAndJunior.GetMatchingPartnerID;
import com.gmail.sungkyulfriends.SeniorAndJunior.MatchingRequest;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_main_friendMatching;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;


public class review_write extends AppCompatActivity {

    private Button finish_button;
    private Dialog success_review_dialog;

    private int selectedCount = 0;
    private ToggleButton[] toggleButtons = new ToggleButton[6]; // 토글 버튼 배열

    public String receiver; // partnerID 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        // login_page에서 userID값 받아오기
        String sender = login_page.userID;

        // 상대방 이름으로 보여주기
        GetMatchingPartnerID getMatchingPartnerID = new GetMatchingPartnerID(this.getApplicationContext());
        TextView receiver_nameTextView = findViewById(R.id.receiver_name);

        getMatchingPartnerID.setOnPartnerInfoReceivedListener(new GetMatchingPartnerID.OnPartnerInfoReceivedListener() {
            @Override
            public void onPartnerInfoReceived(String partnerID, String name, String sex, String mainDept, String studentID, String deptT) {
                // 응답을 받아서 처리하는 코드를 여기에 작성합니다.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        receiver_nameTextView.setText(name + "님의 후기 작성");
                        Log.d("상대방이름: ", name);

                        receiver = partnerID;
                    }
                });
            }
        });

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(review_write.this, MainActivity.class);
                startActivity(intent);
            }
        });
        /*
        finish_button = findViewById(R.id.finish_button);
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(review_write.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

        EditText reviewEditText = findViewById(R.id.reviewEditText);
        TextView charCountTextView = findViewById(R.id.charCountTextView);


        reviewEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 이전 텍스트 변경 이벤트
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 텍스트가 변경될 때 호출되는 이벤트
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 텍스트 변경 후 호출되는 이벤트
                int currentLength = editable.length();
                charCountTextView.setText("글자 수: " + currentLength + "/500");
                if (currentLength > 500) {
                    // 500자를 초과하면 텍스트를 제한
                    String limitedText = editable.toString().substring(0, 500);
                    reviewEditText.setText(limitedText);
                    reviewEditText.setSelection(limitedText.length()); // 커서를 텍스트 끝으로 이동
                }
            }
        });

        // 버튼 1개만 선택되게 하기
        // 토글 버튼들 초기화 및 리스너 설정
        toggleButtons[0] = findViewById(R.id.button_Aplus);
        toggleButtons[1] = findViewById(R.id.button_Azero);
        toggleButtons[2] = findViewById(R.id.button_Bplus);
        toggleButtons[3] = findViewById(R.id.button_Bzero);
        toggleButtons[4] = findViewById(R.id.button_Cplus);
        toggleButtons[5] = findViewById(R.id.button_F);

        for (ToggleButton toggleButton : toggleButtons) {
            toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) { // 선택되었을 경우
                    if (selectedCount < 1) {
                        selectedCount++; // 선택 개수 증가
                    } else {
                        toggleButton.setChecked(false); // 선택 취소 (체크 해제)

                        showSelectionLimitDialog();
                    }
                } else { // 선택 해제되었을 경우
                    selectedCount--; // 선택 개수 감소
                }

            });

        }

        // 작성 완료 버튼 클릭시 수행
        finish_button = findViewById(R.id.finish_button);
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // score 값
                String score = null;

              if(toggleButtons[0].isChecked()){
                  score = "A+";
              }else if(toggleButtons[1].isChecked()){
                  score = "A0";
              }else if(toggleButtons[2].isChecked()){
                  score = "B+";
              }
              else if(toggleButtons[3].isChecked()){
                  score = "B0";
              }
              else if(toggleButtons[4].isChecked()){
                  score = "C+";
              }
              else if(toggleButtons[5].isChecked()){
                  score = "F";
              }

              // content 값
                String content = reviewEditText.getText().toString();

                Log.d("보내는사람", sender);
                Log.d("받는사람", receiver);
                Log.d("학점", score);
                Log.d("내용", content);

              // 서버
                Response.Listener<String> response_listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 응답을 받았을 때 실행할 코드
                        // response 변수에 서버에서 받은 응답이 들어있습니다.

                        Log.d("응답", "response: " + response);
                        // 후기작성 완료 다이얼로그

                        success_review_dialog = new Dialog(review_write.this);
                        success_review_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        success_review_dialog.setContentView(R.layout.activity_success_review_dialog);

//
                        show_success_review_dialog();


                    }
                };


                // 서버로 Volley를 이용해서 요청을 함
                WriteReviewRequest writeReviewRequest = new WriteReviewRequest(sender, receiver, score, content, response_listener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(review_write.this);
                queue.add(writeReviewRequest);

            }
        });




    }

    // 다이얼로그
    private void showSelectionLimitDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("후기 학점은 1개만 선택 가능합니다")
                .setPositiveButton("확인", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    // 후기 작성 완료 다이얼로그
    private void show_success_review_dialog(){
        success_review_dialog.show();

        Button ok_button = success_review_dialog.findViewById(R.id.ok_button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                success_review_dialog.dismiss();
                Intent intent = new Intent(review_write.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}