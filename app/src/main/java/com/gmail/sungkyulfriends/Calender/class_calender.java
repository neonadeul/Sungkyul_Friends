package com.gmail.sungkyulfriends.Calender;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.sungkyulfriends.R;
import com.gmail.sungkyulfriends.MyPage.mypage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class class_calender extends AppCompatActivity {
    Dialog add_button;
    Dialog finish_button;
    TextView monthYearText;
    LocalDate selectedDate;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_class_main_calender);
        monthYearText = findViewById(R.id.monthYearText);
        recyclerView = findViewById(R.id.recyclerView);
        ImageView back_arrow= findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(class_calender.this, mypage.class);
                startActivity(intent);
            }
        });
        ImageButton preBtn = findViewById(R.id.pre_btn);
        ImageButton nextBtn = findViewById(R.id.next_btn);
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = selectedDate.minusMonths(1);
                setMonthView();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = selectedDate.plusMonths(1);
                setMonthView();
            }
        });
        selectedDate = LocalDate.now();
        setMonthView();
        add_button = new Dialog(class_calender.this);

        add_button = new Dialog(class_calender.this);
        add_button.requestWindowFeature(Window.FEATURE_NO_TITLE);
        add_button.setContentView(R.layout.activity_class_main_calender_add_dialog);

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog01();
            }
        });
    }

    public void showDialog01() {
        add_button.show();
        ImageView closeImageView = add_button.findViewById(R.id.close);
        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_button.dismiss();
            }
        });
        Button yesBtn = add_button.findViewById(R.id.finish_button);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_button.dismiss();
                showDialog02();
            }
        });
    }

    public void showDialog02() {
        finish_button = new Dialog(class_calender.this);
        finish_button.requestWindowFeature(Window.FEATURE_NO_TITLE);
        finish_button.setContentView(R.layout.activity_class_main_calender_view_dialog);

        // 다이얼로그 레이아웃 내부 뷰들을 찾기
        ImageView closeButton = finish_button.findViewById(R.id.close);

        // 리뷰 버튼 클릭 리스너 설정
        // 닫기 버튼 클릭 리스너 설정
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_button.dismiss();
            }
        });

        finish_button.show();



        setMonthView();

    }


    private String monthYearFromDate(LocalDate selectedDate){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy년 MM월");
                return selectedDate.format(formatter);
    }
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> dayList = daysInMonthArray(selectedDate);
        calendarAdapter adapter = new calendarAdapter(dayList,class_calender.this);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 7);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> dayList = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int lastDay = yearMonth.lengthOfMonth();
        LocalDate firstDay = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue();
        for (int i = 1; i < 42; i++) {
            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
                dayList.add("");
            } else {
                dayList.add(String.valueOf(i - dayOfWeek));
            }
        }
        return dayList;}


}
