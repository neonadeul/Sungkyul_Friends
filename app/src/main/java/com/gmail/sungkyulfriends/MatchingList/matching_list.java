package com.gmail.sungkyulfriends.MatchingList;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;

import com.gmail.sungkyulfriends.SeniorAndJunior.ChatActivity;
import com.gmail.sungkyulfriends.SeniorAndJunior.GetMatchingState;
import com.gmail.sungkyulfriends.SeniorAndJunior.MatchingCancel;
import com.gmail.sungkyulfriends.SeniorAndJunior.MatchingList_json;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_main_friendMatching;
import com.gmail.sungkyulfriends.SeniorAndJunior.class_matched_firendFirst;
import com.gmail.sungkyulfriends.MyPage.mypage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class matching_list extends AppCompatActivity {

    private ImageButton main_button;
    private ImageButton mypage_button;

    // 매칭확인, 취소버튼
    private Button check_button;
    private Button cancel_button;

    // 다이얼로그
    private Dialog no_matching_possible_dialog;
    private Dialog matching_list_cancel_dialog;
    private Dialog matching_success_dialog;
    Dialog no_one_matched_dialog;

    // 매칭상태
    private GetMatchingState getMatchingState;
    public String puser_Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_list);


        // 확인하기 버튼 아이디값 찾아주기
        check_button = findViewById(R.id.check_button);

        // login_page에서 userID값 받아오기
        String fuserID = login_page.userID;

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list.this, MainActivity.class);
                startActivity(intent);
            }
        });

        main_button = findViewById(R.id.main_button);
        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list.this, MainActivity.class);
                startActivity(intent);
            }
        });


        mypage_button = findViewById(R.id.mypage_button);
        mypage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(matching_list.this, mypage.class);
                startActivity(intent);
            }
        });


        // 매칭확인, 취소버튼 아이디값 찾아주기
        //check_button = findViewById(R.id.check_button);
        cancel_button = findViewById(R.id.cancel_button);

        // 확인하기 버튼 클릭시 수행
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> response_listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("응답", response.toString()); // 응답을 로그로 출력
                        Log.d("fuserID 아이디값", fuserID); // 응답을 로그로 출력

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            // 매칭 정보를 확인
                            if (jsonArray.length() == 0) {
                                // 매칭 실패
                                Log.d("Matching Info", "매칭 실패하였습니다.");
                                no_matching_possible_dialog = new Dialog(matching_list.this);
                                no_matching_possible_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                no_matching_possible_dialog.setContentView(R.layout.activity_no_matching_possible_dialog);
                                showDialog();

                                Log.d("fuserID 아이디값 매칭실패시", fuserID); // 응답을 로그로 출력

                            } else {
                                // 매칭 성공
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String my_userID = jsonObject.getString("my_userID");
                                String puser_ID = jsonObject.getString("puser_ID");
                                String matching_date = jsonObject.getString("matching_date");
                                String my_userName = jsonObject.getString("my_userName");
                                puser_Name = jsonObject.getString("puser_Name");
                                // 로그 출력
                                Log.d("매칭성공", "My UserID: " + my_userID);
                                Log.d("매칭성공", "Partner's UserID: " + puser_ID);
                                Log.d("매칭성공", "Matching Date: " + matching_date);
                                Log.d("매칭성공", "Matching Date: " + my_userName);
                                Log.d("매칭성공", "Matching Date: " + puser_Name);

                                // 매칭성공 다이얼로그 띄우기
                                matching_success_dialog = new Dialog(matching_list.this);
                                matching_success_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                matching_success_dialog.setContentView(R.layout.activity_matching_success_dialog);
                                matching_success_dialog();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("에러", "JSONException 발생");
                        }
                    }
                };

                // 서버로 Volley를 이용해서 요청을 함
                MatchingList_json matchingList_json = new MatchingList_json(fuserID, response_listener, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("에러", error.getMessage());
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(matching_list.this);
                queue.add(matchingList_json);
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////

        // 매칭취소 버튼 클릭시 수행
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> response_listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("매칭취소 응답", response.toString()); // 응답을 로그로 출력
                        Log.d("fuserID 매칭취소", fuserID); // 응답을 로그로 출력

                        Log.d("매칭취소", "매칭신청을 취소하였습니다.");
                        matching_list_cancel_dialog = new Dialog(matching_list.this);
                        matching_list_cancel_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        matching_list_cancel_dialog.setContentView(R.layout.activity_matching_list_cancel_dialog);
                        showDialog_cancel();
                    }
                };

                // 서버로 Volley를 이용해서 요청을 함
                MatchingCancel matchingCancel = new MatchingCancel(fuserID, response_listener, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("에러", error.getMessage());
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(matching_list.this);
                queue.add(matchingCancel);
            }
        });

        /////////////////////////////////////////////////////////////////////////////////
        //
        getMatchingState = new GetMatchingState(this);

        // GetMatchingState 클래스에서 상태를 가져온 후 처리
        getMatchingState.requestMatchingState(new GetMatchingState.MatchingStateCallback() {
            @Override
            public void onMatchingStateReceived(int matchingState) {
                if (matchingState == 2) {
                    check_button.setVisibility(View.GONE); // 매칭 상태가 2이면 확인하기 버튼을 숨김
                    cancel_button.setVisibility(View.GONE); // 매칭 상태가 2이면 매칭취소 버튼을 숨김

                    // 짝선배 짝후배 글씨 누르면 매칭완료된 화면으로 넘어가기
                    TextView friend_matching = findViewById(R.id.friend_matching);
                    friend_matching.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(matching_list.this, class_matched_firendFirst.class);
                            startActivity(intent);
                        }
                    });

                }else if(matchingState == 1){

                    // friend_matching TextView의 marginLeft 100만큼 주기
                    TextView friend_matching = findViewById(R.id.friend_matching);
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) friend_matching.getLayoutParams();
                    params.leftMargin = 100; // 원하는 marginLeft 값으로 설정
                    friend_matching.setLayoutParams(params);

                    check_button.setVisibility(View.VISIBLE); // 매칭 상태가 1이면 확인하기 버튼 보이게
                    cancel_button.setVisibility(View.VISIBLE); // 매칭 상태가 1이면 매칭취소 버튼 보이게

                }else if(matchingState == 0 ){
                    check_button.setVisibility(View.GONE); // 매칭 상태가 0이면 확인하기 버튼을 숨김
                    cancel_button.setVisibility(View.GONE); // 매칭 상태가 0이면 매칭취소 버튼을 숨김
                }
            }
        });

    }

    public void showDialog() {
        no_matching_possible_dialog.show();

        Button ok_button = no_matching_possible_dialog.findViewById(R.id.ok_button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_matching_possible_dialog.dismiss();
            }
        });
    }

    public void showDialog_cancel() {
        matching_list_cancel_dialog.show();

        Button ok_button = matching_list_cancel_dialog.findViewById(R.id.ok_button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_list_cancel_dialog.dismiss();
                Intent intent = new Intent(matching_list.this, class_main_friendMatching.class);
                startActivity(intent);
            }
        });
    }

    public void matching_success_dialog(){

        matching_success_dialog.show();

        TextView puserNameTextView = matching_success_dialog.findViewById(R.id.dialog_text);
        puserNameTextView.setText(puser_Name+"님과 매칭이 완료되었습니다");

        Button ok_button = matching_success_dialog.findViewById(R.id.ok_button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_success_dialog.dismiss();

                // 다른 화면으로 이동
                Intent intent = new Intent(matching_list.this, class_matched_firendFirst.class);
                startActivity(intent);
            }
        });

    }

}
