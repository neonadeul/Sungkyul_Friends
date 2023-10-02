package com.gmail.sungkyulfriends.Calender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sungkyulfriends.R;

public class class_calender_dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_main_calender_view_dialog);

        ImageView close= findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_calender_dialog.this, class_calender.class);
                startActivity(intent);
            }
        });
    }
}
