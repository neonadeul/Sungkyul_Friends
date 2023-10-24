package com.gmail.sungkyulfriends.MyPage;

import android.app.Person;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.sungkyulfriends.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private ArrayList<ReviewData> arrayList;

    public ReviewAdapter(ArrayList<ReviewData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
        ReviewViewHolder holder = new ReviewViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {

        ReviewData item = arrayList.get(position);
        holder.setItem(item);
//
//        ReviewData reviewData = new ReviewData();
//        holder.score.setText(reviewData.getScore());
//        holder.content.setText(reviewData.getContent());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void addItem(ReviewData item){
        arrayList.add(item);
    }

    public void setItems(ArrayList<ReviewData> items) {
        this.arrayList = items;
    }

    public ReviewData getItem(int position) {
        return arrayList.get(position);
    }

    public void setItem(int position, ReviewData item) {
        arrayList.set(position, item);
    }


    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        protected TextView score;
        protected TextView content;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            this.score = (TextView) itemView.findViewById(R.id.score);
            this.content = (TextView) itemView.findViewById(R.id.content);

        }

        public void setItem(ReviewData item){
            score.setText(item.getScore());
            content.setText(item.getContent());
        }
    }
}
