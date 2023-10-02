package com.gmail.sungkyulfriends.SameClass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;

public class class_main_friend extends AppCompatActivity {

    private Button matching_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_friend);

        // 버튼을 찾아서 onClickListener를 설정
        matching_button = findViewById(R.id.matching_button);
        matching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다른 화면으로 이동
                Intent intent = new Intent(class_main_friend.this, class_matching_in_progress.class);
                startActivity(intent);
            }
        });

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_main_friend.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}