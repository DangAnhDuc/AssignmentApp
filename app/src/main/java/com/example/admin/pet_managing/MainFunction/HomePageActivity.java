package com.example.admin.pet_managing.MainFunction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.admin.pet_managing.LiveChat.ChatListActivity;
import com.example.admin.pet_managing.PetManage.PetListActivity;
import com.example.admin.pet_managing.R;
import com.example.admin.pet_managing.Start.LoginActivity;
import com.example.admin.pet_managing.VetContact.VetListActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView petCard,vetCard,chatCard,settingCard,signoutCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        petCard=(CardView) findViewById(R.id.pet_card);
        vetCard=(CardView) findViewById(R.id.vet_card);
        chatCard=(CardView) findViewById(R.id.chat_card);
        settingCard=(CardView) findViewById(R.id.setting_card);
        signoutCard=(CardView) findViewById(R.id.signout_card);

        petCard.setOnClickListener(this);
        vetCard.setOnClickListener(this);
        chatCard.setOnClickListener(this);
        settingCard.setOnClickListener(this);
        signoutCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.pet_card:
                intent=new Intent(HomePageActivity.this,PetListActivity.class);
                startActivity(intent);
                break;
            case R.id.vet_card:
                intent=new Intent(HomePageActivity.this,VetListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.chat_card:
                intent=new Intent(HomePageActivity.this,ChatListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.setting_card:
                intent=new Intent(HomePageActivity.this,SettingListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.signout_card:
                LogOut();
                break;
        }

    }

    private void LogOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return;
    }
}
