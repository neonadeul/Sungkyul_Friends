package com.gmail.sungkyulfriends.SameClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.MyPage.review_page;


public class class_matched_cancelclassFinish extends AppCompatActivity {
    private Button new_button;
    private Button review_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_matched_dialog_classfinish);

        review_button = findViewById(R.id.review_button);
        review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_cancelclassFinish.this, review_page.class);
                startActivity(intent);
            }
        });

       new_button = findViewById(R.id.new_matching_button);
        new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_cancelclassFinish.this, class_main_friend.class);
                startActivity(intent);

            }
        });
        ImageView close= findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_cancelclassFinish.this, class_main_friend.class);
                startActivity(intent);
            }
        });}}