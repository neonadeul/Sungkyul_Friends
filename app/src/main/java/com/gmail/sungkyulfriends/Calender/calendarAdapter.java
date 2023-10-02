package com.gmail.sungkyulfriends.Calender;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.gmail.sungkyulfriends.R;

import java.util.ArrayList;

public class calendarAdapter extends RecyclerView.Adapter<calendarAdapter.CalendarViewHolder> {
    ArrayList<String> dayList;
    public calendarAdapter(ArrayList<String> dayList, class_calender classCalender){
        this.dayList=dayList;
    }
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.activity_calender_cell,parent,false);
        return new CalendarViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.daytext.setText(dayList.get(position));
        holder.daytext.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
    }
    @Override
    public int getItemCount(){
        return dayList.size();
    }
    class CalendarViewHolder extends RecyclerView.ViewHolder{
TextView daytext;

public CalendarViewHolder(@NonNull View itemview){
    super(itemview);
    daytext=itemview.findViewById(R.id.dayText);
}
    }
}
