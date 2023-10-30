package com.gmail.sungkyulfriends.MyPage;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetDataRequest extends StringRequest {

    private static final String URL_GET_DATA = "http://3.34.20.219/Interest.php";

    public GetDataRequest(String userID, String[] interestArray, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, buildUrl(userID, interestArray), listener, errorListener);
    }

    private static String buildUrl(String userID, String[] interestArray) {
        String interestArrayString = joinArray(interestArray, ",");
        return URL_GET_DATA + "?userID=" + userID + "&interestArray=" + interestArrayString;
    }

    private static String joinArray(String[] array, String delimiter) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                result.append(delimiter);
            }
            result.append(array[i]);
        }
        return result.toString();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return super.getParams();
    }
}
