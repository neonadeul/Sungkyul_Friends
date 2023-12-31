package com.gmail.sungkyulfriends.MyPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.MyPage.retouchRequest;
import com.gmail.sungkyulfriends.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class retouch_interest_choice extends AppCompatActivity {

    private Button retouch_interest_button;
    private List<ToggleButton> toggleButtons = new ArrayList<>();
    private int selectedCount = 0;
    private static final int MAX_INTERESTS = 5;
    private List<String> interestArray = new ArrayList<>();
    private static final String PREFS_NAME = "UserInterests";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retouch_interest_choice);
        interestArray = loadSelectedInterests();

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(retouch_interest_choice.this, profile_retouch.class);
                startActivity(intent);
            }
        });


        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn1));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn2));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn3));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn4));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn5));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn6));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn7));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn8));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn9));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn10));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn11));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn12));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn13));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn14));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn15));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn16));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn17));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn18));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn19));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn20));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn21));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn22));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn23));
        toggleButtons.add((ToggleButton) findViewById(R.id.interest_btn24));


        loadUserInterests();

        retouch_interest_button = findViewById(R.id.retouch_interest_button);
        retouch_interest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interestArray = getSelectedInterests();

                if (interestArray.size() > MAX_INTERESTS) {
                    showSelectionLimitDialog();
                } else if (interestArray.isEmpty()) {
                    Toast.makeText(retouch_interest_choice.this, "최소 1개 이상의 관심사를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    sendRequestToServer(interestArray);
                }
            }
        });
    }

    private List<String> loadSelectedInterests() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> interests = preferences.getStringSet(login_page.userID, new HashSet<>());
        return new ArrayList<>(interests);
    }
    private void setToggleButtonStates() {
        for (ToggleButton toggleButton : toggleButtons) {
            String interest = toggleButton.getText().toString().replace(" ", "").replace("#", "");
            toggleButton.setChecked(interestArray.contains(interest));
        }
    }
    private void loadUserInterests() {

        String userID = login_page.userID;
        List<String> interestList = getSelectedInterests();
        String[] interestArray = interestList.toArray(new String[interestList.size()]);

        GetDataRequest getDataRequest = new GetDataRequest(userID, interestArray, new GetDataRequest.OnDataReceivedListener() {
            @Override
            public void onDataReceived(String response) {
                parseAndSetUserInterests(response);
                setToggleButtonStates(); // 토글 버튼 상태 설정
            }
        });

        getDataRequest.execute(this); // 'this'는 현재 Context(여기서는 retouch_interest_choice)입니다.
    }



    private void parseAndSetUserInterests(String response) {
        List<String> userInterests = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(response);
            if (jsonResponse.has("interests")) {
                JSONArray interestsArray = jsonResponse.getJSONArray("interests");

                for (int i = 0; i < interestsArray.length(); i++) {
                    userInterests.add(interestsArray.getString(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (ToggleButton toggleButton : toggleButtons) {
            String interest = toggleButton.getText().toString().replace(" ", "").replace("#", "");
            boolean containsInterest = userInterests.contains(interest);
            Log.d("Toggle Button", "Interest: " + interest + ", Contains: " + containsInterest);
            toggleButton.setChecked(containsInterest);
        }
    }


    private void showSelectionLimitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(retouch_interest_choice.this);
        builder.setMessage("5개까지만 선택 가능합니다")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private List<String> getSelectedInterests() {
        List<String> selectedInterests = new ArrayList<>();

        for (ToggleButton toggleButton : toggleButtons) {
            if (toggleButton.isChecked()) {
                String interest = toggleButton.getText().toString().replace(" ", "").replace("#", "");
                selectedInterests.add(interest);
            }
        }

        return selectedInterests;
    }

    private void handleError(VolleyError error) {
        String errorMessage = error.getMessage();
        if (errorMessage != null) {
            Log.e("VolleyError", errorMessage);
        }
        Toast.makeText(retouch_interest_choice.this, "수정 요청 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
    }

    private void sendRequestToServer(final List<String> interests) {
        String serverUrl = "http://3.34.20.219/UpdateInfo/updateInterest.php";

        // Request를 보내기 전에 UserID 및 interestsArray 파라미터 설정
        retouchRequest request = new retouchRequest(login_page.userID, new String[]{},

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleError(error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userID", login_page.userID);

                // 선택된 관심사를 가져와서 파라미터로 설정
                List<String> selectedInterests = getSelectedInterests();
                for (int i = 0; i < selectedInterests.size(); i++) {
                    params.put("interestArray[" + i + "]", selectedInterests.get(i));
                }

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(retouch_interest_choice.this);
        queue.add(request);
    }

    private void handleResponse(String response) {
        Toast.makeText(retouch_interest_choice.this, "Interests updated successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(retouch_interest_choice.this, profile_retouch.class);
        startActivity(intent);
    }
}
