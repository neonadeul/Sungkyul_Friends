package com.gmail.sungkyulfriends.MyPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class retouch_interest_choice extends AppCompatActivity {

    private Button retouch_interest_button;
    private List<ToggleButton> toggleButtons = new ArrayList<>(); // 토글 버튼 리스트
    private int selectedCount = 0; // 선택된 항목 수
    private static final String PREFS_NAME = "UserInterests";
    private static final int MAX_INTERESTS = 5; // 최대 선택 가능한 관심사 수
    private List<String> interestArray = new ArrayList<>(); // 사용자가 선택한 관심사 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retouch_interest_choice);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(retouch_interest_choice.this, profile_retouch.class);
                startActivity(intent);
            }
        });

        // SharedPreferences에서 선택한 관심사 로드
        interestArray = loadSelectedInterests();

        // 토글 버튼들 초기화 및 리스너 설정
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

        // 서버로부터 최신 관심사 정보를 가져와 Toggle 버튼에 설정
        loadLatestInterests();

        // 선택한 관심사를 토글 버튼에 적용
        for (ToggleButton toggleButton : toggleButtons) {
            String interest = toggleButton.getText().toString().replace(" ", "").replace("#", "");
            toggleButton.setChecked(interestArray.contains(interest));
            toggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 토글 상태 변경 시 호출됩니다.
                    if (toggleButton.isChecked()) {
                        selectedCount++;
                    } else {
                        selectedCount--;
                    }

                    // 5개 초과 선택 시 다이얼로그 표시
                    if (selectedCount > 5) {
                        toggleButton.setChecked(false);
                        showSelectionLimitDialog();
                    }
                }
            });
        }

        retouch_interest_button = findViewById(R.id.retouch_interest_button);
        retouch_interest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 선택한 관심사를 배열로 만들기
                interestArray = getSelectedInterests();
                Log.d("retouch_interest_choice", "userID: " + login_page.userID); // login_page.userID 사용
                Log.d("retouch_interest_choice", "interestArray: " + interestArray.toString());

                // 선택한 항목이 5개를 초과하는지 확인
                if (interestArray.size() > MAX_INTERESTS) {
                    // 5개를 초과하는 경우 다이얼로그 표시
                    showSelectionLimitDialog();
                } else if (interestArray.isEmpty()) {
                    // 선택한 관심사가 없는 경우에 대한 처리
                    Toast.makeText(retouch_interest_choice.this, "최소 1개 이상의 관심사를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // 서버로 요청 보내기
                    sendRequestToServer(interestArray);
                }
            }
        });
    }

    // SharedPreferences에서 선택한 관심사 로드
    private List<String> loadSelectedInterests() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> interests = preferences.getStringSet("selectedInterests", new HashSet<>());
        return new ArrayList<>(interests);
    }

    // 서버로부터 최신 관심사 정보를 가져오고 Toggle 버튼에 설정
    private void loadLatestInterests() {
        // 서버에서 최신 관심사 정보를 가져올 메서드를 호출
        // 이 예제에서는 하드 코딩된 최신 관심사 정보를 사용합니다.

        List<String> latestInterests = getLatestInterestsFromServer(); // 서버에서 최신 정보를 가져오는 메서드
        if (latestInterests != null) {
            for (ToggleButton toggleButton : toggleButtons) {
                String interest = toggleButton.getText().toString().replace(" ", "").replace("#", "");
                toggleButton.setChecked(latestInterests.contains(interest));
            }
        }
    }

    // 서버에서 최신 관심사 정보를 가져오는 메서드
    private List<String> getLatestInterestsFromServer() {
        // 여기에 서버로부터 최신 관심사 정보를 가져오는 코드를 구현하세요.
        // 실제 서버와 통신하여 데이터를 가져오거나 로컬 데이터베이스에서 데이터를 로드할 수 있습니다.
        // 이 메서드를 사용하여 최신 정보를 가져옵니다.

        // 이 예제에서는 하드 코딩된 최신 관심사 목록을 반환합니다.
        List<String> latestInterests = new ArrayList<>();
        return latestInterests;
    }

    // 사용자가 선택한 관심사를 배열로 만들기
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

    // 다이얼로그
    private void showSelectionLimitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(retouch_interest_choice.this);
        builder.setMessage("5개까지만 선택 가능합니다")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 다이얼로그 확인 버튼 클릭 시 아무 작업도 수행하지 않습니다.
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // 서버로 요청 보내기
    private void sendRequestToServer(final List<String> interests) {
        // Check if the interestArray is empty or not
        if (interests.isEmpty()) {
            Toast.makeText(retouch_interest_choice.this, "최소 1개 이상의 관심사를 선택해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // retouchRequest 클래스를 사용하여 서버 요청 보내기
        String[] interestArray = interests.toArray(new String[0]);
        retouchRequest request = new retouchRequest(login_page.userID, interestArray,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 서버 응답 처리
                        handleResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 서버 오류 처리
                        handleError(error);
                    }
                });

        // Volley 요청 큐에 요청 추가
        RequestQueue queue = Volley.newRequestQueue(retouch_interest_choice.this);
        queue.add(request);
    }

    // 서버 응답 처리
    private void handleResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            boolean success = jsonResponse.getBoolean("success");
            if (success) {
                // 성공적으로 처리된 경우입니다.
                // 사용자에게 수정이 성공적으로 이루어졌음을 알리는 Toast 메시지 표시
                Toast.makeText(retouch_interest_choice.this, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                // 화면 전환 등 추가 작업 수행
                Intent intent = new Intent(retouch_interest_choice.this, profile_retouch.class);
                startActivity(intent);
            } else {
                // 처리 실패인 경우입니다.
                // 서버에서 반환한 오류 메시지를 표시
                String errorMessage = jsonResponse.getString("message");
                Toast.makeText(retouch_interest_choice.this, "수정 실패: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // JSON 파싱 오류가 발생한 경우 처리
            Toast.makeText(retouch_interest_choice.this, "서버 응답을 처리하는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    // 서버 오류 처리
    private void handleError(VolleyError error) {
        // 서버 요청 오류 처리
        // 오류 메시지를 출력하여 디버깅에 도움을 줍니다.
        String errorMessage = error.getMessage();
        if (errorMessage != null) {
            Log.e("VolleyError", errorMessage);
        }
        // 예: 사용자에게 오류 메시지 표시 또는 다시 시도하도록 안내
        Toast.makeText(retouch_interest_choice.this, "수정 요청 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
    }
}
