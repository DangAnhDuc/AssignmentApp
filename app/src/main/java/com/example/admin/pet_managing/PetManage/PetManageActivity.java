package com.example.admin.pet_managing.PetManage;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.admin.pet_managing.R;
import com.example.admin.pet_managing.TrackingFragment;

public class PetManageActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_manage);


        bottomNavigationView= (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment sellectedFragment=null;
                switch (item.getItemId()) {
                    case R.id.action_healthIndex:
                        sellectedFragment= HealthIndexFragment.newInstance();
                        break;
                    case R.id.action_tracking:
                        sellectedFragment= TrackingFragment.newInstance();
                        break;
                }

                android.support.v4.app.FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,sellectedFragment);
                transaction.commit();
                return true;
            }

        });

        setDefaultFragment();
    }



    private void setDefaultFragment() {
        android.support.v4.app.FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HealthIndexFragment.newInstance());
        transaction.commit();
    }
}