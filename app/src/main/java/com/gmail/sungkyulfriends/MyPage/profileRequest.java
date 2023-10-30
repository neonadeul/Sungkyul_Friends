package com.gmail.sungkyulfriends.MyPage;

import android.util.Log;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class profileRequest extends StringRequest {
    final static private String URL = "http://3.34.20.219/UpdateInfo/updateUserInfo.php";
    private Map<String, String> map;

    public profileRequest(String userID, String Name, String sex, String year, String dept_t, String main_dept,
                          Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);
        map = new HashMap<>();
        if (userID != null) {
            map.put("userID", userID);
        }
        if (Name != null) {
            map.put("Name", Name);
        }
        if (sex != null) {
            map.put("sex", sex);
        }
        if (year != null) {
            map.put("year", year);
        }
        if (dept_t != null) {
            map.put("dept_t", dept_t);
        }
        if (main_dept != null) {
            map.put("main_dept", main_dept);
        }
        Log.v("TAG", "매핑5 완료");
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    // 서버 응답 처리
    @Override
    protected void deliverResponse(String response) {
        // 요청이 성공했을 때 호출될 콜백 함수
        super.deliverResponse(response);
    }
}
