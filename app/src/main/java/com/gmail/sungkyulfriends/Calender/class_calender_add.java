package com.gmail.sungkyulfriends.Calender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sungkyulfriends.R;

public class class_calender_add extends AppCompatActivity {

    private Button finish_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_calender_add_dialog);

        ImageView close= findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_calender_add.this, class_calender.class);
                startActivity(intent);
            }
        });
        // 버튼을 찾아서 onClickListener를 설정
        finish_button = findViewById(R.id.finish_button);
        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다른 화면으로 이동
                Intent intent = new Intent(class_calender_add.this, class_calender_dialog.class);
                startActivity(intent);
            }
        });
    }
}