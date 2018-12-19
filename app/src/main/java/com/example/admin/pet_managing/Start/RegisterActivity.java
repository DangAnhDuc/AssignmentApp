package com.example.admin.pet_managing.Start;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.pet_managing.MainFunction.HomePageActivity;
import com.example.admin.pet_managing.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmail,edtName,edtPassword;
    Button  btnRegister,btnRegisterFB,btnRegisterGG;


    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database=FirebaseDatabase.getInstance();
        firebaseAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent=new Intent(RegisterActivity.this, HomePageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(intent);
                    return;
                }
            }
        };
        mAuth=FirebaseAuth.getInstance();

        edtName=(EditText) findViewById(R.id.edt_reg_name);
        edtEmail=(EditText) findViewById(R.id.edt_reg_email);
        edtPassword=(EditText) findViewById(R.id.edt_reg_password);
        btnRegister=(Button) findViewById(R.id.btn_reg);
        btnRegisterFB=(Button) findViewById(R.id.btn_fb_reg);
        btnRegisterGG=(Button) findViewById(R.id.btn_gg_reg);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=edtName.getText().toString();
                final String email= edtEmail.getText().toString();
                final String password=edtPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplication(), "Sign up ERROR", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String userId= mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb= database.getReference().child("users").child(userId);
                            Map userInfor=new HashMap<>();
                            userInfor.put("email",email);
                            userInfor.put("name",name);
                            userInfor.put("profileImageUrl","default");

                            currentUserDb.updateChildren(userInfor);
                            Toast.makeText(RegisterActivity.this,"Sign In Successful",Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }
        });

        btnRegisterFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnRegisterGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
