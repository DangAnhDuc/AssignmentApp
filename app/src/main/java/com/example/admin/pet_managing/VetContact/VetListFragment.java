package com.example.admin.pet_managing.VetContact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.pet_managing.Interface.ItemClickListener;
import com.example.admin.pet_managing.Model.Vet;
import com.example.admin.pet_managing.R;
import com.example.admin.pet_managing.ViewHolder.VetViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class VetListFragment extends Fragment {

    View myFragment;
    RecyclerView listVets;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Vet,VetViewHolder> adapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference vets;

    public static VetListFragment newInstance() {
        VetListFragment vetListFragment=new VetListFragment();
        return vetListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseDatabase= FirebaseDatabase.getInstance();
        vets=firebaseDatabase.getReference().child("vets");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment=inflater.inflate(R.layout.fragment_vet_list,container,false);

        listVets=(RecyclerView) myFragment.findViewById(R.id.listVet);
        listVets.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(container.getContext());
        listVets.setLayoutManager(layoutManager);

        loadVets();
        return myFragment;
    }

    private void loadVets() {
        adapter=new FirebaseRecyclerAdapter<Vet, VetViewHolder>(
                Vet.class,R.layout.vet_card_item,VetViewHolder.class,vets
        ) {
            @Override
            protected void populateViewHolder(VetViewHolder viewHolder, Vet model, int position) {

                viewHolder.vetName.setText(model.getName());
                viewHolder.vetAddress.setText(model.getAddress());
                viewHolder.vetWebsite.setText(model.getWebsite());
                viewHolder.vetPhone.setText(model.getPhone());


                Picasso.with(getActivity()).load(model.getImage()).into(viewHolder.vetImage);
                final String number= model.getPhone().toString();
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                        dialIntent.setData(Uri.parse("tel:" + number));
                        startActivity(dialIntent);
                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        listVets.setAdapter(adapter);
    }
}
