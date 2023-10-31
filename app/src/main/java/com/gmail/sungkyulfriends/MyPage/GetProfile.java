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
import java.util.HashMap;
import java.util.Map;

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
        this.userID = login_page.userID;
        retrieveProfileInfo();
    }

    public interface OnProfileInfoListener {
        void onProfileInfoReceived(String name, String sex, String year, String main_dept, String dept_t);
        void onProfileInfoError(String errorMessage);
    }


    public void setOnProfileInfoListener(OnProfileInfoListener listener) {
        this.profileListener = listener;
    }

    private void informProfileListener() {
        if (profileListener != null) {
            profileListener.onProfileInfoReceived(name, sex, year, main_dept, dept_t);
        }
    }

    public void retrieveProfileInfo() {
        String url = "http://3.34.20.219/UpdateInfo/updateUserInfo.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null && !response.isEmpty()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                name = jsonObject.getString("Name");
                                sex = jsonObject.getString("Sex");
                                year = jsonObject.getString("Year");
                                main_dept = jsonObject.getString("MainDept");
                                dept_t = jsonObject.getString("DeptT");
                                informProfileListener();
                            } catch (JSONException e) {
                                if (profileListener != null) {
                                    profileListener.onProfileInfoError("JSON Parsing Error");
                                }
                                e.printStackTrace();}
                            }else{
                                if (profileListener != null) {
                                    profileListener.onProfileInfoError("Empty or null response");
                                }
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
                                profileListener.onProfileInfoError("An unknown error occurred.");
                                Log.e("Volley Error", "An unknown error occurred.");
                            }
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userID", userID);
                params.put("Name", name);
                params.put("sex", sex);
                params.put("year", year);
                params.put("main_dept", main_dept);
                params.put("dept_t", dept_t);
                return params;
            }
        };

        queue.add(stringRequest);
    }

    public String postName() {
        return name;
    }

    public String postSex() {
        return sex;
    }

    public String postMainDept() {
        return main_dept;
    }

    public String postStudentID() {
        return year;
    }

    public String postDeptT() {
        return dept_t;
    }
}
