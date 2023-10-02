package com.gmail.sungkyulfriends.SameClass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;

public class class_matching_in_progress extends AppCompatActivity {

    private Button matching_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_matching_in_progress);

        matching_button = findViewById(R.id.matching_button);
        matching_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matching_in_progress.this, class_main_friend.class);
                startActivity(intent);
            }
        });
        ImageView back_arrow= findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matching_in_progress.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}