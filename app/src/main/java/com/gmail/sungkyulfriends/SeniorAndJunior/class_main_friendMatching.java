package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.LoginRequest;
import com.gmail.sungkyulfriends.LoginRegister.join_personal_information;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.MatchingList.matching_list;
import com.gmail.sungkyulfriends.R;


public class class_main_friendMatching extends AppCompatActivity {

    private Button matching_button;
    private Dialog matching_result_dialog;
    private Spinner sp_dept, sp_year;
    private RadioButton man, woman, doesntMatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_matching_friend);

        // 아이디 값 찾아주기
        matching_button = findViewById(R.id.matching_button);
        sp_dept = findViewById(R.id.sp_dept);
        sp_year = findViewById(R.id.sp_year);
        man= findViewById(R.id.man);
        woman = findViewById(R.id.woman);
        doesntMatter = findViewById(R.id.doesntMatter);

        // login_page에서 userID값 받아오기
        //Intent intent = getIntent();
        //String userID = intent.getStringExtra("userID");
        String userID = login_page.userID;


        ImageView back_arrow= findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_main_friendMatching.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 매칭 버튼 클릭시 수행
        matching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String wantdept = sp_dept.getSelectedItem().toString();
                String wantyear = sp_year.getSelectedItem().toString();
                String wantsex = null;

                if (man.isChecked()) {
                    wantsex = man.getText().toString();
                } else if (woman.isChecked()) {
                    wantsex = woman.getText().toString();
                } else if (doesntMatter.isChecked()) {
                    wantsex = doesntMatter.getText().toString();
                }

                Response.Listener<String> response_listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 응답을 받았을 때 실행할 코드
                        // response 변수에 서버에서 받은 응답이 들어있습니다.

                        Log.d("응답", "response: " + response);
                        // 매칭결과 확인하러가기 다이얼로그

                        matching_result_dialog = new Dialog(class_main_friendMatching.this);
                        matching_result_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        matching_result_dialog.setContentView(R.layout.activity_check_matching_result_dialog);

//                        //
//                        Button matching_button = findViewById(R.id.matching_button);
//                        matching_button.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                showDialog();
//                            }
//                        });

                        showDialog();


                    }
                };


                // 서버로 Volley를 이용해서 요청을 함
                MatchingRequest matchingRequest = new MatchingRequest(userID, wantdept, wantyear, wantsex, response_listener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(class_main_friendMatching.this);
                queue.add(matchingRequest);
            }

        });

    }

    public void showDialog(){
        matching_result_dialog.show();

        Button acceptBtn = matching_result_dialog.findViewById(R.id.accept);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_result_dialog.dismiss();
                Intent intent = new Intent(class_main_friendMatching.this, matching_list.class);
                startActivity(intent);
            }
        });
    }
}