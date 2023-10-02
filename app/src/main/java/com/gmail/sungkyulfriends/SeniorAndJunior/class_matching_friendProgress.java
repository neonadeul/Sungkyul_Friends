package com.gmail.sungkyulfriends.SeniorAndJunior;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;

public class class_matching_friendProgress extends AppCompatActivity
{private Button matching_button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_class_matching_friend_progress);

            matching_button = findViewById(R.id.matching_button);
            matching_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(class_matching_friendProgress.this, class_main_friendMatching.class);
                    startActivity(intent);
                }
            });
            ImageView back_arrow= findViewById(R.id.back_arrow);
            back_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(class_matching_friendProgress.this, MainActivity.class);
                    startActivity(intent);
                }
            });

        }
}
