package com.gmail.sungkyulfriends.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class profile_retouch extends AppCompatActivity {
    private EditText et_name;
    private Spinner sp_year, sp_dept;
    private RadioButton rb_gender1, rb_gender2, rb_dept1, rb_dept2, rb_dept3;

    String userID = login_page.userID;
    private Button retouch_profile_button;
    private Button choice_interest_button;

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
        retouch_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
                Intent intent = new Intent(profile_retouch.this, mypage.class);
                startActivity(intent);
            }
        });

        choice_interest_button = findViewById(R.id.choice_interest_button);
        choice_interest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_retouch.this, retouch_interest_choice.class);
                startActivity(intent);
            }
        });
    }

    private void updateUserProfile() {
        // Get the user input
        String userName = et_name.getText().toString();
        String userGrade = sp_year.getSelectedItem().toString();
        String dept_t = sp_dept.getSelectedItem().toString();
        String userMajor = getSelectedMajor();

        // Send this data to your server for updating the user profile
        String serverUrl = "http://3.34.20.219/UpdateInfo/updateUserInfo.php"; // Replace with your server URL

        profileRequest profileRequest = new profileRequest(
                userID,
                userName,
                getSelectedGender(),
                userGrade,
                dept_t,
                userMajor,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "프로필 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(profile_retouch.this, mypage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "프로필 수정에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "네트워크 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(profileRequest);
    }

    private String getSelectedMajor() {
        if (rb_dept1.isChecked()) {
            return "전공 심화";
        } else if (rb_dept2.isChecked()) {
            return "복수 전공";
        } else if (rb_dept3.isChecked()) {
            return "부전공";
        }
        return "";
    }

    private String getSelectedGender() {
        return rb_gender1.isChecked() ? "남자" : "여자";
    }
}