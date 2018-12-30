package com.example.admin.pet_managing;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.pet_managing.Common.Common;
import com.example.admin.pet_managing.Model.Pet;
import com.example.admin.pet_managing.PetManage.PetListActivity;
import com.example.admin.pet_managing.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class AddPetActivity extends AppCompatActivity {
    public static final int SELECT_IMAGE = 1;
    private final int PICK_IMAGE_REQUEST = 71;
    DatabaseReference pets;
    EditText txtName,txtAge,txtGender,txtType,txtWeight,txtHeight,txtBloodType,txtFavouriteFood,txtPetEmail,txtImage;
    Button btnUpload,btnCancel;
    ImageView imgAva;
    Uri filepath;

    String Name;
    String Age;
    String Gender;
    String Type;
    String Weight;
    String Height;
    String BloodType;
    String FavouriteFood;
    String Email;
    String Image;

    FirebaseStorage storage;
    StorageReference storageReference;
    String imageURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);pets=FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("pets");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("PetImages").child(FirebaseAuth.getInstance().getCurrentUser().getUid());




        imgAva=(ImageView) findViewById(R.id.petAva);
        txtName=(EditText) findViewById(R.id.txtName);
        txtAge=(EditText) findViewById(R.id.txtAge);
        txtGender=(EditText) findViewById(R.id.txtGender);
        txtType=(EditText) findViewById(R.id.txtType);
        txtWeight=(EditText) findViewById(R.id.txtWeight);
        txtHeight=(EditText) findViewById(R.id.txtHeight);
        txtBloodType=(EditText) findViewById(R.id.txtBloodType);
        txtFavouriteFood=(EditText) findViewById(R.id.txtFavouriteFood);
        txtPetEmail=(EditText) findViewById(R.id.txtPetEmail);
        btnUpload=(Button) findViewById(R.id.btnUpload);
        btnCancel=(Button) findViewById(R.id.btnCancel);
        imgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Name=txtName.getText().toString();
                 Age=txtAge.getText().toString();
                 Gender=txtGender.getText().toString();
                 Type=txtType.getText().toString();
                 Weight=txtWeight.getText().toString();
                 Height=txtHeight.getText().toString();
                 BloodType=txtBloodType.getText().toString();
                 FavouriteFood=txtFavouriteFood.getText().toString();
                 Email=txtPetEmail.getText().toString();

                final String petID=UUID.randomUUID().toString();
                final StorageReference ref = storageReference.child(petID);
                if(filepath != null)
                {
                    ref.putFile(filepath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return ref.getDownloadUrl();
                        }

                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                // When the image has successfully uploaded, we get its download URL
                                Uri downloadUri = task.getResult();
                                pets.child(petID).setValue(new Pet(Name,downloadUri.toString(),Type,Weight,Height,Gender,Age,BloodType,FavouriteFood,Email));
                                loadHealthIndex(Name);
                                // Set the download URL to the message box, so that the user can send it to the database
                            } else {
                            }
                        }
                    });

                }
                Toast.makeText(getApplicationContext(),"New pet have been added!!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),PetListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PetListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Cancel adding new pet!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && intent != null && intent.getData() != null )
        {
            filepath = intent.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imgAva.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void loadHealthIndex(String petName) {
        if(Common.petList.size()>0){
            Common.petList.clear();
        }

        pets.orderByChild("name").equalTo(petName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Pet pet=postSnapshot.getValue(Pet.class);
                    Common.petList.add(pet);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
