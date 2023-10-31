package com.gmail.sungkyulfriends.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
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
        retouch_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
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

        requestQueue = Volley.newRequestQueue(this);
        retrieveUserProfile();
    }

    private void retrieveUserProfile() {
        GetProfile userProfile = new GetProfile(getApplicationContext());
        userProfile.setOnProfileInfoListener(new GetProfile.OnProfileInfoListener() {
            @Override
            public void onProfileInfoReceived(String name, String sex, String year, String main_dept, String dept_t) {
          et_name.setText(name);
          sp_year.getSelectedItem();
          sp_dept.getSelectedItem();
          rb_dept1.isChecked();
          rb_dept2.isChecked();
          rb_dept3.isChecked();
          rb_gender1.isChecked();
          rb_gender2.isChecked();


          }
          @Override
           public void onProfileInfoError(String errorMessage) {

          }
      });
        userProfile.retrieveProfileInfo();
    }

    private void updateUserProfile() {
        // Gather the updated profile information
        String name = et_name.getText().toString();
        String year = sp_year.getSelectedItem().toString();
        String dept_t = getSelectedMajor();
        String sex = getSelectedSex();
        String main_dept = sp_dept.getSelectedItem().toString();

        String updateUserUrl = "http://3.34.20.219/UpdateInfo/updateUserInfo.php";

        StringRequest updateRequest = new StringRequest(Request.Method.POST, updateUserUrl,
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
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("userID", userID);
                params.put("userName", name);
                params.put("userGrade", year);
                params.put("dept_t", dept_t);
                params.put("userMajor", sex);
                params.put("main_dept", main_dept);

                return params;
            }
        };

        requestQueue.add(updateRequest);
    }

    private String getSelectedSex() {
        if (rb_gender1.isChecked()) {
            return "남자";
        } else if (rb_gender2.isChecked()) {
            return "여자";
        }
        return "";
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

    private String getSelectedMajor() {
        if (rb_dept1.isChecked()) {
            return "전공 심화";
        } else if (rb_dept2.isChecked()) {
            return "복수 전공";
        } else if (rb_dept3.isChecked()) {
            return "부전공";
        }
        return "";}}
