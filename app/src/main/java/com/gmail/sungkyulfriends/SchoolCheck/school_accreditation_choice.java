package com.gmail.sungkyulfriends.SchoolCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.gmail.sungkyulfriends.MyPage.mypage;
import com.gmail.sungkyulfriends.R;

public class school_accreditation_choice extends AppCompatActivity {

    private ImageButton certificate_of_enrollment_button;
    private ImageButton student_id_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_accreditation_choice);

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(school_accreditation_choice.this, mypage.class);
                startActivity(intent);
            }
        });

        certificate_of_enrollment_button = findViewById(R.id.certificate_of_enrollment_button);
        certificate_of_enrollment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(school_accreditation_choice.this, certificate_of_enrollment.class);
                startActivity(intent);
            }
        });

        student_id_button = findViewById(R.id.student_id_button);
        student_id_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(school_accreditation_choice.this, student_card.class);
                startActivity(intent);
            }
        });

    }
}