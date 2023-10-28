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
        this.userID = login_page.userID; // Consider the way userID is initialized.

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
        String url = "http://3.34.20.219/UpdateInfo/updateUserInfo.php"; // Define your API endpoint

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            userID = jsonObject.getString("userID");
                            name = jsonObject.getString("Name");
                            sex = jsonObject.getString("sex");
                            year = jsonObject.getString("year");
                            main_dept = jsonObject.getString("main_dept");
                            dept_t = jsonObject.getString("dept_t");
                    Log.d("서버에서 상대방정보 받아온값 userID",userID);
                    Log.d("서버에서 상대방정보 받아온값 Name",name);
                    Log.d("서버에서 상대방정보 받아온값 sex",sex);
                    Log.d("서버에서 상대방정보 받아온값 year",year);
                    Log.d("서버에서 상대방정보 받아온값 main_dept",main_dept);
                    Log.d("서버에서 상대방정보 받아온값 dept_t",dept_t);

                            informProfileListener();

                } catch (JSONException e) {
                            if (profileListener != null) {
                                profileListener.onProfileInfoError("Error parsing JSON");
                            }
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (profileListener != null) {
                            profileListener.onProfileInfoError(error.getMessage());
                        }
                        Log.e("Volley Error", error.getMessage());
                    }
                });

        queue.add(stringRequest);
    }

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