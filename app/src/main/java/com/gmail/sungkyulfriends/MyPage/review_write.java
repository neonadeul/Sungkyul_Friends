package com.gmail.sungkyulfriends.MyPage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;


public class review_write extends AppCompatActivity {

    private Button finish_button;

    private int selectedCount = 0;
    private ToggleButton[] toggleButtons = new ToggleButton[6]; // 토글 버튼 배열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(review_write.this, MainActivity.class);
                startActivity(intent);
            }
        });
        finish_button = findViewById(R.id.finish_button);
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(review_write.this, MainActivity.class);
                startActivity(intent);
            }
        });

        EditText reviewEditText = findViewById(R.id.reviewEditText);
        TextView charCountTextView = findViewById(R.id.charCountTextView);


        reviewEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 이전 텍스트 변경 이벤트
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 텍스트가 변경될 때 호출되는 이벤트
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 텍스트 변경 후 호출되는 이벤트
                int currentLength = editable.length();
                charCountTextView.setText("글자 수: " + currentLength + "/500");
                if (currentLength > 500) {
                    // 500자를 초과하면 텍스트를 제한
                    String limitedText = editable.toString().substring(0, 500);
                    reviewEditText.setText(limitedText);
                    reviewEditText.setSelection(limitedText.length()); // 커서를 텍스트 끝으로 이동
                }
            }
        });

        // 버튼 1개만 선택되게 하기
        // 토글 버튼들 초기화 및 리스너 설정
        toggleButtons[0] = findViewById(R.id.button_Aplus);
        toggleButtons[1] = findViewById(R.id.button_Azero);
        toggleButtons[2] = findViewById(R.id.button_Bplus);
        toggleButtons[3] = findViewById(R.id.button_Bzero);
        toggleButtons[4] = findViewById(R.id.button_Cplus);
        toggleButtons[5] = findViewById(R.id.button_F);

        for (ToggleButton toggleButton : toggleButtons) {
            toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) { // 선택되었을 경우
                    if (selectedCount < 1) {
                        selectedCount++; // 선택 개수 증가
                    } else {
                        toggleButton.setChecked(false); // 선택 취소 (체크 해제)

                        showSelectionLimitDialog();
                    }
                } else { // 선택 해제되었을 경우
                    selectedCount--; // 선택 개수 감소
                }

            });

        }
    }

    // 다이얼로그
    private void showSelectionLimitDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("후기 학점은 1개만 선택 가능합니다")
                .setPositiveButton("확인", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}