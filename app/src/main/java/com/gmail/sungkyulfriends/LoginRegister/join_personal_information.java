package com.gmail.sungkyulfriends.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.R;

import org.json.JSONException;
import org.json.JSONObject;

public class join_personal_information extends AppCompatActivity {

    public static String userID;
    private Button next_button, btn_IdCheck;
    private EditText et_id, et_pw, et_pw_ck, et_name, et_studentID;
    private Spinner sp_year, sp_dept;
    private RadioButton rb_gender1, rb_gender2, rb_dept1, rb_dept2, rb_dept3;

    private TextView v_pw;



    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티 시작 시 처음으로 실행되는 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_personal_information);

        //아이디 값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        et_pw_ck = findViewById(R.id.et_pw_ck);
        et_name = findViewById(R.id.et_name);
        et_studentID = findViewById(R.id.et_studentID);
        v_pw = findViewById(R.id.v_pw);

        next_button = findViewById(R.id.next_button);
        btn_IdCheck = findViewById(R.id.btn_IdCheck);

        sp_year = findViewById(R.id.sp_year);
        sp_dept = findViewById(R.id.sp_dept);

        rb_gender1 = findViewById(R.id.rb_gender1);
        rb_gender2 = findViewById(R.id.rb_gender2);
        rb_dept1 = findViewById(R.id.rb_dept1);
        rb_dept2 = findViewById(R.id.rb_dept2);
        rb_dept3 = findViewById(R.id.rb_dept3);




        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(join_personal_information.this, login_page.class);
                startActivity(intent);
            }
        });

        btn_IdCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();

                Response.Listener<String> IdCheck_listener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean duplicate = jsonObject.getBoolean("duplicate");
                            if(duplicate){
                                Toast.makeText(getApplicationContext(),"중복된 아이디입니다.",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"사용 가능한 아이디입니다.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                IdCheck idcheck = new IdCheck(userID, IdCheck_listener, new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                });
                RequestQueue queue= Volley.newRequestQueue(join_personal_information.this);
                queue.add(idcheck);





            }
        });

        et_pw_ck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et_pw.getText().toString().equals(et_pw_ck.getText().toString())){
                    v_pw.setVisibility(View.VISIBLE);
                }
                else{
                    v_pw.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });






        //회원가입(다음) 버튼 클릭 시 수행
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_pw.getText().toString().equals(et_pw_ck.getText().toString())) {
                    // EditText에 현재 입력되어 있는 값을 가져온다.
                    String userID = et_id.getText().toString();
                    String userPassword = et_pw.getText().toString();
                    String userPassword2 = et_pw_ck.getText().toString();
                    String userName = et_name.getText().toString();
                    String studentID = et_studentID.getText().toString();
                    String userYear = sp_year.getSelectedItem().toString();
                    String userDept = sp_dept.getSelectedItem().toString();
                    String userSex = rb_gender1.isChecked() ? "남자" : "여자";
                    String userDeptType = rb_dept1.isChecked() ? "학사" : (rb_dept2.isChecked() ? "석사" : "박사");

                    Response.Listener<String> response_listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                    // 회원가입 완료 후 다음 액티비티로 이동
                                    Intent intent = new Intent(join_personal_information.this, join_interest_choice.class);
                                    // userID를 다음 액티비티로 전달
                                    intent.putExtra("userID", userID);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    // 서버로 Volley를 이용해서 요청을 함
                    RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userPassword2, userName, userSex, userYear, studentID, userDeptType, userDept, response_listener, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.getMessage());
                        }
                    });
                    RequestQueue queue = Volley.newRequestQueue(join_personal_information.this);
                    queue.add(registerRequest);
                } else {
                    Toast.makeText(getApplicationContext(), "비밀 번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}