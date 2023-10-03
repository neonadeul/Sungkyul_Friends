package com.gmail.sungkyulfriends.SeniorAndJunior;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.gmail.sungkyulfriends.R;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ChatData> mDataset;
    private String myNickName;
    private Context context;
    private int spacing;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView TextView_nickname;
        public TextView TextView_msg;
        public View rootView;

        public MyViewHolder(View v) {
            super(v);
            TextView_nickname = v.findViewById(R.id.TextView_nickname);
            TextView_msg = v.findViewById(R.id.TextView_msg);
            rootView = v;
        }
    }

    // ItemDecoration 클래스 정의
    public class ItemSpacingDecoration extends RecyclerView.ItemDecoration {
        private int spacing;

        public ItemSpacingDecoration(int spacing) {
            this.spacing = spacing;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = spacing;
        }
    }

    public ChatAdapter(List<ChatData> myDataset, Context context, String myNickName) {
        mDataset = myDataset;
        this.myNickName = myNickName;
        this.context = context;
        this.spacing = spacing;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChatData chat = mDataset.get(position);

        holder.TextView_nickname.setText(chat.getNickname());
        holder.TextView_msg.setText(chat.getMsg());

        if (chat.getNickname().equals(this.myNickName)) {
            // 자신의 메시지일 경우, 오른쪽 정렬 및 말풍선 스타일 적용
            holder.TextView_msg.setBackgroundResource(R.drawable.chat_bubble_right);
            holder.rootView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.TextView_msg.getLayoutParams();
            params.setMargins(dpToPx(30), 30, 30, 0); // 여백을 dp로 설정 (원하는 크기로 수정)
            holder.TextView_msg.setPadding(dpToPx(15), 10, 10, 10);
            holder.TextView_msg.setLayoutParams(params);
            holder.TextView_nickname.setVisibility(View.GONE); // 닉네임을 숨김
        } else {
            // 상대방의 메시지일 경우, 왼쪽 정렬 및 말풍선 스타일 적용
            holder.TextView_msg.setBackgroundResource(R.drawable.chat_bubble_left);
            holder.rootView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.TextView_msg.getLayoutParams();
            params.setMargins(dpToPx(30), 30, 30, 0); // 여백을 dp로 설정 (원하는 크기로 수정)
            holder.TextView_msg.setPadding(dpToPx(15), 10, 10, 10);
            holder.TextView_msg.setLayoutParams(params);
            holder.TextView_nickname.setVisibility(View.VISIBLE); // 닉네임을 표시
        }

        // 메시지 내용의 길이에 따라 말풍선의 크기 동적으로 조절
        ViewGroup.LayoutParams layoutParams = holder.TextView_msg.getLayoutParams();
        int messageLength = chat.getMsg().length();

        // 원하는 크기 계산 로직을 추가
        int bubbleWidth = calculateBubbleWidth(messageLength);

        // 최소, 최대 크기 설정
        int minWidth = context.getResources().getDimensionPixelSize(R.dimen.min_bubble_width);
        int maxWidth = context.getResources().getDimensionPixelSize(R.dimen.max_bubble_width);

        // 크기를 최소와 최대값 사이로 제한
        bubbleWidth = Math.max(minWidth, Math.min(maxWidth, bubbleWidth));

        // 말풍선 크기 설정
        layoutParams.width = bubbleWidth;
        holder.TextView_msg.setLayoutParams(layoutParams);
    }
    private int dpToPx(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
    // 메시지 길이에 따라 말풍선 너비를 계산하는 메서드
    private int calculateBubbleWidth(int messageLength) {
        // 원하는 로직에 따라 메시지 길이를 기반으로 말풍선 크기를 계산
        int additionalWidth = 50; // 고정된 값 (원하는 크기 조절을 위해 수정)
        int calculatedWidth = messageLength * 10 + additionalWidth;
        return calculatedWidth;
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public ChatData getChat(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat) {
        mDataset.add(chat);
        notifyItemInserted(mDataset.size() - 1);
    }
    public class ItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = spacing; // 마지막 아이템이 아닌 경우 간격을 설정
            }
        }}}

