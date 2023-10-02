package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sungkyulfriends.R;


public class class_matched_cancel extends AppCompatActivity {
    private Button cancel_button;
        private Button refuse_button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_matched_dialog_cancel);

        cancel_button = findViewById(R.id.accept);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_cancel.this, class_matched_cancelFinish.class);
                startActivity(intent);
            }
        });

            refuse_button = findViewById(R.id.refuse);
            refuse_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(class_matched_cancel.this, class_matched_firendFirst.class);
                    startActivity(intent);


                }

            });  ImageView close= findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(class_matched_cancel.this, class_matched_firendFirst.class);
                    startActivity(intent);
                }
            });}}