package com.gmail.sungkyulfriends.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gmail.sungkyulfriends.R;

public class join_finish extends AppCompatActivity {

    private Button go_login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_finish);

        go_login_button = findViewById(R.id.go_login_button);
        go_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(join_finish.this, login_page.class);
                startActivity(intent);
            }
        });

    }
}