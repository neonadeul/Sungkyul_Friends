package com.gmail.sungkyulfriends.SameClass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.LoginRequest;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.MatchingList.matching_list;
import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.SeniorAndJunior.MatchingRequest;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_main_friendMatching;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class class_main_friend extends AppCompatActivity {

    private Button matching_button;
    private TextView main_dept_text;
    private Spinner academic_year_spinner;
    private Spinner department_spinner;
    private Spinner sp_year;
    private RadioButton man, woman, doesntMatter;
    public String department;
    public int academic_year;
    private Dialog matching_result_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_friend);

        // 아이디 값 찾아주기
        matching_button = findViewById(R.id.matching_button);
        department_spinner = findViewById(R.id.department_spinner);
        sp_year = findViewById(R.id.sp_year);
        man = findViewById(R.id.man);
        woman = findViewById(R.id.woman);
        doesntMatter = findViewById(R.id.doesntMatter);

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
                    department = jsonObject.getString("main_dept");

                    // 학과 주전공으로 뜨게하기
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            main_dept_text = findViewById(R.id.main_dept_text);
                            main_dept_text.setText(department);
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

        ///////////////////////////////////////////////////////////////
        // 학과 + 학년 서버로 넘겨주기
        academic_year_spinner = findViewById(R.id.academic_year_spinner);

        academic_year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                academic_year = Integer.parseInt(academic_year_spinner.getSelectedItem().toString().replaceAll("\\D+", ""));

                Log.d("같은수업학년 선택: ", String.valueOf(academic_year));


                // 서버로 보내주기
                Response.Listener<String> response_listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 응답을 받았을 때 실행할 코드
                        // response 변수에 서버에서 받은 응답이 들어있습니다.
                        Log.d("수업불러오기 응답: ", response);
                    }
                };

                // 서버로 Volley를 이용해서 요청을 함
                CourseScheduleRequest courseScheduleRequest = new CourseScheduleRequest(department, academic_year, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 응답을 받았을 때 실행할 코드
                        // response 변수에 서버에서 받은 응답이 들어있습니다.
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            // 스피너에 항목을 추가합니다.
                            List<String> spinnerItems = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String item = jsonArray.getString(i);
                                spinnerItems.add(item);
                            }

                            // ArrayAdapter를 사용해 스피너에 항목을 설정합니다.
                            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(class_main_friend.this, android.R.layout.simple_spinner_item, spinnerItems);
                            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            department_spinner.setAdapter(spinnerAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d("수업불러오기 응답: ", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                });

                RequestQueue requestQueue = Volley.newRequestQueue(class_main_friend.this);
                requestQueue.add(courseScheduleRequest);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("수업학년 ", "못가져옴");
            }
        });
        //////////////////////////////////////////////////////////////////////
        // matching_button 클릭시 수행
        // 매칭 버튼 클릭시 수행
        matching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseName = department_spinner.getSelectedItem().toString();
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

                        Log.d("친구매칭신청 응답: ",  response);
                        // 매칭결과 확인하러가기 다이얼로그

                        matching_result_dialog = new Dialog(class_main_friend.this);
                        matching_result_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        matching_result_dialog.setContentView(R.layout.activity_check_matching_result_dialog);

                        showDialog();


                    }
                };


                // 서버로 Volley를 이용해서 요청을 함
                matchingRequest_classfriendRequest matchingRequest_classfriendRequest = new matchingRequest_classfriendRequest(userID, department, academic_year,courseName,wantyear, wantsex, response_listener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(class_main_friend.this);
                queue.add(matchingRequest_classfriendRequest);

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
                Intent intent = new Intent(class_main_friend.this, matching_list.class);
                startActivity(intent);
            }
        });
    }


}
