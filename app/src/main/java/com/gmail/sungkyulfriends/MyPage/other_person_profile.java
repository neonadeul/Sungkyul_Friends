package com.gmail.sungkyulfriends.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gmail.sungkyulfriends.MatchingList.matching_list_same_class;
import com.gmail.sungkyulfriends.R;

public class other_person_profile extends AppCompatActivity {

    private Button more_review_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_person_profile);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(other_person_profile.this, matching_list_same_class.class);
                startActivity(intent);
            }
        });

        more_review_button = findViewById(R.id.more_review_button);
        more_review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(other_person_profile.this, review_page.class);
                startActivity(intent);
            }
        });

    }
}