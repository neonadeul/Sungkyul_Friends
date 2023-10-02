package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sungkyulfriends.R;

public class class_matched_finish extends AppCompatActivity {

    Button confirm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_matched_finish);

        confirm_btn=findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_matched_finish.this, class_matched_firendFirst.class);
                startActivity(intent);
            }
        });

    }

}