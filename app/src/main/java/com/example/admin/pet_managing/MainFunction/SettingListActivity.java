package com.example.admin.pet_managing.MainFunction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.pet_managing.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingListActivity extends AppCompatActivity {
    DatabaseReference onlineRef;
    Button join,out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_list);
        join=(Button)findViewById(R.id.join);
        out=(Button) findViewById(R.id.out);
        onlineRef=FirebaseDatabase.getInstance().getReference().child(".info/connected");
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
