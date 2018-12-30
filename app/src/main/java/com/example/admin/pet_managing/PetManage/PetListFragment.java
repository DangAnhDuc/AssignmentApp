package com.example.admin.pet_managing.PetManage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.pet_managing.AddPetActivity;
import com.example.admin.pet_managing.Common.Common;
import com.example.admin.pet_managing.Interface.ItemClickListener;
import com.example.admin.pet_managing.Model.Pet;
import com.example.admin.pet_managing.R;
import com.example.admin.pet_managing.ViewHolder.PetViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PetListFragment extends Fragment{
    public final String KEY="PETNUM";
    View myFragment;
    RecyclerView listPets;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Pet,PetViewHolder> adapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference pets;

    FloatingActionButton fab;

    public static PetListFragment newInstance(){
        PetListFragment petListFragment=new PetListFragment();
        return petListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseDatabase= FirebaseDatabase.getInstance();
        pets=firebaseDatabase.getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("pets");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment=inflater.inflate(R.layout.fragment_pet_list,container,false);
        fab=(FloatingActionButton) myFragment.findViewById(R.id.addPet);
        listPets=(RecyclerView) myFragment.findViewById(R.id.listPet);
        listPets.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(container.getContext());
        listPets.setLayoutManager(layoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AddPetActivity.class);
                startActivity(intent);
            }
        });
        loadPets();
        return myFragment;
    }


    private void loadPets() {
        adapter=new FirebaseRecyclerAdapter<Pet, PetViewHolder>(
                Pet.class,R.layout.pet_card_item,PetViewHolder.class,pets
        ) {
            @Override
            protected void populateViewHolder(PetViewHolder viewHolder, final Pet model, int position) {
                viewHolder.petName.setText(model.getName());
                Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.petImage);

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent intent=new Intent(getContext(),PetManageActivity.class);
                        Common.petID=adapter.getRef(position).getKey();
                        Common.petName=model.getName();
                        Common.petEmail=model.getEmail();
                        loadHealthIndex(Common.petName);
                        startActivity(intent);
                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        listPets.setAdapter(adapter);
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
