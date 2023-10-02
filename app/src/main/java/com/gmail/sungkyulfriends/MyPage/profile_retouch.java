package com.gmail.sungkyulfriends.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gmail.sungkyulfriends.LoginRegister.join_interest_choice;
import com.gmail.sungkyulfriends.R;

public class profile_retouch extends AppCompatActivity {

    private Button retouch_profile_button;
    private Button choice_interest_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_retouch);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_retouch.this, mypage.class);
                startActivity(intent);
            }
        });

        retouch_profile_button = findViewById(R.id.retouch_profile_button);
        retouch_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_retouch.this, mypage.class);
                startActivity(intent);
            }
        });

        choice_interest_button = findViewById(R.id.choice_interest_button);
        choice_interest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_retouch.this, retouch_interest_choice.class);
                startActivity(intent);
            }
        });

    }
}