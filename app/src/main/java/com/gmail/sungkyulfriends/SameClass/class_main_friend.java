package com.gmail.sungkyulfriends.SameClass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.LoginRequest;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;

import org.json.JSONException;
import org.json.JSONObject;

public class class_main_friend extends AppCompatActivity {

    private Button matching_button;
    private TextView main_dept_text;
    public String main_dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_friend);

        // 버튼을 찾아서 onClickListener를 설정
        matching_button = findViewById(R.id.matching_button);
        matching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다른 화면으로 이동
                Intent intent = new Intent(class_main_friend.this, class_matching_in_progress.class);
                startActivity(intent);
            }
        });

        // 뒤로가기 버튼
        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_main_friend.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // login_page에서 userID, userPass값 받아오기
        String userID = login_page.userID;
        String userPassword = login_page.userPass;

        // LoginRequest 서버 응답에서 main_dept값 받아오기
        // 서버 응답을 처리하는 리스너 설정
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 서버 응답 처리 코드 작성
                // response 변수에 서버 응답이 포함됩니다.
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    main_dept = jsonObject.getString("main_dept");

                    // 학과 주전공으로 뜨게하기
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            main_dept_text = findViewById(R.id.main_dept_text);
                            main_dept_text.setText(main_dept);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 발생 시 처리하는 코드 작성
                Log.d("loginRequest 에러", "main_dept값 못가져옴");
            }
        };

        // LoginRequest 객체 생성 및 요청 전송
        LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(loginRequest);

    }
}