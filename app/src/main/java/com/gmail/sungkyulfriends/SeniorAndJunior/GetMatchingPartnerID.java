package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;

import org.json.JSONException;
import org.json.JSONObject;


public class GetMatchingPartnerID {

    Context context; // Context 변수 추가
    RequestQueue queue; // RequestQueue 변수 추가

    private String partnerID;
    private String name;
    private String sex;
    private String main_dept;
    private String studentID;
    private String dept_t;

    // 추가
    public interface OnPartnerInfoReceivedListener {
        void onPartnerInfoReceived(String partnerID, String name, String sex, String mainDept, String studentID, String deptT);
    }

    private OnPartnerInfoReceivedListener onPartnerInfoReceivedListener;

    public void setOnPartnerInfoReceivedListener(OnPartnerInfoReceivedListener listener) {
        this.onPartnerInfoReceivedListener = listener;
    }

    private void informListener() {
        if (onPartnerInfoReceivedListener != null) {
            onPartnerInfoReceivedListener.onPartnerInfoReceived(partnerID, name, sex, main_dept, studentID, dept_t);
            Log.d("onPartnerInfoReceivedListener값이", " null이 아님");
        } else {
            Log.d("GetMatchingPartnerID", "onPartnerInfoReceivedListener is null");
        }
    }


    // 생성자를 통해 Context 전달받기
    public GetMatchingPartnerID(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context); // RequestQueue 초기화

        // userID값 받아오기
        String userID = login_page.userID;

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 응답을 처리하는 코드 작성
                // response 변수에 API 응답이 포함됩니다.
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    partnerID = jsonObject.getString("partnerID");
                    name = jsonObject.getString("name");
                    sex = jsonObject.getString("sex");
                    main_dept = jsonObject.getString("main_dept");
                    studentID = jsonObject.getString("studentID");
                    dept_t = jsonObject.getString("dept_t");

                    Log.d("서버에서 상대방정보 받아온값 partnerID",partnerID);
                    Log.d("서버에서 상대방정보 받아온값 name",name);
                    Log.d("서버에서 상대방정보 받아온값 sex",sex);
                    Log.d("서버에서 상대방정보 받아온값 main_dept",main_dept);
                    Log.d("서버에서 상대방정보 받아온값 studentID",studentID);
                    Log.d("서버에서 상대방정보 받아온값 dept_t",dept_t);

                    informListener();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };

        // MatchingPartnerID 생성 시 Context 전달
        MatchingPartnerID matchingPartnerID = MatchingPartnerID.getInstance(context, userID, responseListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("에러", error.getMessage());
            }
        });

        // RequestQueue에 추가
        queue.add(matchingPartnerID);

    }

    public String getPartnerID() {
        return partnerID;
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
        return studentID;
    }

    public String getDeptT() {
        return dept_t;
    }
}
