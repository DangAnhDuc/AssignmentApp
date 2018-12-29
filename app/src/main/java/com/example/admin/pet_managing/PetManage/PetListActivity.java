package com.example.admin.pet_managing.PetManage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.pet_managing.R;

public class PetListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        PetListFragment fragment = PetListFragment.newInstance();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.pet_frame_layout, fragment);
        transaction.commit();

    }
}
