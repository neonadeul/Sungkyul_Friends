package com.gmail.sungkyulfriends.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.SeniorAndJunior.MatchingStateRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class login_page extends AppCompatActivity {

    private Button login_button, join_button;
    private EditText et_id, et_pw;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);

        TextView join_button = findViewById(R.id.join_button);
        login_button = findViewById(R.id.login_button);


        // 회원가입 버튼을 클릭 시 수행
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, join_personal_information.class);
                startActivity(intent);
            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 현재 입력되어 있는 값을 가져온다
                userID = et_id.getText().toString(); //String 뻄
                String userPass = et_pw.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){ //로그인에 성공한 경우
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword"); //php에 있는 변수 값 가져와서 저장
                                Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(
                                        login_page.this, MainActivity.class);
                                intent.putExtra("userID",userID); //putExtra(key, value)
                                intent.putExtra("userPass",userPass);
                                startActivity(intent);

                            }
                            else{ //로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("error", error.getMessage());
                            }
                        }
                        );
                RequestQueue queue= Volley.newRequestQueue(
                        login_page.this);
                queue.add(loginRequest);
            }
        });
    }
}