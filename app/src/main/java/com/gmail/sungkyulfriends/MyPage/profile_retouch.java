package com.gmail.sungkyulfriends.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.R;

import org.json.JSONException;
import org.json.JSONObject;

public class profile_retouch extends AppCompatActivity {
    private EditText et_name;
    private Spinner sp_year, sp_dept;
    private RadioButton rb_gender1, rb_gender2, rb_dept1, rb_dept2, rb_dept3;
    private String userID = login_page.userID;
    private Button retouch_profile_button;
    private Button choice_interest_button;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_retouch);

        et_name = findViewById(R.id.et_name);
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
                Intent intent = new Intent(profile_retouch.this, mypage.class);
                startActivity(intent);
            }
        });

        retouch_profile_button = findViewById(R.id.retouch_profile_button);
        choice_interest_button = findViewById(R.id.choice_interest_button);
        choice_interest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_retouch.this, retouch_interest_choice.class);
                startActivity(intent);
            }
        });
        requestQueue = Volley.newRequestQueue(this);

        retrieveUserProfile();
    }

    private void retrieveUserProfile() {
        retouch_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 수정 버튼을 클릭하면 현재 값을 가져옵니다.
                String userName = et_name.getText().toString();
                String userYear = sp_year.getSelectedItem().toString();
                String userDept = sp_dept.getSelectedItem().toString();
                String userSex = rb_gender1.isChecked() ? "남자" : "여자";
                String userDeptType = rb_dept1.isChecked() ? "주전공" : (rb_dept2.isChecked() ? "복수전공" : "부전공");

                // updateUserProfile로 사용자의 정보를 업데이트합니다.
                updateUserProfile(userName, userYear, userDept, userSex, userDeptType);
            }
        });
    }

    private void updateUserProfile(String name, String year, String dept_t, String sex, String main_dept) {
        String updateUserUrl = "http://3.34.20.219/UpdateInfo/updateUserInfo.php";

        profileRequest request = new profileRequest(userID, name, sex, year, dept_t, main_dept,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleUpdateResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }

    private void handleUpdateResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            boolean success = jsonObject.getBoolean("success");
            if (success) {
                Toast.makeText(getApplicationContext(), "Profile updated successfully.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to update profile.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error parsing response", Toast.LENGTH_SHORT).show();
        }
    }
}
