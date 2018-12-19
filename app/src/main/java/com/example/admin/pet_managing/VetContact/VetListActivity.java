package com.example.admin.pet_managing.VetContact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.pet_managing.R;

public class VetListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_list);
        VetListFragment fragment = VetListFragment.newInstance();
//
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.vet_frame_layout, fragment);
        transaction.commit();
    }
}
