package com.gmail.sungkyulfriends.SeniorAndJunior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gmail.sungkyulfriends.LoginRegister.login_page;
import com.gmail.sungkyulfriends.MainActivity;
import com.gmail.sungkyulfriends.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;


import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public  RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> chatList;
    private String nick= login_page.userID;; //내 로그인 ID

    private String Chat_PartnerID; // 매칭된 상대 ID
    private EditText EditText_chat;
    private Button Button_send;
    private DatabaseReference myRef;

    private String CHAT_NAME; // 1:1 채팅 키 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // name값 class_matched_firendFirst에서 받아오기
//        Intent intent = getIntent();
//        String name = intent.getStringExtra("name");
        // GetMatchingPartnerID에서 이름 받아오기
//        String name = GetMatchingPartnerID.name;
        GetMatchingPartnerID matchingPartner = new GetMatchingPartnerID(this);
        String name = matchingPartner.getName();


        // 상대방 이름 보여주기
        TextView partnerNameTextView = findViewById(R.id.partner_name);
        partnerNameTextView.setText(name);

        // 아이디값 찾아오기
        Button_send = findViewById(R.id.Button_send);
        EditText_chat = findViewById(R.id.EditText_chat);

        // partnerID값 class_matched_firendFirst에서 받아오기
//        String Chat_PartnerID = intent.getStringExtra("partnerID");
        // GetMatchingPartnerID에서 partnerID값 받아오기
//        String Chat_PartnerID = GetMatchingPartnerID.partnerID;
        String Chat_PartnerID = matchingPartner.getPartnerID();


        //채팅 키가 문자 순서대로 생성되어 중복없이 고유한 키 값을 만들도록 조건문 만듦.
        if (Chat_PartnerID != null) {
            if (nick.compareTo(Chat_PartnerID) < 0) {
                CHAT_NAME = nick + "_" + Chat_PartnerID;
            } else {
                CHAT_NAME = Chat_PartnerID + "_" + nick;
            }}


        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messages = EditText_chat.getText().toString(); //새로 채팅메시지를 보내기 위해 EditText_chat에 넣은 메시지를 보내기 위해

                if(messages != null) {
                    ChatData chat = new ChatData();
                    chat.setSender(nick);
                    chat.setMsg(messages);
                    myRef.child(CHAT_NAME).push().setValue(chat); //FRD에서 (chat - 채팅방이름 - 채팅내용&보낸사람닉네임)인 데이터구조 만듦.
                    EditText_chat.setText(""); //입력창(EditText_chat) 초기화
                }

            }
        });

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, class_matched_firendFirst.class);
                startActivity(intent);
            }
        });
        ImageView out_button = findViewById(R.id.out_button);
        out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this); //recycler_view 세팅
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatList, ChatActivity.this, nick);
        mRecyclerView.setAdapter(mAdapter); //adapter 세팅

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chat"); //데이터베이스 접속 후 데이터 가져옴


        myRef.child(CHAT_NAME).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("CHATCHAT", dataSnapshot.getValue().toString());
                ChatData chat = dataSnapshot.getValue(ChatData.class);
                ((ChatAdapter) mAdapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}