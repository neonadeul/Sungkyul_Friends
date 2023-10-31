package com.gmail.sungkyulfriends.MyPage;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetDataRequest extends StringRequest {

    private static final String URL_GET_DATA = "http://3.34.20.219/Interest.php"; // Change the URL if necessary
    private Map<String, String> params;

    public GetDataRequest(String userID, String[] interestArray, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL_GET_DATA, listener, errorListener);
        params = new HashMap<>();
        params.put("userID", userID);

        // Convert the array to a comma-separated string
        String interests = arrayToString(interestArray);
        params.put("interestArray", interests);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    private String arrayToString(String[] array) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }
}
