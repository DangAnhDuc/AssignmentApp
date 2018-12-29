package com.example.admin.pet_managing.MainFunction;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.pet_managing.ChatMessage;
import com.example.admin.pet_managing.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;

public class ChatListActivity extends AppCompatActivity {

    RelativeLayout chatListLayout;
    ListView listView;
    ImageView submit_Button,emoji_Button;
    EmojiconEditText emojiconEditText;
    EmojIconActions emojIconActions;
    FirebaseListAdapter<ChatMessage> adapter;
    FirebaseListAdapter<ChatMessage> adapter1;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        final Date currentTime = Calendar.getInstance().getTime();



        chatListLayout=(RelativeLayout) findViewById(R.id.chat_list_layout);
        listView=(ListView) findViewById(R.id.list_of_message);
        submit_Button= findViewById(R.id.submit_button);
        emoji_Button=findViewById(R.id.emoji_button);
        emojiconEditText= findViewById(R.id.emoji_edit_text);
        emojIconActions=new EmojIconActions(getApplicationContext(),chatListLayout, emojiconEditText, emoji_Button);
        emojIconActions.ShowEmojIcon();

        firebaseDatabase= FirebaseDatabase.getInstance();
        messages=firebaseDatabase.getReference().child("messages");

        submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messages.push().setValue(new ChatMessage(emojiconEditText.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getEmail(),currentTime.getTime()));
                emojiconEditText.setText("");
                emojiconEditText.requestFocus();
            }
        });
        displayMessage();
    }

    private void displayMessage() {
        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.list_message_receive, messages)
        {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                TextView messageText,messageUser,messageTime;
                messageText=v.findViewById(R.id.message_text);
                messageUser=v.findViewById(R.id.message_user);
                messageTime=v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("yyyy-MM-dd HH:mm", model.getMessageTime()));
            }

        };

        listView.setAdapter(adapter);
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
}
