package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sungkyulfriends.R;


public class class_matched_ok extends AppCompatActivity {
    private Button ok_button;
    private Button refuse_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_matched_dialog_cancel);

        ok_button = findViewById(R.id.accept);
       ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_ok.this, class_matched_firendFirst.class);
                startActivity(intent);
            }
        });

        refuse_button = findViewById(R.id.refuse);
        refuse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_ok.this, class_main_friendMatching.class);
                startActivity(intent);
            }
        });}}