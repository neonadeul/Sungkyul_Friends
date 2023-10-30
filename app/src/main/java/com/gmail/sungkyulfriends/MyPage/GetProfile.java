package com.gmail.sungkyulfriends.MyPage;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;

import org.json.JSONException;
import org.json.JSONObject;

public class GetProfile {

    private String userID;
    private String name;
    private String sex;
    private String year;
    private String dept_t;
    private String main_dept;

    private Context context;
    private OnProfileInfoListener profileListener;
    private RequestQueue queue;

    public GetProfile(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(context);
        this.userID = login_page.userID; // Assuming login_page.userID contains the user ID.

        retrieveProfileInfo();
    }

    public interface OnProfileInfoListener {
        void onProfileInfoReceived(String userID, String name, String sex, String year, String main_dept, String dept_t);
        void onProfileInfoError(String errorMessage);
    }

    public void setOnProfileInfoListener(OnProfileInfoListener listener) {
        this.profileListener = listener;
    }

    private void informProfileListener() {
        if (profileListener != null) {
            profileListener.onProfileInfoReceived(userID, name, sex, year, main_dept, dept_t);
        }
    }

    private void retrieveProfileInfo() {
        String url = "http://3.34.20.219/Register.php"; // 사용자 프로필 정보를 가져오는 URL

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // JSON 응답 처리
                            JSONObject jsonObject = new JSONObject(response);
                            userID = jsonObject.getString("userID");
                            name = jsonObject.getString("Name");
                            sex = jsonObject.getString("sex");
                            year = jsonObject.getString("year");
                            main_dept = jsonObject.getString("main_dept");
                            dept_t = jsonObject.getString("dept_t");

                            // Log를 통해 서버로부터 받은 데이터 확인
                            Log.d("Server userID", userID);
                            Log.d("Server Name", name);
                            Log.d("Server sex", sex);
                            Log.d("Server year", year);
                            Log.d("Server main_dept", main_dept);
                            Log.d("Server dept_t", dept_t);

                            // 콜백 리스너를 통해 데이터 전달
                            informProfileListener();
                        } catch (JSONException e) {
                            if (profileListener != null) {
                                profileListener.onProfileInfoError("JSON 파싱 오류");
                            }
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (profileListener != null) {
                            if (error != null && error.getMessage() != null) {
                                profileListener.onProfileInfoError(error.getMessage());
                                Log.e("Volley Error", error.getMessage());
                            } else {
                                profileListener.onProfileInfoError("알 수 없는 오류가 발생했습니다.");
                                Log.e("Volley Error", "알 수 없는 오류가 발생했습니다.");
                            }
                        }
                    }
                });

        queue.add(stringRequest);
    }

    // Getter methods for user information
    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getMainDept() {
        return main_dept;
    }

    public String getStudentID() {
        return year;
    }

    public String getDeptT() {
        return dept_t;
    }
}
