package com.gmail.sungkyulfriends.MyPage;

import android.util.Log;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class profileRequest extends StringRequest {
    private static final String URL = "http://3.34.20.219/UpdateInfo/updateUserInfo.php";
    private Map<String, String> params;

    public profileRequest(String userID, String name, String sex, String year, String dept_t, String main_dept,
                          Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        params = new HashMap<>();
        params.put("userID", userID);
        params.put("Name", name);
        params.put("sex", sex);
        params.put("year", year);
        params.put("main_dept", main_dept);
        params.put("dept_t", dept_t);
        Log.v("TAG", "Mapped 6");
    }

    @Override
    protected Map<String, String> getParams() {
        return params;
    }

    // Server response handling
    @Override
    protected void deliverResponse(String response) {
        // Callback function called upon successful request
        super.deliverResponse(response);
    }
}
